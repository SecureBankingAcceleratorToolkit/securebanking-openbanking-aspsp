/**
 * Copyright 2019 ForgeRock AS.
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
package com.forgerock.openbanking.aspsp.persistence.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents {@link uk.org.openbanking.datamodel.event.OBCallbackUrlData1} in the OB data model. Note that
 * {@link uk.org.openbanking.datamodel.event.OBCallbackUrl1} is skipped in this domain model since it only contains {@code OBCallbackUrlData1}.
 *
 * <p>
 * This class is stored within mongo (instead of the OB model), in order to make it easier to introduce new versions of the Read/Write API.
 *</p>
 *
 * <p>
 * Note that this object is used across multiple versions of the Read/Write API, meaning that some values won't be populated. For this reason it is
 * a mutable {@link Data} rather than an immutable {@link lombok.Value} one.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FRCallbackUrlData {

    private String url;
    private String version;
}
