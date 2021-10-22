package com.example.hotel.dummyData;

import android.content.Context;

import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.model.HotelRoom;

public class DummyRoom {
    private Context context;

    public DummyRoom(Context context) {
        this.context = context;
    }

    public void insertDummy(){
        insertRoom(new HotelRoom("IF-001","Deluxe","https://miro.medium.com/max/8576/1*p1zBnv11CSx_EII8sB9Uaw.jpeg","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-002","Deluxe","https://www.shandonhotelspa.com/wp-content/uploads/2016/01/New-Deluxe-Rooms-1.jpg","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-003","Super-Deluxe","http://mediad.publicbroadcasting.net/p/kplu/files/styles/x_large/public/201404/AP120521026936_0.jpg","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-004","Regular","https://www.ezdanhotels.qa/wp-content/uploads/2018/10/1.png","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-005","Regular","https://www.ezdanhotels.qa/wp-content/uploads/2018/10/1.png","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-006","Regular","https://www.ezdanhotels.qa/wp-content/uploads/2018/10/1.png","standard",true,500_000),context);
    }

    public void insertRoom(HotelRoom hotelRoom, Context context){
        MyDatabaseClient.getInstance(context)
                .getDatabase()
                .hotelDao()
                .insertRoom(hotelRoom);
    }
}
