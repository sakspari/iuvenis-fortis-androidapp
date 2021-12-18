package com.example.hotel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hotel.model.User;

public class UserLoginPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    //    define key
    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public UserLoginPreferences(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userLoginPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String username, String password){
//        menyimpan data login ke sharePreferences dengan key dan value
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);

//        kalau tidak di commit maka tidak akan disimpan perubahannya
        editor.commit();
    }
}
