package com.example.sospocketwednesday;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Item.class}, version = 1)
public abstract class ExpensesDatabase extends RoomDatabase {

    public abstract ItemDao itemDao();
}
