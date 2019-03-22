package com.software.ttsl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddContactData {
    @SerializedName("image")
    @Expose
    private Byte[] image;
    @SerializedName("contactId")
    @Expose
    private long contactId;
    @SerializedName("contactOwner")
    @Expose
    private String contactOwner;
    @SerializedName("leadSource")
    @Expose
    private String leadSource;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("homePhone")
    @Expose
    private String homePhone;
    @SerializedName("otherPhone")
    @Expose
    private String otherPhone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("accountId")
    @Expose
    private long accountId;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("assistant")
    @Expose
    private String assistant;
    @SerializedName("asstPhone")
    @Expose
    private String asstPhone;
    @SerializedName("reportsTo")
    @Expose
    private String reportsTo;
    @SerializedName("emailOptOut")
    @Expose
    private Boolean emailOptOut;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("modifyBy")
    @Expose
    private String modifyBy;
    @SerializedName("modifyDate")
    @Expose
    private long modifyDate;
    @SerializedName("skypeId")
    @Expose
    private String skypeId;
    @SerializedName("secondaryEmail")
    @Expose
    private String secondaryEmail;
    @SerializedName("lastActivityTime")
    @Expose
    private String lastActivityTime;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("mailingAddressStreet")
    @Expose
    private String mailingAddressStreet;
    @SerializedName("mailingAddressCity")
    @Expose
    private String mailingAddressCity;
    @SerializedName("mailingAddressState")
    @Expose
    private String mailingAddressState;
    @SerializedName("mailingAddressZip")
    @Expose
    private String mailingAddressZip;
    @SerializedName("mailingAddressCountry")
    @Expose
    private String mailingAddressCountry;
    @SerializedName("otherAddressStreet")
    @Expose
    private String otherAddressStreet;
    @SerializedName("otherAddressCity")
    @Expose
    private String otherAddressCity;
    @SerializedName("otherAddressState")
    @Expose
    private String otherAddressState;
    @SerializedName("otherAddressZip")
    @Expose
    private String otherAddressZip;
    @SerializedName("otherAddressCountry")
    @Expose
    private String otherAddressCountry;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("salutation")
    @Expose
    private String salutation;

    private boolean isSync = false;


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    private boolean isHeaderShow = false;

    public boolean isHeaderShow() {
        return isHeaderShow;
    }

    public void setHeaderShow(boolean headerShow) {
        isHeaderShow = headerShow;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getContactOwner() {
        return contactOwner;
    }

    public void setContactOwner(String contactOwner) {
        this.contactOwner = contactOwner;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getAsstPhone() {
        return asstPhone;
    }

    public void setAsstPhone(String asstPhone) {
        this.asstPhone = asstPhone;
    }

    public String getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(String reportsTo) {
        this.reportsTo = reportsTo;
    }

    public Boolean getEmailOptOut() {
        return emailOptOut;
    }

    public void setEmailOptOut(Boolean emailOptOut) {
        this.emailOptOut = emailOptOut;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public String getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(String lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getMailingAddressStreet() {
        return mailingAddressStreet;
    }

    public void setMailingAddressStreet(String mailingAddressStreet) {
        this.mailingAddressStreet = mailingAddressStreet;
    }

    public String getMailingAddressCity() {
        return mailingAddressCity;
    }

    public void setMailingAddressCity(String mailingAddressCity) {
        this.mailingAddressCity = mailingAddressCity;
    }

    public String getMailingAddressState() {
        return mailingAddressState;
    }

    public void setMailingAddressState(String mailingAddressState) {
        this.mailingAddressState = mailingAddressState;
    }

    public String getMailingAddressZip() {
        return mailingAddressZip;
    }

    public void setMailingAddressZip(String mailingAddressZip) {
        this.mailingAddressZip = mailingAddressZip;
    }

    public String getMailingAddressCountry() {
        return mailingAddressCountry;
    }

    public void setMailingAddressCountry(String mailingAddressCountry) {
        this.mailingAddressCountry = mailingAddressCountry;
    }

    public String getOtherAddressStreet() {
        return otherAddressStreet;
    }

    public void setOtherAddressStreet(String otherAddressStreet) {
        this.otherAddressStreet = otherAddressStreet;
    }

    public String getOtherAddressCity() {
        return otherAddressCity;
    }

    public void setOtherAddressCity(String otherAddressCity) {
        this.otherAddressCity = otherAddressCity;
    }

    public String getOtherAddressState() {
        return otherAddressState;
    }

    public void setOtherAddressState(String otherAddressState) {
        this.otherAddressState = otherAddressState;
    }

    public String getOtherAddressZip() {
        return otherAddressZip;
    }

    public void setOtherAddressZip(String otherAddressZip) {
        this.otherAddressZip = otherAddressZip;
    }

    public String getOtherAddressCountry() {
        return otherAddressCountry;
    }

    public void setOtherAddressCountry(String otherAddressCountry) {
        this.otherAddressCountry = otherAddressCountry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
