package com.software.ttsl.NewModule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AgentInfoResponse {
    @SerializedName("port")
    @Expose
    private String port;
    @SerializedName("agentAddress")
    @Expose
    private String agentAddress;
    @SerializedName("lclDetail")
    @Expose
    private String lclDetail;
    @SerializedName("lalDetail1")
    @Expose
    private String lalDetail1;
    @SerializedName("hbl")
    @Expose
    private String hbl;
    @SerializedName("notes")
    @Expose
    private String notes;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAgentAddress() {
        return agentAddress;
    }

    public void setAgentAddress(String agentAddress) {
        this.agentAddress = agentAddress;
    }

    public String getLclDetail() {
        return lclDetail;
    }

    public void setLclDetail(String lclDetail) {
        this.lclDetail = lclDetail;
    }

    public String getLalDetail1() {
        return lalDetail1;
    }

    public void setLalDetail1(String lalDetail1) {
        this.lalDetail1 = lalDetail1;
    }

    public String getHbl() {
        return hbl;
    }

    public void setHbl(String hbl) {
        this.hbl = hbl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
