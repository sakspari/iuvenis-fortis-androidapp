package com.example.daoforutsproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.daoforutsproject.Model.BookDetail;
import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomDetail;
import com.example.daoforutsproject.Model.RoomReview;
import com.example.daoforutsproject.Model.User;
import com.example.daoforutsproject.ModelRelation.BookAndRoom;
import com.example.daoforutsproject.ModelRelation.RoomWithDetail;
import com.example.daoforutsproject.ModelRelation.RoomWithReview;
import com.example.daoforutsproject.ModelRelation.UserWithBook;
import com.example.daoforutsproject.ModelRelation.UserWithReview;

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
//    @Query("SELECT * FROM room_detail")
//    List<RoomDetail> getAllRoomDetail();
//
//    @Insert
//    void insertRoomDetail(RoomDetail roomDetail);
//
//    @Update
//    void updateRoomDetail(RoomDetail roomDetail);
//
//    @Delete
//    void deleteRoomDetail(RoomDetail roomDetail);

//    HotelRoomWith Room Detail
//    @Transaction
//    @Query("SELECT * FROM hotel_room")
//    List<RoomWithDetail> getAllRoomWithDetail();
//
//    @Transaction
//    @Query("SELECT * FROM hotel_room WHERE room_id = :room_id")
//    RoomWithDetail getRoomWithDetail(String room_id);

//    ---------------------Booking operation------------------------
        @Transaction
        @Query("SELECT * FROM book_detail")
        List<BookDetail> getAllBookings();

        @Insert
        void insertBooking(BookDetail bookDetail);

        @Update
        void updateBooking(BookDetail bookDetail);

        @Delete
        void deleteBooking(BookDetail bookDetail);
//    ---------------------RoomReview operation------------------------
        @Transaction
        @Query("SELECT * FROM room_review")
        List<RoomReview> getAllReviews();

        @Insert
        void insertReview(RoomReview roomReview);

        @Update
        void updateReview(RoomReview roomReview);

        @Delete
        void deleteReview(RoomReview roomReview);
//    ---------------------User operation------------------------
        @Transaction
        @Query("SELECT * FROM user")
        List<User> getAllUser();

        @Insert
        void insertUser(User user);

        @Update
        void updateUser(User user);

        @Delete
        void deleteUser(User user);
//    ---------------------BookAndRoom------------------------
        @Transaction
        @Query("SELECT * FROM hotel_room")
        List<BookAndRoom> getAllRoomAndBook();
//    ---------------------RoomWithReview------------------------
        @Transaction
        @Query("SELECT * FROM hotel_room")
        List<RoomWithReview> getAllRoomWithReviews();
//    ---------------------UserWithBookings------------------------
        @Transaction
        @Query("SELECT * FROM user")
        List<UserWithBook> getAllUserWithBookings();
//    ---------------------UserWithReviews------------------------
        @Transaction
        @Query("SELECT * FROM user")
        List<UserWithReview> getAllUserWithReviews();
}
