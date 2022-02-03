package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpglink.Adapters.SliderAdapter;
import com.example.rpglink.Helpers.DepthPageTransformer;
import com.example.rpglink.R;
import com.example.rpglink.models.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SlideBaseFragment extends Fragment {

    private ViewPager2 viewPager;
    private SliderAdapter adapter;
    private List<Fragment> list = new ArrayList<>();
    private TextInputEditText login, senha;
    private FirebaseAuth auth;
    private Usuario user;

    public SlideBaseFragment() {
        // Required empty public constructor
    }

    public static SlideBaseFragment newInstance(String param1, String param2) {
        SlideBaseFragment fragment = new SlideBaseFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slide_base, container, false);

        list.add(new SlideFragment1());
        list.add(new SlideFragment2());
        list.add(new LoginFragment());

        viewPager = view.findViewById(R.id.viewPager2);
        adapter = new SliderAdapter(this, list);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(new DepthPageTransformer());

        return view;
    }

    public void pular(){
        viewPager.setCurrentItem(2);
    }


}