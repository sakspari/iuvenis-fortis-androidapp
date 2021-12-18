package com.example.hotel.view.home;

import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel.R;
import com.example.hotel.adapter.OnBookingClickListener;
import com.example.hotel.adapter.RVBookingAdapter;
import com.example.hotel.api.BookDetailApi;
import com.example.hotel.api.UserApi;
import com.example.hotel.databinding.FragmentUrBookedRoomBinding;
import com.example.hotel.model.BookDetail;
import com.example.hotel.model.BookDetailResponse;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.User;
import com.example.hotel.model.UserResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UrBookedRoom#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UrBookedRoom extends Fragment implements OnBookingClickListener {

    private FragmentUrBookedRoomBinding binding;
    private FirebaseAuth mAuth;
    private List<BookDetail> bookDetailList;
    private List<HotelRoom> hotelRoomList;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private RVBookingAdapter adapter;
    private LinearLayout layoutLoading;
    private SwipeRefreshLayout swipeRefreshLayout;
    private User user;
    private OnBookingClickListener listener;

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

        mAuth = FirebaseAuth.getInstance();
        queue = Volley.newRequestQueue(getContext());
//        layoutLoading = getActivity().findViewById(R.id.layout_loading);
        layoutLoading = binding.getRoot().findViewById(R.id.layout_loading);
        swipeRefreshLayout = binding.getRoot().findViewById(R.id.sr_all_user_bookings);

        this.listener = (OnBookingClickListener)this;

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCurrentUser();
            }
        });


        adapter = new RVBookingAdapter(new ArrayList<>(),listener);
        recyclerView = binding.rvLayout;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getCurrentUser();
        return binding.getRoot();
    }

    void getCurrentUser(){
        FirebaseUser fUser = mAuth.getCurrentUser();
        StringRequest stringRequest = new StringRequest(GET,
                UserApi.GET_BY_EMAIL_URL + fUser.getEmail(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                UserResponse userResponse =
                        gson.fromJson(response, UserResponse.class);
                user = userResponse.getUserList().get(0);

                adapter.setUser(user);
                getUserBookingHistory(user.getUser_id());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(binding.getRoot().getContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(binding.getRoot().getContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            // Menambahkan header pada request
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        // Menambahkan request ke request queue
        queue.add(stringRequest);
    }

    void getUserBookingHistory(int user_id){
        swipeRefreshLayout.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(GET, BookDetailApi.GET_ALL_USER_BOOKINGS_URL+user_id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                BookDetailResponse bookDetailResponse =
                        gson.fromJson(response, BookDetailResponse.class);
                adapter.setBookDetailList(bookDetailResponse.getBookDetailList());
                recyclerView.setAdapter(adapter);
                Toast.makeText(binding.getRoot().getContext(), bookDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    String responseBody = new String(error.networkResponse.data,
                            StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);
                    Toast.makeText(getContext(), errors.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                return headers;
            }
        };
        //add to queue req
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(BookDetail bookDetail, HotelRoom hotelRoom) {
        Gson gson = new Gson();
        Bundle bundle = new Bundle();
        bundle.putString("book_detail",gson.toJson(bookDetail));
        bundle.putString("hotel_room",gson.toJson(hotelRoom));
        bundle.putString("user", gson.toJson(user));
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_urBookedRoom_to_detailBooking,bundle);
    }

    @Override
    public void onDeleteButtonClick(BookDetail bookDetail) {
        Toast.makeText(binding.getRoot().getContext(), "delete: "+bookDetail.getFk_room_id(), Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete booking for this room?");
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteBooking(bookDetail);
                getCurrentUser();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();

    }

    void deleteBooking(BookDetail bookDetail){

            setLoading(true);
            StringRequest stringRequest = new StringRequest(DELETE,
                    BookDetailApi.DELETE_BY_ID + String.valueOf(bookDetail.getBook_detail_id()), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();

                    BookDetailResponse bookDetailResponse = gson.fromJson(response, BookDetailResponse.class);
                    setLoading(false);
                    Toast.makeText(binding.getRoot().getContext(),bookDetailResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    getCurrentUser();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    setLoading(false);
                    try {
                        String responseBody = new String(error.networkResponse.data,
                                StandardCharsets.UTF_8);
                        JSONObject errors = new JSONObject(responseBody);
                        Toast.makeText(binding.getRoot().getContext(), errors.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(binding.getRoot().getContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                // Menambahkan header pada request
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Accept", "application/json");
                    return headers;
                }
            };
            // Menambahkan request ke request queue
            queue.add(stringRequest);
    }


    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.GONE);
        }
    }



}