package com.example.daoforutsproject.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "room_detail")
public class RoomDetail {
    @PrimaryKey(autoGenerate = true)
    private int room_detail_id;

    @ColumnInfo(name = "fk_room_id")
    private String fk_room_id;

    @ColumnInfo(name = "size")
    private String size;

//    @ColumnInfo(name = "facilities")
//    private List<String> facilities;

    public int getRoom_detail_id() {
        return room_detail_id;
    }

    public void setRoom_detail_id(int room_detail_id) {
        this.room_detail_id = room_detail_id;
    }

    public String getFk_room_id() {
        return fk_room_id;
    }

    public void setFk_room_id(String fk_room_id) {
        this.fk_room_id = fk_room_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

//    public List<String> getFacilities() {
//        return facilities;
//    }
//
//    public void setFacilities(List<String> facilities) {
//        this.facilities = facilities;
//    }
//
//    public void addFacilities (String facility){
//        this.facilities.add(facility);
//    }
}
