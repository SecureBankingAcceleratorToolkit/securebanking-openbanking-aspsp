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
/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.forgerock.securebanking.openbanking.aspsp.api.payment.v3_1_5.domesticpayments;

import com.forgerock.securebanking.openbanking.aspsp.common.error.OBErrorResponseException;
import com.forgerock.securebanking.openbanking.aspsp.common.util.PaginationUtil;
import com.forgerock.securebanking.openbanking.aspsp.common.util.VersionPathExtractor;
import com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.ConsentStatusCode;
import com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.FRDomesticConsent;
import com.forgerock.securebanking.openbanking.aspsp.persistence.domain.payment.FRWriteDomesticConsent;
import com.forgerock.securebanking.openbanking.aspsp.persistence.repository.payments.DomesticConsentRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import uk.org.openbanking.datamodel.account.Links;
import uk.org.openbanking.datamodel.account.Meta;
import uk.org.openbanking.datamodel.payment.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import static com.forgerock.securebanking.openbanking.aspsp.common.converter.common.FRAccountIdentifierConverter.toOBDebtorIdentification1;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRDataAuthorisationConverter.toOBWriteDomesticConsent4DataAuthorisation;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRDataSCASupportDataConverter.toOBWriteDomesticConsent4DataSCASupportData;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRPaymentRiskConverter.toOBRisk1;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRWriteDomesticConsentConverter.toFRWriteDomesticConsent;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRWriteDomesticConsentConverter.toOBWriteDomestic2DataInitiation;
import static com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.converter.v3_1_5.ConsentStatusCodeToResponseDataStatusConverter.toOBWriteDomesticConsentResponse5DataStatus;
import static com.forgerock.securebanking.openbanking.aspsp.service.IdempotencyService.validateIdempotencyRequest;

@Controller("DomesticPaymentConsentsApiV3.1.5")
@Slf4j
public class DomesticPaymentConsentsApiController implements DomesticPaymentConsentsApi {

    private final DomesticConsentRepository domesticConsentRepository;

    public DomesticPaymentConsentsApiController(DomesticConsentRepository domesticConsentRepository) {
        this.domesticConsentRepository = domesticConsentRepository;
    }

    @Override
    public ResponseEntity<OBWriteDomesticConsentResponse5> createDomesticPaymentConsents(
            OBWriteDomesticConsent4 obWriteDomesticConsent4,
            String authorization,
            String xIdempotencyKey,
            String xJwsSignature,
            DateTime xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String xCustomerUserAgent,
            String clientId,
            HttpServletRequest request,
            Principal principal
    ) throws OBErrorResponseException {
        log.debug("Received: '{}'", obWriteDomesticConsent4);
        FRWriteDomesticConsent frWriteDomesticConsent = toFRWriteDomesticConsent(obWriteDomesticConsent4);
        log.trace("Converted to: '{}'", frWriteDomesticConsent);

        // TODO #6 - get tpp ID (client ID) from JWT
        String tppId = "123456";
        String tppName = "A TPP name";
        Optional<FRDomesticConsent> consentByIdempotencyKey = domesticConsentRepository.findByIdempotencyKeyAndPispId(xIdempotencyKey, tppId);
        if (consentByIdempotencyKey.isPresent()) {
            validateIdempotencyRequest(xIdempotencyKey, frWriteDomesticConsent, consentByIdempotencyKey.get(), () -> consentByIdempotencyKey.get().getDomesticConsent());
            log.info("Idempotent request is valid. Returning [201 CREATED] but take no further action.");
            return ResponseEntity.status(HttpStatus.CREATED).body(packageResponse(consentByIdempotencyKey.get()));
        }
        log.debug("No consent with matching idempotency key has been found. Creating new consent.");

        FRDomesticConsent domesticConsent = FRDomesticConsent.builder()
                // TODO #6 - fix intent ID
                .id(generateIntentId())
                .status(ConsentStatusCode.AWAITINGAUTHORISATION)
                .domesticConsent(frWriteDomesticConsent)
                .pispId(tppId)
                .pispName(tppName)
                .statusUpdate(DateTime.now())
                .idempotencyKey(xIdempotencyKey)
                .obVersion(VersionPathExtractor.getVersionFromPath(request))
                .build();
        log.debug("Saving consent: '{}'", domesticConsent);
        // TODO #6 - determine log statement for analytics
        //consentMetricService.sendConsentActivity(new ConsentStatusEntry(domesticConsent.getId(), domesticConsent.getStatus().name()));
        domesticConsent = domesticConsentRepository.save(domesticConsent);
        log.info("Created consent id: '{}'", domesticConsent.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(packageResponse(domesticConsent));
    }

    @Override
    public ResponseEntity getDomesticPaymentConsentsConsentId(
            String consentId,
            String authorization,
            DateTime xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String xCustomerUserAgent,
            HttpServletRequest request,
            Principal principal
    ) throws OBErrorResponseException {
        Optional<FRDomesticConsent> isDomesticConsent = domesticConsentRepository.findById(consentId);
        if (!isDomesticConsent.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Domestic consent '" + consentId + "' can't be found");
        }
        FRDomesticConsent domesticConsent = isDomesticConsent.get();

        return ResponseEntity.ok(packageResponse(domesticConsent));
    }

    @Override
    public ResponseEntity getDomesticPaymentConsentsConsentIdFundsConfirmation(
            String consentId,
            String authorization,
            DateTime xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String xCustomerUserAgent,
            String httpUrl,
            HttpServletRequest request,
            Principal principal
    ) throws OBErrorResponseException {
        Optional<FRDomesticConsent> isDomesticConsent = domesticConsentRepository.findById(consentId);
        if (!isDomesticConsent.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Domestic consent '" + consentId + "' can't be found");
        }
        FRDomesticConsent domesticConsent = isDomesticConsent.get();

        // TODO #6 - check if funds are available - do we need to do this?
        boolean areFundsAvailable = true;
//        // Check if funds are available on the account selected in consent
//        boolean areFundsAvailable = fundsAvailabilityService.isFundsAvailable(
//                domesticConsent.getAccountId(),
//                domesticConsent.getInitiation().getInstructedAmount().getAmount());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new OBWriteFundsConfirmationResponse1()
                        .data(new OBWriteDataFundsConfirmationResponse1()
                                .fundsAvailableResult(new OBFundsAvailableResult1()
                                        .fundsAvailable(areFundsAvailable)
                                        .fundsAvailableDateTime(DateTime.now())
                                ))
                        .links(PaginationUtil.generateLinksOnePager(httpUrl))
                        .meta(new Meta())
                );

    }

    private OBWriteDomesticConsentResponse5 packageResponse(FRDomesticConsent domesticConsent) {
        return new OBWriteDomesticConsentResponse5()
                .data(new OBWriteDomesticConsentResponse5Data()
                        .initiation(toOBWriteDomestic2DataInitiation(domesticConsent.getInitiation()))
                        .status(toOBWriteDomesticConsentResponse5DataStatus(domesticConsent.getStatus()))
                        .creationDateTime(domesticConsent.getCreated())
                        .statusUpdateDateTime(domesticConsent.getStatusUpdate())
                        .consentId(domesticConsent.getId())
                        .authorisation(toOBWriteDomesticConsent4DataAuthorisation(domesticConsent.getDomesticConsent().getData().getAuthorisation()))
                        .scASupportData(toOBWriteDomesticConsent4DataSCASupportData(domesticConsent.getDomesticConsent().getData().getScASupportData()))
                        .debtor(toOBDebtorIdentification1(domesticConsent.getInitiation().getDebtorAccount()))
                )
                // TODO #6 - use discovery endpoints to determine links
                .links(new Links())
                .risk(toOBRisk1(domesticConsent.getRisk()))
                .meta(new Meta());
    }

    // TODO #6 - use IntentType from aspsp
    private String generateIntentId() {
        String intentId = "PDC_" + UUID.randomUUID().toString();
        return intentId.substring(0, Math.min(intentId.length(), 40));
    }

}