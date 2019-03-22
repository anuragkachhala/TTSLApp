package com.software.ttsl.Utils;

import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.OrderByData;

import java.util.List;

public class DealListSortingData {

    List<DealDataModel> dealDataModelList;
    List<OrderByData> orderByDataList;

    public List<DealDataModel> getDealDataModelList() {
        return dealDataModelList;
    }

    public void setDealDataModelList(List<DealDataModel> dealDataModelList) {
        this.dealDataModelList = dealDataModelList;
    }

    public List<OrderByData> getOrderByDataList() {
        return orderByDataList;
    }

    public void setOrderByDataList(List<OrderByData> orderByDataList) {
        this.orderByDataList = orderByDataList;
    }
}
