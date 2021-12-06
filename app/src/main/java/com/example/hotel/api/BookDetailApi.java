package com.example.hotel.api;

import static com.example.hotel.api.ApiSource.BASE_URL;

public class BookDetailApi {
    public static final String GET_ALL_USER_BOOKINGS_URL = BASE_URL + "user-bookings/"; //ambil data bookingan dari user tertentu
    public static final String CREATE_BOOKING_URL = BASE_URL + "booking";
    public static final String DELETE_BY_ID = BASE_URL + "booking/";
}
