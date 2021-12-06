package com.example.hotel.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User extends BaseObservable{

    @SerializedName("id")
    private int user_id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "username")
    @SerializedName("name")
    private String username;

    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "profile_picture")
    @SerializedName("photo")
    private String profile_picture;

    @NonNull
    @ColumnInfo(name = "user_status")
    @SerializedName("status")
    private boolean user_status;
    //if true = new user
    //else = old user


    public User() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(@NonNull String profile_picture) {
        this.profile_picture = profile_picture;
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
