package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BudgetDataResponse {
    @SerializedName("BudgetId")
    @Expose
    private Integer budgetId;
    @SerializedName("BudgetCode")
    @Expose
    private String budgetCode;
    @SerializedName("BudgetName")
    @Expose
    private String budgetName;
    @SerializedName("BudgetYear")
    @Expose
    private String budgetYear;
    @SerializedName("BudgetSalesmanSectorId")
    @Expose
    private Integer budgetSalesmanSectorId;
    @SerializedName("BudgetSectorId")
    @Expose
    private Integer budgetSectorId;
    @SerializedName("BudgetCurrency")
    @Expose
    private Integer budgetCurrency;
    @SerializedName("BudgetRoe")
    @Expose
    private Integer budgetRoe;
    @SerializedName("BudgetNote")
    @Expose
    private String budgetNote;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public String getBudgetCode() {
        return budgetCode;
    }

    public void setBudgetCode(String budgetCode) {
        this.budgetCode = budgetCode;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public Integer getBudgetSalesmanSectorId() {
        return budgetSalesmanSectorId;
    }

    public void setBudgetSalesmanSectorId(Integer budgetSalesmanSectorId) {
        this.budgetSalesmanSectorId = budgetSalesmanSectorId;
    }

    public Integer getBudgetSectorId() {
        return budgetSectorId;
    }

    public void setBudgetSectorId(Integer budgetSectorId) {
        this.budgetSectorId = budgetSectorId;
    }

    public Integer getBudgetCurrency() {
        return budgetCurrency;
    }

    public void setBudgetCurrency(Integer budgetCurrency) {
        this.budgetCurrency = budgetCurrency;
    }

    public Integer getBudgetRoe() {
        return budgetRoe;
    }

    public void setBudgetRoe(Integer budgetRoe) {
        this.budgetRoe = budgetRoe;
    }

    public String getBudgetNote() {
        return budgetNote;
    }

    public void setBudgetNote(String budgetNote) {
        this.budgetNote = budgetNote;
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
