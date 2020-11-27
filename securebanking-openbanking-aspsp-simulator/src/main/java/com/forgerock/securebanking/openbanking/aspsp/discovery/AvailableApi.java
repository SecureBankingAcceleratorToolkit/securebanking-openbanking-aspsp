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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * This class is used to load all of the available Read/Write API endpoints from the application's spring configuration. These are the
 * endpoints that this application supports (so long as a customer has not disabled it in their configuration).
 *
 * <p>
 * If a customer does not support any of these endpoints, then they should disable them in their application configuration
 * (which subsequently changes the output of the Discovery endpoint).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableApi {
    private OBGroupName groupName;
    private String version;
    private Map<OBApiReference, String> links;
}
