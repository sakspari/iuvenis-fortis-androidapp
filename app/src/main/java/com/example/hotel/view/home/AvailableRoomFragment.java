package com.example.hotel.view.home;

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
import com.example.hotel.databinding.FragmentAvailableRoomBinding;
import com.example.hotel.model.HotelRoom;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvailableRoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvailableRoomFragment extends Fragment {
    FragmentAvailableRoomBinding binding;

    private List<HotelRoom> hotelRoomList;
    private RecyclerView recyclerView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AvailableRoomFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_available_room, container, false);
        hotelRoomList = MyDatabaseClient.getInstance(getContext())
                .getDatabase()
                .hotelDao()
                .getAllRoom();

        List<HotelRoom> availableList = new ArrayList<HotelRoom>();

        for(int i=0;i<hotelRoomList.size();i++){
            if(hotelRoomList.get(i).isRoom_status()){
                availableList.add(hotelRoomList.get(i));
            }
        }

        if(availableList.size()!=0){
            recyclerView = binding.getRoot().findViewById(R.id.rv_layout);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(new RVHotelRoom(availableList));
        }

        return binding.getRoot();
    }
}