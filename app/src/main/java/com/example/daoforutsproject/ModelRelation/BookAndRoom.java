package com.example.daoforutsproject.ModelRelation;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.daoforutsproject.Model.BookDetail;
import com.example.daoforutsproject.Model.HotelRoom;

import java.util.List;

@Entity(tableName = "book_and_room")
public class BookAndRoom {
    @Embedded public HotelRoom hotelRoom;
    @Relation(
            parentColumn = "room_id",
            entityColumn = "fk_room_id",
            entity = BookDetail.class
    )public BookDetail bookDetail;

    public BookAndRoom() {
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }
}
