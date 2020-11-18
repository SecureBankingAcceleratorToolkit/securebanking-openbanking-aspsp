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
package com.forgerock.sba.ob.aspsp.persistence.document.payment;

import com.forgerock.sba.ob.aspsp.common.OBVersion;
import com.forgerock.sba.ob.aspsp.persistence.domain.payment.FRWriteInternationalScheduledConsent;
import com.forgerock.sba.ob.aspsp.persistence.domain.payment.FRWriteInternationalScheduledDataInitiation;
import com.forgerock.sba.ob.aspsp.persistence.domain.payment.common.FRExchangeRateInformation;
import com.forgerock.sba.ob.aspsp.persistence.domain.payment.common.FRPaymentRisk;
import com.forgerock.sba.ob.aspsp.service.currency.CurrencyRateService;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class FRInternationalScheduledConsent implements PaymentConsent, Persistable<String> {
    @Id
    @Indexed
    public String id;
    @Indexed
    public ConsentStatusCode status;
    public FRWriteInternationalScheduledConsent internationalScheduledConsent;

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
    public DateTime statusUpdate;
    @LastModifiedDate
    public Date updated;

    public OBVersion obVersion;

    @Override
    public void setPisp(String pispId, String pispName) {
        this.pispId = pispId;
        this.pispName = pispName;
    }

    @Override
    public FRWriteInternationalScheduledDataInitiation getInitiation() {
        return internationalScheduledConsent.getData().getInitiation();
    }

    @Override
    public FRPaymentRisk getRisk() {
        return internationalScheduledConsent.getRisk();
    }

    @Override
    public boolean isNew() {
        return created == null;
    }

    /**
     * Note: we do not persist the calculated exchange rate fields (such as rate value and expiry date) as the exchange rate object in the initiation must match exactly what the
     * user submitted. We could persist the calculated exchange rate separately but currently it is easier just to generate dynamically as the rate it always the same value
     * for testing purposes.
     *
     * @return {@link FRExchangeRateInformation} with rate nd expiry date fields populated where appropriate.
     */
    public FRExchangeRateInformation getCalculatedExchangeRate() {
        return CurrencyRateService.getCalculatedExchangeRate(getInitiation().getExchangeRateInformation(), created);
    }
}