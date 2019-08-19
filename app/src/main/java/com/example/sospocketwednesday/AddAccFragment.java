package com.example.sospocketwednesday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddAccFragment extends Fragment {


    private Button addEditAcc;
    private EditText editAcc;

    public AddAccFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_acc, container, false);

        addEditAcc = view.findViewById(R.id.addEditAcc);

        editAcc = view.findViewById(R.id.editAccName);

//        String name = editName.getText().toString();
        addEditAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editAcc.getText().toString();
                AccItem item = new AccItem();
                item.setName(name);
                MainActivity.accountsDatabase.accountDao().addItem(item);
            }
        });
        return view;
    }

}
