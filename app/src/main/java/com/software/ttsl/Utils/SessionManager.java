package com.software.ttsl.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.software.ttsl.LoginActivity;
import com.software.ttsl.LoginActivity1;
import com.software.ttsl.MainActivity;
import com.software.ttsl.WelcomeActivity;

import java.util.HashMap;

public class SessionManager {

    private static final String PREF_NAME = "SharedPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_USER_NAME = "user_name";
    public static  final String KEY_USER_TYPE="user_type";
    public static final String KEY_USER_CODE = "user_code";
    private static final String KEY_FIRST_RUN = "firstRun";
    private static final String KEY_EMP_FIRST_LOGIN="first_login";


    private static final String KEY_TOKEN ="access_token";


    SharedPreferences.Editor editor;
    int PRIVATE_MODE =0;

    private SharedPreferences preferences;
    private static SessionManager sessionManager;
    private static Context context;



    private SessionManager() {
        preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
    }



    /*public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }*/


    public static SessionManager getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public  static  Context getContext() {
        return context;
    }


    public static void  setContext(Context context) {
        SessionManager.context = context;
    }


     /* Create login session*/

    public void createLoginSession(String userId, String userName,String userType,Long userCode){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_TYPE,userType);
        editor.putLong(KEY_USER_CODE,userCode);
        editor.commit();
    }

    public void setFirstAPI(boolean isTrue){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean("Login", isTrue);

    }

    public void setLogin(){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }
    public  Boolean getFirstAPI() {
        return  preferences.getBoolean("Login", false);
    }


    public static String getIsLogin() {
        return IS_LOGIN;
    }


    public void setAccessToken(String token){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.commit();
    }

    public String getAccessToken(){
        return preferences.getString(KEY_TOKEN, null);
    }



    /* Get stored session data*/

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER_ID, preferences.getString(KEY_USER_ID, null));
        user.put(KEY_USER_NAME, preferences.getString(KEY_USER_NAME, null));
        user.put(KEY_USER_TYPE,preferences.getString(KEY_USER_TYPE,null));
        return user;
    }


    public String getUserKeyId(){
        return preferences.getString(KEY_USER_NAME, null);
    }


    public Boolean isEmployee(){
       return preferences.getString(KEY_USER_TYPE,"C").equalsIgnoreCase("E");
    }

    public Boolean isUser(){
        return preferences.getString(KEY_USER_TYPE,"C").equalsIgnoreCase("U");
    }


     /* Check login method wil check user login status
    If false it will redirect user to login page
      Else won't do anything*/

    public void checkLogin(){

        if(!this.isLoggedIn()){
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }else {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }



    }

    public void setFirstRun(){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean(KEY_FIRST_RUN, false);
        editor.commit();
    }

    public void setFirstEmployeeLogin(){
        SharedPreferences.Editor editor= preferences.edit();
        editor.putBoolean(KEY_EMP_FIRST_LOGIN, true);
        editor.commit();
    }

    public boolean isFirstRun(){
        return preferences.getBoolean(KEY_FIRST_RUN, true);
    }

    public  boolean isFirstEmployeeLogin(){
        return preferences.getBoolean(KEY_EMP_FIRST_LOGIN, false);
    }


      /*Clear session details
      Clearing all data from Shared Preferences*/

    public void logoutUser(){
        SharedPreferences.Editor editor= preferences.edit();
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



      /*Quick check for login
    Get Login State*/
    public boolean isLoggedIn(){
        return preferences.getBoolean(IS_LOGIN, false);
    }

}
