package com.software.ttsl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerChallengeModel {


    /*   private long customerChallengeID;
    private String customerChallengeContact;
    private long customerClosedOn;
    private long customerContactId;
    private boolean isHeadershow = false;

*/

    @SerializedName("customerId")
    @Expose
    private Long customerId;
    @SerializedName("contactId")
    @Expose
    private Long contactId;
    @SerializedName("customerName")
    @Expose
    private String customer;
    @SerializedName("contactName")
    @Expose
    private String contactName;
    @SerializedName("leadId")
    @Expose
    private Long leadId;
    @SerializedName("leadName")
    @Expose
    private String leadName;
    @SerializedName("logDate")
    @Expose
    private Long customerLogDate;
    @SerializedName("priority")
    @Expose
    private String customerPriority;
    @SerializedName("origin")
    @Expose
    private String customerOrigin;
    @SerializedName("reason")
    @Expose
    private String customerReason;
    @SerializedName("dueDate")
    @Expose
    private Long customerDueDate;
    @SerializedName("closeddate")
    @Expose
    private Long closeddate;
    @SerializedName("incharge")
    @Expose
    private String customerInCharge;
    @SerializedName("ccMailId")
    @Expose
    private String ccMailId;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("notes")
    @Expose
    private String note;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("internalNote")
    @Expose
    private String internalNote;
    @SerializedName("customerFeedback")
    @Expose
    private String customerFeedback;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private Long createdDate;
    @SerializedName("createdTime")
    @Expose
    private Long createdTime;
    @SerializedName("modifyBy")
    @Expose
    private String modifyBy;
    @SerializedName("modifyDate")
    @Expose
    private Long modifyDate;
    @SerializedName("modifyTime")
    @Expose
    private Long modifyTime;
    private Boolean isHeadershow = false;
    private boolean isSync = false;

    public Boolean getHeadershow() {
        return isHeadershow;
    }

    public void setHeadershow(Boolean headershow) {
        isHeadershow = headershow;
    }



    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public Long getCustomerLogDate() {
        return customerLogDate;
    }

    public void setCustomerLogDate(Long customerLogDate) {
        this.customerLogDate = customerLogDate;
    }

    public String getCustomerPriority() {
        return customerPriority;
    }

    public void setCustomerPriority(String customerPriority) {
        this.customerPriority = customerPriority;
    }

    public String getCustomerOrigin() {
        return customerOrigin;
    }

    public void setCustomerOrigin(String customerOrigin) {
        this.customerOrigin = customerOrigin;
    }

    public String getCustomerReason() {
        return customerReason;
    }

    public void setCustomerReason(String customerReason) {
        this.customerReason = customerReason;
    }

    public Long getCustomerDueDate() {
        return customerDueDate;
    }

    public void setCustomerDueDate(Long customerDueDate) {
        this.customerDueDate = customerDueDate;
    }

    public Long getCloseddate() {
        return closeddate;
    }

    public void setCloseddate(Long closeddate) {
        this.closeddate = closeddate;
    }

    public String getCustomerInCharge() {
        return customerInCharge;
    }

    public void setCustomerInCharge(String customerInCharge) {
        this.customerInCharge = customerInCharge;
    }

    public String getCcMailId() {
        return ccMailId;
    }

    public void setCcMailId(String ccMailId) {
        this.ccMailId = ccMailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInternalNote() {
        return internalNote;
    }

    public void setInternalNote(String internalNote) {
        this.internalNote = internalNote;
    }

    public String getCustomerFeedback() {
        return customerFeedback;
    }

    public void setCustomerFeedback(String customerFeedback) {
        this.customerFeedback = customerFeedback;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }
}
