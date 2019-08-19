package com.example.sospocketwednesday;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Switch dSwitch;
//    private String mode = "light";
//    public static String appTheme;
//    private Button themeBtn;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        ((MainActivity) getActivity())
                .setActionBarTitle("Настройки");

//        themeBtn = view.findViewById(R.id.themeBtn);
        dSwitch = view.findViewById(R.id.mySwitch);

//        themeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.holderBg.setBackgroundResource(R.color.grey);
//                ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.greydark)));
//                Objects.requireNonNull(getActivity()).getWindow().setStatusBarColor(getResources().getColor(R.color.greyblack));
////                ((MainActivity) getActivity()).getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red_force)));
////                ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable();
//
//            }
//        });

        if (MainActivity.appTheme == "night"){
            dSwitch.setChecked(true);
        }

        dSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    MainActivity.holderBg.setBackgroundResource(R.color.grey);
                    ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.greydark)));
                    Objects.requireNonNull(getActivity()).getWindow().setStatusBarColor(getResources().getColor(R.color.greyblack));
                    MainActivity.appTheme = "night";
                }
                else {
                    MainActivity.holderBg.setBackgroundResource(R.color.sky);
                    ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.design_default_color_primary)));
                    Objects.requireNonNull(getActivity()).getWindow().setStatusBarColor(getResources().getColor(R.color.design_default_color_primary_dark));
                    MainActivity.appTheme = "light";
                }
            }
        });


//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
//
//        }

        return view;
    }

}
