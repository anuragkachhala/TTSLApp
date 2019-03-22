package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SailingScheduleRequest {
    @SerializedName("vesselId")
    @Expose
    private String vesselId;
    @SerializedName("podId")
    @Expose
    private String podId;
    @SerializedName("polId")
    @Expose
    private String polId;

    public String getVesselId() {
        return vesselId;
    }

    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId;
    }

    public String getPolId() {
        return polId;
    }

    public void setPolId(String polId) {
        this.polId = polId;
    }

}
