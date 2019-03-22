package com.software.ttsl.Request;


import com.software.ttsl.Response.ImageResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeadDataModel {

    private transient ImageResponse imageResponse;

    public ImageResponse getImageResponse() {
        return imageResponse;
    }

    public void setImageResponse(ImageResponse imageResponse) {
        this.imageResponse = imageResponse;
    }

    @SerializedName("uploadedInputStream")
    @Expose
    private byte[] uploadedInputStream;
    @SerializedName("leadId")
    @Expose
    private long leadId;
    @SerializedName("leadOwner")
    @Expose
    private String leadOwner;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("leadSource")
    @Expose
    private String leadSource;
    @SerializedName("leadStatus")
    @Expose
    private String leadStatus;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("noOfEmployees")
    @Expose
    private String noOfEmployees;
    @SerializedName("annualRevenue")
    @Expose
    private String annualRevenue;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("emailOptOut")
    @Expose
    private Boolean emailOptOut;
    @SerializedName("skypeId")
    @Expose
    private String skypeId;
    @SerializedName("addressStreet")
    @Expose
    private String addressStreet;
    @SerializedName("addressCity")
    @Expose
    private String addressCity;
    @SerializedName("addressState")
    @Expose
    private String addressState;
    @SerializedName("addressZipCode")
    @Expose
    private String addressZipCode;
    @SerializedName("addressCounty")
    @Expose
    private String addressCounty;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("modifyDate")
    @Expose
    private long modifyDate;
    @SerializedName("salutation")
    @Expose
    private String salutation;
    @SerializedName("secondaryEmailId")
    @Expose
    private String secondaryEmailId;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("modifyBy")
    @Expose
    private String modifyBy;

    private boolean isSync = false;


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    private boolean isHeaderShow = false;


    public boolean isHeaderShow() {
        return isHeaderShow;
    }

    public void setHeaderShow(boolean headerShow) {
        isHeaderShow = headerShow;
    }

    public byte[] getUploadedInputStream() {
        return uploadedInputStream;
    }

    public void setUploadedInputStream(byte[] uploadedInputStream) {
        this.uploadedInputStream = uploadedInputStream;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getLeadOwner() {
        return leadOwner;
    }

    public void setLeadOwner(String leadOwner) {
        this.leadOwner = leadOwner;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(String noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    public String getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(String annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Boolean getEmailOptOut() {
        return emailOptOut;
    }

    public void setEmailOptOut(Boolean emailOptOut) {
        this.emailOptOut = emailOptOut;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
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

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(long modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getSecondaryEmailId() {
        return secondaryEmailId;
    }

    public void setSecondaryEmailId(String secondaryEmailId) {
        this.secondaryEmailId = secondaryEmailId;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

}

