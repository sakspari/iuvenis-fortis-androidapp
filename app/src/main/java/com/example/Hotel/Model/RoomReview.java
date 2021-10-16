package com.example.Hotel.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "room_review")
public class RoomReview {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "review_id")
    private int review_id;

//    @NonNull
    @ColumnInfo(name = "fk_room_id")
    private String fk_room_id;

//    @NonNull
    @ColumnInfo(name = "fk_username")
    private String fk_username;

    @ColumnInfo(name = "review_date")
    private Date review_date;

    @ColumnInfo(name = "review_description")
    private String review_description;

    public RoomReview() {
    }

    public int getReview_id() {
        return review_id;
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

//    @NonNull
    public String getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(String fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

//    @NonNull
    public String getFk_username() {
        return fk_username;
    }

    public void setFk_username(String fk_username) {
        this.fk_username = fk_username;
    }

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }

    public String getReview_description() {
        return review_description;
    }

    public void setReview_description(String review_description) {
        this.review_description = review_description;
    }
}
