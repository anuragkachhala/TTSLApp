package com.software.ttsl.model;

import java.util.List;

public class LeadListSortingData {
    List<AddLeadData> addLeadDataList;
    List<OrderByData> orderByDataList;

    public List<AddLeadData> getAddLeadDataList() {
        return addLeadDataList;
    }

    public void setAddLeadDataList(List<AddLeadData> addLeadDataList) {
        this.addLeadDataList = addLeadDataList;
    }

    public List<OrderByData> getOrderByDataList() {
        return orderByDataList;
    }

    public void setOrderByDataList(List<OrderByData> orderByDataList) {
        this.orderByDataList = orderByDataList;
    }
}
