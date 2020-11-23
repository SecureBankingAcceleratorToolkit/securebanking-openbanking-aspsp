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
package com.forgerock.securebanking.openbanking.aspsp.common.filepayment;

import com.forgerock.securebanking.openbanking.aspsp.persistence.domain.payment.FRFilePayment;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents a parsed valid payment file
 */
public interface PaymentFile {

    /**
     * @return Number of transactions in payyment file
     */
    int getNumberOfTransactions();

    /**
     * @return The control sum (sum of all transaction amounts)
     */
    BigDecimal getControlSum();

    /**
     * @return Media Type of the file contents
     */
    MediaType getContentType();

    /**
     * @return List of each transaction with essential payment details and status
     */
    List<FRFilePayment> getPayments();

}