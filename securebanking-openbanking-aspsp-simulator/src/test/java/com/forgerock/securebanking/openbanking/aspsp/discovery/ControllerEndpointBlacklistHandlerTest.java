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
package com.forgerock.securebanking.openbanking.aspsp.discovery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticConsent4;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticConsentResponse5;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static uk.org.openbanking.testsupport.payment.OBWriteDomesticConsentTestDataFactory.aValidOBWriteDomesticConsent4;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class ControllerEndpointBlacklistHandlerTest {

    private static final String BASE_URL = "http://localhost:";
    private static final String ENABLED_VERSION = "v3.1.5";
    private static final String DISABLED_VERSION = "v3.1.6";
    private static final String DISABLED_ENDPOINT_OVERRIDE_VERSION = "v3.1.5";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateDomesticPaymentConsentGivenApiVersionIsEnabled() {
        // Given
        OBWriteDomesticConsent4 paymentConsent = aValidOBWriteDomesticConsent4();
        HttpEntity<OBWriteDomesticConsent4> request = new HttpEntity<>(paymentConsent, httpHeaders());
        String url = paymentConsentsUrl(ENABLED_VERSION);

        // When
        ResponseEntity<?> response = restTemplate.postForEntity(url, request, OBWriteDomesticConsentResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldFailToCreateDomesticPaymentConsentGivenApiVersionIsDisabled() {
        // Given
        OBWriteDomesticConsent4 paymentConsent = aValidOBWriteDomesticConsent4();
        HttpEntity<OBWriteDomesticConsent4> request = new HttpEntity<>(paymentConsent, httpHeaders());
        String url = paymentConsentsUrl(DISABLED_VERSION);

        // When
        ResponseEntity<?> response = restTemplate.postForEntity(url, request, OBWriteDomesticConsentResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldFailToGetDomesticPaymentConsentGivenApiEndpointIsDisabled() {
        // Given
        HttpEntity<OBWriteDomesticConsent4> request = new HttpEntity<>(aValidOBWriteDomesticConsent4(), httpHeaders());
        ResponseEntity<OBWriteDomesticConsentResponse5> persistedConsent = restTemplate.postForEntity(
                paymentConsentsUrl(ENABLED_VERSION),
                request,
                OBWriteDomesticConsentResponse5.class);
        String url = paymentConsentIdUrl(ENABLED_VERSION, persistedConsent.getBody().getData().getConsentId());

        // When
        ResponseEntity<?> response = restTemplate.exchange(url, GET, new HttpEntity<>(httpHeaders()), OBWriteDomesticConsentResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldFailToCreateDomesticPaymentGivenApiEndpointIsDisabledForVersion() {
        // Given
        OBWriteDomesticConsent4 paymentConsent = aValidOBWriteDomesticConsent4();
        HttpEntity<OBWriteDomesticConsent4> request = new HttpEntity<>(paymentConsent, httpHeaders());
        String url = paymentsUrl(DISABLED_ENDPOINT_OVERRIDE_VERSION);

        // When
        ResponseEntity<?> response = restTemplate.postForEntity(url, request, OBWriteDomesticConsentResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String paymentsUrl(String version) {
        return BASE_URL + port + "/open-banking/" + version + "/pisp/domestic-payments";
    }

    private String paymentConsentsUrl(String version) {
        return BASE_URL + port + "/open-banking/" + version + "/pisp/domestic-payment-consents";
    }

    private String paymentConsentIdUrl(String version, String id) {
        return paymentConsentsUrl(version) + "/" + id;
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