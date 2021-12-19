package com.example.hotel.api;

import static com.example.hotel.api.ApiSource.BASE_URL;

public class ReviewApi {
    public static final String GET_ALL_REVIEW_ROOMS_URL = BASE_URL + "room-reviews/";
    public static final String GET_USER_REVIEW_ROOM_URL = BASE_URL + "user-room-reviews/";
    public static final String CREATE_REVIEW_URL = BASE_URL + "review";
    public static final String UPDATE_REVIEW_URL = BASE_URL + "review/";
}
