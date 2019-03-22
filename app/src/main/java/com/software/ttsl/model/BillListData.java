package com.software.ttsl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillListData {

    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    @SerializedName("bookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("etd")
    @Expose
    private String etd;
    @SerializedName("eta")
    @Expose
    private String eta;

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BillListData) {
            BillListData billListData =(BillListData)obj;
            return (billListData.getBookingNumber().equals(this.getBookingNumber())&& billListData.getBookingNumber()==this.getBookingNumber());
        }
        return false;
    }


    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode =0;
        hashcode += getBookingNumber().hashCode();
        return hashcode;
    }



}
