package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpglink.R;

public class PlayerCardFragment extends Fragment {

    public PlayerCardFragment() {
        // Required empty public constructor
    }

    public static PlayerCardFragment newInstance() {
        PlayerCardFragment fragment = new PlayerCardFragment();
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
        return inflater.inflate(R.layout.fragment_player_card, container, false);
    }
}