package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingInvoiceResponce {

    @SerializedName("bookingId")
    @Expose
    private String bookingId;
    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("localAmount")
    @Expose
    private String localAmount;
    @SerializedName("dueDate")
    @Expose
    private String dueDate;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(String localAmount) {
        this.localAmount = localAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
