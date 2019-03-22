package com.software.ttsl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SalesBudgetModel {

    @SerializedName("salesId")
    @Expose
    private long salesId;
    @SerializedName("budgetNumber")
    @Expose
    private String budgetNumber;
    @SerializedName("year")
    @Expose
    private String salesYear;
    @SerializedName("budgetType")
    @Expose
    private long budgetType;
    @SerializedName("salesmanId")
    @Expose
    private long salesmanId;
    @SerializedName("sectorId")
    @Expose
    private long sectorId;
    @SerializedName("currencyId")
    @Expose
    private long currencyId;
    @SerializedName("roe")
    @Expose
    private String salesROE;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("note")
    @Expose
    private String salesNote;
    @SerializedName("locId")
    @Expose
    private long locId;
    @SerializedName("fyId")
    @Expose
    private long fyId;
    @SerializedName("fyPrdId")
    @Expose
    private long fyPrdId;
    @SerializedName("createdBy")
    @Expose
    private String salesCreatedBY;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("createdTime")
    @Expose
    private long salesCreatedTime;
    @SerializedName("modifyBy")
    @Expose
    private String salesModifyBy;
    @SerializedName("modifyDate")
    @Expose
    private String modifyDate;
    @SerializedName("modifyTime")
    @Expose
    private long salesModifyTime;


    private transient boolean isSync = false;
    private transient boolean isHeader = false;

    public long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public long getSalesId() {
        return salesId;
    }

    public void setSalesId(long salesId) {
        this.salesId = salesId;
    }

    public String getSalesYear() {
        return salesYear;
    }

    public void setSalesYear(String salesYear) {
        this.salesYear = salesYear;
    }

    public long getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(long budgetType) {
        this.budgetType = budgetType;
    }


    public long getSectorId() {
        return sectorId;
    }

    public void setSectorId(long sectorId) {
        this.sectorId = sectorId;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public String getSalesROE() {
        return salesROE;
    }

    public void setSalesROE(String salesROE) {
        this.salesROE = salesROE;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSalesNote() {
        return salesNote;
    }

    public void setSalesNote(String salesNote) {
        this.salesNote = salesNote;
    }

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public long getFyId() {
        return fyId;
    }

    public void setFyId(long fyId) {
        this.fyId = fyId;
    }

    public long getFyPrdId() {
        return fyPrdId;
    }

    public void setFyPrdId(long fyPrdId) {
        this.fyPrdId = fyPrdId;
    }

    public String getSalesCreatedBY() {
        return salesCreatedBY;
    }

    public void setSalesCreatedBY(String salesCreatedBY) {
        this.salesCreatedBY = salesCreatedBY;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public long getSalesCreatedTime() {
        return salesCreatedTime;
    }

    public void setSalesCreatedTime(long salesCreatedTime) {
        this.salesCreatedTime = salesCreatedTime;
    }

    public String getSalesModifyBy() {
        return salesModifyBy;
    }

    public void setSalesModifyBy(String salesModifyBy) {
        this.salesModifyBy = salesModifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public long getSalesModifyTime() {
        return salesModifyTime;
    }

    public void setSalesModifyTime(long salesModifyTime) {
        this.salesModifyTime = salesModifyTime;
    }
}
