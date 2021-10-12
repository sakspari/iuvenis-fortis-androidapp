package com.example.daoforutsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.daoforutsproject.Database.MyAppDatabase;
import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomDetail;
import com.example.daoforutsproject.ModelRelation.RoomWithDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        HotelRoom hotelRoom = new HotelRoom("");
//        RoomDetail roomDetail = new RoomDetail();
//        roomDetail.setFk_room_id("kamar5");
////        roomDetail.setRoom_detail_id(1);
//        roomDetail.setSize("5 x 1");
////        addHotelRoom(hotelRoom);
////        addRoomDetail(roomDetail);
//        getRoomWithDetail();
//
//    }
//
//    private void addHotelRoom(HotelRoom hotelRoom) {
//        class AddHotelRoom extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                MyAppDatabase.getInstance(getApplicationContext())
//                        .getDatabase()
//                        .myDao()
//                        .insertRoom(hotelRoom);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void unused) {
//                super.onPostExecute(unused);
//                Toast.makeText(getApplicationContext(), "Berhasil menambahkan data kamar", Toast.LENGTH_SHORT).show();
//            }
//        }
//        AddHotelRoom addHotelRoom = new AddHotelRoom();
//        addHotelRoom.execute();
//    }
//
//    private void addRoomDetail(RoomDetail roomDetail) {
//        class AddRoomDetail extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//                MyAppDatabase.getInstance(getApplicationContext())
//                        .getDatabase()
//                        .myDao()
//                        .insertRoomDetail(roomDetail);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void unused) {
//                super.onPostExecute(unused);
//                Toast.makeText(getApplicationContext(), "Berhasil menambahkan detail data kamar", Toast.LENGTH_SHORT).show();
//            }
//        }
//        AddRoomDetail addRoomDetail = new AddRoomDetail();
//        addRoomDetail.execute();
//    }
//
//    private void getRoomWithDetail() {
//        class GetRoomWithDetail extends AsyncTask<Void, Void, List<RoomWithDetail>> {
//
//            @Override
//            protected List<RoomWithDetail> doInBackground(Void... voids) {
//                List<RoomWithDetail> roomWithDetails = MyAppDatabase.getInstance(getApplicationContext())
//                        .getDatabase()
//                        .myDao()
//                        .getAllRoomWithDetail();
//                return roomWithDetails;
//            }
//
////            @Override
////            protected void onPostExecute(List<RoomWithDetail> roomWithDetails) {
////                super.onPostExecute(roomWithDetails);
////            }
//
//                        @Override
//            protected void onPostExecute(List<RoomWithDetail> roomWithDetails) {
//                super.onPostExecute(roomWithDetails);
////                if (roomWithDetails!=null) {
//                    for (int i = 0; i < roomWithDetails.size(); i++) {
//                        System.out.println(roomWithDetails.get(i).getHotelRoom().getRoom_id());
//                        System.out.println(roomWithDetails.get(i).getHotelRoom().getRoom_type());
////                    if(roomWithDetails.get(i).getRoomDetail()!=null)
////                        System.out.println("\tNo details");
////                    else{
//                        if(roomWithDetails.get(i).getRoomDetail()!=null)
//                        System.out.println("\t"+roomWithDetails.get(i).getRoomDetail().getSize());
//                        else
//                            System.out.println("\t\tdidnt have details!");
////                    }
//                    }
////                } else {
////                    System.out.println("Data Kosong");
////                }
//
//            }
//        }
//        GetRoomWithDetail getRoomWithDetail = new GetRoomWithDetail();
//        getRoomWithDetail.execute();
//    }
}