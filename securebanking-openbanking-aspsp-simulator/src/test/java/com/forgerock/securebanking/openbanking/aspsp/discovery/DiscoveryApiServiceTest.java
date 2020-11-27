/**
 * Copyright 2020 ForgeRock AS.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.forgerock.securebanking.openbanking.aspsp.discovery;

import com.forgerock.securebanking.openbanking.aspsp.common.OBGroupName;
import com.forgerock.securebanking.openbanking.aspsp.common.OBApiReference;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import uk.org.openbanking.datamodel.discovery.GenericOBDiscoveryAPILinks;
import uk.org.openbanking.datamodel.discovery.OBDiscoveryAPI;

import java.util.HashMap;
import java.util.Map;

import static com.forgerock.securebanking.openbanking.aspsp.testsupport.discovery.AvailableApisTestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link DiscoveryApiService}.
 */
public class DiscoveryApiServiceTest {

    private static final String TEST_VERSION = "v3.1.1";

    @Test
    public void shouldGetDiscoveryApisByVersionAndGroupName() {
        // Given
        AvailableApiConfigurationProperties availableApis = getAvailableApiConfig();
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(new DiscoveryApiConfigurationProperties(), availableApis);

        // When
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApisByVersionAndGroupName();

        // Then
        assertThat(discoveryApis).isNotNull();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.AISP))).isTrue();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.PISP))).isTrue();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.EVENT))).isTrue();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.CBPII))).isTrue();
    }

    @Test
    public void shouldGetDiscoveryApisWithoutSpecificVersion() {
        // Given
        AvailableApiConfigurationProperties availableApis = getAvailableApiConfig();
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setVersions(ImmutableMap.of(TEST_VERSION, false));
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApis);

        // When
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApisByVersionAndGroupName();

        // Then
        assertThat(discoveryApis).isNotNull();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.AISP), 1)).isTrue();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.PISP), 1)).isTrue();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.EVENT), 1)).isTrue();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.CBPII), 1)).isTrue();
    }

    @Test
    public void shouldGetDiscoveryApisWithoutSpecificEndpoint() {
        // Given
        AvailableApiConfigurationProperties availableApis = getAvailableApiConfig();
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setApis(ImmutableMap.of(OBApiReference.GET_ACCOUNT, false));
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApis);

        // When
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApisByVersionAndGroupName();

        // Then
        Map<String, OBDiscoveryAPI> accountApis = discoveryApis.get(OBGroupName.AISP);
        assertThat(containsAllVersions(accountApis)).isTrue();
        Map<String, String> links = ((GenericOBDiscoveryAPILinks) accountApis.get(TEST_VERSION).getLinks()).getLinks();
        // assert GET_ACCOUNT is excluded but GET_ACCOUNTS is present
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNT.getReference())).isFalse();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/aisp/accounts/{AccountId}")).isFalse();
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNTS.getReference())).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/aisp/accounts")).isTrue();
    }

    @Test
    public void shouldGetDiscoveryApisWithoutSpecificEndpointVersion() {
        // Given
        AvailableApiConfigurationProperties availableApis = getAvailableApiConfig();
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setVersionApiOverrides(ImmutableMap.of("v3_1_2", ImmutableMap.of(OBApiReference.GET_ACCOUNTS, false)));
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApis);

        // When
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApisByVersionAndGroupName();

        // Then
        Map<String, OBDiscoveryAPI> accountApis = discoveryApis.get(OBGroupName.AISP);
        String version = "v3.1.2";
        Map<String, String> links = ((GenericOBDiscoveryAPILinks) accountApis.get(version).getLinks()).getLinks();
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNT.getReference())).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + version + "/aisp/accounts/{AccountId}")).isTrue();
        // assert GET_ACCOUNTS is excluded
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNTS.getReference())).isFalse();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + version + "/aisp/accounts")).isFalse();

        // check another version and assert both links are included
        links = ((GenericOBDiscoveryAPILinks) accountApis.get(TEST_VERSION).getLinks()).getLinks();
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNT.getReference())).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/aisp/accounts/{AccountId}")).isTrue();
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNTS.getReference())).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/aisp/accounts")).isTrue();
    }

    @Test
    public void shouldGetEmptyDiscoveryApisGivenNoAvailableEndpoints() {
        // Given
        AvailableApiConfigurationProperties availableApis = new AvailableApiConfigurationProperties();
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(new DiscoveryApiConfigurationProperties(), availableApis);

        // When
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApisByVersionAndGroupName();

        // Then
        assertThat(discoveryApis).isEmpty();
    }

    @Test
    public void shouldGetEmptyDiscoveryApisGivenAllVersionsDisabled() {
        // Given
        AvailableApiConfigurationProperties availableApis = getAvailableApiConfig();
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setVersions(allVersionsDisabled());
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApis);

        // When
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApisByVersionAndGroupName();

        // Then
        assertThat(discoveryApis).isEmpty();
    }

    private boolean containsAllVersions(Map<String, OBDiscoveryAPI> apiVersions) {
        boolean isValid = true;
        for (int patch = 1; patch <= PATCH_VERSIONS; patch++) {
            if (!apiVersions.containsKey(BASE_VERSION + patch)) {
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean containsAllVersionsExcept(Map<String, OBDiscoveryAPI> apiVersions, int excludedPatch) {
        boolean isValid = true;
        for (int patch = 1; patch <= PATCH_VERSIONS; patch++) {
            if (patch != excludedPatch && !apiVersions.containsKey(BASE_VERSION + patch)) {
                isValid = false;
            }
        }
        return isValid;
    }

    private Map<String, Boolean> allVersionsDisabled() {
        Map<String, Boolean> versions = new HashMap<>();
        for (int patch = 1; patch <= PATCH_VERSIONS; patch++) {
            versions.put(BASE_VERSION + patch, false);
        }
        return versions;
    }
}