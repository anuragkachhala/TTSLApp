package com.software.ttsl.model;

public class DeviceContactData {
    private String name ;
    private String emailId;
    private Boolean isChecked;

    public DeviceContactData(String name, String emailId, Boolean isChecked) {
        this.name = name;
        this.emailId = emailId;
        this.isChecked = isChecked;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
