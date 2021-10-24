package com.example.hotel.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.R;
import com.example.hotel.database.MyDatabaseClient;
import com.example.hotel.databinding.RvBookDetailBinding;
import com.example.hotel.model.BookDetail;

import java.util.List;

public class RVBookingAdapter extends RecyclerView.Adapter<RVBookingAdapter.viewHolder> {

    private List<BookDetail> bookDetailList;
    RvBookDetailBinding binding;

    public RVBookingAdapter(List<BookDetail> bookDetailList) {
        this.bookDetailList = bookDetailList;
    }



    @NonNull
    @Override
    public RVBookingAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_book_detail, parent, false);

        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVBookingAdapter.viewHolder holder, int position) {
        BookDetail bookDetail = bookDetailList.get(position);
        holder.rvBookDetailBinding.setBookingDetail(bookDetail);
        holder.rvBookDetailBinding.setHotelRoom(MyDatabaseClient.getInstance(binding.getRoot().getContext()).getDatabase().hotelDao().roomFromId(bookDetail.getFk_room_id()));

        holder.rvBookDetailBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return bookDetailList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RvBookDetailBinding rvBookDetailBinding;

        public viewHolder(RvBookDetailBinding rvBookDetailBinding) {
            super(rvBookDetailBinding.getRoot());
            this.rvBookDetailBinding = rvBookDetailBinding;

            rvBookDetailBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id_booking",rvBookDetailBinding.getBookingDetail().getBook_detail_id());

                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_urBookedRoom_to_detailBooking,bundle);
                }
            });
        }

    }
}
