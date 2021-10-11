package com.example.daoforutsproject.ModelRelation;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.daoforutsproject.Model.BookDetail;
import com.example.daoforutsproject.Model.User;

import java.util.List;

@Entity(tableName = "user_with_book")
public class UserWithBook {
    @Embedded public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "fk_username",
            entity = BookDetail.class
    )public List<BookDetail> userBookings;

    public UserWithBook() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BookDetail> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<BookDetail> userBookings) {
        this.userBookings = userBookings;
    }
}
