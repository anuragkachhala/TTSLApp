package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerChallengeDataResponse {
    @SerializedName("CustomerChallengeId")
    @Expose
    private Integer customerChallengeId;
    @SerializedName("CustomerChallengeCode")
    @Expose
    private String customerChallengeCode;
    @SerializedName("CustomerChallengeName")
    @Expose
    private String customerChallengeName;
    @SerializedName("ContactId")
    @Expose
    private Integer contactId;
    @SerializedName("LeadId")
    @Expose
    private Integer leadId;
    @SerializedName("CustomerChallengeLagDate")
    @Expose
    private String customerChallengeLagDate;
    @SerializedName("CustomerChallengePriority")
    @Expose
    private Boolean customerChallengePriority;
    @SerializedName("CustomerChallengeOriginId")
    @Expose
    private Integer customerChallengeOriginId;
    @SerializedName("CustomerChallengeReason")
    @Expose
    private String customerChallengeReason;
    @SerializedName("CustomerChallengeDueDate")
    @Expose
    private String customerChallengeDueDate;
    @SerializedName("CustomerChallengeClosedOn")
    @Expose
    private String customerChallengeClosedOn;
    @SerializedName("CustomerChallengeIncharge")
    @Expose
    private String customerChallengeIncharge;
    @SerializedName("CustomerChallengeCcMail")
    @Expose
    private String customerChallengeCcMail;
    @SerializedName("StatusId")
    @Expose
    private Integer statusId;
    @SerializedName("CustomerChallengeSubject")
    @Expose
    private String customerChallengeSubject;
    @SerializedName("CustomerChallengeNote")
    @Expose
    private String customerChallengeNote;
    @SerializedName("CustomerChallengeDiscription")
    @Expose
    private String customerChallengeDiscription;
    @SerializedName("CustomerChallengeInternalNote")
    @Expose
    private String customerChallengeInternalNote;
    @SerializedName("CustomerChallengeFeedBack")
    @Expose
    private String customerChallengeFeedBack;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getCustomerChallengeId() {
        return customerChallengeId;
    }

    public void setCustomerChallengeId(Integer customerChallengeId) {
        this.customerChallengeId = customerChallengeId;
    }

    public String getCustomerChallengeCode() {
        return customerChallengeCode;
    }

    public void setCustomerChallengeCode(String customerChallengeCode) {
        this.customerChallengeCode = customerChallengeCode;
    }

    public String getCustomerChallengeName() {
        return customerChallengeName;
    }

    public void setCustomerChallengeName(String customerChallengeName) {
        this.customerChallengeName = customerChallengeName;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getCustomerChallengeLagDate() {
        return customerChallengeLagDate;
    }

    public void setCustomerChallengeLagDate(String customerChallengeLagDate) {
        this.customerChallengeLagDate = customerChallengeLagDate;
    }

    public Boolean getCustomerChallengePriority() {
        return customerChallengePriority;
    }

    public void setCustomerChallengePriority(Boolean customerChallengePriority) {
        this.customerChallengePriority = customerChallengePriority;
    }

    public Integer getCustomerChallengeOriginId() {
        return customerChallengeOriginId;
    }

    public void setCustomerChallengeOriginId(Integer customerChallengeOriginId) {
        this.customerChallengeOriginId = customerChallengeOriginId;
    }

    public String getCustomerChallengeReason() {
        return customerChallengeReason;
    }

    public void setCustomerChallengeReason(String customerChallengeReason) {
        this.customerChallengeReason = customerChallengeReason;
    }

    public String getCustomerChallengeDueDate() {
        return customerChallengeDueDate;
    }

    public void setCustomerChallengeDueDate(String customerChallengeDueDate) {
        this.customerChallengeDueDate = customerChallengeDueDate;
    }

    public String getCustomerChallengeClosedOn() {
        return customerChallengeClosedOn;
    }

    public void setCustomerChallengeClosedOn(String customerChallengeClosedOn) {
        this.customerChallengeClosedOn = customerChallengeClosedOn;
    }

    public String getCustomerChallengeIncharge() {
        return customerChallengeIncharge;
    }

    public void setCustomerChallengeIncharge(String customerChallengeIncharge) {
        this.customerChallengeIncharge = customerChallengeIncharge;
    }

    public String getCustomerChallengeCcMail() {
        return customerChallengeCcMail;
    }

    public void setCustomerChallengeCcMail(String customerChallengeCcMail) {
        this.customerChallengeCcMail = customerChallengeCcMail;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getCustomerChallengeSubject() {
        return customerChallengeSubject;
    }

    public void setCustomerChallengeSubject(String customerChallengeSubject) {
        this.customerChallengeSubject = customerChallengeSubject;
    }

    public String getCustomerChallengeNote() {
        return customerChallengeNote;
    }

    public void setCustomerChallengeNote(String customerChallengeNote) {
        this.customerChallengeNote = customerChallengeNote;
    }

    public String getCustomerChallengeDiscription() {
        return customerChallengeDiscription;
    }

    public void setCustomerChallengeDiscription(String customerChallengeDiscription) {
        this.customerChallengeDiscription = customerChallengeDiscription;
    }

    public String getCustomerChallengeInternalNote() {
        return customerChallengeInternalNote;
    }

    public void setCustomerChallengeInternalNote(String customerChallengeInternalNote) {
        this.customerChallengeInternalNote = customerChallengeInternalNote;
    }

    public String getCustomerChallengeFeedBack() {
        return customerChallengeFeedBack;
    }

    public void setCustomerChallengeFeedBack(String customerChallengeFeedBack) {
        this.customerChallengeFeedBack = customerChallengeFeedBack;
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
