package com.example.daoforutsproject.ModelRelation;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.daoforutsproject.Model.RoomReview;
import com.example.daoforutsproject.Model.User;

import java.util.List;

@Entity(tableName = "user_with_review")
public class UserWithReview {
    @Embedded public User user;
    @Relation(
            parentColumn = "username",
            entityColumn = "fk_username",
            entity = RoomReview.class
    )public List<RoomReview> reviewList;

    public UserWithReview(User user, List<RoomReview> reviewList) {
        this.user = user;
        this.reviewList = reviewList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<RoomReview> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<RoomReview> reviewList) {
        this.reviewList = reviewList;
    }
}
