package com.example.hotel.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HotelRoomResponse {
    private String message;

    @SerializedName("data")
    private List<HotelRoom> hotelRoomList;

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
