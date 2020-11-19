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
 * OpenAPI spec version: v3.1.5
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
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Provides further details on an entry in the report.
 */
@ApiModel(description = "Provides further details on an entry in the report.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-19T14:46:29.699+01:00")
public class OBTransaction6Detail {
    @JsonProperty("AccountId")
    private String accountId = null;

    @JsonProperty("TransactionId")
    private String transactionId = null;

    @JsonProperty("TransactionReference")
    private String transactionReference = null;

    @JsonProperty("StatementReference")
    private List<String> statementReference = null;

    @JsonProperty("CreditDebitIndicator")
    private OBCreditDebitCode1 creditDebitIndicator = null;

    @JsonProperty("Status")
    private OBEntryStatus1Code status = null;

    @JsonProperty("TransactionMutability")
    private OBTransactionMutability1Code transactionMutability = null;

    @JsonProperty("BookingDateTime")
    private DateTime bookingDateTime = null;

    @JsonProperty("ValueDateTime")
    private DateTime valueDateTime = null;

    @JsonProperty("TransactionInformation")
    private String transactionInformation = null;

    @JsonProperty("AddressLine")
    private String addressLine = null;

    @JsonProperty("Amount")
    private OBActiveOrHistoricCurrencyAndAmount9 amount = null;

    @JsonProperty("ChargeAmount")
    private OBActiveOrHistoricCurrencyAndAmount10 chargeAmount = null;

    @JsonProperty("CurrencyExchange")
    private OBCurrencyExchange5 currencyExchange = null;

    @JsonProperty("BankTransactionCode")
    private OBBankTransactionCodeStructure1 bankTransactionCode = null;

    @JsonProperty("ProprietaryBankTransactionCode")
    private ProprietaryBankTransactionCodeStructure1 proprietaryBankTransactionCode = null;

    @JsonProperty("Balance")
    private OBTransactionCashBalance balance = null;

    @JsonProperty("MerchantDetails")
    private OBMerchantDetails1 merchantDetails = null;

    @JsonProperty("CreditorAgent")
    private OBBranchAndFinancialInstitutionIdentification61 creditorAgent = null;

    @JsonProperty("CreditorAccount")
    private OBCashAccount60 creditorAccount = null;

    @JsonProperty("DebtorAgent")
    private OBBranchAndFinancialInstitutionIdentification62 debtorAgent = null;

    @JsonProperty("DebtorAccount")
    private OBCashAccount61 debtorAccount = null;

    @JsonProperty("CardInstrument")
    private OBTransactionCardInstrument1 cardInstrument = null;

    @JsonProperty("SupplementaryData")
    private OBSupplementaryData1 supplementaryData = null;

    public OBTransaction6Detail accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    /**
     * Get accountId
     *
     * @return accountId
     **/
    @NotNull
    @Size(min = 1, max = 40)
    @ApiModelProperty(required = true, value = "")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public OBTransaction6Detail transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * Get transactionId
     *
     * @return transactionId
     **/
    @Size(min = 1, max = 210)
    @ApiModelProperty(value = "")
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public OBTransaction6Detail transactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
        return this;
    }

    /**
     * Get transactionReference
     *
     * @return transactionReference
     **/
    @Size(min = 1, max = 210)
    @ApiModelProperty(value = "")
    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public OBTransaction6Detail statementReference(List<String> statementReference) {
        this.statementReference = statementReference;
        return this;
    }

    public OBTransaction6Detail addStatementReferenceItem(String statementReferenceItem) {
        if (this.statementReference == null) {
            this.statementReference = new ArrayList<String>();
        }
        this.statementReference.add(statementReferenceItem);
        return this;
    }

    /**
     * Get statementReference
     *
     * @return statementReference
     **/
    @ApiModelProperty(value = "")
    public List<String> getStatementReference() {
        return statementReference;
    }

    public void setStatementReference(List<String> statementReference) {
        this.statementReference = statementReference;
    }

    public OBTransaction6Detail creditDebitIndicator(OBCreditDebitCode1 creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
        return this;
    }

    /**
     * Get creditDebitIndicator
     *
     * @return creditDebitIndicator
     **/
    @NotNull
    @Valid
    @ApiModelProperty(required = true, value = "")
    public OBCreditDebitCode1 getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    public void setCreditDebitIndicator(OBCreditDebitCode1 creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }

    public OBTransaction6Detail status(OBEntryStatus1Code status) {
        this.status = status;
        return this;
    }

    /**
     * Get status
     *
     * @return status
     **/
    @NotNull
    @Valid
    @ApiModelProperty(required = true, value = "")
    public OBEntryStatus1Code getStatus() {
        return status;
    }

    public void setStatus(OBEntryStatus1Code status) {
        this.status = status;
    }

    public OBTransaction6Detail transactionMutability(OBTransactionMutability1Code transactionMutability) {
        this.transactionMutability = transactionMutability;
        return this;
    }

    /**
     * Get transactionMutability
     *
     * @return transactionMutability
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBTransactionMutability1Code getTransactionMutability() {
        return transactionMutability;
    }

    public void setTransactionMutability(OBTransactionMutability1Code transactionMutability) {
        this.transactionMutability = transactionMutability;
    }

    public OBTransaction6Detail bookingDateTime(DateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
        return this;
    }

    /**
     * Get bookingDateTime
     *
     * @return bookingDateTime
     **/
    @NotNull
    @ApiModelProperty(required = true, value = "")
    public DateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(DateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public OBTransaction6Detail valueDateTime(DateTime valueDateTime) {
        this.valueDateTime = valueDateTime;
        return this;
    }

    /**
     * Get valueDateTime
     *
     * @return valueDateTime
     **/
    @ApiModelProperty(value = "")
    public DateTime getValueDateTime() {
        return valueDateTime;
    }

    public void setValueDateTime(DateTime valueDateTime) {
        this.valueDateTime = valueDateTime;
    }

    public OBTransaction6Detail transactionInformation(String transactionInformation) {
        this.transactionInformation = transactionInformation;
        return this;
    }

    /**
     * Get transactionInformation
     *
     * @return transactionInformation
     **/
    @Size(min = 1, max = 500)
    @ApiModelProperty(value = "")
    public String getTransactionInformation() {
        return transactionInformation;
    }

    public void setTransactionInformation(String transactionInformation) {
        this.transactionInformation = transactionInformation;
    }

    public OBTransaction6Detail addressLine(String addressLine) {
        this.addressLine = addressLine;
        return this;
    }

    /**
     * Get addressLine
     *
     * @return addressLine
     **/
    @Size(min = 1, max = 70)
    @ApiModelProperty(value = "")
    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public OBTransaction6Detail amount(OBActiveOrHistoricCurrencyAndAmount9 amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Get amount
     *
     * @return amount
     **/
    @NotNull
    @Valid
    @ApiModelProperty(required = true, value = "")
    public OBActiveOrHistoricCurrencyAndAmount9 getAmount() {
        return amount;
    }

    public void setAmount(OBActiveOrHistoricCurrencyAndAmount9 amount) {
        this.amount = amount;
    }

    public OBTransaction6Detail chargeAmount(OBActiveOrHistoricCurrencyAndAmount10 chargeAmount) {
        this.chargeAmount = chargeAmount;
        return this;
    }

    /**
     * Get chargeAmount
     *
     * @return chargeAmount
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBActiveOrHistoricCurrencyAndAmount10 getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(OBActiveOrHistoricCurrencyAndAmount10 chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public OBTransaction6Detail currencyExchange(OBCurrencyExchange5 currencyExchange) {
        this.currencyExchange = currencyExchange;
        return this;
    }

    /**
     * Get currencyExchange
     *
     * @return currencyExchange
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBCurrencyExchange5 getCurrencyExchange() {
        return currencyExchange;
    }

    public void setCurrencyExchange(OBCurrencyExchange5 currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    public OBTransaction6Detail bankTransactionCode(OBBankTransactionCodeStructure1 bankTransactionCode) {
        this.bankTransactionCode = bankTransactionCode;
        return this;
    }

    /**
     * Get bankTransactionCode
     *
     * @return bankTransactionCode
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBBankTransactionCodeStructure1 getBankTransactionCode() {
        return bankTransactionCode;
    }

    public void setBankTransactionCode(OBBankTransactionCodeStructure1 bankTransactionCode) {
        this.bankTransactionCode = bankTransactionCode;
    }

    public OBTransaction6Detail proprietaryBankTransactionCode(ProprietaryBankTransactionCodeStructure1 proprietaryBankTransactionCode) {
        this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
        return this;
    }

    /**
     * Get proprietaryBankTransactionCode
     *
     * @return proprietaryBankTransactionCode
     **/
    @Valid
    @ApiModelProperty(value = "")
    public ProprietaryBankTransactionCodeStructure1 getProprietaryBankTransactionCode() {
        return proprietaryBankTransactionCode;
    }

    public void setProprietaryBankTransactionCode(ProprietaryBankTransactionCodeStructure1 proprietaryBankTransactionCode) {
        this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
    }

    public OBTransaction6Detail balance(OBTransactionCashBalance balance) {
        this.balance = balance;
        return this;
    }

    /**
     * Get balance
     *
     * @return balance
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBTransactionCashBalance getBalance() {
        return balance;
    }

    public void setBalance(OBTransactionCashBalance balance) {
        this.balance = balance;
    }

    public OBTransaction6Detail merchantDetails(OBMerchantDetails1 merchantDetails) {
        this.merchantDetails = merchantDetails;
        return this;
    }

    /**
     * Get merchantDetails
     *
     * @return merchantDetails
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBMerchantDetails1 getMerchantDetails() {
        return merchantDetails;
    }

    public void setMerchantDetails(OBMerchantDetails1 merchantDetails) {
        this.merchantDetails = merchantDetails;
    }

    public OBTransaction6Detail creditorAgent(OBBranchAndFinancialInstitutionIdentification61 creditorAgent) {
        this.creditorAgent = creditorAgent;
        return this;
    }

    /**
     * Get creditorAgent
     *
     * @return creditorAgent
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBBranchAndFinancialInstitutionIdentification61 getCreditorAgent() {
        return creditorAgent;
    }

    public void setCreditorAgent(OBBranchAndFinancialInstitutionIdentification61 creditorAgent) {
        this.creditorAgent = creditorAgent;
    }

    public OBTransaction6Detail creditorAccount(OBCashAccount60 creditorAccount) {
        this.creditorAccount = creditorAccount;
        return this;
    }

    /**
     * Get creditorAccount
     *
     * @return creditorAccount
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBCashAccount60 getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(OBCashAccount60 creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public OBTransaction6Detail debtorAgent(OBBranchAndFinancialInstitutionIdentification62 debtorAgent) {
        this.debtorAgent = debtorAgent;
        return this;
    }

    /**
     * Get debtorAgent
     *
     * @return debtorAgent
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBBranchAndFinancialInstitutionIdentification62 getDebtorAgent() {
        return debtorAgent;
    }

    public void setDebtorAgent(OBBranchAndFinancialInstitutionIdentification62 debtorAgent) {
        this.debtorAgent = debtorAgent;
    }

    public OBTransaction6Detail debtorAccount(OBCashAccount61 debtorAccount) {
        this.debtorAccount = debtorAccount;
        return this;
    }

    /**
     * Get debtorAccount
     *
     * @return debtorAccount
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBCashAccount61 getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(OBCashAccount61 debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

    public OBTransaction6Detail cardInstrument(OBTransactionCardInstrument1 cardInstrument) {
        this.cardInstrument = cardInstrument;
        return this;
    }

    /**
     * Get cardInstrument
     *
     * @return cardInstrument
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBTransactionCardInstrument1 getCardInstrument() {
        return cardInstrument;
    }

    public void setCardInstrument(OBTransactionCardInstrument1 cardInstrument) {
        this.cardInstrument = cardInstrument;
    }

    public OBTransaction6Detail supplementaryData(OBSupplementaryData1 supplementaryData) {
        this.supplementaryData = supplementaryData;
        return this;
    }

    /**
     * Get supplementaryData
     *
     * @return supplementaryData
     **/
    @Valid
    @ApiModelProperty(value = "")
    public OBSupplementaryData1 getSupplementaryData() {
        return supplementaryData;
    }

    public void setSupplementaryData(OBSupplementaryData1 supplementaryData) {
        this.supplementaryData = supplementaryData;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OBTransaction6Detail obTransaction6Detail = (OBTransaction6Detail) o;
        return Objects.equals(this.accountId, obTransaction6Detail.accountId) &&
                Objects.equals(this.transactionId, obTransaction6Detail.transactionId) &&
                Objects.equals(this.transactionReference, obTransaction6Detail.transactionReference) &&
                Objects.equals(this.statementReference, obTransaction6Detail.statementReference) &&
                Objects.equals(this.creditDebitIndicator, obTransaction6Detail.creditDebitIndicator) &&
                Objects.equals(this.status, obTransaction6Detail.status) &&
                Objects.equals(this.transactionMutability, obTransaction6Detail.transactionMutability) &&
                Objects.equals(this.bookingDateTime, obTransaction6Detail.bookingDateTime) &&
                Objects.equals(this.valueDateTime, obTransaction6Detail.valueDateTime) &&
                Objects.equals(this.transactionInformation, obTransaction6Detail.transactionInformation) &&
                Objects.equals(this.addressLine, obTransaction6Detail.addressLine) &&
                Objects.equals(this.amount, obTransaction6Detail.amount) &&
                Objects.equals(this.chargeAmount, obTransaction6Detail.chargeAmount) &&
                Objects.equals(this.currencyExchange, obTransaction6Detail.currencyExchange) &&
                Objects.equals(this.bankTransactionCode, obTransaction6Detail.bankTransactionCode) &&
                Objects.equals(this.proprietaryBankTransactionCode, obTransaction6Detail.proprietaryBankTransactionCode) &&
                Objects.equals(this.balance, obTransaction6Detail.balance) &&
                Objects.equals(this.merchantDetails, obTransaction6Detail.merchantDetails) &&
                Objects.equals(this.creditorAgent, obTransaction6Detail.creditorAgent) &&
                Objects.equals(this.creditorAccount, obTransaction6Detail.creditorAccount) &&
                Objects.equals(this.debtorAgent, obTransaction6Detail.debtorAgent) &&
                Objects.equals(this.debtorAccount, obTransaction6Detail.debtorAccount) &&
                Objects.equals(this.cardInstrument, obTransaction6Detail.cardInstrument) &&
                Objects.equals(this.supplementaryData, obTransaction6Detail.supplementaryData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, transactionId, transactionReference, statementReference, creditDebitIndicator, status, transactionMutability, bookingDateTime, valueDateTime, transactionInformation, addressLine, amount, chargeAmount, currencyExchange, bankTransactionCode, proprietaryBankTransactionCode, balance, merchantDetails, creditorAgent, creditorAccount, debtorAgent, debtorAccount, cardInstrument, supplementaryData);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OBTransaction6Detail {\n");

        sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
        sb.append("    transactionId: ").append(toIndentedString(transactionId)).append("\n");
        sb.append("    transactionReference: ").append(toIndentedString(transactionReference)).append("\n");
        sb.append("    statementReference: ").append(toIndentedString(statementReference)).append("\n");
        sb.append("    creditDebitIndicator: ").append(toIndentedString(creditDebitIndicator)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    transactionMutability: ").append(toIndentedString(transactionMutability)).append("\n");
        sb.append("    bookingDateTime: ").append(toIndentedString(bookingDateTime)).append("\n");
        sb.append("    valueDateTime: ").append(toIndentedString(valueDateTime)).append("\n");
        sb.append("    transactionInformation: ").append(toIndentedString(transactionInformation)).append("\n");
        sb.append("    addressLine: ").append(toIndentedString(addressLine)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    chargeAmount: ").append(toIndentedString(chargeAmount)).append("\n");
        sb.append("    currencyExchange: ").append(toIndentedString(currencyExchange)).append("\n");
        sb.append("    bankTransactionCode: ").append(toIndentedString(bankTransactionCode)).append("\n");
        sb.append("    proprietaryBankTransactionCode: ").append(toIndentedString(proprietaryBankTransactionCode)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    merchantDetails: ").append(toIndentedString(merchantDetails)).append("\n");
        sb.append("    creditorAgent: ").append(toIndentedString(creditorAgent)).append("\n");
        sb.append("    creditorAccount: ").append(toIndentedString(creditorAccount)).append("\n");
        sb.append("    debtorAgent: ").append(toIndentedString(debtorAgent)).append("\n");
        sb.append("    debtorAccount: ").append(toIndentedString(debtorAccount)).append("\n");
        sb.append("    cardInstrument: ").append(toIndentedString(cardInstrument)).append("\n");
        sb.append("    supplementaryData: ").append(toIndentedString(supplementaryData)).append("\n");
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

