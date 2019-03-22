package com.software.ttsl.model;

public class PendingInvoice {

    private String mounth;
    private String billNumber;
    private double amount;
    private String dueDate;
    private Boolean isChecked;
    private String month;
    private int monthId;

    public PendingInvoice(){

    }

    public PendingInvoice(String mounth, String billNumber, double amount, String dueDate, Boolean isChecked, String month, int monthId) {

        this.mounth = mounth;
        this.billNumber = billNumber;
        this.amount = amount;
        this.dueDate = dueDate;
        this.isChecked = isChecked;
        this.month = month;
        this.monthId = monthId;
    }

    public String getMounth() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth = mounth;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getMonthId() {
        return monthId;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }
}
