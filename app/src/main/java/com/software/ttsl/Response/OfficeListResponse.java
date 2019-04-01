package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfficeListResponse {
    @SerializedName("OfficeId")
    @Expose
    private Integer officeId;
    @SerializedName("OfficeName")
    @Expose
    private String officeName;
    @SerializedName("logo")
    @Expose
    private Object logo;

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }
}
