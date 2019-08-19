package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BalanceFragment extends Fragment {

    private TextView textTotal;
    private List<Item> items = new ArrayList<>();
    private int total;
    private BarChart barChart;
    private int price;
    private int exps;
    private int incs;
    private Button expenses;
    private Button incomes;
    int counta;

    public BalanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_balance, container, false);

        barChart = view.findViewById(R.id.bar_chart);

        textTotal = view.findViewById(R.id.balance_total);

        expenses = view.findViewById(R.id.rashi);
        incomes = view.findViewById(R.id.dohod);

        ((MainActivity) getActivity())
                .setActionBarTitle("Баланс");

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new PieExpensesFragment(), null)
                        .addToBackStack(null).commit();
            }
        });

        counta = 0;
//        items = MainActivity.incomesDatabase.itemDao().getAllItems();

        for (Item item1 : MainActivity.incomesDatabase.itemDao().getAllItems()){
            if (item1.getAccount() == MainActivity.accountNumb){
                items.add(counta, item1);
                counta = counta + 1;
            }
        }

        total = 0;

        price = 0;

        incs = 0;

        String mytime = (DateFormat.format("MM", new java.util.Date()).toString());

        List<BarEntry> barEntries = new ArrayList<>();

        for (Item item : items){
            if (item.getDate() == Integer.parseInt(mytime)){
                incs += Integer.parseInt(item.getPrice());
            }
            total += Integer.parseInt(item.getPrice());
        }
        barEntries.add(new BarEntry(3, new float[]{0, incs}));

        counta = 0;
//        items = MainActivity.expensesDatabase.itemDao().getAllItems();
        items = new ArrayList<>();

        for (Item item1 : MainActivity.expensesDatabase.itemDao().getAllItems()){
            if (item1.getAccount() == MainActivity.accountNumb){
                items.add(counta, item1);
                counta = counta + 1;
            }
        }

        exps = 0;
        for (Item item : items){
            price = Integer.parseInt(item.getPrice());
            if (item.getDate() == Integer.parseInt(mytime)){
                exps += Integer.parseInt(item.getPrice());
            }
            total -= price;
        }
        barEntries.add(new BarEntry(1, new float[]{0, exps}));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Сводка");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        barData.setValueTextColor(getResources().getColor(R.color.white));


        barChart.setVisibility(View.VISIBLE);
        barChart.animateY(500);
        barChart.setData(barData);
        barChart.setFitBars(true);
//        barChart

        Description description = new Description();
        description.setText("Расходы и Доходы");
        description.setTextColor(getResources().getColor(R.color.white));
        barChart.setDescription(description);
//        barChart.setNoDataTextColor(getResources().getColor(R.color.white));
        barChart.invalidate();

        mytime = (DateFormat.format("MM", new java.util.Date()).toString());

        String month = "";
        switch (Integer.parseInt(mytime)){
            case 1:
                month = "Январь";
                break;
            case 2:
                month = "Февраль";
                break;
            case 3:
                month = "Март";
                break;
            case 4:
                month = "Апрель";
                break;
            case 5:
                month = "Мая";
                break;
            case 6:
                month = "Июнь";
                break;
            case 7:
                month = "Июль";
                break;
            case 8:
                month = "Август";
                break;
            case 9:
                month = "Сентябрь";
                break;
            case 10:
                month = "Октябрь";
                break;
            case 11:
                month = "Ноябрь";
                break;
            case 12:
                month = "Декабрь";
        }

        textTotal.setText(Integer.toString(total) + " за " + month);

        return view;
    }

}
