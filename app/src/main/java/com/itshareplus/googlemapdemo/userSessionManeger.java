package com.itshareplus.googlemapdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;

import object.SalesOrders;

/**
 * Created by AZeaage on 4/17/2017.
 */

public class userSessionManeger {
    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    Context context;
    int PRIVATE_MODE =0;
    private static final String PREFER_NAME="AndroidExamplePref";
    private static final String IS_USER_LOGIN="IsUserLoggedIn";
    public static final String KEY_NAME="EmployeeName";
    public static final String KEY_CODE="EmployeeCode";
    public static final String KEY_positionTitle="positionTitle";
    public static final String KEY_todaySalesOrdersTitle="todaySalesOrdersTitle";

    //constructor

    public userSessionManeger(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor=preferences.edit();

    }

    public void createUserLoginSession(String name, String code ,String positionTitle){
        editor.putBoolean(IS_USER_LOGIN,true);
        //shared the name in pref
        editor.putString(KEY_NAME,name);

        editor.putString(KEY_CODE,code);

        editor.putString(KEY_positionTitle,positionTitle);

        editor.commit();
    }

    public boolean checkLogin(){

        if(!this.IsUserLoggedIn()){

            Intent i = new Intent(context,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(i);
            return true;

        }
        return false;
    }

    private boolean IsUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

    public HashMap<String,String> getUserDetails(){

        HashMap<String,String> user=new HashMap<>();
        user.put(KEY_NAME,preferences.getString(KEY_NAME,null));
        user.put(KEY_CODE,preferences.getString(KEY_CODE,null));
        user.put(KEY_positionTitle,preferences.getString(KEY_positionTitle,null));
        return user;
    }

    public void logoutUser(){

        editor.clear();
        editor.commit();

        Intent i = new Intent(context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(i);
    }
}
