package com.example.daoforutsproject.ModelRelation;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.daoforutsproject.Model.HotelRoom;
import com.example.daoforutsproject.Model.RoomDetail;

@Entity(tableName = "room_with_detail")
public class RoomWithDetail {
    @Embedded public HotelRoom hotelRoom;
    @Relation(
            parentColumn = "room_id",
            entityColumn = "fk_room_id",
            entity = RoomDetail.class
    )
    public RoomDetail roomDetail;

    public RoomWithDetail() {
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public RoomDetail getRoomDetail() {
        return roomDetail;
    }

    public void setRoomDetail(RoomDetail roomDetail) {
        this.roomDetail = roomDetail;
    }
}
