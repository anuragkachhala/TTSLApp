package com.software.ttsl.NewModule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SailScheduleResponse {
    @SerializedName("origin_Location")
    @Expose
    private String originLocation;
    @SerializedName("port_Of_Loading")
    @Expose
    private String portOfLoading;
    @SerializedName("terminal")
    @Expose
    private String terminal;
    @SerializedName("port_Of_Discharge")
    @Expose
    private String portOfDischarge;
    @SerializedName("sector")
    @Expose
    private String sector;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("vessel")
    @Expose
    private String vessel;
    @SerializedName("voy")
    @Expose
    private String voy;
    @SerializedName("from_Eta")
    @Expose
    private String fromEta;
    @SerializedName("from_Etd")
    @Expose
    private String fromEtd;
    @SerializedName("cfs_Cut_off")
    @Expose
    private String cfsCutOff;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("last_Stuffing")
    @Expose
    private String lastStuffing;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("transit")
    @Expose
    private String transit;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("m_Vessel")
    @Expose
    private String mVessel;
    @SerializedName("voyy")
    @Expose
    private String voyy;
    @SerializedName("to_Eta")
    @Expose
    private String toEta;
    @SerializedName("to_Etd")
    @Expose
    private String toEtd;
    @SerializedName("importExport")
    @Expose
    private String importExport;
    @SerializedName("schedule_id")
    @Expose
    private Integer scheduleId;

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getPortOfLoading() {
        return portOfLoading;
    }

    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

    public String getVoy() {
        return voy;
    }

    public void setVoy(String voy) {
        this.voy = voy;
    }

    public String getFromEta() {
        return fromEta;
    }

    public void setFromEta(String fromEta) {
        this.fromEta = fromEta;
    }

    public String getFromEtd() {
        return fromEtd;
    }

    public void setFromEtd(String fromEtd) {
        this.fromEtd = fromEtd;
    }

    public String getCfsCutOff() {
        return cfsCutOff;
    }

    public void setCfsCutOff(String cfsCutOff) {
        this.cfsCutOff = cfsCutOff;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLastStuffing() {
        return lastStuffing;
    }

    public void setLastStuffing(String lastStuffing) {
        this.lastStuffing = lastStuffing;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTransit() {
        return transit;
    }

    public void setTransit(String transit) {
        this.transit = transit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMVessel() {
        return mVessel;
    }

    public void setMVessel(String mVessel) {
        this.mVessel = mVessel;
    }

    public String getVoyy() {
        return voyy;
    }

    public void setVoyy(String voyy) {
        this.voyy = voyy;
    }

    public String getToEta() {
        return toEta;
    }

    public void setToEta(String toEta) {
        this.toEta = toEta;
    }

    public String getToEtd() {
        return toEtd;
    }

    public void setToEtd(String toEtd) {
        this.toEtd = toEtd;
    }

    public String getImportExport() {
        return importExport;
    }

    public void setImportExport(String importExport) {
        this.importExport = importExport;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
}
