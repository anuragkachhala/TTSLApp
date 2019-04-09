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
    @SerializedName("LeadId")
    @Expose
    private long leadId;
    @SerializedName("lead name1")
    @Expose
    private String leadOwner;

    @SerializedName("LeadName")
    @Expose
    private String leadName;

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    @SerializedName("LeadCompanyName")
    @Expose
    private String company;
    @SerializedName("LeadCode")
    @Expose
    private String leadCode;

    @SerializedName("OwnerId")
    @Expose
    private int ownerId;

    @SerializedName("LeadFirstName")
    @Expose
    private String firstName;
    @SerializedName("LeadLastName")
    @Expose
    private String lastName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("LeadEmail")
    @Expose
    private String email;
    @SerializedName("LeadPhone")
    @Expose
    private String phone;
    @SerializedName("LeadFax")
    @Expose
    private String fax;
    @SerializedName("LeadMobile")
    @Expose
    private String mobile;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("leadSource")
    @Expose
    private String leadSource;
    @SerializedName("StatusId")
    @Expose
    private int statusId;

    private String leadStatus;
    @SerializedName("IndustryId")
    @Expose
    private int industryId;


    private String industry;
    @SerializedName("LeadNoEmployees")
    @Expose
    private String noOfEmployees;
    @SerializedName("LeadRevenue")
    @Expose
    private String annualRevenue;
    @SerializedName("LeadRating")
    @Expose
    private int ratingId;
    private String rating;
    @SerializedName("LeadEmailOpt")
    @Expose
    private Boolean emailOptOut;
    @SerializedName("LeadSkypeId")
    @Expose
    private String skypeId;
    @SerializedName("LeadStreet")
    @Expose
    private String addressStreet;
    @SerializedName("CityId")
    @Expose
    private int cityId;
    private String addressCity;
    @SerializedName("StateId")
    @Expose
    private int stateId;
    private String addressState;
    @SerializedName("LeadZipCode")
    @Expose
    private String addressZipCode;
    @SerializedName("CountryId")
    @Expose
    private int countryID;
    private String addressCounty;
    @SerializedName("LeadDiscription")
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
    @SerializedName("LeadSolution")
    @Expose
    private String salutation;
    @SerializedName("LeadSecondaryMail")
    @Expose
    private String secondaryEmailId;
    @SerializedName("LeadTwitterId")
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


    public String getLeadCode() {
        return leadCode;
    }

    public void setLeadCode(String leadCode) {
        this.leadCode = leadCode;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }


}

