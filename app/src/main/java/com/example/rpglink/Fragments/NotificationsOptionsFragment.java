package com.example.rpglink.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.rpglink.R;

public class NotificationsOptionsFragment extends Fragment {

    private SwitchCompat naoPerturbeSw;
    //private TextView naoPerturbeInTrackSimTxt, naoPerturbeInTrackNaoTxt;


    public NotificationsOptionsFragment() {
    }

    public static NotificationsOptionsFragment newInstance(String param1, String param2) {
        NotificationsOptionsFragment fragment = new NotificationsOptionsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications_options, container, false);

        //trecho pra mudar a fonte (via XML não funciona nessa ver.)
        naoPerturbeSw = view.findViewById(R.id.naoPerturbeSw);
        naoPerturbeSw.setSwitchTypeface(ResourcesCompat.getFont(getActivity(), R.font.ubuntu_medium));

        return view;
    }
}