package com.example.sospocketwednesday;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button bnExpenses, bnIncomes, bnBalance, addAccBn;
    private ListView listView;
//    private String[] accNumbers = {"1", "2", "3", "4", "5"};
//    private String[] accNames;
    private List<AccItem> accItems;
    private TextView selectedAccount;

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
        addAccBn = view.findViewById(R.id.addAccBn);

        bnExpenses.setOnClickListener(this);
        bnBalance.setOnClickListener(this);
        bnIncomes.setOnClickListener(this);
        addAccBn.setOnClickListener(this);


        listView = view.findViewById(R.id.listView);

        accItems = MainActivity.accountsDatabase.accountDao().getAllItems();

        final CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        if ((MainActivity.accountNam == "") && (accItems.size() > 0)){
//            MainActivity.accountNam = accNames[0];
            MainActivity.accountNam = accItems.get(0).getName();
            MainActivity.accountNumb = accItems.get(0).getId();
        }
        selectedAccount = view.findViewById(R.id.selectedAcc);
        selectedAccount.setText(MainActivity.accountNam);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.accountNumb = accItems.get(accItems.size() - position - 1).getId();
//                MainActivity.accountNam = accNames[position];
                MainActivity.accountNam = accItems.get(accItems.size() - position - 1).getName();
                selectedAccount.setText(MainActivity.accountNam);

//                listView.setSelection(position);

//                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                        new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_close_red_24dp)
                                .setTitle("Удаление счёта").setMessage("Вы хотите удалить счёт?")
                                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MainActivity.accountsDatabase.accountDao().deleteItem(accItems.get(accItems.size() - position - 1));
                                        customAdapter.notifyDataSetChanged();
                                        listView.setAdapter(customAdapter);
                                    }
                                }).setNegativeButton("Нет", null).show();

                        return true;
                    }
                }
        );

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
                break;
            case R.id.addAccBn:
                MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, new AddAccFragment(), null)
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

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
//            return accNames.length;
            return MainActivity.accountsDatabase.accountDao().getAllItems().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view1 = getLayoutInflater().inflate(R.layout.row, null);

            TextView name = view1.findViewById(R.id.accName);
            TextView number = view1.findViewById(R.id.accNumber);

//            name.setText(accNames[position]);
            name.setText(accItems.get(accItems.size() - position - 1).getName());
            number.setText(Integer.toString(position +1));

            return view1;
        }
    }
}
