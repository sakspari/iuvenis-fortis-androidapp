package com.example.hotel.view.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.hotel.R;
import com.example.hotel.adapter.ReviewListener;
import com.example.hotel.databinding.FragmentDetailBookingBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomDetail;
import com.example.hotel.model.RoomReview;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;
import com.example.hotel.view.home.dialog.BookingDialog;
import com.example.hotel.view.home.dialog.ReviewDialog;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailBooking extends Fragment implements ReviewListener {
    private FragmentDetailBookingBinding binding;
    private int id_booking;
    private RoomReview roomReview;
    BookDetail bookDetail;
    ReviewListener listener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailBooking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailBooking.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailBooking newInstance(String param1, String param2) {
        DetailBooking fragment = new DetailBooking();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_booking, container, false);
        //set User
//        User user = new UserLoginPreferences(binding.getRoot().getContext()).getUserLogin();
//        binding.setUser(user);

        //get bundle of id booking
        Bundle bundle = getArguments();
        Gson gson = new Gson();
        User user = gson.fromJson(bundle.getString("user"),User.class);
        BookDetail bookDetail = gson.fromJson(bundle.getString("book_detail"),BookDetail.class);
        binding.setUser(user);
        binding.setBookDetail(bookDetail);

        roomReview = new RoomReview();
        roomReview.setFk_room_id(bookDetail.getFk_room_id());
        roomReview.setFk_username(user.getUser_id());

        this.listener = (ReviewListener) this;

        binding.btnReviewNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ReviewDialog(listener).show(getActivity().getSupportFragmentManager(),"review_dialog");
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onReviewSubmit(String review_desc) {
        Toast.makeText(getContext(), review_desc, Toast.LENGTH_SHORT).show();
        roomReview.setReview_date(new Date());
        roomReview.setReview_description(review_desc);
        binding.setRoomReview(roomReview);
        storeReview(roomReview);
    }

    void storeReview(RoomReview roomReview){

    }
}