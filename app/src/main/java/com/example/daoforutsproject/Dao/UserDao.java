package com.example.daoforutsproject.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.daoforutsproject.Model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Transaction
    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

}
