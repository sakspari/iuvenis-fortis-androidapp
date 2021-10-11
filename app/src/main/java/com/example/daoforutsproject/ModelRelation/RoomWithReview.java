package com.example.daoforutsproject.ModelRelation;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomReview;

import java.util.List;

@Entity(tableName = "room_with_review")
public class RoomWithReview {
    @Embedded public HotelRoom hotelRoom;
    @Relation(
            parentColumn = "room_id",
            entityColumn = "fk_room_id",
            entity = RoomReview.class
    )public List<RoomReview> roomReviewList;

    public RoomWithReview(HotelRoom hotelRoom, List<RoomReview> roomReviewList) {
        this.hotelRoom = hotelRoom;
        this.roomReviewList = roomReviewList;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public List<RoomReview> getRoomReviewList() {
        return roomReviewList;
    }

    public void setRoomReviewList(List<RoomReview> roomReviewList) {
        this.roomReviewList = roomReviewList;
    }
}
