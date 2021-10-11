package com.example.daoforutsproject.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "book_detail")
public class BookDetail {
    @PrimaryKey(autoGenerate = true)
    private int book_detail_id;

    @ColumnInfo(name = "fk_room_id")
    private String fk_room_id;

    @ColumnInfo(name = "fk_user_id")
    private String fk_user_id;

    @ColumnInfo(name = "check_in_date")
    private Date check_in_date;

    @ColumnInfo(name = "check_out_date")
    private Date check_out_date;
}
