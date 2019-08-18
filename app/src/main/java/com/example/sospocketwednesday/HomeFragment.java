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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button bnExpenses, bnIncomes, bnBalance;
    private ListView listView;
    private String[] accNumbers = {"1", "2", "3", "4", "5"};
    private String[] accNames = {"Основной", "Кредитка", "Дебетовая", "Qiwi", "Заначка"};
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

        bnExpenses.setOnClickListener(this);
        bnBalance.setOnClickListener(this);
        bnIncomes.setOnClickListener(this);

        listView = view.findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        if (MainActivity.accountNam == ""){
            MainActivity.accountNam = accNames[0];
        }
        selectedAccount = view.findViewById(R.id.selectedAcc);
        selectedAccount.setText(MainActivity.accountNam);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.accountNumb = position;
                MainActivity.accountNam = accNames[position];
                selectedAccount.setText(MainActivity.accountNam);

                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
            }
        });

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

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return accNames.length;
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

            name.setText(accNames[position]);
            number.setText(Integer.toString(position +1));

            return view1;
        }
    }
}
