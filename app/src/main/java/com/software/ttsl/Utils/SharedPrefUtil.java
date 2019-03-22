package com.software.ttsl.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefUtil
{
    private static final String TAG= SharedPrefUtil.class.getName();

    private static final String PREF_NAME = "SharedPrefUtil";
    public static final String KEY_RATING="leadRating";
    public static final String KEY_ACCOUNT_TYPE="accountType";
    public static final String KEY_DEAL_STAGE="deal_stage";
    SharedPreferences.Editor editor;
    int PRIVATE_MODE =0;

    private SharedPreferences preferences;
    private static Context context;
    private static  SharedPrefUtil sharedPrefUtil;

    private SharedPrefUtil() {
        preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }



    public static SharedPrefUtil getInstance() {
        if (sharedPrefUtil == null) {
            sharedPrefUtil = new SharedPrefUtil();
        }
        return sharedPrefUtil;
    }


    public  static  Context getContext() {
        return context;
    }


    public static void  setContext(Context context) {
        SharedPrefUtil.context = context;
    }


    public void setLeadData(String key,String value){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString(key,value);
        editor.commit();
        Log.e("SharedPref   ",value);

    }


    public void setAccountType(String accountType){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCOUNT_TYPE,accountType);
        editor.commit();
        Log.e(TAG,accountType);
    }


    public String getAccountType(){
      return   preferences.getString(KEY_ACCOUNT_TYPE,null);
    }


    public void  setDealStage(String dealStage){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_DEAL_STAGE,dealStage);
        editor.commit();
        Log.e(TAG,dealStage);
    }

    public String getDealStage(){
        return preferences.getString(KEY_DEAL_STAGE,null);
    }


}




