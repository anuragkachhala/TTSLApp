package com.software.ttsl.Utils;

import java.util.HashMap;

public class SingletonDealUtilData {


    private static SingletonDealUtilData singletonDealUtilData = new SingletonDealUtilData();
    private  HashMap<String,String> dealUtilHashMap;
    public static SingletonDealUtilData getInstance(){
        return singletonDealUtilData;
    }


    public void  dealHashMap(HashMap<String,String> dealHashMap){
        dealUtilHashMap = dealHashMap;
    }

    public HashMap<String,String> getDealUtilHashMap(){
        return dealUtilHashMap;
    }

}
