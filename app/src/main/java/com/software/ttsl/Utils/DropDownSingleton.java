package com.software.ttsl.Utils;

import com.software.ttsl.Response.FormDropDown.DropDownDataModel;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;

public class DropDownSingleton {



    private static DropDownSingleton dropDownSingleton = new DropDownSingleton();
    private HashMap<String,String[][]> dropDownHashMap = new HashMap<>();
    DropDownDataModel dropDownDataModel = null;
    private DropDownSingleton() {
    }


    public static DropDownSingleton getInstance( )
    {
        return dropDownSingleton;
    }


    public void addToHashMap(List<DropDownDataModel> dropDownDataModelList,String dropDownName){
        dropDownHashMap.put(dropDownName,convertInto2DArray(dropDownDataModelList));
    }


    private String[][] convertInto2DArray(List<DropDownDataModel> dropDownDataModelList){
        String[][] arrays = new String[2][];

        String[] key = new String[dropDownDataModelList.size()];
        String[] value= new String[dropDownDataModelList.size()];
        for(int i= 0 ; i<dropDownDataModelList.size(); i++){
            dropDownDataModel = dropDownDataModelList.get(i);
            key[i] = dropDownDataModel.getKey();
            value[i] = dropDownDataModel.getValue();
        }
        arrays[0] = key;
        arrays[1] = value;
        return arrays;
    }


    public String[][] getDropDownFormHashMap(String dropDownName){
        if(dropDownHashMap.containsKey(dropDownName)){
            return dropDownHashMap.get(dropDownName);
        }
        return null;
    }









}
