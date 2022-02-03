package com.example.rpglink.Fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rpglink.R;

public class MenuHamburguerFragment extends DialogFragment {

    private ImageView fecharMenu;
    private Button profile, configNotifications, securityOptions;

    public MenuHamburguerFragment() {
    }

    public static MenuHamburguerFragment newInstance() {
        MenuHamburguerFragment fragment = new MenuHamburguerFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_menu_hamburguer, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        fecharMenu = view.findViewById(R.id.fecharMenu);
        configNotifications = view.findViewById(R.id.configNotificationsBtn);
        securityOptions = view.findViewById(R.id.securityOptionsBtn);
        profile = view.findViewById(R.id.profile_btn);

        fecharMenu.setOnClickListener(v -> Navigation.findNavController(getActivity().findViewById(R.id.navController)).popBackStack());

        configNotifications.setOnClickListener(v -> Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_menuHamburguerFragment_to_notificationsOptionsFragment));

        securityOptions.setOnClickListener(v -> Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_menuHamburguerFragment_to_securityOptionsFragment));

        profile.setOnClickListener(v -> Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_menuHamburguerFragment_to_profileFragment));

        return view;

    }
}