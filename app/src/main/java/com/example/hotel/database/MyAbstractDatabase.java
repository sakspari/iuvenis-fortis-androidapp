package com.example.hotel.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.hotel.converters.Converters;
import com.example.hotel.dao.BookingDao;
import com.example.hotel.dao.HotelDao;
import com.example.hotel.dao.ReviewDao;
import com.example.hotel.dao.UserDao;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomReview;
import com.example.hotel.model.User;

@Database(entities = {
        HotelRoom.class,
        RoomReview.class,
        BookDetail.class,
        User.class
},version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MyAbstractDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract HotelDao hotelDao();
    public abstract ReviewDao reviewDao();
    public abstract BookingDao bookingDao();

}
