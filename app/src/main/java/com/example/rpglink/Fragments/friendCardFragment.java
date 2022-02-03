package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;

import com.example.rpglink.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;

public class friendCardFragment extends Fragment {

    private ShapeableImageView profilePic;

    public friendCardFragment() {
        // Required empty public constructor
    }

    public static friendCardFragment newInstance() {
        friendCardFragment fragment = new friendCardFragment();
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
        View view = inflater.inflate(R.layout.friends_card, container, false);



        profilePic = view.findViewById(R.id.friendsCardProfilePic);


        return view;
    }
}