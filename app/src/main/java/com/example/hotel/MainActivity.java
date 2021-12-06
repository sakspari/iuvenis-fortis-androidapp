package com.example.hotel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----------------Navigation Components---------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);

//        set up title of app bar
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.profilFragment, R.id.homeFragment, R.id.mapFragment)
                .build();

        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
//        navigation
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
//        ----------------end of Navigation Components---------------------------

//        List<HotelRoom> hotelRoomList = MyDatabaseClient.getInstance(getApplicationContext())
//                .getDatabase()
//                .hotelDao()
//                .getAllRoom();

//        //insert Dummy data ke dalam databse (jika database masih kosong)
//        if(hotelRoomList.isEmpty()){
//            new DummyRoom(getApplicationContext()).insertDummy();
//        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}