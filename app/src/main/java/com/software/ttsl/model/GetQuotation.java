package com.software.ttsl.model;

public class GetQuotation {

    private boolean isChecked=false;
    private String quotationType;
    private String quotationDiscription;
    private String shippingModeId;
    private int backgroundColor;

    public GetQuotation(boolean isChecked, String quotationType, String quotationDiscription) {
        this.isChecked = isChecked;
        this.quotationType = quotationType;
        this.quotationDiscription = quotationDiscription;
    }

    public GetQuotation(String quotationType, String quotationDiscription) {
        this.quotationType = quotationType;
        this.quotationDiscription = quotationDiscription;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getShippingModeId() {
        return shippingModeId;
    }

    public void setShippingModeId(String shippingModeId) {
        this.shippingModeId = shippingModeId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    public String getQuotationDiscription() {
        return quotationDiscription;
    }

    public void setQuotationDiscription(String quotationDiscription) {
        this.quotationDiscription = quotationDiscription;
    }
}
