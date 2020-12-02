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
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads a list of APIs from the Spring configuration that the application supports.
 *
 * <p>
 * This is not to be confused with the list of APIs that the customer supports (as provided by the Discovery endpoint).
 * This can be specified (overridden) within the customer's config.
 */
@Component
@Data
@ConfigurationProperties(prefix = "aspsp.available-apis")
public class AvailableApiConfigurationProperties {

    private List<AvailableApi> accountAndTransactionApi;
    private List<AvailableApi> paymentInitiationApi;
    private List<AvailableApi> eventNotificationApi;
    private List<AvailableApi> fundsConfirmationApi;

    private List<AvailableApi> availableApis = null;

    /**
     * Provides a list of @link AvailableApi} instances, which describe the endpoints the application is capable of supporting.
     * These endpoints are loaded from the application's Spring configuration.
     *
     * @return the {@link List} of {@link AvailableApi} instances.
     */
    public List<AvailableApi> getAvailableApis() {
        if (availableApis != null) {
            return availableApis;
        }

        availableApis = new ArrayList<>();

        addApi(OBGroupName.AISP, accountAndTransactionApi);
        addApi(OBGroupName.PISP, paymentInitiationApi);
        addApi(OBGroupName.EVENT, eventNotificationApi);
        addApi(OBGroupName.CBPII, fundsConfirmationApi);

        return availableApis;
    }

    private void addApi(OBGroupName groupName, List<AvailableApi> api) {
        if (api == null) {
            return;
        }
        // add the group names to the OBApi objects to provide a simple flat structure
        api.forEach(a -> a.setGroupName(groupName));
        availableApis.addAll(api);
    }
}