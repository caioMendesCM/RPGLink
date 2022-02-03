package com.example.rpglink.Fragments;

import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.rpglink.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecurityOptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecurityOptionsFragment extends Fragment {
    private TextView todosRdTxt, amigosRdTxt;
    private RadioButton todosRdBtn, amigosRdBtn;
    private RadioGroup radioGroup;

    public static SecurityOptionsFragment newInstance() {
        SecurityOptionsFragment fragment = new SecurityOptionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_security_options, container, false);

        todosRdTxt = view.findViewById(R.id.todosRdTxt);
        amigosRdTxt = view.findViewById(R.id.amigosRdTxt);
        todosRdBtn  = view.findViewById(R.id.todosRdBtn);
        amigosRdBtn = view.findViewById(R.id.amigosRdBtn);
        radioGroup = view.findViewById(R.id.radioGroup);


        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(radioGroup.getCheckedRadioButtonId() == todosRdBtn.getId()){
                todosRdTxt.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_white));
                amigosRdTxt.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_blue));
            }else if(radioGroup.getCheckedRadioButtonId() == amigosRdBtn.getId()){
                amigosRdTxt.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_white));
                todosRdTxt.setTextColor(ContextCompat.getColor(getActivity(),R.color.app_blue));
            }
        });

        return view;
    }
}