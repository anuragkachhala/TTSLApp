package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParticipantsDataModel {
    @SerializedName("ParticipantId")
    @Expose
    private Integer participantId;
    @SerializedName("ParticipantCode")
    @Expose
    private String participantCode;
    @SerializedName("ParticipantName")
    @Expose
    private String participantName;
    @SerializedName("EventId")
    @Expose
    private Integer eventId;
    @SerializedName("ParticipantConstant")
    @Expose
    private String participantConstant;
    @SerializedName("ParticipantEmail")
    @Expose
    private String participantEmail;
    @SerializedName("AuditUserId")
    @Expose
    private Integer auditUserId;
    @SerializedName("AuditTs")
    @Expose
    private String auditTs;
    @SerializedName("Deleted")
    @Expose
    private Boolean deleted;

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public String getParticipantCode() {
        return participantCode;
    }

    public void setParticipantCode(String participantCode) {
        this.participantCode = participantCode;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getParticipantConstant() {
        return participantConstant;
    }

    public void setParticipantConstant(String participantConstant) {
        this.participantConstant = participantConstant;
    }

    public String getParticipantEmail() {
        return participantEmail;
    }

    public void setParticipantEmail(String participantEmail) {
        this.participantEmail = participantEmail;
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
