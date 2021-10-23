package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.dummyData.DummyRoom;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomReview;
import com.example.hotel.model.User;
import com.example.hotel.view.home.dialog.BookingDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookingDialog.BookingDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        ----------------Navigation Components---------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
//        set up title of app bar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.profilFragment, R.id.homeFragment, R.id.mapFragment).build();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
//        navigation
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
//        ----------------end of Navigation Components---------------------------

        List<HotelRoom> hotelRoomList = MyDatabaseClient.getInstance(getApplicationContext())
                .getDatabase()
                .hotelDao()
                .getAllRoom();

        if(hotelRoomList.isEmpty()){
            new DummyRoom(getApplicationContext()).insertDummy();
        }

Date date = new Date();
        System.out.println(date.toString());
        getAllUser();
        getAllRooms();
        getAllReview();
    }

//    ------------------USER FUNCTION--------------------

    public void insertUser(User user) {
        class InsertUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .insertUser(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "User Added Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        InsertUser insertUser = new InsertUser();
        insertUser.execute();
    }

    public void getAllUser() {
        class GetUser extends AsyncTask<Void, Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                List<User> userList = MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .userDao()
                        .getAllUser();
                return userList;
            }

            @Override
            protected void onPostExecute(List<User> users) {
                super.onPostExecute(users);
                //ganti bagian ini sesuai kebutuhan
                //jangan bandingkan dengan null object bagian if-nya
                if (users.size()!=0) {
                    for (int i = 0; i < users.size(); i++) {
                        System.out.println(users.get(i).getUsername());
                    }
                }
            }
        }
        GetUser getUser = new GetUser();
        getUser.execute();
    }


    //    -------------------HOTEL ROOM FUNCTION--------------------------
    public void insertHotelRoom(HotelRoom hotelRoom) {
        class InsertHotelRoom extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .hotelDao()
                        .insertRoom(hotelRoom);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Room insert Succecssfully", Toast.LENGTH_SHORT).show();
            }
        }
        InsertHotelRoom insertHotelRoom = new InsertHotelRoom();
        insertHotelRoom.execute();
    }

    public void getAllRooms() {
        class GetRooms extends AsyncTask<Void, Void, List<HotelRoom>> {

            @Override
            protected List<HotelRoom> doInBackground(Void... voids) {
                List<HotelRoom> hotelRooms = MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .hotelDao()
                        .getAllRoom();
                return hotelRooms;
            }

            @Override
            protected void onPostExecute(List<HotelRoom> hotelRooms) {
                super.onPostExecute(hotelRooms);
                //ganti bagian ini sesuai kebutuhan
                if (hotelRooms.size()!=0) {
                    for (int i = 0; i < hotelRooms.size(); i++) {
                        String status = "booked";
                        if (hotelRooms.get(i).isRoom_status())
                            status = "available";
                        System.out.println(hotelRooms.get(i).getRoom_id() + " " + status);
                    }
                }
            }
        }
        GetRooms getRooms = new GetRooms();
        getRooms.execute();
    }

    public void updateHotelRoom(HotelRoom hotelRoom) {
        class UpdateHotelRoom extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .hotelDao()
                        .updateRoom(hotelRoom);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        UpdateHotelRoom updateHotelRoom = new UpdateHotelRoom();
        updateHotelRoom.execute();
    }

    public void deleteHotelRoom(HotelRoom hotelRoom) {
        class DeleteHotelRoom extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .hotelDao()
                        .deleteRoom(hotelRoom);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Room Delete Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        DeleteHotelRoom deleteHotelRoom = new DeleteHotelRoom();
        deleteHotelRoom.execute();
    }

    //    ---------------------REVIEW FUNCTION--------------------------------
    public void insertReview(RoomReview roomReview) {
        class InsertRoomReview extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .reviewDao()
                        .insertReview(roomReview);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Review insert Succecssfully", Toast.LENGTH_SHORT).show();
            }
        }
        InsertRoomReview insertRoomReview = new InsertRoomReview();
        insertRoomReview.execute();
    }

    public void getAllReview() {
        class GetAllReview extends AsyncTask<Void, Void, List<RoomReview>> {

            @Override
            protected List<RoomReview> doInBackground(Void... voids) {
                List<RoomReview> roomReviews = MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .reviewDao()
                        .getAllReviews();
                return roomReviews;
            }

            @Override
            protected void onPostExecute(List<RoomReview> roomReviews) {
                super.onPostExecute(roomReviews);
                //ganti bagian ini sesuai kebutuhan
                if (roomReviews.size()!=0) {
                    for (int i = 0; i < roomReviews.size(); i++) {
                        User user = MyDatabaseClient.getInstance(getApplicationContext())
                                .getDatabase()
                                .reviewDao()
                                .getUser(roomReviews.get(i).getFk_username());

                        HotelRoom hotelRoom = MyDatabaseClient.getInstance(getApplicationContext())
                                .getDatabase()
                                .reviewDao()
                                .getHotelRoom(roomReviews.get(i).getFk_room_id());

                        System.out.println("\n\t" + user.getUsername() + " : "+hotelRoom.getRoom_id()+" on: "+roomReviews.get(i).getReview_date().toString());
                        System.out.println("\t\t" + roomReviews.get(i).getReview_description() + " ");
                    }
                }
            }
        }
        GetAllReview getAllReview = new GetAllReview();
        getAllReview.execute();
    }

    public void updateReview(RoomReview roomReview){
        class UpdateReview extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .reviewDao()
                        .updateReview(roomReview);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Update Review Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        UpdateReview updateReview = new UpdateReview();
        updateReview.execute();
    }


    public void deleteReview(RoomReview roomReview){
        class DeleteReview extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .reviewDao()
                        .deleteReview(roomReview);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Update Review Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        DeleteReview deleteReview = new DeleteReview();
        deleteReview.execute();
    }

//    ---------------------BOOKING FUNCTION--------------------------------
public void insertBooking(BookDetail bookDetail) {
    class InsertBooking extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            MyDatabaseClient.getInstance(getApplicationContext())
                    .getDatabase()
                    .bookingDao()
                    .insertBooking(bookDetail);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(getApplicationContext(), "Bookings insert Succecssfully", Toast.LENGTH_SHORT).show();
        }
    }
    InsertBooking insertBooking = new InsertBooking();
    insertBooking.execute();
}

    public void getAllBookings() {
        class GetAllBookings extends AsyncTask<Void, Void, List<BookDetail>> {

            @Override
            protected List<BookDetail> doInBackground(Void... voids) {
                List<BookDetail> bookDetailList = MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .bookingDao()
                        .getAllBookings();
                return bookDetailList;
            }

            @Override
            protected void onPostExecute(List<BookDetail> bookDetails) {
                super.onPostExecute(bookDetails);
                //ganti bagian ini sesuai kebutuhan
                if (bookDetails.size()!=0) {
                    for (int i = 0; i < bookDetails.size(); i++) {
                        System.out.println("\t\t" + bookDetails.get(i).getBook_detail_id() + " ");
                    }
                }
            }
        }
        GetAllBookings getAllBookings = new GetAllBookings();
        getAllBookings.execute();
    }

    public void updateBookings(BookDetail bookDetail){
        class UpdateBooking extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .bookingDao()
                        .updateBooking(bookDetail);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Update Booking Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        UpdateBooking updateBooking = new UpdateBooking();
        updateBooking.execute();
    }


    public void deleteBooking(BookDetail bookDetail){
        class DeleteBooking extends AsyncTask<Void, Void, Void>{

            @Override
            protected Void doInBackground(Void... voids) {
                MyDatabaseClient.getInstance(getApplicationContext())
                        .getDatabase()
                        .bookingDao()
                        .deleteBooking(bookDetail);
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                Toast.makeText(getApplicationContext(), "Booking deleted Successfully", Toast.LENGTH_SHORT).show();
            }
        }

        DeleteBooking deleteBooking = new DeleteBooking();
        deleteBooking.execute();
    }

    @Override
    public void passDate(String dateIn, String dateOut) {
        System.out.println(dateIn);
        System.out.println(dateOut);
        getAllBookings();
    }
}