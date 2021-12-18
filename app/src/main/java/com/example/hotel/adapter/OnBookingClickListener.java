package com.example.hotel.adapter;

import com.example.hotel.model.BookDetail;
import com.example.hotel.model.HotelRoom;

public interface OnBookingClickListener {
    void onItemClick(BookDetail bookDetail, HotelRoom hotelRoom);
    void onDeleteButtonClick(BookDetail bookDetail);
}
