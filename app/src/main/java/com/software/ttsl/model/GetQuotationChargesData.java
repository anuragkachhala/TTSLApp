package com.software.ttsl.model;

public class GetQuotationChargesData {

    private String chargesType;
    private int chargesAmount;

    public GetQuotationChargesData() {

    }

    public GetQuotationChargesData(String chargesType, int chargesAmount) {
        this.chargesType = chargesType;
        this.chargesAmount = chargesAmount;
    }

    public String getChargesType() {
        return chargesType;
    }

    public void setChargesType(String chargesType) {
        this.chargesType = chargesType;
    }

    public int getChargesAmount() {
        return chargesAmount;
    }

    public void setChargesAmount(int chargesAmount) {
        this.chargesAmount = chargesAmount;
    }
}
