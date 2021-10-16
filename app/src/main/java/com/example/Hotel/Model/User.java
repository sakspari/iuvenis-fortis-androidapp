package com.example.Hotel.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    private String username;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @NonNull
    @ColumnInfo(name = "user_profile_url")
    private String user_profile_url;

    @NonNull
    @ColumnInfo(name = "user_status")
    private boolean user_status;
    //if true = new user
    //else = old user


    public User(@NonNull String username, @NonNull String password, @NonNull String user_profile_url, boolean user_status) {
        this.username = username;
        this.password = password;
        this.user_profile_url = user_profile_url;
        this.user_status = user_status;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getUser_profile_url() {
        return user_profile_url;
    }

    public void setUser_profile_url(@NonNull String user_profile_url) {
        this.user_profile_url = user_profile_url;
    }

    public boolean isUser_status() {
        return user_status;
    }

    public void setUser_status(boolean user_status) {
        this.user_status = user_status;
    }
}
