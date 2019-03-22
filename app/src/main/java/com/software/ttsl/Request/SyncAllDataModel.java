package com.software.ttsl.Request;

import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.CustomerChallengeModel;
import com.software.ttsl.model.SalesBudgetModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SyncAllDataModel {

    @SerializedName("lead")
    @Expose
    private List<LeadDataModel> lead = new ArrayList<LeadDataModel>();

    @SerializedName("imageStore")
    @Expose
    private List<ImageResponse> imageResponses = new ArrayList<ImageResponse>();

    public List<ImageResponse> getImageResponses() {
        return imageResponses;
    }

    public void setImageResponses(List<ImageResponse> imageResponses) {
        this.imageResponses = imageResponses;
    }

    @SerializedName("contact")
    @Expose
    private List<AddContactData> contact = new  ArrayList<AddContactData>();

    @SerializedName("account")
    @Expose
    private List<AccountDataModel> account = new ArrayList<AccountDataModel>();

    @SerializedName("deal")
    @Expose
    private List<DealDataModel> deal = new ArrayList<DealDataModel>();

    @SerializedName("task")
    @Expose
    private List<TaskDataModel> task = new ArrayList<TaskDataModel>();

    @SerializedName("event")
    @Expose
    private List<EventDataModel> event = new ArrayList<EventDataModel>();

    @SerializedName("call")
    @Expose
    private List<CallDataModel> call = new ArrayList<CallDataModel>();

    @SerializedName("customerChallenge")
    @Expose
    private List<CustomerChallengeModel> customer = new ArrayList<CustomerChallengeModel>();

    @SerializedName("sales") //Need to verify the Serialized name
    @Expose
    private List<SalesBudgetModel> sales = new ArrayList<SalesBudgetModel>();

    public List<SalesBudgetModel> getSales() {
        return sales;
    }

    public void setSales(List<SalesBudgetModel> sales) {
        this.sales = sales;
    }

    public List<CustomerChallengeModel> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerChallengeModel> customer) {
        this.customer = customer;
    }

    public List<LeadDataModel> getLead() {
        return lead;
    }

    public void setLead(List<LeadDataModel> lead) {
        this.lead = lead;
    }

    public List<AddContactData> getContact() {
        return contact;
    }

    public void setContact(List<AddContactData> contact) {
        this.contact = contact;
    }

    public List<AccountDataModel> getAccount() {
        return account;
    }

    public void setAccount(List<AccountDataModel> account) {
        this.account = account;
    }

    public List<DealDataModel> getDeal() {
        return deal;
    }

    public void setDeal(List<DealDataModel> deal) {
        this.deal = deal;
    }

    public List<TaskDataModel> getTask() {
        return task;
    }

    public void setTask(List<TaskDataModel> task) {
        this.task = task;
    }

    public List<EventDataModel> getEvent() {
        return event;
    }

    public void setEvent(List<EventDataModel> event) {
        this.event = event;
    }

    public List<CallDataModel> getCall() {
        return call;
    }

    public void setCall(List<CallDataModel> call) {
        this.call = call;
    }

    }
