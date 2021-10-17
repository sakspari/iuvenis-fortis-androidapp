package com.example.hotel.Database;

import android.content.Context;

import androidx.room.Room;

public class MyDatabaseClient {
    private Context context;
    private static MyDatabaseClient myDatabaseClient;

    private MyAbstractDatabase database;

    public MyDatabaseClient(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, MyAbstractDatabase.class, "my_db")
                .allowMainThreadQueries()
                .build();
    }
    public static synchronized MyDatabaseClient getInstance(Context context){
        if(myDatabaseClient == null){
            myDatabaseClient = new MyDatabaseClient(context);
        }
        return myDatabaseClient;
    }

    public MyAbstractDatabase getDatabase() {
        return database;
    }
}