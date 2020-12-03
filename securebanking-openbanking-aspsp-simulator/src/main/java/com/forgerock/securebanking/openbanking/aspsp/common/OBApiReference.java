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
package com.forgerock.securebanking.openbanking.aspsp.common;

import org.springframework.http.HttpMethod;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.*;

/**
 * A version agnostic list of Open Banking API endpoints, along with their HTTP methods. This is used to help build up
 * a list of endpoints that are supported by the application and returned in the Discovery endpoint. It is also used to
 * disable specific controller methods that are not supported by the customer.
 */
public enum OBApiReference {

    CREATE_ACCOUNT_ACCESS_CONSENT("CreateAccountAccessConsent", POST),
    DELETE_ACCOUNT_ACCESS_CONSENT("DeleteAccountAccessConsent", DELETE),
    GET_ACCOUNT_ACCESS_CONSENT("GetAccountAccessConsent", GET),

    GET_ACCOUNTS("GetAccounts", GET),
    GET_ACCOUNT("GetAccount", GET),
    GET_ACCOUNT_TRANSACTIONS("GetAccountTransactions", GET),
    GET_ACCOUNT_BENEFICIARIES("GetAccountBeneficiaries", GET),
    GET_ACCOUNT_BALANCES("GetAccountBalances", GET),
    GET_ACCOUNT_DIRECT_DEBITS("GetAccountDirectDebits", GET),
    GET_ACCOUNT_STANDING_ORDERS("GetAccountStandingOrders", GET),
    GET_ACCOUNT_PRODUCT("GetAccountProduct", GET),
    GET_STANDING_ORDERS("GetStandingOrders", GET),
    GET_DIRECT_DEBITS("GetDirectDebits", GET),
    GET_BENEFICIARIES("GetBeneficiaries", GET),
    GET_TRANSACTIONS("GetTransactions", GET),
    GET_BALANCES("GetBalances", GET),
    GET_PRODUCTS("GetProducts", GET),
    GET_ACCOUNT_OFFERS("GetAccountOffers", GET),
    GET_ACCOUNT_PARTY("GetAccountParty", GET),
    GET_ACCOUNT_PARTIES("GetAccountParties", GET),
    GET_ACCOUNT_SCHEDULED_PAYMENTS("GetAccountScheduledPayments", GET),
    GET_ACCOUNT_STATEMENTS("GetAccountStatements", GET),
    GET_ACCOUNT_STATEMENT("GetAccountStatement", GET),
    GET_ACCOUNT_STATEMENT_FILE("GetAccountStatementFile", GET),
    GET_ACCOUNT_STATEMENT_TRANSACTIONS("GetAccountStatementTransactions", GET),
    GET_OFFERS("GetOffers", GET),
    GET_PARTY("GetParty", GET),
    GET_SCHEDULED_PAYMENTS("GetScheduledPayments", GET),
    GET_STATEMENTS("GetStatements", GET),

    CREATE_FUNDS_CONFIRMATION_CONSENT("CreateFundsConfirmationConsent", POST),
    GET_FUNDS_CONFIRMATION_CONSENT("GetFundsConfirmationConsent", GET),
    CREATE_FUNDS_CONFIRMATION("CreateFundsConfirmation", POST),
    GET_FUNDS_CONFIRMATION("GetFundsConfirmation", GET),
    DELETE_FUNDS_CONFIRMATION_CONSENT("DeleteFundsConfirmationConsent", DELETE),

    CREATE_CALLBACK_URL("CreateCallbackUrl", POST),
    GET_CALLBACK_URLS("GetCallbackUrls", GET),
    AMEND_CALLBACK_URL("AmendCallbackUrl", PUT),
    DELETE_CALLBACK_URL("DeleteCallbackUrl", DELETE),

    CREATE_EVENT_SUBSCRIPTION("CreateEventSubscription", POST),
    GET_EVENT_SUBSCRIPTION("GetEventSubscription", GET),
    AMEND_EVENT_SUBSCRIPTION("AmendEventSubscription", PUT),
    DELETE_EVENT_SUBSCRIPTION("DeleteEventSubscription", DELETE),

    EVENT_AGGREGATED_POLLING("EventAggregatedPolling", GET),

    CREATE_SINGLE_IMMEDIATE_PAYMENT("CreateSingleImmediatePayment", POST),
    GET_SINGLE_IMMEDIATE_PAYMENT("GetSingleImmediatePayment", GET),
    CREATE_PAYMENT_SUBMISSION("CreatePaymentSubmission", POST),
    GET_PAYMENT_SUBMISSION("GetPaymentSubmission", GET),

    CREATE_DOMESTIC_PAYMENT_CONSENT("CreateDomesticPaymentConsent", POST),
    GET_DOMESTIC_PAYMENT_CONSENT("GetDomesticPaymentConsent", GET),
    CREATE_DOMESTIC_PAYMENT("CreateDomesticPayment", POST),
    GET_DOMESTIC_PAYMENT("GetDomesticPayment", GET),
    GET_DOMESTIC_PAYMENT_CONSENTS_CONSENT_ID_FUNDS_CONFIRMATION("GetDomesticPaymentConsentsConsentIdFundsConfirmation", GET),
    GET_DOMESTIC_PAYMENTS_DOMESTIC_PAYMENT_ID_PAYMENT_DETAILS("GetDomesticPaymentsDomesticPaymentIdPaymentDetails", GET),

    CREATE_DOMESTIC_SCHEDULED_PAYMENT_CONSENT("CreateDomesticScheduledPaymentConsent", POST),
    GET_DOMESTIC_SCHEDULED_PAYMENT_CONSENT("GetDomesticScheduledPaymentConsent", GET),
    CREATE_DOMESTIC_SCHEDULED_PAYMENT("CreateDomesticScheduledPayment", POST),
    GET_DOMESTIC_SCHEDULED_PAYMENT("GetDomesticScheduledPayment", GET),
    GET_DOMESTIC_SCHEDULED_PAYMENTS_DOMESTIC_SCHEDULED_PAYMENT_ID_PAYMENT_DETAILS("GetDomesticScheduledPaymentsDomesticScheduledPaymentIdPaymentDetails", GET),

    CREATE_DOMESTIC_STANDING_ORDER_CONSENT("CreateDomesticStandingOrderConsent", POST),
    GET_DOMESTIC_STANDING_ORDER_CONSENT("GetDomesticStandingOrderConsent", GET),
    CREATE_DOMESTIC_STANDING_ORDER("CreateDomesticStandingOrder", POST),
    GET_DOMESTIC_STANDING_ORDER("GetDomesticStandingOrder", GET),
    GET_DOMESTIC_STANDING_ORDERS_DOMESTIC_STANDING_ORDER_ID_PAYMENT_DETAILS("GetDomesticStandingOrdersDomesticStandingOrderIdPaymentDetails", GET),

    CREATE_INTERNATIONAL_PAYMENT_CONSENT("CreateInternationalPaymentConsent", POST),
    GET_INTERNATIONAL_PAYMENT_CONSENT("GetInternationalPaymentConsent", GET),
    CREATE_INTERNATIONAL_PAYMENT("CreateInternationalPayment", POST),
    GET_INTERNATIONAL_PAYMENT("GetInternationalPayment", GET),
    GET_INTERNATIONAL_PAYMENT_CONSENTS_CONSENT_ID_FUNDS_CONFIRMATION("GetInternationalPaymentConsentsConsentIdFundsConfirmation", GET),
    GET_INTERNATIONAL_PAYMENTS_INTERNATIONAL_PAYMENT_ID_PAYMENT_DETAILS("GetInternationalPaymentsInternationalPaymentIdPaymentDetails", GET),

    CREATE_INTERNATIONAL_SCHEDULED_PAYMENT_CONSENT("CreateInternationalScheduledPaymentConsent", POST),
    GET_INTERNATIONAL_SCHEDULED_PAYMENT_CONSENT("GetInternationalScheduledPaymentConsent", GET),
    CREATE_INTERNATIONAL_SCHEDULED_PAYMENT("CreateInternationalScheduledPayment", POST),
    GET_INTERNATIONAL_SCHEDULED_PAYMENT("GetInternationalScheduledPayment", GET),
    GET_INTERNATIONAL_SCHEDULED_PAYMENT_CONSENTS_CONSENT_ID_FUNDS_CONFIRMATION("GetInternationalScheduledPaymentConsentsConsentIdFundsConfirmation", GET),
    GET_INTERNATIONAL_SCHEDULED_PAYMENT_DETAILS("GetInternationalScheduledPaymentDetails", GET),

    CREATE_INTERNATIONAL_STANDING_ORDER_CONSENT("CreateInternationalStandingOrderConsent", POST),
    GET_INTERNATIONAL_STANDING_ORDER_CONSENT("GetInternationalStandingOrderConsent", GET),
    CREATE_INTERNATIONAL_STANDING_ORDER("CreateInternationalStandingOrder", POST),
    GET_INTERNATIONAL_STANDING_ORDER("GetInternationalStandingOrder", GET),
    GET_INTERNATIONAL_STANDING_ORDER_PAYMENT_DETAILS("GetInternationalStandingOrderPaymentDetails", GET),

    CREATE_FILE_PAYMENT_CONSENT("CreateFilePaymentConsent", POST),
    GET_FILE_PAYMENT_CONSENT("GetFilePaymentConsent", GET),

    CREATE_FILE_PAYMENT_FILE("CreateFilePaymentFile", POST),
    GET_FILE_PAYMENT_FILE("GetFilePaymentFile", GET),

    CREATE_FILE_PAYMENT("CreateFilePayment", POST),
    GET_FILE_PAYMENT("GetFilePayment", GET),
    GET_FILE_PAYMENT_DETAILS("GetFilePaymentDetails", GET),
    GET_FILE_PAYMENT_REPORT("GetFilePaymentReport", GET);

    private static final OBApiReference[] REFERENCES = OBApiReference.values();

    /**
     * A reference to the endpoint which is returned in the Discovery endpoint.
     */
    private final String reference;

    /**
     * The HTTP method (e.g. GET). This is used to help map an API URL to a specific endpoint (e.g. GET
     * and DELETE share the same URL).
     */
    private final HttpMethod httpMethod;

    /**
     * Constructor used by Spring to instantiate from references in config.
     *
     * @param reference a reference for the API endpoint.
     */
    OBApiReference(String reference) {
        this.reference = reference;
        this.httpMethod = fromReference(reference) != null ? fromReference(reference).httpMethod : null;
    }

    /**
     * Constructor used by enum values above.
     *
     * @param reference a reference for the API endpoint.
     * @param httpMethod the HTTP method (e.g. GET) for the endpoint.
     */
    OBApiReference(String reference, HttpMethod httpMethod) {
        this.reference = reference;
        this.httpMethod = httpMethod;
    }

    public String getReference() {
        return reference;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public static OBApiReference fromReference(String reference) {
        return Arrays.stream(REFERENCES)
                .filter(r -> r.reference.equals(reference))
                .findFirst()
                .orElse(null);
    }
}
