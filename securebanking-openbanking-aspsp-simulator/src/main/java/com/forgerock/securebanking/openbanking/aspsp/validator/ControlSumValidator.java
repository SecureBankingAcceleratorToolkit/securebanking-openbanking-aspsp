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
package com.forgerock.securebanking.openbanking.aspsp.validator;

import com.forgerock.securebanking.openbanking.aspsp.common.error.OBErrorException;
import com.forgerock.securebanking.openbanking.aspsp.common.error.OBRIErrorType;
import com.forgerock.securebanking.openbanking.aspsp.common.filepayment.PaymentFile;
import com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment.FRFileConsent;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class ControlSumValidator {

    /**
     * Check that the provided payment file and payment file consent metadata have the same control sum (defined as sum of all transaction amounts in file).
     * This is an extra validation step to ensure correct and valid file has been uploaded for the consent
     * @param paymentFile Payment file body
     * @param consent Payment file consent
     * @throws OBErrorException Validation failed
     */
    public static void validate(FRFileConsent consent, PaymentFile paymentFile) throws OBErrorException {
        validate(paymentFile, consent.getInitiation().getControlSum(), consent.getId());
    }

    private static void validate(PaymentFile paymentFile, BigDecimal consentControlSum, String consentId) throws OBErrorException {
        BigDecimal fileControlSum = paymentFile.getControlSum();
        log.debug("Metadata indicates expected control sum of '{}'. File contains actual control sum of '{}'", consentControlSum, fileControlSum);
        if (fileControlSum.compareTo(consentControlSum) != 0) {
            log.warn("File consent metadata indicated control sum of '{}' but found a control sum of '{}' in uploaded file", consentControlSum, fileControlSum);
            throw new OBErrorException(OBRIErrorType.REQUEST_FILE_INCORRECT_CONTROL_SUM, fileControlSum.toPlainString(), consentControlSum.toPlainString());
        }
        log.debug("File control sum count is correct for consent id: {}", consentId);
    }
}
