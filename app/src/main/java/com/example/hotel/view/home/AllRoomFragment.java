package com.example.hotel.view.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.hotel.adapter.RVHotelRoom;
import com.example.hotel.api.RoomApi;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.FragmentAllRoomBinding;
import com.example.hotel.model.HotelRoom;
import com.example.hotel.model.HotelRoomResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllRoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllRoomFragment extends Fragment {

    private FragmentAllRoomBinding binding;
    private List<HotelRoom> hotelRoomList;
    private RecyclerView recyclerView;
    private RVHotelRoom adapter;
    private RequestQueue queue;
    private SwipeRefreshLayout srHotelRoom;
    private LinearLayout layoutLoading;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllRoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllRoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllRoomFragment newInstance(String param1, String param2) {
        AllRoomFragment fragment = new AllRoomFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_room, container, false);

        queue = Volley.newRequestQueue(getContext());
        layoutLoading = binding.getRoot().findViewById(R.id.layout_loading);
        srHotelRoom = binding.getRoot().findViewById(R.id.sr_all_room);

         srHotelRoom.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRoom();
            }
        });

        recyclerView = binding.getRoot().findViewById(R.id.rv_layout);
        adapter = new RVHotelRoom(new ArrayList<>(),getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //set Orientasi
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
            recyclerView.setLayoutManager(layoutManager);
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
        }
//        if(hotelRoomList!=null){
//            recyclerView.setAdapter(new RVHotelRoom(hotelRoomList));
//        }

        getAllRoom();
        return binding.getRoot();
    }

    private void getAllRoom() {
        srHotelRoom.setRefreshing(true);
        StringRequest stringRequest = new StringRequest(GET, RoomApi.GET_ALL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HotelRoomResponse hotelRoomResponse =
                        gson.fromJson(response, HotelRoomResponse.class);
                adapter.setHotelRoomList(hotelRoomResponse.getHotelRoomList());
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(), hotelRoomResponse.getMessage()+"-->size: "+hotelRoomResponse.getHotelRoomList().size(), Toast.LENGTH_SHORT).show();
                srHotelRoom.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                srHotelRoom.setRefreshing(false);
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

    // Fungsi ini digunakan menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }
}