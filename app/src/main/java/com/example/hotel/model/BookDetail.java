package com.example.hotel.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "book_detail")
public class BookDetail {
    @PrimaryKey(autoGenerate = true)
    private int book_detail_id;

    @NonNull
    @ColumnInfo(name = "fk_room_id")
    private String fk_room_id;

    @NonNull
    @ColumnInfo(name = "fk_username")
    private String fk_username;

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

    public String getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(String fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

    public String getFk_username() {
        return fk_username;
    }

    public void setFk_username(String fk_username) {
        this.fk_username = fk_username;
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
}
