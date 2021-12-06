package com.example.hotel.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel.R;
import com.example.hotel.databinding.RvReviewBinding;
import com.example.hotel.model.RoomReview;

import java.util.List;

public class RVReview extends RecyclerView.Adapter<RVReview.viewHolder>{
    RvReviewBinding binding;

    public RVReview(List<RoomReview> roomReviewList) {
        this.roomReviewList = roomReviewList;
    }

    List<RoomReview> roomReviewList;

    @NonNull
    @Override
    public RVReview.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_review, parent, false);
        return new viewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RVReview.viewHolder holder, int position) {
        RoomReview roomReview = roomReviewList.get(position);
        holder.rvReviewBinding.setReview(roomReview);
    }

    @Override
    public int getItemCount() {
        return roomReviewList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        RvReviewBinding rvReviewBinding;

        public viewHolder(RvReviewBinding rvReviewBinding) {
            super(rvReviewBinding.getRoot());
            this.rvReviewBinding = rvReviewBinding;
        }
    }
}
