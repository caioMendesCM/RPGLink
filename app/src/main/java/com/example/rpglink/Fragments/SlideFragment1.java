package com.example.rpglink.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.rpglink.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SlideFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlideFragment1 extends Fragment {

    private TextView textView;

    public SlideFragment1() {
    }

    public static SlideFragment1 newInstance(String param1, String param2) {
        SlideFragment1 fragment = new SlideFragment1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.slide_1, container, false);

        final ViewPager2 viewPager2 = getActivity().findViewById(R.id.viewPager2);

        textView = view.findViewById(R.id.pular1Txt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager2.setCurrentItem(2);
            }
        });

        return view;
    }



}