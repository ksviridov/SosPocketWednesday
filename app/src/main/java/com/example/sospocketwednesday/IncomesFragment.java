package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomesFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private ItemAdapter adapter;
    private FloatingActionButton fab;
    private List<Item> items;

    public IncomesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_incomes, container, false);

        ((MainActivity) getActivity())
                .setActionBarTitle("Доходы");

        fab = view.findViewById(R.id.fab_incomes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new AddIncomesFragment(), null)
                        .addToBackStack(null).commit();
            }
        });

        items = MainActivity.incomesDatabase.itemDao().getAllItems();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_incomes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new ItemAdapter();
//        adapter.submitList(items);
        allTimeSort();
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                MainActivity.incomesDatabase.itemDao().deleteItem(adapter.getItemAt(viewHolder.getAdapterPosition()));
                allTimeSort();
//                adapter.submitList(MainActivity.incomesDatabase.itemDao().getAllItems());
                Toast.makeText(getActivity(), "Item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem sortItem = menu.findItem(R.id.sort);
        AppCompatImageButton bSort = (AppCompatImageButton) sortItem.getActionView();
//        bSort.setText("Sort");
        bSort.setBackgroundResource(R.drawable.ic_sort_white);

        bSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new SettingsFragment(), null)
                        .addToBackStack(null).commit();
                return true;
//            case R.id.sort:
//                Toast.makeText(getActivity(), "Sort", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.sort_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.thisMonth_sort:
                List<Item> lastMonth = new ArrayList<>();
                int counta = 0;

                String mytime = (DateFormat.format("MM", new java.util.Date()).toString());
                for (Item item1 : MainActivity.incomesDatabase.itemDao().getAllItems()){
                    if ((item1.getDate() == Integer.parseInt(mytime)) && (item1.getAccount() == MainActivity.accountNumb)){
                        lastMonth.add(counta, item1);
                        counta = counta + 1;
                    }
                }

                adapter.submitList(lastMonth);

                Toast.makeText(getActivity(), "Отсортировано", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.prevMonth_sort:
                List<Item> lastMonth1 = new ArrayList<>();
                int counta1 = 0;

                String mytime1 = (DateFormat.format("MM", new java.util.Date()).toString());
                for (Item item1 : MainActivity.incomesDatabase.itemDao().getAllItems()){
                    if ((item1.getDate() == (Integer.parseInt(mytime1) - 1)) && (item1.getAccount() == MainActivity.accountNumb)){
                        lastMonth1.add(counta1, item1);
                        counta1 = counta1 + 1;
                    }
                }

                adapter.submitList(lastMonth1);

                Toast.makeText(getActivity(), "Отсортировано", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.allTime_sort:
                allTimeSort();
//                adapter.submitList(MainActivity.incomesDatabase.itemDao().getAllItems());

                Toast.makeText(getActivity(), "Отсортировано", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }


    }

    private void allTimeSort(){
        List<Item> allTime = new ArrayList<>();
        int counta2 = 0;

        for (Item item1 : MainActivity.incomesDatabase.itemDao().getAllItems()){
            if (item1.getAccount() == MainActivity.accountNumb){
                allTime.add(counta2, item1);
                counta2 = counta2 + 1;
            }
        }

        adapter.submitList(allTime);
    }
}
