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

import com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.ConsentStatusCode;
import com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.FRDomesticConsent;
import com.forgerock.securebanking.openbanking.aspsp.persistence.domain.payment.FRWriteDomesticConsent;
import com.forgerock.securebanking.openbanking.aspsp.persistence.domain.payment.FRWriteDomesticConsentData;
import com.forgerock.securebanking.openbanking.aspsp.persistence.domain.payment.common.FRDataAuthorisation;
import com.forgerock.securebanking.openbanking.aspsp.persistence.repository.payments.DomesticConsentRepository;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import uk.org.openbanking.datamodel.payment.OBWriteDataDomestic2;
import uk.org.openbanking.datamodel.payment.OBWriteDomestic2;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticResponse5;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticResponse5Data;

import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRPaymentRiskConverter.toFRRisk;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRWriteDomesticConsentConverter.toFRWriteDomesticDataInitiation;
import static com.forgerock.securebanking.openbanking.aspsp.persistence.domain.payment.common.FRDataAuthorisation.AuthorisationType.SINGLE;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static uk.org.openbanking.datamodel.service.converter.payment.OBDomesticConverter.toOBWriteDomestic2DataInitiation;
import static uk.org.openbanking.testsupport.payment.OBWriteDomesticConsentTestDataFactory.aValidOBWriteDomestic2;

/**
 * An SpringBoot test for the {@link DomesticPaymentsApiController}.
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class DomesticPaymentsApiControllerTest {

    private static final String BASE_URL = "http://localhost:";
    private static final String DOMESTIC_PAYMENTS_URI = "/open-banking/v3.1.5/pisp/domestic-payments";

    @LocalServerPort
    private int port;

    @Autowired
    private DomesticConsentRepository domesticConsentRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldCreateDomesticPayment() throws Exception {
        // Given
        OBWriteDomestic2 payment = aValidOBWriteDomestic2();
        savePaymentConsent(payment);
        HttpEntity<OBWriteDomestic2> request = new HttpEntity<>(payment, httpHeaders());

        // When
        ResponseEntity<OBWriteDomesticResponse5> response = restTemplate.postForEntity(paymentsUrl(), request, OBWriteDomesticResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        OBWriteDomesticResponse5Data paymentData = response.getBody().getData();
        assertThat(paymentData.getConsentId()).isEqualTo(payment.getData().getConsentId());
        // convert from new to old before comparing (due to missing fields on older versions)
        assertThat(paymentData.getInitiation()).isEqualTo(toOBWriteDomestic2DataInitiation(payment.getData().getInitiation()));
        assertThat(response.getBody().getLinks().getSelf().endsWith("/domestic-payments/" + paymentData.getDomesticPaymentId())).isTrue();
    }

    @Test
    public void shouldGetDomesticPaymentById() {
        // Given
        OBWriteDomestic2 payment = aValidOBWriteDomestic2();
        savePaymentConsent(payment);
        HttpEntity<OBWriteDomestic2> request = new HttpEntity<>(payment, httpHeaders());
        ResponseEntity<OBWriteDomesticResponse5> persistedPayment = restTemplate.postForEntity(paymentsUrl(), request, OBWriteDomesticResponse5.class);
        String url = paymentIdUrl(persistedPayment.getBody().getData().getDomesticPaymentId());

        // When
        ResponseEntity<OBWriteDomesticResponse5> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(httpHeaders()), OBWriteDomesticResponse5.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        OBWriteDomesticResponse5Data paymentData = response.getBody().getData();
        assertThat(paymentData.getConsentId()).isEqualTo(payment.getData().getConsentId());
        assertThat(paymentData.getInitiation()).isEqualTo(toOBWriteDomestic2DataInitiation(payment.getData().getInitiation()));
        assertThat(response.getBody().getLinks().getSelf().endsWith("/domestic-payments/" + paymentData.getDomesticPaymentId())).isTrue();
    }

    private FRDomesticConsent savePaymentConsent(OBWriteDomestic2 payment) {
        FRDomesticConsent frDomesticConsent = aValidFRDomesticConsent(payment);
        domesticConsentRepository.save(frDomesticConsent);
        return frDomesticConsent;
    }

    private String paymentsUrl() {
        return BASE_URL + port + DOMESTIC_PAYMENTS_URI;
    }

    private String paymentIdUrl(String id) {
        return paymentsUrl() + "/" + id;
    }

    private HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("dummyAuthToken");
        headers.add("x-idempotency-key", "dummyIdempotencyKey");
        headers.add("x-jws-signature", "dummyJwsSignature");
        return headers;
    }

    private FRDomesticConsent aValidFRDomesticConsent(OBWriteDomestic2 payment) {
        return FRDomesticConsent.builder()
                .id(payment.getData().getConsentId())
                .domesticConsent(FRWriteDomesticConsent.builder()
                        .data(toFRWriteDomesticConsentData(payment.getData()))
                        .risk(toFRRisk(payment.getRisk()))
                        .build())
                .created(new DateTime())
                .statusUpdate(new DateTime())
                .status(ConsentStatusCode.AUTHORISED)
                .build();
    }

    private FRWriteDomesticConsentData toFRWriteDomesticConsentData(OBWriteDataDomestic2 data) {
        return FRWriteDomesticConsentData.builder()
                .initiation(toFRWriteDomesticDataInitiation(data.getInitiation()))
                .authorisation(FRDataAuthorisation.builder()
                        .authorisationType(SINGLE)
                        .completionDateTime(new DateTime())
                        .build())
                .build();
    }

}
