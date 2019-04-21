package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddExpensesFragment extends Fragment {

    private EditText editName, editPrice;
    private Button button;


    public AddExpensesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_expenses, container, false);

        ((MainActivity) getActivity())
                .setActionBarTitle("Добавить Расходы");

        editName = view.findViewById(R.id.name_expenses);
        editPrice = view.findViewById(R.id.price_expenses);
        button = view.findViewById(R.id.bn_add_expenses);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String price = editPrice.getText().toString();

                String mt = (DateFormat.format("MM", new java.util.Date()).toString());
                int myTime = Integer.parseInt(mt);

                Item item = new Item();
                item.setName(name);
                item.setPrice(price);
                item.setDate(myTime);

                MainActivity.expensesDatabase.itemDao().addItem(item);

                editName.setText("");
                editPrice.setText("");

                Toast.makeText(getActivity(), "Item Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
