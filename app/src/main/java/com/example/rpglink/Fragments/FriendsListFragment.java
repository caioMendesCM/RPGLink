package com.example.rpglink.Fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.rpglink.Adapters.FriendsListAdapter;
import com.example.rpglink.R;

import java.util.Objects;

public class FriendsListFragment extends DialogFragment {

    private RecyclerView friendsList;
    private FriendsListAdapter friendsListAdapter;

    public FriendsListFragment() {
        // Required empty public constructor
    }

    public static FriendsListFragment newInstance() {
        FriendsListFragment fragment = new FriendsListFragment();
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
        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);

        Objects.requireNonNull(getDialog()).getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        friendsList = view.findViewById(R.id.friendsListRecyclerVw);
        friendsListAdapter = new FriendsListAdapter(getContext());
        friendsList.setAdapter(friendsListAdapter);
        friendsList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = (int) (ViewGroup.LayoutParams.MATCH_PARENT);
            int height = (int) (ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setLayout(width, height);
        }
    }
}