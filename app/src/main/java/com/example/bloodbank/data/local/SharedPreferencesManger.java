package com.example.bloodbank.data.local;

import android.app.Activity;
import android.content.SharedPreferences;
import com.example.bloodbank.data.model.login.LoginData;
import com.google.gson.Gson;

public class SharedPreferencesManger {
    public static SharedPreferences sharedPreferences = null;
    public static final String USER_DATA = "USER_DATA";
    public static final String USER_PASSWORD = "USER_PASSWORD";
    public static final String REMEMBER = "REMEMBER";
    public static final String PASSWORD = "PASSWORD";
    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "blood", activity.MODE_PRIVATE);
        }
    }
    public static void SaveString(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }
    public static void SaveBoolean(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }
    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }
    public static void saveUserData(Activity activity, LoginData loginData) {
        if (loginData.getApiToken() == null) {
            loginData.setApiToken(loadUserData(activity).getApiToken());
        }
        SaveData(activity, USER_DATA, loginData);
    }
    public static String LoadString(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        return sharedPreferences.getString(data_Key, null);
    }
    public static boolean LoadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        return sharedPreferences.getBoolean(data_Key, false);
    }
    public static LoginData  loadUserData(Activity activity) {
        LoginData userData = null;
        Gson gson = new Gson();
        userData = gson.fromJson(LoadString(activity, USER_DATA), LoginData.class);
        return userData;
    }
    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }


}
