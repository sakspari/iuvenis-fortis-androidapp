package com.example.hotel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomReview;
import com.example.hotel.model.User;

import java.util.List;

@Dao
public interface ReviewDao {
    @Transaction
    @Query("SELECT * FROM room_review")
    List<RoomReview> getAllReviews();

    @Query("SELECT * FROM room_review WHERE fk_username = :username AND fk_room_id = :hotel_room_id")
    RoomReview getRoomReview(String username, String hotel_room_id);

    @Insert
    void insertReview(RoomReview roomReview);

    @Update
    void updateReview(RoomReview roomReview);

    @Delete
    void deleteReview(RoomReview roomReview);

    //    mengambil user yang membuat review
    @Query("SELECT * FROM user where username = :username")
    User getUser(String username);

    //    mengambil kamar hotel di review
    @Query("SELECT * FROM hotel_room where room_id = :room_id")
    HotelRoom getHotelRoom(String room_id);

}
