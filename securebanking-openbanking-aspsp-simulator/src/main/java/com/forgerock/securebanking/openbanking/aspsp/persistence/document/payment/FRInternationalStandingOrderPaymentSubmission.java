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
package com.forgerock.securebanking.openbanking.aspsp.persistence.document.payment;

import com.forgerock.securebanking.common.openbanking.domain.payment.PaymentSubmission;
import com.forgerock.securebanking.openbanking.aspsp.common.version.OBVersion;
import com.forgerock.securebanking.common.openbanking.domain.payment.data.FRWriteInternationalStandingOrder;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Data
@Document
public class FRInternationalStandingOrderPaymentSubmission implements PaymentSubmission {
    @Id
    @Indexed
    public String id;

    public FRWriteInternationalStandingOrder internationalStandingOrder;

    @CreatedDate
    public Date created;
    @LastModifiedDate
    public Date updated;

    public String idempotencyKey;

    public OBVersion version;
}
