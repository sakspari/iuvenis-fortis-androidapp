package com.example.hotel.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_detail")
public class RoomDetail {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int room_detail_id;

    @NonNull
    @ColumnInfo(name = "fk_room_id")
    private String fk_room_id;

    @ColumnInfo(name = "price")
    private Integer price;

    @ColumnInfo(name = "room_description")
    private String room_description;

    public RoomDetail(@NonNull String fk_room_id, Integer price, String room_description) {
        this.fk_room_id = fk_room_id;
        this.price = price;
        this.room_description = room_description;
    }

    public int getRoom_detail_id() {
        return room_detail_id;
    }

    public void setRoom_detail_id(int room_detail_id) {
        this.room_detail_id = room_detail_id;
    }

    @NonNull
    public String getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(@NonNull String fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRoom_description() {
        return room_description;
    }

    public void setRoom_description(String room_description) {
        this.room_description = room_description;
    }
}
