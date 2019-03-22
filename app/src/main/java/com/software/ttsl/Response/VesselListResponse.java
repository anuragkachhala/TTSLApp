package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VesselListResponse {

    @SerializedName("vesselId")
    @Expose
    private long vesselId;
    @SerializedName("vesselName")
    @Expose
    private String vesselName;

    public long getVesselId() {
        return vesselId;
    }

    public void setVesselId(long vesselId) {
        this.vesselId = vesselId;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

}
