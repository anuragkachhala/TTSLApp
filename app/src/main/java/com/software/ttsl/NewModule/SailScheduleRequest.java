package com.software.ttsl.NewModule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SailScheduleRequest {
    @SerializedName("port_Of_Loading")
    @Expose
    private String portOfLoading;
    @SerializedName("port_Of_Discharge")
    @Expose
    private String portOfDischarge;

    public String getPortOfLoading() {
        return portOfLoading;
    }

    public void setPortOfLoading(String portOfLoading) {
        this.portOfLoading = portOfLoading;
    }

    public String getPortOfDischarge() {
        return portOfDischarge;
    }

    public void setPortOfDischarge(String portOfDischarge) {
        this.portOfDischarge = portOfDischarge;
    }

}
