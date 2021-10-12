package com.example.daoforutsproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.daoforutsproject.Model.HotelRoom;

import java.util.List;

@Dao
public interface HotelDao {
    @Transaction
    @Query("SELECT * FROM hotel_room")
    List<HotelRoom> getAllRoom();

    @Insert
    void insertRoom(HotelRoom hotelRoom);

    @Update
    void updateRoom(HotelRoom hotelRoom);

    @Delete
    void deleteRoom(HotelRoom hotelRoom);
}
