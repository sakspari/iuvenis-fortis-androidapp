package com.example.hotel.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@Entity(tableName = "book_detail")
public class BookDetail {
    @PrimaryKey
    @SerializedName("id")
    private int book_detail_id;

    @NonNull
    @ColumnInfo(name = "fk_room_id")
    @SerializedName("room_id")
    private int fk_room_id;

    @NonNull
    @ColumnInfo(name = "fk_user_id")
    @SerializedName("user_id")
    private int fk_user_id;

    @ColumnInfo(name = "booking_date")
    private Date booking_date;

    @ColumnInfo(name = "check_in_date")
    private Date check_in_date;

    @ColumnInfo(name = "check_out_date")
    private Date check_out_date;

    public BookDetail() {
    }

    public int getBook_detail_id() {
        return book_detail_id;
    }

    public void setBook_detail_id(int book_detail_id) {
        this.book_detail_id = book_detail_id;
    }

    public int getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(int fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

    public int getFk_user_id() {
        return fk_user_id;
    }

    public void setFk_user_id(int fk_user_id) {
        this.fk_user_id = fk_user_id;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }
}
