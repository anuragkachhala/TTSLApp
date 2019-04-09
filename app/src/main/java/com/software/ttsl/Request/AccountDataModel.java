package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountDataModel {

    @SerializedName("AccountId")
    @Expose
    private long id;
    @SerializedName("OwnerId")
    @Expose
    private long ownerId;
    @SerializedName("AccountCode")
    @Expose
    private String accountCode;

    @SerializedName("LeadId")
    @Expose
    private long leadId;

    @SerializedName("accountOwner")
    @Expose
    private String accountOwner;
    @SerializedName("rating")
    @Expose
    private String rating;

    @SerializedName("AccountRating")
    @Expose
    private  long accountId;
    @SerializedName("AccountName")
    @Expose
    private String accountName;
    @SerializedName("AccountPhone")
    @Expose
    private String phone;
    @SerializedName("AccountSite")
    @Expose
    private String accountSite;
    @SerializedName("AccountFax")
    @Expose
    private String fax;
    @SerializedName("parentAccount")
    @Expose
    private String parentAccount;

    @SerializedName("AccountWebsite")
    @Expose
    private String webSite;
    @SerializedName("accountNumber")
    @Expose
    private long accountNumber;
    @SerializedName("AccountTickerSymbol")
    @Expose
    private String tickerSymbol;
    @SerializedName("accountType")
    @Expose
    private String accountType;
    @SerializedName("AccountOwnership")
    @Expose
    private String ownerShip;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("employees")
    @Expose
    private String employees;
    @SerializedName("AccountRevenue")
    @Expose
    private String annualRevenue;
    @SerializedName("sicCode")
    @Expose
    private String sicCode;
    @SerializedName("AccountBillAddressStreet")
    @Expose
    private String billingAddressStreet;
    @SerializedName("billingAddressCity")
    @Expose
    private String billingAddressCity;
    @SerializedName("billingAddressState")
    @Expose
    private String billingAddressState;
    @SerializedName("AccountBillAddressCode")
    @Expose
    private String billingAddressCode;
    @SerializedName("billingAddressCountry")
    @Expose
    private String billingAddressCountry;
    @SerializedName("AccountShipAddressStreet")
    @Expose
    private String shippingAddressStreet;
    @SerializedName("shippingAddressCity")
    @Expose
    private String shippingAddressCity;
    @SerializedName("shippingAddressState")
    @Expose
    private String shippingAddressState;
    @SerializedName("AccountShipAddressCode")
    @Expose
    private String shippingAddressCode;
    @SerializedName("shippingAddressCountry")
    @Expose
    private String shippingAddressCountry;
    @SerializedName("AccountDiscription")
    @Expose
    private String description;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdTime")
    @Expose
    private long createdTime;
    @SerializedName("modifyBy")
    @Expose
    private String modifyBy;
    @SerializedName("modifyTime")
    @Expose
    private long modifyTime;
    @SerializedName("parentAccountId")
    @Expose
    private long parentAccountId;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountSite() {
        return accountSite;
    }

    public void setAccountSite(String accountSite) {
        this.accountSite = accountSite;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(String parentAccount) {
        this.parentAccount = parentAccount;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOwnerShip() {
        return ownerShip;
    }

    public void setOwnerShip(String ownerShip) {
        this.ownerShip = ownerShip;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public String getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(String annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public String getSicCode() {
        return sicCode;
    }

    public void setSicCode(String sicCode) {
        this.sicCode = sicCode;
    }

    public String getBillingAddressStreet() {
        return billingAddressStreet;
    }

    public void setBillingAddressStreet(String billingAddressStreet) {
        this.billingAddressStreet = billingAddressStreet;
    }

    public String getBillingAddressCity() {
        return billingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        this.billingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return billingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        this.billingAddressState = billingAddressState;
    }

    public String getBillingAddressCode() {
        return billingAddressCode;
    }

    public void setBillingAddressCode(String billingAddressCode) {
        this.billingAddressCode = billingAddressCode;
    }

    public String getBillingAddressCountry() {
        return billingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        this.billingAddressCountry = billingAddressCountry;
    }

    public String getShippingAddressStreet() {
        return shippingAddressStreet;
    }

    public void setShippingAddressStreet(String shippingAddressStreet) {
        this.shippingAddressStreet = shippingAddressStreet;
    }

    public String getShippingAddressCity() {
        return shippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        this.shippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressState() {
        return shippingAddressState;
    }

    public void setShippingAddressState(String shippingAddressState) {
        this.shippingAddressState = shippingAddressState;
    }

    public String getShippingAddressCode() {
        return shippingAddressCode;
    }

    public void setShippingAddressCode(String shippingAddressCode) {
        this.shippingAddressCode = shippingAddressCode;
    }

    public String getShippingAddressCountry() {
        return shippingAddressCountry;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        this.shippingAddressCountry = shippingAddressCountry;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(long parentAccountId) {
        this.parentAccountId = parentAccountId;
    }


}