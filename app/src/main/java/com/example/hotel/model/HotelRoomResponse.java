package com.example.hotel.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HotelRoomResponse {


    @SerializedName("data")
    private List<HotelRoom> hotelRoomList;

    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HotelRoom> getHotelRoomList() {
        return hotelRoomList;
    }

    public void setHotelRoomList(List<HotelRoom> hotelRoomList) {
        this.hotelRoomList = hotelRoomList;
    }
}
