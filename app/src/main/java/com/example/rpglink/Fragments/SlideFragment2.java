package com.example.rpglink.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.rpglink.R;

public class SlideFragment2 extends Fragment {

    private TextView textView;

    public SlideFragment2() {
        // Required empty public constructor
    }

    public static SlideFragment2 newInstance(String param1) {
        SlideFragment2 fragment = new SlideFragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.slide_2, container, false);

        final ViewPager2 viewPager2 = getActivity().findViewById(R.id.viewPager2);

        textView = view.findViewById(R.id.pular2Txt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(2);
            }
        });

        return view;
    }
}