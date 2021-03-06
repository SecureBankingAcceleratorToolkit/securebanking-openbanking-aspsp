/**
 * Copyright © 2020 ForgeRock AS (obst@forgerock.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.forgerock.securebanking.openbanking.aspsp.discovery;

import com.forgerock.securebanking.openbanking.aspsp.common.OBGroupName;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import uk.org.openbanking.datamodel.discovery.GenericOBDiscoveryAPILinks;
import uk.org.openbanking.datamodel.discovery.OBDiscoveryAPI;

import java.util.HashMap;
import java.util.Map;

import static com.forgerock.securebanking.openbanking.aspsp.common.OBApiReference.GET_ACCOUNT;
import static com.forgerock.securebanking.openbanking.aspsp.common.OBApiReference.GET_ACCOUNTS;
import static com.forgerock.securebanking.openbanking.aspsp.testsupport.discovery.AvailableApisTestDataFactory.*;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link DiscoveryApiService}.
 */
public class DiscoveryApiServiceTest {

    private static final String TEST_VERSION = "v3.1.1";

    private AvailableApiEndpointsResolver availableApisResolver = mock(AvailableApiEndpointsResolver.class);

    private ControllerEndpointBlacklistHandler blacklistHandler = mock(ControllerEndpointBlacklistHandler.class);

    @Test
    public void shouldGetDiscoveryApisByVersionAndGroupName() {
        // Given
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApisResolver, blacklistHandler);
        when(availableApisResolver.getAvailableApiEndpoints()).thenReturn(getAvailableApiEndpoints());

        // When
        discoveryApiService.init();

        // Then
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApis();
        assertThat(discoveryApis).isNotNull();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.AISP))).isTrue();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.PISP))).isTrue();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.EVENT))).isTrue();
        assertThat(containsAllVersions(discoveryApis.get(OBGroupName.CBPII))).isTrue();
    }

    @Test
    public void shouldGetDiscoveryApisWithoutSpecificVersion() {
        // Given
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setVersions(ImmutableMap.of(TEST_VERSION, false));
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApisResolver, blacklistHandler);
        when(availableApisResolver.getAvailableApiEndpoints()).thenReturn(getAvailableApiEndpoints());

        // When
        discoveryApiService.init();

        // Then
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApis();
        assertThat(discoveryApis).isNotNull();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.AISP), 1)).isTrue();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.PISP), 1)).isTrue();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.EVENT), 1)).isTrue();
        assertThat(containsAllVersionsExcept(discoveryApis.get(OBGroupName.CBPII), 1)).isTrue();
    }

    @Test
    public void shouldGetDiscoveryApisWithoutSpecificEndpoint() {
        // Given
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setApis(ImmutableMap.of(GET_ACCOUNT, false));
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApisResolver, blacklistHandler);
        when(availableApisResolver.getAvailableApiEndpoints()).thenReturn(getAvailableApiEndpoints());

        // When
        discoveryApiService.init();

        // Then
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApis();
        Map<String, OBDiscoveryAPI> accountApis = discoveryApis.get(OBGroupName.AISP);
        assertThat(containsAllVersions(accountApis)).isTrue();
        Map<String, String> links = ((GenericOBDiscoveryAPILinks) accountApis.get(TEST_VERSION).getLinks()).getLinks();
        // assert GET_ACCOUNT is excluded but GET_ACCOUNTS is present
        assertThat(links.containsKey(GET_ACCOUNT.getReference())).isFalse();
        assertThat(links.containsValue(BASE_URL + TEST_VERSION + "/aisp/accounts/{AccountId}")).isFalse();
        assertThat(links.containsKey(GET_ACCOUNTS.getReference())).isTrue();
        assertThat(links.containsValue(BASE_URL + TEST_VERSION + "/aisp/accounts")).isTrue();
    }

    @Test
    public void shouldGetDiscoveryApisWithoutSpecificEndpointVersion() {
        // Given
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setVersionApiOverrides(ImmutableMap.of("v3_1_2", ImmutableMap.of(GET_ACCOUNTS, false)));
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApisResolver, blacklistHandler);
        when(availableApisResolver.getAvailableApiEndpoints()).thenReturn(getAvailableApiEndpoints());

        // When
        discoveryApiService.init();

        // Then
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApis();
        Map<String, OBDiscoveryAPI> accountApis = discoveryApis.get(OBGroupName.AISP);
        String version = "v3.1.2";
        Map<String, String> links = ((GenericOBDiscoveryAPILinks) accountApis.get(version).getLinks()).getLinks();
        assertThat(links.containsKey(GET_ACCOUNT.getReference())).isTrue();
        assertThat(links.containsValue(BASE_URL + version + "/aisp/accounts/{AccountId}")).isTrue();
        // assert GET_ACCOUNTS is excluded
        assertThat(links.containsKey(GET_ACCOUNTS.getReference())).isFalse();
        assertThat(links.containsValue(BASE_URL + version + "/aisp/accounts")).isFalse();

        // check another version and assert both links are included
        links = ((GenericOBDiscoveryAPILinks) accountApis.get(TEST_VERSION).getLinks()).getLinks();
        assertThat(links.containsKey(GET_ACCOUNT.getReference())).isTrue();
        assertThat(links.containsValue(BASE_URL + TEST_VERSION + "/aisp/accounts/{AccountId}")).isTrue();
        assertThat(links.containsKey(GET_ACCOUNTS.getReference())).isTrue();
        assertThat(links.containsValue(BASE_URL + TEST_VERSION + "/aisp/accounts")).isTrue();
    }

    @Test
    public void shouldGetEmptyDiscoveryApisGivenNoAvailableEndpoints() {
        // Given
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApisResolver, blacklistHandler);
        when(availableApisResolver.getAvailableApiEndpoints()).thenReturn(emptyList());

        // When
        discoveryApiService.init();

        // Then
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApis();
        assertThat(discoveryApis).isEmpty();
    }

    @Test
    public void shouldGetEmptyDiscoveryApisGivenAllVersionsDisabled() {
        // Given
        DiscoveryApiConfigurationProperties discoveryProperties = new DiscoveryApiConfigurationProperties();
        discoveryProperties.setVersions(allVersionsDisabled());
        DiscoveryApiService discoveryApiService = new DiscoveryApiService(discoveryProperties, availableApisResolver, blacklistHandler);
        when(availableApisResolver.getAvailableApiEndpoints()).thenReturn(getAvailableApiEndpoints());

        // When
        discoveryApiService.init();

        // Then
        Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApis = discoveryApiService.getDiscoveryApis();
        assertThat(discoveryApis).isEmpty();
    }

    private boolean containsAllVersions(Map<String, OBDiscoveryAPI> apiVersions) {
        boolean isValid = true;
        for (int patch = 1; patch <= PATCHES; patch++) {
            if (!apiVersions.containsKey(VERSION_PREFIX + patch)) {
                isValid = false;
            }
        }
        return isValid;
    }

    private boolean containsAllVersionsExcept(Map<String, OBDiscoveryAPI> apiVersions, int excludedPatch) {
        boolean isValid = true;
        for (int patch = 1; patch <= PATCHES; patch++) {
            if (patch != excludedPatch && !apiVersions.containsKey(VERSION_PREFIX + patch)) {
                isValid = false;
            }
        }
        return isValid;
    }

    private Map<String, Boolean> allVersionsDisabled() {
        Map<String, Boolean> versions = new HashMap<>();
        for (int patch = 1; patch <= PATCHES; patch++) {
            versions.put(VERSION_PREFIX + patch, false);
        }
        return versions;
    }
}