/**
 * Copyright 2019 ForgeRock AS.
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.forgerock.openbanking.aspsp.persistence.repository.accounts.accountsaccessconsents;

import com.forgerock.openbanking.aspsp.persistence.document.account.FRAccountAccessConsent;
import com.forgerock.openbanking.aspsp.persistence.domain.account.common.FRExternalRequestStatusCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FRAccountAccessConsentRepository extends MongoRepository<FRAccountAccessConsent, String> {

    Optional<FRAccountAccessConsent> findByConsentId(@Param("consentId") String consentId);

    Collection<FRAccountAccessConsent> findByUserId(@Param("userId") String userId);

    Collection<FRAccountAccessConsent> findByUserIdAndClientId(@Param("userId") String userId, @Param("clientId") String clientId);

    Collection<FRAccountAccessConsent> findByUserIdAndAccountAccessConsentDataStatusIn(@Param("userId") String userId, @Param("statuses") List<FRExternalRequestStatusCode> status);
}
