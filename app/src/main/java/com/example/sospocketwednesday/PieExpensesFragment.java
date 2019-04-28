package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PieExpensesFragment extends Fragment {


    private List<Item> items;
    private PieChart pieChart;
    private int raznoe;
    private int products;
    private int technique;
    private int cloth;
    private int entertainment;


    public PieExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_expenses, container, false);

        pieChart = view.findViewById(R.id.pie_chart_expenses);




        List<PieEntry> pieEntries = new ArrayList<>();

        items = MainActivity.expensesDatabase.itemDao().getAllItems();
        raznoe = 0;
        products = 0;
        technique = 0;
        cloth = 0;
        entertainment = 0;

        String mytime = (DateFormat.format("MM", new java.util.Date()).toString());

        for (Item item : items) {
            if (item.getDate() == Integer.parseInt(mytime)) {
                switch (item.getType()) {
                    case "Разное":
                        raznoe += Integer.parseInt(item.getPrice());
                        continue;
                    case "Продукты":
                        products += Integer.parseInt(item.getPrice());
                        continue;
                    case "Техника":
                        technique += Integer.parseInt(item.getPrice());
                        continue;
                    case "Одежда":
                        cloth += Integer.parseInt(item.getPrice());
                        continue;
                    case "Развлечения":
                        entertainment += Integer.parseInt(item.getPrice());
                        continue;
                }
            }
        }
        if (raznoe > 0){
            pieEntries.add(new PieEntry(raznoe, "Разное"));
        }

        if (products > 0){
            pieEntries.add(new PieEntry(products, "Продукты"));
        }

        if (entertainment > 0){
            pieEntries.add(new PieEntry(entertainment, "Развлечения"));
        }

        if (cloth > 0){
            pieEntries.add(new PieEntry(cloth, "Одежда"));
        }

        if (technique > 0){
            pieEntries.add(new PieEntry(technique, "Техника"));
        }

        pieChart.setVisibility(View.VISIBLE);
        pieChart.animateXY(2000, 2000);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "CheckOut");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

        Description description = new Description();
        description.setText("HAHAHAHA");
        pieChart.setDescription(description);

        pieChart.invalidate();

        return view;
    }

}
