package com.example.sospocketwednesday;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "accounts")
public class AccItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "account_name")
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
