package com.example.daoforutsproject.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.daoforutsproject.Converters.Converters;
import com.example.daoforutsproject.Dao.HotelDao;
import com.example.daoforutsproject.Dao.MyDao;
import com.example.daoforutsproject.Dao.UserDao;
import com.example.daoforutsproject.Model.BookDetail;
import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomDetail;
import com.example.daoforutsproject.Model.RoomReview;
import com.example.daoforutsproject.Model.User;

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
    public abstract MyDao myDao();

}
