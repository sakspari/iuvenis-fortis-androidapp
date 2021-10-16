package com.example.Hotel.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.Hotel.Converters.Converters;
import com.example.Hotel.Dao.BookingDao;
import com.example.Hotel.Dao.HotelDao;
import com.example.Hotel.Dao.ReviewDao;
import com.example.Hotel.Dao.UserDao;
import com.example.Hotel.Model.BookDetail;
import com.example.Hotel.Model.HotelRoom;
import com.example.Hotel.Model.RoomReview;
import com.example.Hotel.Model.User;

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
