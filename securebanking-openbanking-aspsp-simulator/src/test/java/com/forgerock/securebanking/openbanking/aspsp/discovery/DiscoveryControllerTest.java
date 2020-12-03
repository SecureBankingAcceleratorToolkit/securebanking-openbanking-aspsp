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

import com.forgerock.securebanking.openbanking.aspsp.common.OBApiReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import uk.org.openbanking.datamodel.discovery.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * A SpringBoot test for the {@link DiscoveryController}.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class DiscoveryControllerTest {

    private static final String BASE_URL = "http://localhost:";
    private static final String DISCOVERY_PATH = "/open-banking/discovery";
    private static final String FINANCIAL_ID = "0015800001041REAAY";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldGetDiscoveryUrlsFilteredByVersion() {
        // Given
        String[] enabledVersions = {"v3.1.5", "v3.1.6"};
        String[] disabledVersions = {"v3.0", "v3.1", "v3.1.3"};

        // When
        ResponseEntity<OBDiscoveryResponse> response = restTemplate.getForEntity(discoveryUrl(), OBDiscoveryResponse.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        OBDiscovery data = response.getBody().getData();
        assertThat(data.getFinancialId()).isEqualTo(FINANCIAL_ID);
        List<OBDiscoveryAPI<OBDiscoveryAPILinks>> accountsApis = data.getAccountAndTransactionAPIs();
        assertThat(containsVersions(accountsApis, enabledVersions)).isTrue();
        // assert others are filtered
        assertThat(containsVersions(accountsApis, disabledVersions)).isFalse();
    }

    @Test
    public void shouldGetDiscoveryUrlsFilteredByApiEndpoint() {
        // Given
        OBApiReference[] disabledEndpoints = {OBApiReference.GET_ACCOUNT_TRANSACTIONS, OBApiReference.GET_TRANSACTIONS};
        OBApiReference[] enabledEndpoints = {OBApiReference.GET_ACCOUNT, OBApiReference.GET_ACCOUNT_BALANCES};

        // When
        ResponseEntity<OBDiscoveryResponse> response = restTemplate.getForEntity(discoveryUrl(), OBDiscoveryResponse.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        OBDiscovery data = response.getBody().getData();
        List<OBDiscoveryAPI<OBDiscoveryAPILinks>> accountsApis = data.getAccountAndTransactionAPIs();
        assertThat(isEndpointDisabled(accountsApis, disabledEndpoints)).isTrue();
        // assert others are not filtered
        assertThat(isEndpointDisabled(accountsApis, enabledEndpoints)).isFalse();
    }

    @Test
    public void shouldGetDiscoveryUrlsFilteredByVersionAndApiEndpoint() {
        // Given
        String disabledVersion = "v3.1.6";
        OBApiReference disabledEndpoint = OBApiReference.GET_ACCOUNT_STATEMENT;

        // When
        ResponseEntity<OBDiscoveryResponse> response = restTemplate.getForEntity(discoveryUrl(), OBDiscoveryResponse.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        OBDiscovery data = response.getBody().getData();
        List<OBDiscoveryAPI<OBDiscoveryAPILinks>> accountsApis = data.getAccountAndTransactionAPIs();
        assertThat(isEndpointDisabledByVersion(accountsApis, disabledVersion, disabledEndpoint)).isTrue();
        // assert others are not filtered
        assertThat(isEndpointDisabledByVersion(accountsApis, "v3.1.5", disabledEndpoint)).isFalse();
        assertThat(isEndpointDisabledByVersion(accountsApis, disabledVersion, OBApiReference.GET_BALANCES)).isFalse();
    }

    private boolean containsVersions(List<OBDiscoveryAPI<OBDiscoveryAPILinks>> api, String... versions) {
        boolean contains = true;
        for (String version : versions) {
            contains = api.stream().anyMatch(a -> a.getVersion().equals(version));
        }
        return contains;
    }

    private boolean isEndpointDisabled(List<OBDiscoveryAPI<OBDiscoveryAPILinks>> api, OBApiReference... endpoints) {
        boolean isDisabled = false;
        for (OBApiReference endpoint : endpoints) {
            isDisabled = api.stream()
                    .map(a -> ((GenericOBDiscoveryAPILinks) a.getLinks()))
                    .flatMap(l -> l.getLinks().entrySet().stream())
                    .noneMatch(l -> l.getKey().equals(endpoint.getReference()));
        }
        return isDisabled;
    }

    private boolean isEndpointDisabledByVersion(List<OBDiscoveryAPI<OBDiscoveryAPILinks>> api, String version, OBApiReference endpoint) {
        return api.stream()
                .filter(a -> a.getVersion().equals(version))
                .map(a -> ((GenericOBDiscoveryAPILinks) a.getLinks()))
                .flatMap(l -> l.getLinks().entrySet().stream())
                .noneMatch(l -> l.getKey().equals(endpoint.getReference()));
    }

    private String discoveryUrl() {
        return BASE_URL + port + DISCOVERY_PATH;
    }
}