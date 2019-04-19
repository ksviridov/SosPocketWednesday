package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment {

    private TextView textTotal;
    private List<Item> items;
    private int total;

    public BalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);

        textTotal = view.findViewById(R.id.balance_total);

        items = MainActivity.incomesDatabase.itemDao().getAllItems();
        total = 0;

        for (Item item : items){
            total += Integer.parseInt(item.getPrice());
        }

        items = MainActivity.expensesDatabase.itemDao().getAllItems();

        for (Item item : items){
            total -= Integer.parseInt(item.getPrice());
        }

        textTotal.setText(Integer.toString(total));

        return view;
    }

}
