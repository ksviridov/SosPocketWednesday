package com.example.sospocketwednesday;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static ExpensesDatabase expensesDatabase;
    public static IncomesDatabase incomesDatabase;
    public static AccountsDatabase accountsDatabase;
    public static String appTheme = "light";
    public static View holderBg;
    public static int accountNumb = 0;
    public static String accountNam = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        appTheme = "Black";
//        setSupportActionBar();
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        holderBg = findViewById(R.id.holderBg);
        holderBg.setBackgroundResource(R.color.sky);

//        if (appTheme == "Black"){
//            holderBg.setBackgroundResource(R.color.red);
////            fmLay.setBackgroundResource(R.color.red);
//        }


        expensesDatabase = Room.databaseBuilder(getApplicationContext(), ExpensesDatabase.class, "incomes_database")
                .allowMainThreadQueries().build();

        incomesDatabase = Room.databaseBuilder(getApplicationContext(), IncomesDatabase.class, "expenses_database")
                .allowMainThreadQueries().build();

        accountsDatabase = Room.databaseBuilder(getApplicationContext(), AccountsDatabase.class, "accounts_database")
                .allowMainThreadQueries().build();

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment(), null).commit();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.settings:
//                MainActivity.fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, new SettingsFragment(), null)
//                        .addToBackStack(null).commit();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
