package com.example.hotel.view.home;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.FragmentUrBookedRoomBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UrBookedRoom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UrBookedRoom extends Fragment {

    FragmentUrBookedRoomBinding binding;
    List<BookDetail> bookDetailList;
    List<HotelRoom> hotelRoomList;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UrBookedRoom() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UrBookedRoom.
     */
    // TODO: Rename and change types and number of parameters
    public static UrBookedRoom newInstance(String param1, String param2) {
        UrBookedRoom fragment = new UrBookedRoom();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ur_booked_room, container, false);

        hotelRoomList = new ArrayList<HotelRoom>();

        User user = new UserLoginPreferences(binding.getRoot().getContext()).getUserLogin();

        bookDetailList = MyDatabaseClient.getInstance(getContext())
                .getDatabase()
                .bookingDao()
                .getUserBookings(user.getUsername());

        if (bookDetailList != null) {
            recyclerView = binding.getRoot().findViewById(R.id.rv_layout);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new RVBookingAdapter(bookDetailList));
        }

        return binding.getRoot();
    }

}