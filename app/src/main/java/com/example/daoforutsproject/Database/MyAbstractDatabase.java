package com.example.daoforutsproject.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.daoforutsproject.Dao.MyDao;
import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomDetail;

@Database(entities = {
        HotelRoom.class,
        RoomDetail.class
},version = 1)
public abstract class MyAbstractDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
