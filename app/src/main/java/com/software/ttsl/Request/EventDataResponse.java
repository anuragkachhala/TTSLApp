package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDataResponse {
    @SerializedName("EventId")
    @Expose
    private Integer eventId;
    @SerializedName("EventCode")
    @Expose
    private String eventCode;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("EventLocation")
    @Expose
    private String eventLocation;
    @SerializedName("CityId")
    @Expose
    private Integer cityId;
    @SerializedName("EventIsEventAllDay")
    @Expose
    private Integer eventIsEventAllDay;
    @SerializedName("EventFrom")
    @Expose
    private String eventFrom;
    @SerializedName("EventTo")
    @Expose
    private String eventTo;
    @SerializedName("EventFromTime")
    @Expose
    private String eventFromTime;
    @SerializedName("EventFromToTime")
    @Expose
    private String eventFromToTime;
    @SerializedName("EventHost")
    @Expose
    private String eventHost;
    @SerializedName("EventFromParticipants")
    @Expose
    private Integer eventFromParticipants;
    @SerializedName("AccountId")
    @Expose
    private Integer accountId;
    @SerializedName("ContactId")
    @Expose
    private Integer contactId;
    @SerializedName("LeadId")
    @Expose
    private Integer leadId;
    @SerializedName("EventDescription")
    @Expose
    private String eventDescription;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getEventIsEventAllDay() {
        return eventIsEventAllDay;
    }

    public void setEventIsEventAllDay(Integer eventIsEventAllDay) {
        this.eventIsEventAllDay = eventIsEventAllDay;
    }

    public String getEventFrom() {
        return eventFrom;
    }

    public void setEventFrom(String eventFrom) {
        this.eventFrom = eventFrom;
    }

    public String getEventTo() {
        return eventTo;
    }

    public void setEventTo(String eventTo) {
        this.eventTo = eventTo;
    }

    public String getEventFromTime() {
        return eventFromTime;
    }

    public void setEventFromTime(String eventFromTime) {
        this.eventFromTime = eventFromTime;
    }

    public String getEventFromToTime() {
        return eventFromToTime;
    }

    public void setEventFromToTime(String eventFromToTime) {
        this.eventFromToTime = eventFromToTime;
    }

    public String getEventHost() {
        return eventHost;
    }

    public void setEventHost(String eventHost) {
        this.eventHost = eventHost;
    }

    public Integer getEventFromParticipants() {
        return eventFromParticipants;
    }

    public void setEventFromParticipants(Integer eventFromParticipants) {
        this.eventFromParticipants = eventFromParticipants;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Integer getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditTs() {
        return auditTs;
    }

    public void setAuditTs(String auditTs) {
        this.auditTs = auditTs;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
