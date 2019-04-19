package com.example.sospocketwednesday;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static ExpensesDatabase expensesDatabase;
    public static IncomesDatabase incomesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expensesDatabase = Room.databaseBuilder(getApplicationContext(), ExpensesDatabase.class, "incomes_database")
                .allowMainThreadQueries().build();

        incomesDatabase = Room.databaseBuilder(getApplicationContext(), IncomesDatabase.class, "expenses_database")
                .allowMainThreadQueries().build();

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment(), null).commit();
        }
    }
}
