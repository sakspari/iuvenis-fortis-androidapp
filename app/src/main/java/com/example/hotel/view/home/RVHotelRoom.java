package com.example.hotel.view.home;

<<<<<<<<< Temporary merge branch 1
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.databinding.RvHotelRoomBinding;
import com.example.hotel.model.HotelRoom;


import java.util.List;

public class RVHotelRoom extends RecyclerView.Adapter<RVHotelRoom.viewHolder>{
    private List<HotelRoom> ListRoom;
    RvHotelRoomBinding binding;

    public RVHotelRoom(List<HotelRoom> listRoom) {
        ListRoom = listRoom;
    }

    @NonNull
    @Override
    public RVHotelRoom.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RVHotelRoom.viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ListRoom.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public viewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
=========
public class RVHotelRoom{
//    private List<HotelRoom> ListRoom;
//    RvHotelRoomBinding binding;
//
//    public RVHotelRoom(List<HotelRoom> listRoom) {
//        ListRoom = listRoom;
//    }
//
//    @NonNull
//    @Override
//    public RVHotelRoom.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RVHotelRoom.viewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return ListRoom.size();
//    }
//
//    public class viewHolder extends RecyclerView.ViewHolder {
//        public viewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
>>>>>>>>> Temporary merge branch 2
}
