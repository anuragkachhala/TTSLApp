package com.software.ttsl.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ParticipantsData implements Parcelable{

    private long leadId;
    private String participantsName;
    private String participantsEmail;
    private boolean isChecked = false;
    private String constant;


    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public ParticipantsData(Parcel parcel) {
        leadId = parcel.readLong();
        participantsName =parcel.readString();
        participantsEmail =parcel.readString();
        constant = parcel.readString();


    }


    public ParticipantsData(String participantsName) {
        this.participantsName = participantsName;
    }

    public ParticipantsData() {
    }



    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public long getId() {
        return leadId;
    }

    public void setId(long leadId) {
        this.leadId = leadId;
    }

    public String getParticipantsName() {
        return participantsName;
    }

    public void setParticipantsName(String participantsName) {
        this.participantsName = participantsName;
    }

    public String getParticipantsEmail() {
        return participantsEmail;
    }

    public void setParticipantsEmail(String participantsEmail) {
        this.participantsEmail = participantsEmail;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(leadId);
        parcel.writeString(participantsName);
        parcel.writeString(participantsEmail);
        parcel.writeString(constant);

    }

    public static final Parcelable.Creator CREATOR  = new Parcelable.Creator(){

        @Override
        public ParticipantsData createFromParcel(Parcel parcel) {
            return new ParticipantsData(parcel);
        }

        @Override
        public ParticipantsData[] newArray(int i) {
            return new ParticipantsData[i];
        }
    };


    public int hashCode(){
        System.out.println("In hashcode");
        int hashcode = 0;
        hashcode = (int)this.getId();

        return hashcode;
    }

    public boolean equals(Object obj){
        System.out.println("In equals");
        if (obj instanceof ParticipantsData) {
            ParticipantsData pp = (ParticipantsData) obj;
            return (pp.getId()== this.getId());
        } else {
            return false;
        }
    }
}
