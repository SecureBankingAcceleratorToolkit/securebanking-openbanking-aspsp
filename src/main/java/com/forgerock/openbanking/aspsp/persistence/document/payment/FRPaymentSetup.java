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
package com.forgerock.openbanking.aspsp.persistence.document.payment;

import com.forgerock.openbanking.aspsp.common.OBVersion;
import com.forgerock.openbanking.aspsp.persistence.domain.payment.FRWriteDomesticConsent;
import com.forgerock.openbanking.aspsp.persistence.domain.payment.FRWriteDomesticDataInitiation;
import com.forgerock.openbanking.aspsp.persistence.domain.payment.common.FRPaymentRisk;
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

import java.util.Date;

/**
 * FRPaymentConsent setup entity for storing in DB
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class FRPaymentSetup implements PaymentConsent, Persistable<String> {
    @Id
    @Indexed
    public String id;
    @Indexed
    public ConsentStatusCode status;
    public FRWriteDomesticConsent paymentSetupRequest;

    @Indexed
    public String accountId;
    @Indexed
    public String userId;
    @Indexed
    public String pispId;
    public String pispName;
    public String idempotencyKey;

    @CreatedDate
    public DateTime created;
    @LastModifiedDate
    public Date updated;

    public OBVersion obVersion;

    @Override
    public void setPisp(String pispId, String pispName) {
        this.pispId = pispId;
        this.pispName = pispName;
    }

    @Override
    public FRWriteDomesticDataInitiation getInitiation() {
        return paymentSetupRequest.getData().getInitiation();
    }

    @Override
    public FRPaymentRisk getRisk() {
        return paymentSetupRequest.getRisk();
    }

    @Override
    public boolean isNew() {
        return created == null;
    }
}
