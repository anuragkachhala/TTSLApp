package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventDataModel {
    @SerializedName("eventId")
    @Expose
    private Long eventId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("allDay")
    @Expose
    private Boolean allDay;
    @SerializedName("fromDate")
    @Expose
    private Long fromDate;
    @SerializedName("toDate")
    @Expose
    private Long toDate;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("repeat")
    @Expose
    private String repeat;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("reminder")
    @Expose
    private String reminder;

    @SerializedName("createdBy")
    @Expose
    private String createdBy;

    @SerializedName("createdTime")
    @Expose
    private Long createdTime;

    @SerializedName("modifyBy")
    @Expose
    private String modifyBy;
    @SerializedName("modifyTime")
    @Expose
    private Long modifyTime;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("attachments")
    @Expose
    private String attachments;
    @SerializedName("noOfParticipants")
    @Expose
    private Long noOfParticipants;
    @SerializedName("participants")
    @Expose
    private List<Participant> participants = null;
    @SerializedName("contactId")
    @Expose
    private Long contactId;
    @SerializedName("accountId")
    @Expose
    private Long accountId;
    @SerializedName("fromTime")
    @Expose
    private Long fromTime;
    @SerializedName("toTime")
    @Expose
    private Long toTime;

    @SerializedName("leadId")
    @Expose
    private Long leadId;

    @SerializedName("leadName")
    @Expose
    private String leadName;


    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    private boolean isSync = false;


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Long getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Long getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Long getNoOfParticipants() {
        return noOfParticipants;
    }

    public void setNoOfParticipants(Long noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getFromTime() {
        return fromTime;
    }

    public void setFromTime(Long fromTime) {
        this.fromTime = fromTime;
    }

    public Long getToTime() {
        return toTime;
    }

    public void setToTime(Long toTime) {
        this.toTime = toTime;
    }
}
