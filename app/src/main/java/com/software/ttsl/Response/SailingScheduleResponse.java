package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SailingScheduleResponse {

    @SerializedName("etd")
    @Expose
    private String etd;
    @SerializedName("eta")
    @Expose
    private String eta;
    @SerializedName("vesselName")
    @Expose
    private String vesselName;
    @SerializedName("polCode")
    @Expose
    private String polCode;
    @SerializedName("podCode")
    @Expose
    private String podCode;

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

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getPolCode() {
        return polCode;
    }

    public void setPolCode(String polCode) {
        this.polCode = polCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

}

