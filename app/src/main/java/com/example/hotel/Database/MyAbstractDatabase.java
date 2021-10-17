package com.example.hotel.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.hotel.Converters.Converters;
import com.example.hotel.Dao.BookingDao;
import com.example.hotel.Dao.HotelDao;
import com.example.hotel.Dao.ReviewDao;
import com.example.hotel.Dao.UserDao;
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
