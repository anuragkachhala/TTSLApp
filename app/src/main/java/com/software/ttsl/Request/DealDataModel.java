package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DealDataModel {

    @SerializedName("dealId")
    @Expose
    private long dealId;
    @SerializedName("dealOwner")
    @Expose
    private String dealOwner;
    @SerializedName("amount")
    @Expose
    private long amount;
    @SerializedName("dealName")
    @Expose
    private String dealName;
    @SerializedName("closingDate")
    @Expose
    private String closingDate;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("probability")
    @Expose
    private long probability;
    @SerializedName("nextStep")
    @Expose
    private String nextStep;
    @SerializedName("expectedRevenue")
    @Expose
    private String expectedRevenue;
    @SerializedName("leadSource")
    @Expose
    private String leadSource;
    @SerializedName("campaignSource")
    @Expose
    private String campaignSource;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("modifiedBy")
    @Expose
    private String modifiedBy;
    @SerializedName("createdTime")
    @Expose
    private long createdTime;
    @SerializedName("modifiedTime")
    @Expose
    private long modifiedTime;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("attachments")
    @Expose
    private String attachments;

    private boolean isHeaderShow = false;

    private boolean isSync = false;


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }


    public boolean isHeaderShow() {
        return isHeaderShow;
    }

    public void setHeaderShow(boolean headerShow) {
        isHeaderShow = headerShow;
    }


    private Long accountId;

    private Long contactId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }



    public Long getDealId() {
        return dealId;
    }

    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    public String getDealOwner() {
        return dealOwner;
    }

    public void setDealOwner(String dealOwner) {
        this.dealOwner = dealOwner;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getProbability() {
        return probability;
    }

    public void setProbability(Long probability) {
        this.probability = probability;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public String getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(String expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getCampaignSource() {
        return campaignSource;
    }

    public void setCampaignSource(String campaignSource) {
        this.campaignSource = campaignSource;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

}
