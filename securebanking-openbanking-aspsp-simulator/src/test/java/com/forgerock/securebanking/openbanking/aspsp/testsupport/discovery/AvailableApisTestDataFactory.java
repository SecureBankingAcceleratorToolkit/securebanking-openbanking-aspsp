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
package com.forgerock.securebanking.openbanking.aspsp.testsupport.discovery;

import com.forgerock.securebanking.openbanking.aspsp.common.OBGroupName;
import com.forgerock.securebanking.openbanking.aspsp.common.OBApiReference;
import com.forgerock.securebanking.openbanking.aspsp.discovery.AvailableApi;
import com.forgerock.securebanking.openbanking.aspsp.discovery.AvailableApiConfigurationProperties;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test data factory to generate a list of Read/Write APIs that are supported by the application (though not necessarily by the customer).
 */
public class AvailableApisTestDataFactory {

    public static final String BASE_URL = "http://aspsp/open-banking/";
    public static final String VERSION_PREFIX = "v3.1.";
    public static final int PATCHES = 6;

    public static AvailableApiConfigurationProperties getAvailableApiConfig() {
        AvailableApiConfigurationProperties availableApis = new AvailableApiConfigurationProperties();
        availableApis.setAccountAndTransactionApi(generateAccountApis());
        availableApis.setPaymentInitiationApi(generatePaymentApis());
        availableApis.setEventNotificationApi(generateEventApis());
        availableApis.setFundsConfirmationApi(generateFundApis());
        return availableApis;
    }

    public static List<AvailableApi> generateAccountApis() {
        List<Pair<OBApiReference, String>> content = ImmutableList.of(
                Pair.of(OBApiReference.GET_ACCOUNT, "/aisp/accounts/{AccountId}"),
                Pair.of(OBApiReference.GET_ACCOUNTS, "/aisp/accounts")
        );
        return generateApi(OBGroupName.AISP, content);
    }

    public static List<AvailableApi> generatePaymentApis() {
        List<Pair<OBApiReference, String>> content = ImmutableList.of(
                Pair.of(OBApiReference.CREATE_DOMESTIC_PAYMENT_CONSENT, "/pisp/domestic-payment-consents"),
                Pair.of(OBApiReference.GET_DOMESTIC_PAYMENT_CONSENT, "/pisp/domestic-payment-consents/{ConsentId}")
        );
        return generateApi(OBGroupName.PISP, content);
    }

    public static List<AvailableApi> generateEventApis() {
        List<Pair<OBApiReference, String>> content = ImmutableList.of(
                Pair.of(OBApiReference.CREATE_CALLBACK_URL, "/callback-urls"),
                Pair.of(OBApiReference.GET_CALLBACK_URLS, "/callback-urls/{CallbackUrlId}")
        );
        return generateApi(OBGroupName.EVENT, content);
    }

    public static List<AvailableApi> generateFundApis() {
        List<Pair<OBApiReference, String>> content = ImmutableList.of(
                Pair.of(OBApiReference.CREATE_FUNDS_CONFIRMATION, "/cbpii/funds-confirmations"),
                Pair.of(OBApiReference.GET_FUNDS_CONFIRMATION, "/cbpii/funds-confirmations/{FundsConfirmationId}")
        );
        return generateApi(OBGroupName.CBPII, content);
    }

    private static List<AvailableApi> generateApi(OBGroupName groupName, List<Pair<OBApiReference, String>> content) {
        List<AvailableApi> apiVersions = new ArrayList<>();
        for (int patch = 1; patch <= PATCHES; patch++) {
            Map<OBApiReference, String> links = generateLinks(content, patch);
            apiVersions.add(new AvailableApi(groupName, VERSION_PREFIX + patch, links));
        }
        return apiVersions;
    }

    private static Map<OBApiReference, String> generateLinks(List<Pair<OBApiReference, String>> content, int patch) {
        Map<OBApiReference, String> links = new HashMap<>();
        for (Pair<OBApiReference, String> refAndPath : content) {
            links.put(refAndPath.getKey(), BASE_URL + VERSION_PREFIX + patch + refAndPath.getValue());
        }
        return links;
    }
}
