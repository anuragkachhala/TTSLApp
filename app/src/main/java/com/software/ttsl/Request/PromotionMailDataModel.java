package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromotionMailDataModel {

    @SerializedName("primaryId")
    @Expose
    private Integer primaryId;
    @SerializedName("fromEmailId")
    @Expose
    private String fromEmailId;
    @SerializedName("commercialNvo")
    @Expose
    private Integer commercialNvo;
    @SerializedName("sendBy")
    @Expose
    private Integer sendBy;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("addAttachment")
    @Expose
    private String addAttachment;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("locId")
    @Expose
    private Integer locId;
    @SerializedName("fyId")
    @Expose
    private Integer fyId;
    @SerializedName("fyPrdId")
    @Expose
    private Integer fyPrdId;
    @SerializedName("createdBy")
    @Expose
    private Integer createdBy;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("modifyBy")
    @Expose
    private Integer modifyBy;
    @SerializedName("modifyDate")
    @Expose
    private String modifyDate;
    @SerializedName("modifyTime")
    @Expose
    private String modifyTime;
    @SerializedName("toEmailId")
    @Expose
    private String toEmailId;

    public Integer getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(Integer primaryId) {
        this.primaryId = primaryId;
    }

    public String getFromEmailId() {
        return fromEmailId;
    }

    public void setFromEmailId(String fromEmailId) {
        this.fromEmailId = fromEmailId;
    }

    public Integer getCommercialNvo() {
        return commercialNvo;
    }

    public void setCommercialNvo(Integer commercialNvo) {
        this.commercialNvo = commercialNvo;
    }

    public Integer getSendBy() {
        return sendBy;
    }

    public void setSendBy(Integer sendBy) {
        this.sendBy = sendBy;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getAddAttachment() {
        return addAttachment;
    }

    public void setAddAttachment(String addAttachment) {
        this.addAttachment = addAttachment;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

    public Integer getFyId() {
        return fyId;
    }

    public void setFyId(Integer fyId) {
        this.fyId = fyId;
    }

    public Integer getFyPrdId() {
        return fyPrdId;
    }

    public void setFyPrdId(Integer fyPrdId) {
        this.fyPrdId = fyPrdId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getToEmailId() {
        return toEmailId;
    }

    public void setToEmailId(String toEmailId) {
        this.toEmailId = toEmailId;
    }

}

