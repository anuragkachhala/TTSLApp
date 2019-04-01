package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactDataResponse {
    @SerializedName("ContactId")
    @Expose
    private Integer contactId;
    @SerializedName("ContactCode")
    @Expose
    private String contactCode;
    @SerializedName("ContactName")
    @Expose
    private String contactName;
    @SerializedName("LeadId")
    @Expose
    private Integer leadId;
    @SerializedName("OwnerId")
    @Expose
    private Integer ownerId;
    @SerializedName("ContactPhone")
    @Expose
    private String contactPhone;
    @SerializedName("ContactLeadSource")
    @Expose
    private String contactLeadSource;
    @SerializedName("ContactFirstName")
    @Expose
    private String contactFirstName;
    @SerializedName("ContactLastName")
    @Expose
    private String contactLastName;
    @SerializedName("ContactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("ContactTitle")
    @Expose
    private String contactTitle;
    @SerializedName("DepartmentId")
    @Expose
    private Integer departmentId;
    @SerializedName("ContactHomePhone")
    @Expose
    private String contactHomePhone;
    @SerializedName("AccountId")
    @Expose
    private Object accountId;
    @SerializedName("ContactOtherPhone")
    @Expose
    private String contactOtherPhone;
    @SerializedName("ContactFax")
    @Expose
    private String contactFax;
    @SerializedName("ContactMobile")
    @Expose
    private String contactMobile;
    @SerializedName("ContactDob")
    @Expose
    private String contactDob;
    @SerializedName("ContactAssistant")
    @Expose
    private String contactAssistant;
    @SerializedName("ContactAsstPhone")
    @Expose
    private String contactAsstPhone;
    @SerializedName("ContactReportTo")
    @Expose
    private String contactReportTo;
    @SerializedName("ContactSecondaryMail")
    @Expose
    private String contactSecondaryMail;
    @SerializedName("ContactSkypeId")
    @Expose
    private String contactSkypeId;
    @SerializedName("ContactSolution")
    @Expose
    private String contactSolution;
    @SerializedName("ContactTwitter")
    @Expose
    private String contactTwitter;
    @SerializedName("ContactMailingStreet")
    @Expose
    private String contactMailingStreet;
    @SerializedName("ContactMailingCityId")
    @Expose
    private Integer contactMailingCityId;
    @SerializedName("ContactMailingCode")
    @Expose
    private Integer contactMailingCode;
    @SerializedName("ContactMailingCountryId")
    @Expose
    private Integer contactMailingCountryId;
    @SerializedName("ContactOtherStateId")
    @Expose
    private Integer contactOtherStateId;
    @SerializedName("ContactMailingStateId")
    @Expose
    private Integer contactMailingStateId;
    @SerializedName("ContactOtherStreet")
    @Expose
    private String contactOtherStreet;
    @SerializedName("ContactOtherCode")
    @Expose
    private Integer contactOtherCode;
    @SerializedName("ContactOtherCityId")
    @Expose
    private Integer contactOtherCityId;
    @SerializedName("ContactOtherCountryId")
    @Expose
    private Integer contactOtherCountryId;
    @SerializedName("ContactDescription")
    @Expose
    private String contactDescription;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactLeadSource() {
        return contactLeadSource;
    }

    public void setContactLeadSource(String contactLeadSource) {
        this.contactLeadSource = contactLeadSource;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getContactHomePhone() {
        return contactHomePhone;
    }

    public void setContactHomePhone(String contactHomePhone) {
        this.contactHomePhone = contactHomePhone;
    }

    public Object getAccountId() {
        return accountId;
    }

    public void setAccountId(Object accountId) {
        this.accountId = accountId;
    }

    public String getContactOtherPhone() {
        return contactOtherPhone;
    }

    public void setContactOtherPhone(String contactOtherPhone) {
        this.contactOtherPhone = contactOtherPhone;
    }

    public String getContactFax() {
        return contactFax;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactDob() {
        return contactDob;
    }

    public void setContactDob(String contactDob) {
        this.contactDob = contactDob;
    }

    public String getContactAssistant() {
        return contactAssistant;
    }

    public void setContactAssistant(String contactAssistant) {
        this.contactAssistant = contactAssistant;
    }

    public String getContactAsstPhone() {
        return contactAsstPhone;
    }

    public void setContactAsstPhone(String contactAsstPhone) {
        this.contactAsstPhone = contactAsstPhone;
    }

    public String getContactReportTo() {
        return contactReportTo;
    }

    public void setContactReportTo(String contactReportTo) {
        this.contactReportTo = contactReportTo;
    }

    public String getContactSecondaryMail() {
        return contactSecondaryMail;
    }

    public void setContactSecondaryMail(String contactSecondaryMail) {
        this.contactSecondaryMail = contactSecondaryMail;
    }

    public String getContactSkypeId() {
        return contactSkypeId;
    }

    public void setContactSkypeId(String contactSkypeId) {
        this.contactSkypeId = contactSkypeId;
    }

    public String getContactSolution() {
        return contactSolution;
    }

    public void setContactSolution(String contactSolution) {
        this.contactSolution = contactSolution;
    }

    public String getContactTwitter() {
        return contactTwitter;
    }

    public void setContactTwitter(String contactTwitter) {
        this.contactTwitter = contactTwitter;
    }

    public String getContactMailingStreet() {
        return contactMailingStreet;
    }

    public void setContactMailingStreet(String contactMailingStreet) {
        this.contactMailingStreet = contactMailingStreet;
    }

    public Integer getContactMailingCityId() {
        return contactMailingCityId;
    }

    public void setContactMailingCityId(Integer contactMailingCityId) {
        this.contactMailingCityId = contactMailingCityId;
    }

    public Integer getContactMailingCode() {
        return contactMailingCode;
    }

    public void setContactMailingCode(Integer contactMailingCode) {
        this.contactMailingCode = contactMailingCode;
    }

    public Integer getContactMailingCountryId() {
        return contactMailingCountryId;
    }

    public void setContactMailingCountryId(Integer contactMailingCountryId) {
        this.contactMailingCountryId = contactMailingCountryId;
    }

    public Integer getContactOtherStateId() {
        return contactOtherStateId;
    }

    public void setContactOtherStateId(Integer contactOtherStateId) {
        this.contactOtherStateId = contactOtherStateId;
    }

    public Integer getContactMailingStateId() {
        return contactMailingStateId;
    }

    public void setContactMailingStateId(Integer contactMailingStateId) {
        this.contactMailingStateId = contactMailingStateId;
    }

    public String getContactOtherStreet() {
        return contactOtherStreet;
    }

    public void setContactOtherStreet(String contactOtherStreet) {
        this.contactOtherStreet = contactOtherStreet;
    }

    public Integer getContactOtherCode() {
        return contactOtherCode;
    }

    public void setContactOtherCode(Integer contactOtherCode) {
        this.contactOtherCode = contactOtherCode;
    }

    public Integer getContactOtherCityId() {
        return contactOtherCityId;
    }

    public void setContactOtherCityId(Integer contactOtherCityId) {
        this.contactOtherCityId = contactOtherCityId;
    }

    public Integer getContactOtherCountryId() {
        return contactOtherCountryId;
    }

    public void setContactOtherCountryId(Integer contactOtherCountryId) {
        this.contactOtherCountryId = contactOtherCountryId;
    }

    public String getContactDescription() {
        return contactDescription;
    }

    public void setContactDescription(String contactDescription) {
        this.contactDescription = contactDescription;
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
