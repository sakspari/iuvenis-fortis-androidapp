package com.example.hotel.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.hotel.model.RoomDetail;

@Dao
public interface RoomDetailDao {
    @Insert
    void insertDetailRoom(RoomDetail roomDetail);

}
