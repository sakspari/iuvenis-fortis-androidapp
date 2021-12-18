package com.example.hotel.view.home;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.adapter.BookingDialogListener;
import com.example.hotel.adapter.RVReview;
import com.example.hotel.api.BookDetailApi;
import com.example.hotel.api.RoomDetailApi;
import com.example.hotel.api.UserApi;
import com.example.hotel.databinding.FragmentDetailRoomBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.BookDetailResponse;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.RoomDetail;
import com.example.hotel.model.RoomDetailResponse;
import com.example.hotel.model.RoomReview;
import com.example.hotel.model.User;
import com.example.hotel.model.UserResponse;
import com.example.hotel.view.home.dialog.BookingDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailRoom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailRoom extends Fragment implements BookingDialogListener {

    FirebaseAuth mAuth;
    FragmentDetailRoomBinding binding;
    List<RoomReview> roomReviewList;
    RecyclerView recyclerView;
    String dataKamar;
    HotelRoom hotelRoom;
    private RequestQueue queue;
    User user;
    RoomDetail roomDetail;
    BookDetail bookDetail;
    BookingDialogListener listener;
    SimpleDateFormat dateFormatter;

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

        queue = Volley.newRequestQueue(getContext());
        this.listener = (BookingDialogListener) this;

        bookDetail = new BookDetail();

        dateFormatter = new SimpleDateFormat("dd-MM-yy", Locale.US);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fUser = mAuth.getCurrentUser();
        getCurrentUser(String.valueOf(fUser.getEmail()));
        //set User
//        User user = new UserLoginPreferences(binding.getRoot().getContext()).getUserLogin();
        getCurrentUser(String.valueOf(fUser.getEmail()));


        //get bundle of id Room
        Bundle bundle = getArguments();
        dataKamar = bundle.getString("data_kamar");
        hotelRoom = new Gson().fromJson(dataKamar, HotelRoom.class);

        binding.setHotelRoom(hotelRoom);
        getRoomDetail(String.valueOf(hotelRoom.getRoom_id()));

        if (roomReviewList != null) {
            recyclerView = binding.getRoot().findViewById(R.id.rv_layout);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new RVReview(roomReviewList));
        }

        //action for booking button
        binding.btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hotelRoom.isRoom_status()) {
                    new BookingDialog(listener).show(getActivity().getSupportFragmentManager(),"BOOKING_DIALOG");
                    bookDetail.setFk_user_id(user.getUser_id());
                    bookDetail.setFk_room_id(hotelRoom.getRoom_id());
                    bookDetail.setBooking_date(new Date());
                } else {
                    Toast.makeText(getContext(), "Ooopss, Room curently unavailable", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }

    void getCurrentUser(String email) {
        StringRequest stringRequest = new StringRequest(GET, UserApi.GET_BY_EMAIL_URL + email, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                UserResponse userResponse =
                        gson.fromJson(response, UserResponse.class);
                user = userResponse.getUserList().get(0);
                binding.setUser(user);

                byte[] imageByteArray = Base64.decode(user.getProfile_picture(), Base64.DEFAULT);
                Bitmap imageProfile = BitmapFactory.decodeByteArray(imageByteArray,0,imageByteArray.length); //convert kembali ke bitmap

                Glide.with(binding.getRoot().getContext())
                        .load(imageProfile)
                        .placeholder(R.drawable.ic_baseline_person_24)
                        .into(binding.profileImg);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                srHotelRoom.setRefreshing(false);
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(getContext(), errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        //add to queue request
        queue.add(stringRequest);
    }

    void getRoomDetail(String room_id) {
        StringRequest stringRequest = new StringRequest(GET, RoomDetailApi.GET_DETAIL_URL + room_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RoomDetailResponse roomDetailResponse =
                        gson.fromJson(response, RoomDetailResponse.class);
                if (roomDetailResponse.getRoomDetailList().size() != 0) {
                    roomDetail = roomDetailResponse.getRoomDetailList().get(0);
                    binding.setRoomDetail(roomDetail);
                    System.out.printf(roomDetail.getRoom_description());
                } else {
                    binding.setRoomDetail(new RoomDetail(null, null, "No Details Available for this Room!"));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(getContext(), errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        //add to queue request
        queue.add(stringRequest);
    }


    public void storeBooking(BookDetail bookDetail) {
        StringRequest stringRequest = new StringRequest(POST, BookDetailApi.CREATE_BOOKING_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        BookDetailResponse bookDetailResponse =
                                gson.fromJson(response, BookDetailResponse.class);
                        Toast.makeText(binding.getRoot().getContext(), bookDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(binding.getRoot().getContext(),
                            errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(binding.getRoot().getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                Gson gson = new Gson();
                String requestBody = gson.toJson(bookDetail);
                return requestBody.getBytes(StandardCharsets.UTF_8);
            }

            // Mendeklarasikan content type dari request body yang ditambahkan
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    @Override
    public void onBookPress(String checkInDate, String checkOutDate) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy", Locale.US);
        try {
            bookDetail.setCheck_in_date(dateFormatter.parse(checkInDate));
            bookDetail.setCheck_out_date(dateFormatter.parse(checkOutDate));
            storeBooking(bookDetail);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}