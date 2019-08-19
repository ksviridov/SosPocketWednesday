package com.example.sospocketwednesday;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void addItem(AccItem accItem);

    @Delete
    void deleteItem(AccItem accItem);


    @Query("SELECT * FROM accounts ORDER BY id DESC")
    List<AccItem> getAllItems();

}
