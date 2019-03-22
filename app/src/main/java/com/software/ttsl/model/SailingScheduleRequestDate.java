package com.software.ttsl.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SailingScheduleRequestDate implements Parcelable {
    private int  loadingPortId;
    private int  dischargePortId;
    private Long VesselId;
    private String fromETD;
    private String toETD;
    private String fromETA;
    private String toETA;


    public SailingScheduleRequestDate(){

    }

    public SailingScheduleRequestDate(Parcel in) {
        loadingPortId = in.readInt();
        dischargePortId = in.readInt();
        if (in.readByte() == 0) {
            VesselId = null;
        } else {
            VesselId = in.readLong();
        }
        fromETD = in.readString();
        toETD = in.readString();
        fromETA = in.readString();
        toETA = in.readString();
    }

    public static final Creator<SailingScheduleRequestDate> CREATOR = new Creator<SailingScheduleRequestDate>() {
        @Override
        public SailingScheduleRequestDate createFromParcel(Parcel in) {
            return new SailingScheduleRequestDate(in);
        }

        @Override
        public SailingScheduleRequestDate[] newArray(int size) {
            return new SailingScheduleRequestDate[size];
        }
    };

    public int getLoadingPortId() {
        return loadingPortId;
    }

    public void setLoadingPortId(int loadingPortId) {
        this.loadingPortId = loadingPortId;
    }

    public int getDischargePortId() {
        return dischargePortId;
    }

    public void setDischargePortId(int dischargePortId) {
        this.dischargePortId = dischargePortId;
    }

    public Long getVesselId() {
        return VesselId;
    }

    public void setVesselId(Long vesselId) {
        VesselId = vesselId;
    }

    public String getFromETD() {
        return fromETD;
    }

    public void setFromETD(String fromETD) {
        this.fromETD = fromETD;
    }

    public String getToETD() {
        return toETD;
    }

    public void setToETD(String toETD) {
        this.toETD = toETD;
    }

    public String getFromETA() {
        return fromETA;
    }

    public void setFromETA(String fromETA) {
        this.fromETA = fromETA;
    }

    public String getToETA() {
        return toETA;
    }

    public void setToETA(String toETA) {
        this.toETA = toETA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(loadingPortId);
        parcel.writeInt(dischargePortId);
        if (VesselId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(VesselId);
        }
        parcel.writeString(fromETD);
        parcel.writeString(toETD);
        parcel.writeString(fromETA);
        parcel.writeString(toETA);
    }
}
