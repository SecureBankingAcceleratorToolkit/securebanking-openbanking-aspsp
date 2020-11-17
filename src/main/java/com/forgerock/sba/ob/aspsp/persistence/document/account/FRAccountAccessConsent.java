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
package com.forgerock.sba.ob.aspsp.persistence.document.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forgerock.sba.ob.aspsp.common.OBVersion;
import com.forgerock.sba.ob.aspsp.persistence.domain.account.FRReadConsentResponse;
import com.forgerock.sba.ob.aspsp.persistence.domain.account.common.FRExternalPermissionsCode;
import com.forgerock.sba.ob.aspsp.persistence.domain.account.common.FRExternalRequestStatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FRAccountAccessConsent implements AccountRequest, Persistable<String> {
    @Id
    @Indexed
    public String id;
    private FRReadConsentResponse accountAccessConsent;
    @Indexed
    private String clientId;
    private String aispId;
    private String aispName;
    private String consentId;
    private List<String> accountIds = new ArrayList<>();
    private String userId;

    @CreatedDate
    public Date created;
    @LastModifiedDate
    public Date updated;

    OBVersion obVersion;

    @Override
    public boolean isNew() {
        return created == null;
    }

    public void setAisp(String clientId, String aispId, String aispName) {
        this.clientId = clientId;
        this.aispId = aispId;
        this.aispName = aispName;
    }

    @Override
    @JsonIgnore
    public FRExternalRequestStatusCode getStatus() {
        return accountAccessConsent.getData().getStatus();
    }

    @Override
    @JsonIgnore
    public void setStatus(FRExternalRequestStatusCode code) {
        getAccountAccessConsent().getData().setStatus(code);
    }

    @Override
    @JsonIgnore
    public List<FRExternalPermissionsCode> getPermissions() {
        return getAccountAccessConsent().getData().getPermissions();
    }

    @Override
    @JsonIgnore
    public DateTime getExpirationDateTime() {
        return getAccountAccessConsent().getData().getExpirationDateTime();
    }

    @Override
    @JsonIgnore
    public DateTime getTransactionFromDateTime() {
        return getAccountAccessConsent().getData().getTransactionFromDateTime();
    }

    @Override
    @JsonIgnore
    public DateTime getTransactionToDateTime() {
        return getAccountAccessConsent().getData().getTransactionToDateTime();
    }

}
