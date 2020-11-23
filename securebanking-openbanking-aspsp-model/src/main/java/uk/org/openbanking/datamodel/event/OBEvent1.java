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
 * Event Notification API Specification - TPP Endpoints
 * Swagger for Event Notification API Specification - TPP Endpoints
 *
 * OpenAPI spec version: v3.0.0
 * Contact: ServiceDesk@openbanking.org.uk
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package uk.org.openbanking.datamodel.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * Events.
 */
@ApiModel(description = "Events.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-12-03T11:34:40.772Z")
public class OBEvent1 {
  @JsonProperty("urn:uk:org:openbanking:events:resource-update")
  private OBEventResourceUpdate1 urnukorgopenbankingeventsresourceUpdate = null;

  public OBEvent1 urnukorgopenbankingeventsresourceUpdate(OBEventResourceUpdate1 urnukorgopenbankingeventsresourceUpdate) {
    this.urnukorgopenbankingeventsresourceUpdate = urnukorgopenbankingeventsresourceUpdate;
    return this;
  }

   /**
   * Get urnukorgopenbankingeventsresourceUpdate
   * @return urnukorgopenbankingeventsresourceUpdate
  **/
  @ApiModelProperty(required = true, value = "")
  public OBEventResourceUpdate1 getUrnukorgopenbankingeventsresourceUpdate() {
    return urnukorgopenbankingeventsresourceUpdate;
  }

  public void setUrnukorgopenbankingeventsresourceUpdate(OBEventResourceUpdate1 urnukorgopenbankingeventsresourceUpdate) {
    this.urnukorgopenbankingeventsresourceUpdate = urnukorgopenbankingeventsresourceUpdate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OBEvent1 obEvent1 = (OBEvent1) o;
    return Objects.equals(this.urnukorgopenbankingeventsresourceUpdate, obEvent1.urnukorgopenbankingeventsresourceUpdate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(urnukorgopenbankingeventsresourceUpdate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OBEvent1 {\n");

    sb.append("    urnukorgopenbankingeventsresourceUpdate: ").append(toIndentedString(urnukorgopenbankingeventsresourceUpdate)).append("\n");
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
