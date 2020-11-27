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
import org.springframework.stereotype.Component;
import uk.org.openbanking.datamodel.discovery.GenericOBDiscoveryAPILinks;
import uk.org.openbanking.datamodel.discovery.OBDiscoveryAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Examines customer specific config (if applicable) and determines which of the available APIs should be enabled/disabled.
 * The resulting list of supported APIs is provided by the Discovery API.
 */
@Component
public class DiscoveryApiService {

    private final DiscoveryApiConfigurationProperties discoveryProperties;

    private final AvailableApiConfigurationProperties availableApiProperties;

    private Map<OBGroupName, Map<String, OBDiscoveryAPI>> discoveryApisByVersionAndGroupName;

    public DiscoveryApiService(DiscoveryApiConfigurationProperties discoveryProperties, AvailableApiConfigurationProperties availableApiProperties) {
        this.discoveryProperties = discoveryProperties;
        this.availableApiProperties = availableApiProperties;
    }

    /**
     * Provides a {@link Map} of Open Banking APIs that both the application and customer supports. The APIs are grouped by "group" name
     * (e.g. AISP, PISP) and listed by version.
     *
     * @return a {@link Map} of supported Open Banking APIs.
     */
    public Map<OBGroupName, Map<String, OBDiscoveryAPI>> getDiscoveryApisByVersionAndGroupName() {
        if (discoveryApisByVersionAndGroupName != null) {
            return discoveryApisByVersionAndGroupName;
        }
        discoveryApisByVersionAndGroupName = new HashMap<>();

        List<AvailableApi> availableApis = availableApiProperties.getAvailableApis();

        // iterate over each API and its map of links
        for (AvailableApi availableApi : availableApis) {
            for (Map.Entry<OBApiReference, String> link : availableApi.getLinks().entrySet()) {

                if (isVersionEnabled(availableApi.getVersion())
                        && isApiEnabled(link.getKey())
                        && isVersionOverrideEnabled(availableApi.getVersion(), link.getKey())) {

                    // Init map
                    if (!discoveryApisByVersionAndGroupName.containsKey(availableApi.getGroupName())) {
                        discoveryApisByVersionAndGroupName.put(availableApi.getGroupName(), new HashMap<>());
                    }
                    if (!discoveryApisByVersionAndGroupName.get(availableApi.getGroupName()).containsKey(availableApi.getVersion())) {
                        discoveryApisByVersionAndGroupName.get(availableApi.getGroupName())
                                .put(availableApi.getVersion(), new OBDiscoveryAPI<GenericOBDiscoveryAPILinks>()
                                        .version(availableApi.getVersion())
                                        .links(new GenericOBDiscoveryAPILinks())
                                );
                    }
                    GenericOBDiscoveryAPILinks links = (GenericOBDiscoveryAPILinks) discoveryApisByVersionAndGroupName.get(availableApi.getGroupName()).get(availableApi.getVersion()).getLinks();
                    links.addLink(link.getKey().getReference(), link.getValue());
                }
            }
        }

        return discoveryApisByVersionAndGroupName;
    }

    private boolean isVersionEnabled(String version) {
        return !discoveryProperties.getVersions().containsKey(version) || discoveryProperties.getVersions().get(version);
    }

    private boolean isApiEnabled(OBApiReference obApiReference) {
        return !discoveryProperties.getApis().containsKey(obApiReference) || discoveryProperties.getApis().get(obApiReference);
    }

    private boolean isVersionOverrideEnabled(String version, OBApiReference obApiReference) {
        // Use _ instead of . in yaml to reduce ambiguity over yml separator vs version
        String yamlVersion = version.replace(".", "_");
        Map<String, Map<OBApiReference, Boolean>> versionApiOverrides = discoveryProperties.getVersionApiOverrides();
        if (versionApiOverrides.containsKey(yamlVersion) && versionApiOverrides.get(yamlVersion).containsKey(obApiReference)) {
            return versionApiOverrides.get(yamlVersion).get(obApiReference);
        }
        return true;
    }
}
