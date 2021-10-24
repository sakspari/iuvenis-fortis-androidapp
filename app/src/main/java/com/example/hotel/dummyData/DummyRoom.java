package com.example.hotel.dummyData;

import android.content.Context;

import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomDetail;
import com.example.hotel.view.home.DetailRoom;

public class DummyRoom {
    private Context context;

    public DummyRoom(Context context) {
        this.context = context;
    }

    public void insertDummy(){
        insertRoom(new HotelRoom("IF-001","Deluxe","https://miro.medium.com/max/8576/1*p1zBnv11CSx_EII8sB9Uaw.jpeg","standard",false,500_000),context);
        insertRoom(new HotelRoom("IF-002","Deluxe","https://www.shandonhotelspa.com/wp-content/uploads/2016/01/New-Deluxe-Rooms-1.jpg","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-003","Super-Deluxe","https://mydecorative.com/wp-content/uploads/2013/06/Modern-minimal-hotel-room-design.jpg","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-004","Regular","https://www.ezdanhotels.qa/wp-content/uploads/2018/10/1.png","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-005","Regular","https://www.ezdanhotels.qa/wp-content/uploads/2018/10/1.png","standard",true,500_000),context);
        insertRoom(new HotelRoom("IF-006","Regular","https://www.ezdanhotels.qa/wp-content/uploads/2018/10/1.png","standard",true,500_000),context);

        //insert detail
        insertDetailRoom(new RoomDetail("IF-001",300_000,"kamar ini sedang dalam konstruksi" +
                "sedang tidak tersedia untuk sementara waktu" +
                "silahkan memesan kamar yang lain"),context);
        insertDetailRoom(new RoomDetail("IF-002",350_000,"Lorem Ipsum Dolor sit"),context);
        insertDetailRoom(new RoomDetail("IF-003",500_000,"Lorem Ipsum Dolor sit"),context);
        insertDetailRoom(new RoomDetail("IF-004",250_000,"Lorem Ipsum Dolor sit"),context);
        insertDetailRoom(new RoomDetail("IF-005",250_000,"Lorem Ipsum Dolor sit"),context);
        insertDetailRoom(new RoomDetail("IF-006",250_000,"Lorem Ipsum Dolor sit"),context);


    }

    public void insertRoom(HotelRoom hotelRoom, Context context){
        MyDatabaseClient.getInstance(context)
                .getDatabase()
                .hotelDao()
                .insertRoom(hotelRoom);
    }

    public void insertDetailRoom(RoomDetail detailRoom, Context context){
        MyDatabaseClient.getInstance(context)
                .getDatabase()
                .roomDetailDao()
                .insertDetailRoom(detailRoom);
    }
}
