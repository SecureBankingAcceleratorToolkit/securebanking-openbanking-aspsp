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
package com.forgerock.securebanking.openbanking.aspsp.api.payment.v3_1_5.domesticpayments;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticConsent4;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticConsentResponse5;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticConsentResponse5Data;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static uk.org.openbanking.datamodel.service.converter.payment.OBDomesticConverter.toOBWriteDomestic2DataInitiation;
import static uk.org.openbanking.testsupport.payment.OBWriteDomesticConsentTestDataFactory.aValidOBWriteDomesticConsent4;

/**
 * A SpringBoot test for the {@link DomesticPaymentConsentsApiController}.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class DomesticPaymentConsentsApiControllerTest {

    private static final String BASE_URL = "http://localhost:";
    private static final String PAYMENT_CONSENTS_URI = "/open-banking/v3.1.5/pisp/domestic-payment-consents";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateDomesticPaymentConsent() {
        // Given
        OBWriteDomesticConsent4 paymentConsent = aValidOBWriteDomesticConsent4();
        HttpEntity<OBWriteDomesticConsent4> request = new HttpEntity<>(paymentConsent, httpHeaders());

        // When
        ResponseEntity<OBWriteDomesticConsentResponse5> response = restTemplate.postForEntity(paymentConsentsUrl(), request, OBWriteDomesticConsentResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        OBWriteDomesticConsentResponse5Data consentData = response.getBody().getData();
        assertThat(consentData.getConsentId()).isNotNull();
        // convert from new to old before comparing (due to missing fields on older versions)
        assertThat(consentData.getInitiation()).isEqualTo(toOBWriteDomestic2DataInitiation(paymentConsent.getData().getInitiation()));
        assertThat(response.getBody().getLinks().getSelf().endsWith("/domestic-payment-consents/" + consentData.getConsentId())).isTrue();
    }

    @Test
    public void shouldGetDomesticPaymentConsentById() {
        // Given
        OBWriteDomesticConsent4 paymentConsent = aValidOBWriteDomesticConsent4();
        HttpEntity<OBWriteDomesticConsent4> request = new HttpEntity<>(paymentConsent, httpHeaders());
        ResponseEntity<OBWriteDomesticConsentResponse5> persistedConsent = restTemplate.postForEntity(paymentConsentsUrl(), request, OBWriteDomesticConsentResponse5.class);
        String url = paymentConsentIdUrl(persistedConsent.getBody().getData().getConsentId());

        // When
        ResponseEntity<OBWriteDomesticConsentResponse5> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders()), OBWriteDomesticConsentResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        OBWriteDomesticConsentResponse5Data consentData = response.getBody().getData();
        assertThat(consentData.getConsentId()).isNotNull();
        assertThat(consentData.getInitiation()).isEqualTo(toOBWriteDomestic2DataInitiation(paymentConsent.getData().getInitiation()));
        assertThat(response.getBody().getLinks().getSelf().endsWith("/domestic-payment-consents/" + consentData.getConsentId())).isTrue();
    }

    private String paymentConsentsUrl() {
        return BASE_URL + port + PAYMENT_CONSENTS_URI;
    }

    private String paymentConsentIdUrl(String id) {
        return paymentConsentsUrl() + "/" + id;
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("dummyAuthToken");
        headers.add("x-idempotency-key", UUID.randomUUID().toString());
        headers.add("x-jws-signature", "dummyJwsSignature");
        return headers;
    }
}