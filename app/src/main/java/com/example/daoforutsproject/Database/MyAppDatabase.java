package com.example.daoforutsproject.Database;

import android.content.Context;

import androidx.room.Room;

public class MyAppDatabase {
    private Context context;
    private static MyAppDatabase myAppDatabase;

    private MyAbstractDatabase database;

    public MyAppDatabase(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, MyAbstractDatabase.class, "my_db")
                .allowMainThreadQueries()
                .build();
    }
    public static synchronized MyAppDatabase getInstance(Context context){
        if(myAppDatabase == null){
            myAppDatabase = new MyAppDatabase(context);
        }
        return myAppDatabase;
    }

    public MyAbstractDatabase getDatabase() {
        return database;
    }
}