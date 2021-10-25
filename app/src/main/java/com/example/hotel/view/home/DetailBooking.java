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

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.FragmentDetailBookingBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomDetail;
import com.example.hotel.model.RoomReview;
import com.example.hotel.model.User;
import com.example.hotel.preferences.UserLoginPreferences;
import com.google.android.material.button.MaterialButton;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailBooking#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailBooking extends Fragment {
    private FragmentDetailBookingBinding binding;
    private int id_booking;
    private RoomReview roomReview;
    BookDetail bookDetail;

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
        User user = new UserLoginPreferences(binding.getRoot().getContext()).getUserLogin();
        binding.setUser(user);

        //get bundle of id booking
        Bundle bundle = getArguments();
        id_booking = bundle.getInt("id_booking");

        //get Booking detail
        bookDetail = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .bookingDao()
                .bookingById(id_booking);

        //set booking detail
        binding.setBookDetail(bookDetail);

        //get Hotel Room
        HotelRoom hotelRoom = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .hotelDao()
                .roomFromId(bookDetail.getFk_room_id());
        //set Hotel Room
        binding.setHotelRoom(hotelRoom);

        //get Detail Room
        RoomDetail detailRoom = MyDatabaseClient.getInstance(binding.getRoot().getContext())
                .getDatabase()
                .hotelDao()
                .getDetailRoom(hotelRoom.getRoom_id());
        //set detail Room
        binding.setRoomDetail(detailRoom);

        //getReview
        RoomReview review = MyDatabaseClient.getInstance(getContext())
                .getDatabase()
                .reviewDao()
                .getRoomReview(bookDetail.getFk_username(), bookDetail.getFk_room_id());
        if (review != null)
            binding.setRoomReview(review);

        //aksi untuk btnReview
        binding.btnReviewNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                onStop();
//
            }
        });


        return binding.getRoot();
    }

    public void openDialog() {

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.review_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();

        EditText reviewDesc = dialog.findViewById(R.id.reviewDesc);
        MaterialButton submitButton = dialog.findViewById(R.id.btnSubmitReview);

        RoomReview roomReview = new RoomReview();
        roomReview.setFk_room_id(bookDetail.getFk_room_id());
        roomReview.setFk_username(bookDetail.getFk_username());
        roomReview.setReview_date(new Date());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!reviewDesc.getText().toString().isEmpty()) {
                    roomReview.setReview_description(reviewDesc.getText().toString());
                    RoomReview old = MyDatabaseClient.getInstance(getContext())
                            .getDatabase()
                            .reviewDao()
                            .getRoomReview(bookDetail.getFk_username(), bookDetail.getFk_room_id());

                    if (old == null) {
                        MyDatabaseClient.getInstance(getContext())
                                .getDatabase()
                                .reviewDao()
                                .insertReview(roomReview);
                        Toast.makeText(getContext(), "Review berhasil!", Toast.LENGTH_SHORT).show();
                    } else {
                        roomReview.setReview_id(old.getReview_id());
                        MyDatabaseClient.getInstance(getContext())
                                .getDatabase()
                                .reviewDao()
                                .updateReview(roomReview);
                        Toast.makeText(getContext(), "Review diupdate!", Toast.LENGTH_SHORT).show();
                    }

                    RoomReview review = MyDatabaseClient.getInstance(getContext())
                            .getDatabase()
                            .reviewDao()
                            .getRoomReview(bookDetail.getFk_username(), bookDetail.getFk_room_id());
                    if (review != null)
                        binding.setRoomReview(review);

                    dialog.dismiss();
                }else{
                    Toast.makeText(getContext(), "Ooops, silahkan isi review dulu", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void reloadCurrentFragment() {
        int id = Navigation.findNavController(binding.getRoot()).getCurrentDestination().getId();
        Navigation.findNavController(binding.getRoot()).popBackStack(id, true);
        Navigation.findNavController(binding.getRoot()).navigate(id);
    }


    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
        RoomReview review = MyDatabaseClient.getInstance(getContext())
                .getDatabase()
                .reviewDao()
                .getRoomReview(bookDetail.getFk_username(), bookDetail.getFk_room_id());
        if (review != null)
            binding.setRoomReview(review);
    }
}