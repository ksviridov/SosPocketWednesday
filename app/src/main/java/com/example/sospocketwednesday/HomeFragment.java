package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button bnExpenses, bnIncomes, bnBalance;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ((MainActivity) getActivity())
                .setActionBarTitle("Навигационное Меню");

        bnExpenses = view.findViewById(R.id.bn_expenses_fragment);
        bnIncomes = view.findViewById(R.id.bn_incomes_fragment);
        bnBalance = view.findViewById(R.id.bn_balance_fragment);

        bnExpenses.setOnClickListener(this);
        bnBalance.setOnClickListener(this);
        bnIncomes.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_expenses_fragment:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new ExpensesFragment(), null)
                        .addToBackStack(null).commit();
                break;
            case R.id.bn_balance_fragment:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new BalanceFragment(), null)
                        .addToBackStack(null).commit();
                break;
            case R.id.bn_incomes_fragment:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new IncomesFragment(), null)
                        .addToBackStack(null).commit();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        menu.findItem(R.id.sort).setVisible(false);

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
