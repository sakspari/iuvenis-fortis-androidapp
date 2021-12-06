package com.example.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomDetailResponse {

    @SerializedName("data")
    List<RoomDetail> roomDetailList;

    @SerializedName("message")
    private String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RoomDetail> getRoomDetailList() {
        return roomDetailList;
    }

    public void setRoomDetailList(List<RoomDetail> roomDetailList) {
        this.roomDetailList = roomDetailList;
    }
}
