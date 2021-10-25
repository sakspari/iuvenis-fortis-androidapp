package com.example.hotel;

import androidx.appcompat.app.ActionBar;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----------------Navigation Components---------------------------
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

        //insert Dummy data ke dalam databse (jika database masih kosong)
        if(hotelRoomList.isEmpty()){
            new DummyRoom(getApplicationContext()).insertDummy();
        }
    }

}