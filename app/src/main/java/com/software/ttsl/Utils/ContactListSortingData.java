package com.software.ttsl.Utils;

import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.AddLeadData;
import com.software.ttsl.model.OrderByData;

import java.util.List;

public class ContactListSortingData {
    List<AddContactData> addContactDataList;
    List<OrderByData> orderByDataList;


    public List<AddContactData> getAddContactDataList() {
        return addContactDataList;
    }

    public void setAddContactDataList(List<AddContactData> addContactDataList) {
        this.addContactDataList = addContactDataList;
    }

    public List<OrderByData> getOrderByDataList() {
        return orderByDataList;
    }

    public void setOrderByDataList(List<OrderByData> orderByDataList) {
        this.orderByDataList = orderByDataList;
    }
}
