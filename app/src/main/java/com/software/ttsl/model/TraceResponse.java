package com.software.ttsl.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TraceResponse {

    @SerializedName("cfsReceivedId")
    @Expose
    private String cfsReceivedId;
    @SerializedName("slrNumber")
    @Expose
    private Object slrNumber;
    @SerializedName("bookingId")
    @Expose
    private String bookingId;
    @SerializedName("jobId")
    @Expose
    private Object jobId;
    @SerializedName("shippingBillNumber")
    @Expose
    private Object shippingBillNumber;
    @SerializedName("shippingBillDate")
    @Expose
    private Object shippingBillDate;
    @SerializedName("cartingDate")
    @Expose
    private String cartingDate;
    @SerializedName("customerClearanceDate")
    @Expose
    private String customerClearanceDate;
    @SerializedName("hdrCfsReceivedId")
    @Expose
    private Object hdrCfsReceivedId;
    @SerializedName("receiptNumber")
    @Expose
    private Object receiptNumber;
    @SerializedName("receiptDate")
    @Expose
    private Object receiptDate;
    @SerializedName("cfsPolCode")
    @Expose
    private String cfsPolCode;
    @SerializedName("cfsPodCode")
    @Expose
    private String cfsPodCode;
    @SerializedName("cfsFdcCode")
    @Expose
    private String cfsFdcCode;
    @SerializedName("status")
    @Expose

    private long status;
    @SerializedName("sobDate")
    @Expose
    private String sobDate;
    @SerializedName("containerNumber")
    @Expose
    private String containerNumber;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("bookingDate")
    @Expose
    private String bookingDate;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("bookingNumber")
    @Expose
    private String bookingNumber;
    @SerializedName("grWt")
    @Expose
    private String grWt;




    public String getSobDate() {
        return sobDate;
    }

    public void setSobDate(String sobDate) {
        this.sobDate = sobDate;
    }

    public String getContainerNumber() {
        return containerNumber;
    }

    public void setContainerNumber(String containerNumber) {
        this.containerNumber = containerNumber;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public String getGrWt() {
        return grWt;
    }

    public void setGrWt(String grWt) {
        this.grWt = grWt;
    }




    public String getCfsReceivedId() {
        return cfsReceivedId;
    }

    public void setCfsReceivedId(String cfsReceivedId) {
        this.cfsReceivedId = cfsReceivedId;
    }

    public Object getSlrNumber() {
        return slrNumber;
    }

    public void setSlrNumber(Object slrNumber) {
        this.slrNumber = slrNumber;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Object getJobId() {
        return jobId;
    }

    public void setJobId(Object jobId) {
        this.jobId = jobId;
    }

    public Object getShippingBillNumber() {
        return shippingBillNumber;
    }

    public void setShippingBillNumber(Object shippingBillNumber) {
        this.shippingBillNumber = shippingBillNumber;
    }

    public Object getShippingBillDate() {
        return shippingBillDate;
    }

    public void setShippingBillDate(Object shippingBillDate) {
        this.shippingBillDate = shippingBillDate;
    }

    public String getCartingDate() {
        return cartingDate;
    }

    public void setCartingDate(String  cartingDate) {
        this.cartingDate = cartingDate;
    }

    public String getCustomerClearanceDate() {
        return customerClearanceDate;
    }

    public void setCustomerClearanceDate(String customerClearanceDate) {
        this.customerClearanceDate = customerClearanceDate;
    }

    public Object getHdrCfsReceivedId() {
        return hdrCfsReceivedId;
    }

    public void setHdrCfsReceivedId(Object hdrCfsReceivedId) {
        this.hdrCfsReceivedId = hdrCfsReceivedId;
    }

    public Object getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(Object receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public Object getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Object receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getCfsPolCode() {
        return cfsPolCode;
    }

    public void setCfsPolCode(String cfsPolCode) {
        this.cfsPolCode = cfsPolCode;
    }

    public String getCfsPodCode() {
        return cfsPodCode;
    }

    public void setCfsPodCode(String cfsPodCode) {
        this.cfsPodCode = cfsPodCode;
    }

    public String getCfsFdcCode() {
        return cfsFdcCode;
    }

    public void setCfsFdcCode(String cfsFdcCode) {
        this.cfsFdcCode = cfsFdcCode;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

}
