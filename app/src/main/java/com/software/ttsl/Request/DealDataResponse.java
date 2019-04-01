package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealDataResponse {
    @SerializedName("DealId")
    @Expose
    private Integer dealId;
    @SerializedName("DealCode")
    @Expose
    private String dealCode;
    @SerializedName("DealName")
    @Expose
    private String dealName;
    @SerializedName("OwnerId")
    @Expose
    private Integer ownerId;
    @SerializedName("DealAmount")
    @Expose
    private Integer dealAmount;
    @SerializedName("DealDate")
    @Expose
    private String dealDate;
    @SerializedName("AccountId")
    @Expose
    private Integer accountId;
    @SerializedName("DealStage")
    @Expose
    private String dealStage;
    @SerializedName("TypeId")
    @Expose
    private Integer typeId;
    @SerializedName("DealProbability")
    @Expose
    private Integer dealProbability;
    @SerializedName("DealNextStep")
    @Expose
    private String dealNextStep;
    @SerializedName("DealExpected")
    @Expose
    private String dealExpected;
    @SerializedName("DealLeadSourceId")
    @Expose
    private Integer dealLeadSourceId;
    @SerializedName("DealCampaignSource")
    @Expose
    private String dealCampaignSource;
    @SerializedName("ContactId")
    @Expose
    private Integer contactId;
    @SerializedName("DealDescription")
    @Expose
    private String dealDescription;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getDealCode() {
        return dealCode;
    }

    public void setDealCode(String dealCode) {
        this.dealCode = dealCode;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(Integer dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getDealStage() {
        return dealStage;
    }

    public void setDealStage(String dealStage) {
        this.dealStage = dealStage;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDealProbability() {
        return dealProbability;
    }

    public void setDealProbability(Integer dealProbability) {
        this.dealProbability = dealProbability;
    }

    public String getDealNextStep() {
        return dealNextStep;
    }

    public void setDealNextStep(String dealNextStep) {
        this.dealNextStep = dealNextStep;
    }

    public String getDealExpected() {
        return dealExpected;
    }

    public void setDealExpected(String dealExpected) {
        this.dealExpected = dealExpected;
    }

    public Integer getDealLeadSourceId() {
        return dealLeadSourceId;
    }

    public void setDealLeadSourceId(Integer dealLeadSourceId) {
        this.dealLeadSourceId = dealLeadSourceId;
    }

    public String getDealCampaignSource() {
        return dealCampaignSource;
    }

    public void setDealCampaignSource(String dealCampaignSource) {
        this.dealCampaignSource = dealCampaignSource;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getDealDescription() {
        return dealDescription;
    }

    public void setDealDescription(String dealDescription) {
        this.dealDescription = dealDescription;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditTs() {
        return auditTs;
    }

    public void setAuditTs(String auditTs) {
        this.auditTs = auditTs;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
