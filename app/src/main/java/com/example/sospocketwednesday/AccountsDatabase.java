package com.example.sospocketwednesday;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {AccItem.class}, version = 1)
public abstract class AccountsDatabase extends RoomDatabase {

    public abstract AccountDao accountDao();
}
