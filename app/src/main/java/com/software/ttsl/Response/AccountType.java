package com.software.ttsl.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccountType {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("accTypeList")
    @Expose
    private String accTypeList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccTypeList() {
        return accTypeList;
    }

    public void setAccTypeList(String accTypeList) {
        this.accTypeList = accTypeList;
    }

}
