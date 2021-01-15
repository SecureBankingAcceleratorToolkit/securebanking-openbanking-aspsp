/**
 * Copyright © 2020 ForgeRock AS (obst@forgerock.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.forgerock.securebanking.openbanking.aspsp.api.payment.v3_1_5.domesticpayments;

import com.forgerock.securebanking.common.error.OBErrorResponseException;
import com.forgerock.securebanking.common.openbanking.domain.payment.data.FRWriteDataDomestic;
import com.forgerock.securebanking.common.openbanking.domain.payment.data.FRWriteDomestic;
import com.forgerock.securebanking.openbanking.aspsp.common.util.VersionPathExtractor;
import com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.FRDomesticPaymentSubmission;
import com.forgerock.securebanking.openbanking.aspsp.persistence.repository.IdempotentRepositoryAdapter;
import com.forgerock.securebanking.openbanking.aspsp.persistence.repository.payments.DomesticPaymentSubmissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import uk.org.openbanking.datamodel.account.Meta;
import uk.org.openbanking.datamodel.payment.OBWriteDomestic2;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticResponse5;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticResponse5Data;
import uk.org.openbanking.datamodel.payment.OBWriteDomesticResponse5Data.StatusEnum;
import uk.org.openbanking.datamodel.payment.OBWritePaymentDetailsResponse1;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

import static com.forgerock.securebanking.openbanking.aspsp.api.common.LinksHelper.createDomesticPaymentLink;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.FRAccountIdentifierConverter.toOBDebtorIdentification1;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRWriteDomesticConsentConverter.toOBWriteDomestic2DataInitiation;
import static com.forgerock.securebanking.openbanking.aspsp.common.converter.payment.FRWriteDomesticConverter.toFRWriteDomestic;
import static com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.FRPaymentStatus.PENDING;

@Controller("DomesticPaymentsApiV3.1.5")
@Slf4j
public class DomesticPaymentsApiController implements DomesticPaymentsApi {

    private final DomesticPaymentSubmissionRepository domesticPaymentSubmissionRepository;

    public DomesticPaymentsApiController(DomesticPaymentSubmissionRepository domesticPaymentSubmissionRepository) {
        this.domesticPaymentSubmissionRepository = domesticPaymentSubmissionRepository;
    }

    public ResponseEntity<OBWriteDomesticResponse5> createDomesticPayments(
            OBWriteDomestic2 obWriteDomestic2,
            String authorization,
            String xIdempotencyKey,
            String xJwsSignature,
            DateTime xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String xCustomerUserAgent,
            HttpServletRequest request,
            Principal principal
    ) throws OBErrorResponseException {
        log.debug("Received payment submission: '{}'", obWriteDomestic2);
        FRWriteDomestic frWriteDomestic = toFRWriteDomestic(obWriteDomestic2);
        log.trace("Converted to: '{}'", frWriteDomestic);

        FRDomesticPaymentSubmission frPaymentSubmission = FRDomesticPaymentSubmission.builder()
                .id(obWriteDomestic2.getData().getConsentId())
                .domesticPayment(frWriteDomestic)
                .paymentStatus(PENDING)
                .created(DateTime.now())
                .updated(DateTime.now())
                .idempotencyKey(xIdempotencyKey)
                .obVersion(VersionPathExtractor.getVersionFromPath(request))
                .build();
        frPaymentSubmission = new IdempotentRepositoryAdapter<>(domesticPaymentSubmissionRepository)
                .idempotentSave(frPaymentSubmission);
        return ResponseEntity.status(HttpStatus.CREATED).body(packagePayment(frPaymentSubmission));
    }

    public ResponseEntity getDomesticPaymentsDomesticPaymentId(
            String domesticPaymentId,
            String authorization,
            DateTime xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String xCustomerUserAgent,
            HttpServletRequest request,
            Principal principal
    ) {

        Optional<FRDomesticPaymentSubmission> isPaymentSubmission = domesticPaymentSubmissionRepository.findById(domesticPaymentId);
        if (!isPaymentSubmission.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment submission '" + domesticPaymentId + "' can't be found");
        }
        FRDomesticPaymentSubmission frPaymentSubmission = isPaymentSubmission.get();

        return ResponseEntity.ok(packagePayment(frPaymentSubmission));
    }

    public ResponseEntity<OBWritePaymentDetailsResponse1> getDomesticPaymentsDomesticPaymentIdPaymentDetails(
            String domesticPaymentId,
            String authorization,
            DateTime xFapiAuthDate,
            String xFapiCustomerIpAddress,
            String xFapiInteractionId,
            String xCustomerUserAgent,
            HttpServletRequest request,
            Principal principal
    ) {
        // Optional endpoint - not implemented
        return new ResponseEntity<OBWritePaymentDetailsResponse1>(HttpStatus.NOT_IMPLEMENTED);
    }

    private OBWriteDomesticResponse5 packagePayment(FRDomesticPaymentSubmission paymentSubmission) {
        FRWriteDataDomestic data = paymentSubmission.getDomesticPayment().getData();
        return new OBWriteDomesticResponse5()
                .data(new OBWriteDomesticResponse5Data()
                        .domesticPaymentId(paymentSubmission.getId())
                        .initiation(toOBWriteDomestic2DataInitiation(data.getInitiation()))
                        .creationDateTime(paymentSubmission.getCreated())
                        .statusUpdateDateTime(paymentSubmission.getUpdated())
                        .status(StatusEnum.fromValue(paymentSubmission.getPaymentStatus().getValue()))
                        .consentId(data.getConsentId())
                        .debtor(toOBDebtorIdentification1(data.getInitiation().getDebtorAccount())))
                .links(createDomesticPaymentLink(this.getClass(), paymentSubmission.getId()))
                .meta(new Meta());
    }
}
