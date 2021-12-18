package com.example.hotel.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "hotel_room")
public class HotelRoom {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "room_id")
    @SerializedName("id")
    private int room_id;

    @ColumnInfo(name = "room_type")
    private String room_type;

    @ColumnInfo(name = "room_photos_url")
    @SerializedName("photo")
    private String room_photos_url;

    @ColumnInfo(name = "facility_type")
    private String facility_type;

    @ColumnInfo(name = "room_status")
    private boolean room_status;

    @ColumnInfo(name = "price")
    private double price;

    //available=true
    //booked=false


    public HotelRoom() {
    }

    public HotelRoom(@NonNull int room_id, String room_type, String room_photos_url, String facility_type, boolean room_status, double price) {
        this.room_id = room_id;
        this.room_type = room_type;
        this.room_photos_url = room_photos_url;
        this.facility_type = facility_type;
        this.room_status = room_status;
        this.price = price;
    }

    @NonNull
    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(@NonNull int room_id) {
        this.room_id = room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRoom_photos_url() {
        return room_photos_url;
    }

    public void setRoom_photos_url(String room_photos_url) {
        this.room_photos_url = room_photos_url;
    }

    public String getFacility_type() {
        return facility_type;
    }

    public void setFacility_type(String facility_type) {
        this.facility_type = facility_type;
    }

    public boolean isRoom_status() {
        return room_status;
    }

    public void setRoom_status(boolean room_status) {
        this.room_status = room_status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
