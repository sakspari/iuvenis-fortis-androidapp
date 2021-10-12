package com.example.daoforutsproject.Dao;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomReview;
import com.example.daoforutsproject.Model.User;

import java.util.List;

@Dao
public interface ReviewDao {
    @Transaction
    @Query("SELECT * FROM room_review")
    List<RoomReview> getAllReviews();

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
