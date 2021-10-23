package com.example.hotel.view.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.FragmentDetailRoomBinding;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomDetail;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;
import com.example.hotel.view.home.dialog.BookingDialog;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailRoom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailRoom extends Fragment implements BookingDialog.BookingDialogListener {

    FragmentDetailRoomBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailRoom() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailRoom.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailRoom newInstance(String param1, String param2) {
        DetailRoom fragment = new DetailRoom();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_room, container, false);

        //set User
        User user = new UserLoginPreferences(binding.getRoot().getContext()).getUserLogin();
        binding.setUser(user);

        //get bundle of id Room
        Bundle bundle = getArguments();
        String id_kamar = bundle.getString("id_room");

        //get Hotel room
        HotelRoom hotelRoom = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .hotelDao()
                .roomFromId(id_kamar);
        // set Hotel Room
        binding.setHotelRoom(hotelRoom);

        //get Detail Room
        RoomDetail roomDetail = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .hotelDao()
                .getDetailRoom(hotelRoom.getRoom_id());

        //set Detail
        binding.setRoomDetail(roomDetail);

        //action for booking button
        binding.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(binding.getRoot().getContext(), "Add some action here", Toast.LENGTH_SHORT).show();
                openDialog();
            }
        });

        return binding.getRoot();
    }


    //method open custom dialog
    public void openDialog(){
        BookingDialog bookingDialog = new BookingDialog();
        bookingDialog.show(getActivity().getSupportFragmentManager(),"booking dialog");
    }

    @Override
    public void passDate(Date dateIn, Date dateOut) {
        System.out.println(dateIn.toString());
        System.out.println(dateOut.toString());
    }
}