package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpensesFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private EditText editName, editPrice;
    private Button button;
    private Button bType;


    public AddExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_add_expenses, container, false);

        ((MainActivity) getActivity())
                .setActionBarTitle("Добавить Расходы");

        editName = view.findViewById(R.id.name_expenses);
        editPrice = view.findViewById(R.id.price_expenses);
        button = view.findViewById(R.id.bn_add_expenses);
        bType = view.findViewById(R.id.button_popup);

        bType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        button.setEnabled(false);

        editName.addTextChangedListener(itemTextWatcher);



        editPrice.addTextChangedListener(itemTextWatcher);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String price = editPrice.getText().toString();
                String type = bType.getText().toString();

                String mt = (DateFormat.format("MM", new java.util.Date()).toString());
                int myTime = Integer.parseInt(mt);

                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setDate(myTime);
                item.setType(type);
                item.setAccount(MainActivity.accountNumb);

                MainActivity.expensesDatabase.itemDao().addItem(item);

                editName.setText("");
                editPrice.setText("");

                Toast.makeText(getActivity(), "Добавлено", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private TextWatcher itemTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = editName.getText().toString().trim();
            String price = editPrice.getText().toString().trim();

            button.setEnabled(!name.isEmpty() && !price.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.products_popup:
                bType.setText("Продукты");
                return true;
            case R.id.technique_popup:
                bType.setText("Техника");
                return true;
            case R.id.entertainment_popup:
                bType.setText("Развлечения");
                return true;
            case R.id.cloth_popup:
                bType.setText("Одежда");
                return true;
            case R.id.different_popup:
                bType.setText("Разное");
                return true;
            default:
                return false;
        }
    }



}
