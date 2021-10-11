package com.example.daoforutsproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomDetail;
import com.example.daoforutsproject.ModelRelation.RoomWithDetail;

import java.util.List;

@Dao
public interface MyDao {

//    HotelRoom Operation
    @Transaction
    @Query("SELECT * FROM hotel_room")
    List<HotelRoom> getAllRoom();

    @Insert
    void insertRoom(HotelRoom hotelRoom);

    @Update
    void updateRoom(HotelRoom hotelRoom);

    @Delete
    void deleteRoom(HotelRoom hotelRoom);

//   Hotel RoomDetailOperation
    @Query("SELECT * FROM room_detail")
    List<RoomDetail> getAllRoomDetail();

    @Insert
    void insertRoomDetail(RoomDetail roomDetail);

    @Update
    void updateRoomDetail(RoomDetail roomDetail);

    @Delete
    void deleteRoomDetail(RoomDetail roomDetail);

//    HotelRoomWith Room Detail
    @Transaction
    @Query("SELECT * FROM hotel_room")
    List<RoomWithDetail> getAllRoomWithDetail();

    @Transaction
    @Query("SELECT * FROM hotel_room WHERE room_id = :room_id")
    RoomWithDetail getRoomWithDetail(String room_id);

//    ---------------------Booking operation------------------------
//    ---------------------RoomReview operation------------------------
//    ---------------------User operation------------------------
//    ---------------------BookAndRoom------------------------
//    ---------------------RoomWithReview------------------------
//    ---------------------UserWithBookings------------------------
//    ---------------------UserWithReviews------------------------
}
