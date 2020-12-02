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
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.forgerock.securebanking.openbanking.aspsp.testsupport.discovery.AvailableApisTestDataFactory.*;
import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link AvailableApiConfigurationProperties}.
 */
public class AvailableApiConfigurationPropertiesTest {

    private static final String TEST_VERSION = "v3.1.1";

    @Test
    public void shouldGetAllAvailableApis() {
        // Given
        AvailableApiConfigurationProperties apiConfig = new AvailableApiConfigurationProperties();
        apiConfig.setAccountAndTransactionApi(generateAccountApis());
        apiConfig.setPaymentInitiationApi(generatePaymentApis());
        apiConfig.setEventNotificationApi(generateEventApis());
        apiConfig.setFundsConfirmationApi(generateFundApis());

        // When
        List<AvailableApi> availableApis = apiConfig.getAvailableApis();

        // Then
        assertThat(availableApis).isNotNull();
        List<AvailableApi> accountApis = filterByGroupName(OBGroupName.AISP, availableApis);
        List<AvailableApi> paymentApis = filterByGroupName(OBGroupName.PISP, availableApis);
        List<AvailableApi> eventApis = filterByGroupName(OBGroupName.EVENT, availableApis);
        List<AvailableApi> fundApis = filterByGroupName(OBGroupName.CBPII, availableApis);

        assertThat(containsAllVersions(accountApis)).isTrue();
        assertThat(containsAllVersions(paymentApis)).isTrue();
        assertThat(containsAllVersions(eventApis)).isTrue();
        assertThat(containsAllVersions(fundApis)).isTrue();
    }

    @Test
    public void shouldGetAvailableAccountApis() {
        // Given
        AvailableApiConfigurationProperties apiConfig = new AvailableApiConfigurationProperties();
        apiConfig.setAccountAndTransactionApi(generateAccountApis());

        // When
        List<AvailableApi> availableApis = apiConfig.getAvailableApis();

        // Then
        assertThat(availableApis).isNotNull();
        List<AvailableApi> accountApis = filterByGroupName(OBGroupName.AISP, availableApis);
        Map<OBApiReference, String> links = getLinksForVersion(TEST_VERSION, accountApis);
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNT)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/aisp/accounts/{AccountId}")).isTrue();
        assertThat(links.containsKey(OBApiReference.GET_ACCOUNTS)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/aisp/accounts")).isTrue();
    }

    @Test
    public void shouldGetAvailablePaymentApis() {
        // Given
        AvailableApiConfigurationProperties apiConfig = new AvailableApiConfigurationProperties();
        apiConfig.setPaymentInitiationApi(generatePaymentApis());

        // When
        List<AvailableApi> availableApis = apiConfig.getAvailableApis();

        // Then
        assertThat(availableApis).isNotNull();
        List<AvailableApi> paymentApis = filterByGroupName(OBGroupName.PISP, availableApis);
        Map<OBApiReference, String> links = getLinksForVersion(TEST_VERSION, paymentApis);
        assertThat(links.containsKey(OBApiReference.CREATE_DOMESTIC_PAYMENT_CONSENT)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/pisp/domestic-payment-consents")).isTrue();
        assertThat(links.containsKey(OBApiReference.GET_DOMESTIC_PAYMENT_CONSENT)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/pisp/domestic-payment-consents/{ConsentId}")).isTrue();
    }

    @Test
    public void shouldGetAvailableEventApis() {
        // Given
        AvailableApiConfigurationProperties apiConfig = new AvailableApiConfigurationProperties();
        apiConfig.setEventNotificationApi(generateEventApis());

        // When
        List<AvailableApi> availableApis = apiConfig.getAvailableApis();

        // Then
        assertThat(availableApis).isNotNull();
        List<AvailableApi> eventApis = filterByGroupName(OBGroupName.EVENT, availableApis);
        Map<OBApiReference, String> links = getLinksForVersion(TEST_VERSION, eventApis);
        assertThat(links.containsKey(OBApiReference.CREATE_CALLBACK_URL)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/callback-urls")).isTrue();
        assertThat(links.containsKey(OBApiReference.GET_CALLBACK_URLS)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/callback-urls/{CallbackUrlId}")).isTrue();
    }

    @Test
    public void shouldGetAvailableFundApis() {
        // Given
        AvailableApiConfigurationProperties apiConfig = new AvailableApiConfigurationProperties();
        apiConfig.setFundsConfirmationApi(generateFundApis());

        // When
        List<AvailableApi> availableApis = apiConfig.getAvailableApis();

        // Then
        assertThat(availableApis).isNotNull();
        List<AvailableApi> fundApis = filterByGroupName(OBGroupName.CBPII, availableApis);
        Map<OBApiReference, String> links = getLinksForVersion(TEST_VERSION, fundApis);
        assertThat(links.containsKey(OBApiReference.CREATE_FUNDS_CONFIRMATION)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/cbpii/funds-confirmations")).isTrue();
        assertThat(links.containsKey(OBApiReference.GET_FUNDS_CONFIRMATION)).isTrue();
        assertThat(links.containsValue("${aspsp.baseUrl}/open-banking/" + TEST_VERSION + "/cbpii/funds-confirmations/{FundsConfirmationId}")).isTrue();
    }

    private List<AvailableApi> filterByGroupName(OBGroupName groupName, List<AvailableApi> availableApis) {
        if (availableApis == null) {
            return null;
        }
        return availableApis.stream()
                .filter(a -> a.getGroupName() == groupName)
                .collect(Collectors.toList());
    }

    private boolean containsAllVersions(List<AvailableApi> availableApis) {
        boolean containsAll = true;
        for (int patch = 1; patch <= PATCH_VERSIONS; patch++) {
            if (!containsVersion(availableApis, BASE_VERSION + patch)) {
                containsAll = false;
            }
        }
        return containsAll;
    }

    private boolean containsVersion(List<AvailableApi> availableApis, String version) {
        return availableApis.stream().anyMatch(a -> a.getVersion().equals(version));
    }

    private Map<OBApiReference, String> getLinksForVersion(String version, List<AvailableApi> apis) {
        return apis.stream()
                .filter(a -> a.getVersion().equals(version))
                .map(AvailableApi::getLinks)
                .flatMap(l -> l.entrySet().stream())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}