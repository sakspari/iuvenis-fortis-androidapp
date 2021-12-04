package com.example.hotel.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotel.R;
import com.example.hotel.databinding.RvHotelRoomBinding;
import com.example.hotel.model.HotelRoom;

import java.util.ConcurrentModificationException;
import java.util.List;

public class RVHotelRoom extends RecyclerView.Adapter<RVHotelRoom.viewHolder> {
    private List<HotelRoom> hotelRoomList;
    private Context context;
    RvHotelRoomBinding binding;

    public RVHotelRoom(List<HotelRoom> hotelRoomList) {
        this.hotelRoomList = hotelRoomList;
    }

    public RVHotelRoom(List<HotelRoom> hotelRoomList, Context context) {
        this.hotelRoomList = hotelRoomList;
        this.context = context;
    }

    @NonNull
    @Override
    public RVHotelRoom.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_hotel_room, parent, false);
        return new viewHolder(binding);
    }

    public void setHotelRoomList(List<HotelRoom> hotelRoomList) {
        this.hotelRoomList = hotelRoomList;
    }

    @Override
    public void onBindViewHolder(@NonNull RVHotelRoom.viewHolder holder, int position) {
        HotelRoom hotelRoom = hotelRoomList.get(position);
        holder.rvHotelRoomBinding.setKamarHotel(hotelRoom);
        if(hotelRoom.isRoom_status()){
            holder.rvHotelRoomBinding.setStatus("Available");
        }else{
            holder.rvHotelRoomBinding.setStatus("Currently Unavailable");
        }
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

            rvHotelRoomBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("id_room",rvHotelRoomBinding.getKamarHotel().getRoom_id());


                    //seleksi jalur fragment
                    if(Navigation.findNavController(binding.getRoot()).getCurrentDestination() == Navigation.findNavController(binding.getRoot()).getGraph().findNode(R.id.allRoomFragment)) {
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_allRoomFragment_to_detailRoom, bundle);
                    }
                    else
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_availableRoomFragment_to_detailRoom,bundle);
                }
            });
        }
    }

    @BindingAdapter("hotelImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_baseline_home_24)
                .into(view);

    }

}