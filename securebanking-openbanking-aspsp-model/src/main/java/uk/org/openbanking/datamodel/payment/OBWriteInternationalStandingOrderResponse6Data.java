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
/*
 * Payment Initiation API
 * Swagger for Payment Initiation API Specification
 *
 * OpenAPI spec version: v3.1.4
 * Contact: ServiceDesk@openbanking.org.uk
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package uk.org.openbanking.datamodel.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OBWriteInternationalStandingOrderResponse6Data
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-19T14:14:13.501+01:00")
public class OBWriteInternationalStandingOrderResponse6Data {
    @JsonProperty("InternationalStandingOrderId")
    private String internationalStandingOrderId = null;

    @JsonProperty("ConsentId")
    private String consentId = null;

    @JsonProperty("CreationDateTime")
    private DateTime creationDateTime = null;

    /**
     * Specifies the status of resource in code form.
     */
    public enum StatusEnum {
        CANCELLED("Cancelled"),

        INITIATIONCOMPLETED("InitiationCompleted"),

        INITIATIONFAILED("InitiationFailed"),

        INITIATIONPENDING("InitiationPending");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("Status")
    private StatusEnum status = null;

    @JsonProperty("StatusUpdateDateTime")
    private DateTime statusUpdateDateTime = null;

    @JsonProperty("Refund")
    private OBWriteInternationalRefundResponse1DataRefund refund = null;

    @JsonProperty("Charges")
    private List<OBWriteDomesticConsentResponse4DataCharges> charges = null;

    @JsonProperty("Initiation")
    private OBWriteInternationalStandingOrder4DataInitiation initiation = null;

    @JsonProperty("MultiAuthorisation")
    private OBWriteDomesticResponse4DataMultiAuthorisation multiAuthorisation = null;

    public OBWriteInternationalStandingOrderResponse6Data internationalStandingOrderId(String internationalStandingOrderId) {
        this.internationalStandingOrderId = internationalStandingOrderId;
        return this;
    }

    /**
     * OB: Unique identification as assigned by the ASPSP to uniquely identify the international standing order resource.
     *
     * @return internationalStandingOrderId
     **/
    @NotNull
    @Size(min = 1, max = 40)
    @ApiModelProperty(required = true, value = "OB: Unique identification as assigned by the ASPSP to uniquely identify the international standing order resource.")
    public String getInternationalStandingOrderId() {
        return internationalStandingOrderId;
    }

    public void setInternationalStandingOrderId(String internationalStandingOrderId) {
        this.internationalStandingOrderId = internationalStandingOrderId;
    }

    public OBWriteInternationalStandingOrderResponse6Data consentId(String consentId) {
        this.consentId = consentId;
        return this;
    }

    /**
     * OB: Unique identification as assigned by the ASPSP to uniquely identify the consent resource.
     *
     * @return consentId
     **/
    @NotNull
    @Size(min = 1, max = 128)
    @ApiModelProperty(required = true, value = "OB: Unique identification as assigned by the ASPSP to uniquely identify the consent resource.")
    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public OBWriteInternationalStandingOrderResponse6Data creationDateTime(DateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }

    /**
     * Date and time at which the resource was created.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
     *
     * @return creationDateTime
     **/
    @NotNull
    @Valid
    @ApiModelProperty(required = true, value = "Date and time at which the resource was created.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00")
    public DateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(DateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public OBWriteInternationalStandingOrderResponse6Data status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * Specifies the status of resource in code form.
     *
     * @return status
     **/
    @NotNull
    @ApiModelProperty(required = true, value = "Specifies the status of resource in code form.")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public OBWriteInternationalStandingOrderResponse6Data statusUpdateDateTime(DateTime statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
        return this;
    }

    /**
     * Date and time at which the resource status was updated.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00
     *
     * @return statusUpdateDateTime
     **/
    @NotNull
    @Valid
    @ApiModelProperty(required = true, value = "Date and time at which the resource status was updated.All dates in the JSON payloads are represented in ISO 8601 date-time format.  All date-time fields in responses must include the timezone. An example is below: 2017-04-05T10:43:07+00:00")
    public DateTime getStatusUpdateDateTime() {
        return statusUpdateDateTime;
    }

    public void setStatusUpdateDateTime(DateTime statusUpdateDateTime) {
        this.statusUpdateDateTime = statusUpdateDateTime;
    }

    public OBWriteInternationalStandingOrderResponse6Data refund(OBWriteInternationalRefundResponse1DataRefund refund) {
        this.refund = refund;
        return this;
    }

    /**
     * Get refund
     *
     * @return refund
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBWriteInternationalRefundResponse1DataRefund getRefund() {
        return refund;
    }

    public void setRefund(OBWriteInternationalRefundResponse1DataRefund refund) {
        this.refund = refund;
    }

    public OBWriteInternationalStandingOrderResponse6Data charges(List<OBWriteDomesticConsentResponse4DataCharges> charges) {
        this.charges = charges;
        return this;
    }

    public OBWriteInternationalStandingOrderResponse6Data addChargesItem(OBWriteDomesticConsentResponse4DataCharges chargesItem) {
        if (this.charges == null) {
            this.charges = new ArrayList<OBWriteDomesticConsentResponse4DataCharges>();
        }
        this.charges.add(chargesItem);
        return this;
    }

    /**
     * Get charges
     *
     * @return charges
     **/
    @Valid
    @ApiModelProperty(value = "")
    public List<OBWriteDomesticConsentResponse4DataCharges> getCharges() {
        return charges;
    }

    public void setCharges(List<OBWriteDomesticConsentResponse4DataCharges> charges) {
        this.charges = charges;
    }

    public OBWriteInternationalStandingOrderResponse6Data initiation(OBWriteInternationalStandingOrder4DataInitiation initiation) {
        this.initiation = initiation;
        return this;
    }

    /**
     * Get initiation
     *
     * @return initiation
     **/
    @NotNull
    @Valid
    @ApiModelProperty(required = true, value = "")
    public OBWriteInternationalStandingOrder4DataInitiation getInitiation() {
        return initiation;
    }

    public void setInitiation(OBWriteInternationalStandingOrder4DataInitiation initiation) {
        this.initiation = initiation;
    }

    public OBWriteInternationalStandingOrderResponse6Data multiAuthorisation(OBWriteDomesticResponse4DataMultiAuthorisation multiAuthorisation) {
        this.multiAuthorisation = multiAuthorisation;
        return this;
    }

    /**
     * Get multiAuthorisation
     *
     * @return multiAuthorisation
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBWriteDomesticResponse4DataMultiAuthorisation getMultiAuthorisation() {
        return multiAuthorisation;
    }

    public void setMultiAuthorisation(OBWriteDomesticResponse4DataMultiAuthorisation multiAuthorisation) {
        this.multiAuthorisation = multiAuthorisation;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OBWriteInternationalStandingOrderResponse6Data obWriteInternationalStandingOrderResponse6Data = (OBWriteInternationalStandingOrderResponse6Data) o;
        return Objects.equals(this.internationalStandingOrderId, obWriteInternationalStandingOrderResponse6Data.internationalStandingOrderId) &&
                Objects.equals(this.consentId, obWriteInternationalStandingOrderResponse6Data.consentId) &&
                Objects.equals(this.creationDateTime, obWriteInternationalStandingOrderResponse6Data.creationDateTime) &&
                Objects.equals(this.status, obWriteInternationalStandingOrderResponse6Data.status) &&
                Objects.equals(this.statusUpdateDateTime, obWriteInternationalStandingOrderResponse6Data.statusUpdateDateTime) &&
                Objects.equals(this.refund, obWriteInternationalStandingOrderResponse6Data.refund) &&
                Objects.equals(this.charges, obWriteInternationalStandingOrderResponse6Data.charges) &&
                Objects.equals(this.initiation, obWriteInternationalStandingOrderResponse6Data.initiation) &&
                Objects.equals(this.multiAuthorisation, obWriteInternationalStandingOrderResponse6Data.multiAuthorisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internationalStandingOrderId, consentId, creationDateTime, status, statusUpdateDateTime, refund, charges, initiation, multiAuthorisation);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBWriteInternationalStandingOrderResponse6Data {\n");

        sb.append("    internationalStandingOrderId: ").append(toIndentedString(internationalStandingOrderId)).append("\n");
        sb.append("    consentId: ").append(toIndentedString(consentId)).append("\n");
        sb.append("    creationDateTime: ").append(toIndentedString(creationDateTime)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    statusUpdateDateTime: ").append(toIndentedString(statusUpdateDateTime)).append("\n");
        sb.append("    refund: ").append(toIndentedString(refund)).append("\n");
        sb.append("    charges: ").append(toIndentedString(charges)).append("\n");
        sb.append("    initiation: ").append(toIndentedString(initiation)).append("\n");
        sb.append("    multiAuthorisation: ").append(toIndentedString(multiAuthorisation)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
