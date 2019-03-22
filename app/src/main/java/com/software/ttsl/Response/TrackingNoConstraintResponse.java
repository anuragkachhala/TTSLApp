package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackingNoConstraintResponse {

    @SerializedName("trackType")
    @Expose
    private String trackType;
    @SerializedName("minLength")
    @Expose
    private long minLength;
    @SerializedName("maxLength")
    @Expose
    private long maxLength;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("startWith")
    @Expose
    private String startWith;
    @SerializedName("startWithLength")
    @Expose
    private String startWithLength;


    @SerializedName("trackingId")
    @Expose
    private long trackingId;


    public long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(long trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackType() {
        return trackType;
    }

    public void setTrackType(String trackType) {
        this.trackType = trackType;
    }

    public long getMinLength() {
        return minLength;
    }

    public void setMinLength(long minLength) {
        this.minLength = minLength;
    }

    public long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(long maxLength) {
        this.maxLength = maxLength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    public String getStartWithLength() {
        return startWithLength;
    }

    public void setStartWithLength(String startWithLength) {
        this.startWithLength = startWithLength;
    }

}
