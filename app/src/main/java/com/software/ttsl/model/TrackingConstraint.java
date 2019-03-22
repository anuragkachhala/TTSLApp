package com.software.ttsl.model;

public class TrackingConstraint {

    private String trackingPlaceHolder;

    public String trackingNoType;
    public String trackingNoStartWith;
    public String trackingNoStartWithLength;
    public long trackingNoMaxLength;
    public long trackingNoMinLength;

    public TrackingConstraint(String trackingPlaceHolder, String trackingNoType, String trackingNoStartWith, String trackingNoStartWithLength, long trackingNoMaxLength, long trackingNoMinLength) {
        this.trackingPlaceHolder = trackingPlaceHolder;
        this.trackingNoType = trackingNoType;
        this.trackingNoStartWith = trackingNoStartWith;
        this.trackingNoStartWithLength = trackingNoStartWithLength;
        this.trackingNoMaxLength = trackingNoMaxLength;
        this.trackingNoMinLength = trackingNoMinLength;
    }

    public String getTrackingPlaceHolder() {
        return trackingPlaceHolder;
    }

    public void setTrackingPlaceHolder(String trackingPlaceHolder) {
        this.trackingPlaceHolder = trackingPlaceHolder;
    }

    public String getTrackingNoType() {
        return trackingNoType;
    }

    public void setTrackingNoType(String trackingNoType) {
        this.trackingNoType = trackingNoType;
    }

    public String getTrackingNoStartWith() {
        return trackingNoStartWith;
    }

    public void setTrackingNoStartWith(String trackingNoStartWith) {
        this.trackingNoStartWith = trackingNoStartWith;
    }

    public String getTrackingNoStartWithLength() {
        return trackingNoStartWithLength;
    }

    public void setTrackingNoStartWithLength(String trackingNoStartWithLength) {
        this.trackingNoStartWithLength = trackingNoStartWithLength;
    }

    public long getTrackingNoMaxLength() {
        return trackingNoMaxLength;
    }

    public void setTrackingNoMaxLength(long trackingNoMaxLength) {
        this.trackingNoMaxLength = trackingNoMaxLength;
    }

    public long getTrackingNoMinLength() {
        return trackingNoMinLength;
    }

    public void setTrackingNoMinLength(long trackingNoMinLength) {
        this.trackingNoMinLength = trackingNoMinLength;
    }
}
