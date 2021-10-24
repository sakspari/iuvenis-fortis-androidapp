package com.example.hotel.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.User;

import java.util.List;
@Dao
public interface BookingDao {
    @Transaction
    @Query("SELECT * FROM book_detail")
    List<BookDetail> getAllBookings();

    @Query("SELECT * FROM book_detail WHERE fk_username = :username")
    List<BookDetail> getUserBookings(String username);

    @Query("SELECT * FROM book_detail WHERE book_detail_id = :id")
    BookDetail bookingById (int id);

    @Insert
    void insertBooking(BookDetail bookDetail);

    @Update
    void updateBooking(BookDetail bookDetail);

    @Delete
    void deleteBooking(BookDetail bookDetail);

    //    mengambil user yang membuat Bookingan
    @Query("SELECT * FROM user where username = :username")
    User getUser(String username);

    //    mengambil kamar hotel di Booking
    @Query("SELECT * FROM hotel_room where room_id = :room_id")
    HotelRoom getHotelRoom(String room_id);
}
