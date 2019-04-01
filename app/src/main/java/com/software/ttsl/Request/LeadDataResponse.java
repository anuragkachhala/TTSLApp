package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeadDataResponse {
    @SerializedName("LeadId")
    @Expose
    private Integer leadId;
    @SerializedName("LeadCode")
    @Expose
    private String leadCode;
    @SerializedName("LeadName")
    @Expose
    private String leadName;
    @SerializedName("OwnerId")
    @Expose
    private Integer ownerId;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("LeadFirstName")
    @Expose
    private String leadFirstName;
    @SerializedName("LeadLastName")
    @Expose
    private String leadLastName;
    @SerializedName("LeadCompanyName")
    @Expose
    private String leadCompanyName;
    @SerializedName("LeadEmail")
    @Expose
    private String leadEmail;
    @SerializedName("LeadPhone")
    @Expose
    private String leadPhone;
    @SerializedName("LeadFax")
    @Expose
    private String leadFax;
    @SerializedName("LeadMobile")
    @Expose
    private String leadMobile;
    @SerializedName("LeadWebsite")
    @Expose
    private String leadWebsite;
    @SerializedName("LeadSource")
    @Expose
    private String leadSource;
    @SerializedName("StatusId")
    @Expose
    private Integer statusId;
    @SerializedName("IndustryId")
    @Expose
    private Integer industryId;
    @SerializedName("LeadNoEmployees")
    @Expose
    private Integer leadNoEmployees;
    @SerializedName("LeadRevenue")
    @Expose
    private Integer leadRevenue;
    @SerializedName("LeadRating")
    @Expose
    private Integer leadRating;
    @SerializedName("LeadEmailOpt")
    @Expose
    private String leadEmailOpt;
    @SerializedName("LeadSkypeId")
    @Expose
    private String leadSkypeId;
    @SerializedName("LeadDiscription")
    @Expose
    private String leadDiscription;
    @SerializedName("LeadSolution")
    @Expose
    private String leadSolution;
    @SerializedName("LeadSecondaryMail")
    @Expose
    private String leadSecondaryMail;
    @SerializedName("LeadTwitterId")
    @Expose
    private String leadTwitterId;
    @SerializedName("LeadStreet")
    @Expose
    private String leadStreet;
    @SerializedName("CityId")
    @Expose
    private Integer cityId;
    @SerializedName("StateId")
    @Expose
    private Integer stateId;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;
    @SerializedName("LeadZipCode")
    @Expose
    private String leadZipCode;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getLeadCode() {
        return leadCode;
    }

    public void setLeadCode(String leadCode) {
        this.leadCode = leadCode;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLeadFirstName() {
        return leadFirstName;
    }

    public void setLeadFirstName(String leadFirstName) {
        this.leadFirstName = leadFirstName;
    }

    public String getLeadLastName() {
        return leadLastName;
    }

    public void setLeadLastName(String leadLastName) {
        this.leadLastName = leadLastName;
    }

    public String getLeadCompanyName() {
        return leadCompanyName;
    }

    public void setLeadCompanyName(String leadCompanyName) {
        this.leadCompanyName = leadCompanyName;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getLeadFax() {
        return leadFax;
    }

    public void setLeadFax(String leadFax) {
        this.leadFax = leadFax;
    }

    public String getLeadMobile() {
        return leadMobile;
    }

    public void setLeadMobile(String leadMobile) {
        this.leadMobile = leadMobile;
    }

    public String getLeadWebsite() {
        return leadWebsite;
    }

    public void setLeadWebsite(String leadWebsite) {
        this.leadWebsite = leadWebsite;
    }

    public String getLeadSource() {
        return leadSource;
    }

    public void setLeadSource(String leadSource) {
        this.leadSource = leadSource;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getLeadNoEmployees() {
        return leadNoEmployees;
    }

    public void setLeadNoEmployees(Integer leadNoEmployees) {
        this.leadNoEmployees = leadNoEmployees;
    }

    public Integer getLeadRevenue() {
        return leadRevenue;
    }

    public void setLeadRevenue(Integer leadRevenue) {
        this.leadRevenue = leadRevenue;
    }

    public Integer getLeadRating() {
        return leadRating;
    }

    public void setLeadRating(Integer leadRating) {
        this.leadRating = leadRating;
    }

    public String getLeadEmailOpt() {
        return leadEmailOpt;
    }

    public void setLeadEmailOpt(String leadEmailOpt) {
        this.leadEmailOpt = leadEmailOpt;
    }

    public String getLeadSkypeId() {
        return leadSkypeId;
    }

    public void setLeadSkypeId(String leadSkypeId) {
        this.leadSkypeId = leadSkypeId;
    }

    public String getLeadDiscription() {
        return leadDiscription;
    }

    public void setLeadDiscription(String leadDiscription) {
        this.leadDiscription = leadDiscription;
    }

    public String getLeadSolution() {
        return leadSolution;
    }

    public void setLeadSolution(String leadSolution) {
        this.leadSolution = leadSolution;
    }

    public String getLeadSecondaryMail() {
        return leadSecondaryMail;
    }

    public void setLeadSecondaryMail(String leadSecondaryMail) {
        this.leadSecondaryMail = leadSecondaryMail;
    }

    public String getLeadTwitterId() {
        return leadTwitterId;
    }

    public void setLeadTwitterId(String leadTwitterId) {
        this.leadTwitterId = leadTwitterId;
    }

    public String getLeadStreet() {
        return leadStreet;
    }

    public void setLeadStreet(String leadStreet) {
        this.leadStreet = leadStreet;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getLeadZipCode() {
        return leadZipCode;
    }

    public void setLeadZipCode(String leadZipCode) {
        this.leadZipCode = leadZipCode;
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
