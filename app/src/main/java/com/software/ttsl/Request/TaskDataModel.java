package com.software.ttsl.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskDataModel {


    @SerializedName("taskId")
    @Expose
    private long taskId;
    @SerializedName("taskOwner")
    @Expose
    private String taskOwner;
    @SerializedName("subject")
    @Expose
    private String taskSubject;
    @SerializedName("priority")
    @Expose
    private String taskPriority;
    @SerializedName("status")
    @Expose
    private String taskStatus;
    @SerializedName("dueDate")
    @Expose
    private String taskDueDate;
    @SerializedName("contact")
    @Expose
    private String taskContact;
    @SerializedName("contactId")
    @Expose
    private long taskContactId;
    @SerializedName("account")
    @Expose
    private String taskAccount;
    @SerializedName("accountId")
    @Expose
    private long taskAccountId;
    @SerializedName("description")
    @Expose
    private String taskDescription;
    @SerializedName("taskCreatedBy")
    @Expose
    private String taskCreatedBy;
    @SerializedName("taskModifyBy")
    @Expose
    private String taskModifyBy;
    @SerializedName("taskCreatedTime")
    @Expose
    private long taskCreatedTime;
    @SerializedName("taskModifyTime")
    @Expose
    private long taskModifyTime;
    private boolean taskCompleted =false;
    @SerializedName("leadId")
    @Expose
    private long leadId;
    @SerializedName("leadName")
    @Expose
    private String leadName;





    private boolean isSync = false;


    public boolean isSync() {
        return isSync;
    }

    public void setSync(boolean sync) {
        isSync = sync;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    private boolean headerShow = false;

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public boolean isHeaderShow() {
        return headerShow;
    }

    public void setHeaderShow(boolean headerShow) {
        this.headerShow = headerShow;
    }

    public String getTaskSubject() {
        return taskSubject;
    }

    public void setTaskSubject(String taskSubject) {
        this.taskSubject = taskSubject;
    }

    public String getTaskCreatedBy() {
        return taskCreatedBy;
    }

    public void setTaskCreatedBy(String taskCreatedBy) {
        this.taskCreatedBy = taskCreatedBy;
    }

    public String getTaskModifyBy() {
        return taskModifyBy;
    }

    public void setTaskModifyBy(String taskModifyBy) {
        this.taskModifyBy = taskModifyBy;
    }

    public long getTaskCreatedTime() {
        return taskCreatedTime;
    }

    public void setTaskCreatedTime(long taskCreatedTime) {
        this.taskCreatedTime = taskCreatedTime;
    }

    public long getTaskModifyTime() {
        return taskModifyTime;
    }

    public void setTaskModifyTime(long taskModifyTime) {
        this.taskModifyTime = taskModifyTime;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(String taskOwner) {
        this.taskOwner = taskOwner;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskContact() {
        return taskContact;
    }

    public void setTaskContact(String taskContact) {
        this.taskContact = taskContact;
    }

    public long getTaskContactId() {
        return taskContactId;
    }

    public void setTaskContactId(long taskContactId) {
        this.taskContactId = taskContactId;
    }

    public String getTaskAccount() {
        return taskAccount;
    }

    public void setTaskAccount(String taskAccount) {
        this.taskAccount = taskAccount;
    }

    public long getTaskAccountId() {
        return taskAccountId;
    }

    public void setTaskAccountId(long taskAccountId) {
        this.taskAccountId = taskAccountId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
