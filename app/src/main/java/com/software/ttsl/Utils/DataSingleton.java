package com.software.ttsl.Utils;

import com.software.ttsl.model.GetQuotationFormData;

public class DataSingleton {

    private GetQuotationFormData getQuotationFormData;
    private static DataSingleton mDataSingleton;

    private DataSingleton(){
        getQuotationFormData= new GetQuotationFormData();
    }

    public static DataSingleton getInstance(){
        //if there is no instance available... create new one
        if (mDataSingleton == null){
         mDataSingleton=   new DataSingleton();
        }

        return mDataSingleton;
    }

    public GetQuotationFormData getQuotationFormData() {
         return getQuotationFormData;
    }


    public void clearQuotion(){
        getQuotationFormData = null;
        getQuotationFormData = new GetQuotationFormData();
    }

    public void clearAll(){
        getQuotationFormData = null;
        mDataSingleton = null;
    }
}
