package com.example.hotel.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User extends BaseObservable{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "user_profile_url")
    private String user_profile_url;

    @NonNull
    @ColumnInfo(name = "user_status")
    private boolean user_status;
    //if true = new user
    //else = old user


    public User() {
    }


    @NonNull
    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    @Bindable
    public String getUser_profile_url() {
        return user_profile_url;
    }

    public void setUser_profile_url(@NonNull String user_profile_url) {
        this.user_profile_url = user_profile_url;
    }

    @Bindable
    public boolean isUser_status() {
        return user_status;
    }

    public void setUser_status(boolean user_status) {
        this.user_status = user_status;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
