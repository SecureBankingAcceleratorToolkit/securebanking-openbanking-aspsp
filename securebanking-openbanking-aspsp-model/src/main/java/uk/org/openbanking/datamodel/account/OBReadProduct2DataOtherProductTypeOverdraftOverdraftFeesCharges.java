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
 * Account and Transaction API Specification
 * Swagger for Account and Transaction API Specification
 *
 * OpenAPI spec version: v3.1.2-RC1
 * Contact: ServiceDesk@openbanking.org.uk
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package uk.org.openbanking.datamodel.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Overdraft fees and charges
 */
@ApiModel(description = "Overdraft fees and charges")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-06-13T15:59:01.633+01:00")
public class OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges {
    @JsonProperty("OverdraftFeeChargeCap")
    private List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap> overdraftFeeChargeCap = null;

    @JsonProperty("OverdraftFeeChargeDetail")
    private List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeDetail> overdraftFeeChargeDetail = new ArrayList<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeDetail>();

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges overdraftFeeChargeCap(List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap> overdraftFeeChargeCap) {
        this.overdraftFeeChargeCap = overdraftFeeChargeCap;
        return this;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges addOverdraftFeeChargeCapItem(OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap overdraftFeeChargeCapItem) {
        if (this.overdraftFeeChargeCap == null) {
            this.overdraftFeeChargeCap = new ArrayList<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap>();
        }
        this.overdraftFeeChargeCap.add(overdraftFeeChargeCapItem);
        return this;
    }

    /**
     * Get overdraftFeeChargeCap
     *
     * @return overdraftFeeChargeCap
     **/
    @Valid
    @ApiModelProperty(value = "")
    public List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap> getOverdraftFeeChargeCap() {
        return overdraftFeeChargeCap;
    }

    public void setOverdraftFeeChargeCap(List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeCap> overdraftFeeChargeCap) {
        this.overdraftFeeChargeCap = overdraftFeeChargeCap;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges overdraftFeeChargeDetail(List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeDetail> overdraftFeeChargeDetail) {
        this.overdraftFeeChargeDetail = overdraftFeeChargeDetail;
        return this;
    }

    public OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges addOverdraftFeeChargeDetailItem(OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeDetail overdraftFeeChargeDetailItem) {
        this.overdraftFeeChargeDetail.add(overdraftFeeChargeDetailItem);
        return this;
    }

    /**
     * Get overdraftFeeChargeDetail
     *
     * @return overdraftFeeChargeDetail
     **/
    @NotNull
    @Valid
    @Size(min = 1)
    @ApiModelProperty(required = true, value = "")
    public List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeDetail> getOverdraftFeeChargeDetail() {
        return overdraftFeeChargeDetail;
    }

    public void setOverdraftFeeChargeDetail(List<OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeeChargeDetail> overdraftFeeChargeDetail) {
        this.overdraftFeeChargeDetail = overdraftFeeChargeDetail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges obReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges = (OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges) o;
        return Objects.equals(this.overdraftFeeChargeCap, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges.overdraftFeeChargeCap) &&
                Objects.equals(this.overdraftFeeChargeDetail, obReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges.overdraftFeeChargeDetail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(overdraftFeeChargeCap, overdraftFeeChargeDetail);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBReadProduct2DataOtherProductTypeOverdraftOverdraftFeesCharges {\n");

        sb.append("    overdraftFeeChargeCap: ").append(toIndentedString(overdraftFeeChargeCap)).append("\n");
        sb.append("    overdraftFeeChargeDetail: ").append(toIndentedString(overdraftFeeChargeDetail)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}
