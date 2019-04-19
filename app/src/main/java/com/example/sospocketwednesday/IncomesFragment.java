package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomesFragment extends Fragment {

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
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
