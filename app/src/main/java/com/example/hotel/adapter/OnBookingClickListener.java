package com.example.hotel.adapter;

import com.example.hotel.model.BookDetail;

public interface OnBookingClickListener {
    void onItemClick(BookDetail bookDetail);
    void onDeleteButtonClick(BookDetail bookDetail);
}
