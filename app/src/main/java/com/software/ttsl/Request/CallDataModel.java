package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CallDataModel {
    @SerializedName("callId")
    @Expose
    private long callId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("callPurpose")
    @Expose
    private String callPurpose;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("callType")
    @Expose
    private String callType;
    @SerializedName("callStartTime")
    @Expose
    private long callStartTime;
    @SerializedName("callDuration")
    @Expose
    private long callDuration;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("callResult")
    @Expose
    private String callResult;
    @SerializedName("billable")
    @Expose
    private Boolean billable;
    @SerializedName("reminder")
    @Expose
    private String reminder;
    @SerializedName("notes")
    @Expose
    private String notes;
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
    @SerializedName("accountId")
    @Expose
    private long accountId;
    @SerializedName("contactId")
    @Expose
    private long contactId;
    @SerializedName("callStartDate")
    @Expose
    private long callStartDate;

    private long leadId;

    private String contactLeadName;

    private boolean isSync = false;


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getContactLeadName() {
        return contactLeadName;
    }

    public void setContactLeadName(String contactLeadName) {
        this.contactLeadName = contactLeadName;
    }

    private boolean isHeaderShow = false;

    public boolean isHeaderShow() {
        return isHeaderShow;
    }

    public void setHeaderShow(boolean headerShow) {
        isHeaderShow = headerShow;
    }

    public long getCallId() {
        return callId;
    }

    public void setCallId(long callId) {
        this.callId = callId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCallPurpose() {
        return callPurpose;
    }

    public void setCallPurpose(String callPurpose) {
        this.callPurpose = callPurpose;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public long getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(long callStartTime) {
        this.callStartTime = callStartTime;
    }

    public long getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(long callDuration) {
        this.callDuration = callDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallResult() {
        return callResult;
    }

    public void setCallResult(String callResult) {
        this.callResult = callResult;
    }

    public Boolean getBillable() {
        return billable;
    }

    public void setBillable(Boolean billable) {
        this.billable = billable;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public long getCallStartDate() {
        return callStartDate;
    }

    public void setCallStartDate(long callStartDate) {
        this.callStartDate = callStartDate;
    }

}
