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
package com.forgerock.securebanking.openbanking.aspsp.testsupport;

import com.forgerock.securebanking.openbanking.aspsp.persistence.domain.account.FRCashBalance;
import org.joda.time.DateTime;

import static com.forgerock.securebanking.openbanking.aspsp.persistence.domain.account.common.FRBalanceType.INTERIMAVAILABLE;
import static com.forgerock.securebanking.openbanking.aspsp.persistence.domain.account.common.FRCreditDebitIndicator.CREDIT;
import static com.forgerock.securebanking.openbanking.aspsp.testsupport.FRAmountTestDataFactory.aValidFRAmount;

/**
 * Test data factory for {@link FRCashBalance}.
 */
public class FRCashBalanceTestDataFactory {

    /**
     * @return a valid instance of {@link FRCashBalance}
     */
    public static FRCashBalance aValidFRCashBalance() {
        return aValidFRCashBalanceBuilder()
                .build();
    }

    /**
     * @return an instance of {@link FRCashBalance.FRCashBalanceBuilder} with the required values populated.
     */
    public static FRCashBalance.FRCashBalanceBuilder aValidFRCashBalanceBuilder() {
        return FRCashBalance.builder()
                .accountId("12345")
                .creditDebitIndicator(CREDIT)
                .type(INTERIMAVAILABLE)
                .dateTime(DateTime.now())
                .amount(aValidFRAmount());
    }
}
