package com.example.hotel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookDetailResponse {

    private String message;

    @SerializedName("data")
    private List<BookDetail> bookDetailList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookDetail> getBookDetailList() {
        return bookDetailList;
    }

    public void setBookDetailList(List<BookDetail> bookDetailList) {
        this.bookDetailList = bookDetailList;
    }
}
