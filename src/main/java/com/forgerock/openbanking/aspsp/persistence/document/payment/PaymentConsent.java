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
package com.forgerock.openbanking.aspsp.persistence.document.payment;

import com.forgerock.openbanking.aspsp.persistence.domain.payment.common.FRPaymentRisk;
import org.joda.time.DateTime;

/**
 * Representation of a payment.
 */
public interface PaymentConsent {

    void setPisp(String pispId, String pispName);

    String getPispName();

    String getId();

    ConsentStatusCode getStatus();

    void setStatus(ConsentStatusCode status);

    Object getInitiation();

    FRPaymentRisk getRisk();

    String getAccountId();

    void setAccountId(String accountId);

    String getUserId();

    void setUserId(String userId);

    String getPispId();

    DateTime getCreated();

    default DateTime getStatusUpdate() {
        return null; // Implemented in V3.0 onwards
    }

    default boolean isNew() {
        return getCreated() == null;
    }
}
