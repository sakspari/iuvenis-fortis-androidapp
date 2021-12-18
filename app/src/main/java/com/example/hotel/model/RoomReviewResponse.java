package com.example.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomReviewResponse {

    @SerializedName("data")
    private List<RoomReview> roomReviewList;
    @SerializedName("message")
    private String message;

    public List<RoomReview> getRoomReviewList() {
        return roomReviewList;
    }

    public void setRoomReviewList(List<RoomReview> roomReviewList) {
        this.roomReviewList = roomReviewList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
