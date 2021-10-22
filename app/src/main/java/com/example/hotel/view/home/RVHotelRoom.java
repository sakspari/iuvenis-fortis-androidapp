package com.example.hotel.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.R;
import com.example.hotel.databinding.RvHotelRoomBinding;
import com.example.hotel.model.HotelRoom;

import java.util.List;

public class RVHotelRoom extends RecyclerView.Adapter<RVHotelRoom.viewHolder> {
    private List<HotelRoom> hotelRoomList;
    RvHotelRoomBinding binding;

    public RVHotelRoom(List<HotelRoom> hotelRoomList) {
        this.hotelRoomList = hotelRoomList;
    }

    @NonNull
    @Override
    public RVHotelRoom.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_hotel_room, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVHotelRoom.viewHolder holder, int position) {
        HotelRoom hotelRoom = hotelRoomList.get(position);
        holder.rvHotelRoomBinding.setKamarHotel(hotelRoom);
        holder.rvHotelRoomBinding.executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return hotelRoomList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private RvHotelRoomBinding rvHotelRoomBinding;

        public viewHolder(RvHotelRoomBinding rvHotelRoomBinding) {
            super(rvHotelRoomBinding.getRoot());
            this.rvHotelRoomBinding = rvHotelRoomBinding;
        }
    }

}