package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpglink.Helpers.CardCampList;
import com.example.rpglink.Helpers.LastCampList;
import com.example.rpglink.R;

public class LastCampFragment extends Fragment {

    private TextView rpgName, campName, playerNun, characterName, characterNvl, pvStat, higherStat1, higherStat2, higherStat3;

    int position;

    public LastCampFragment() {
        // Required empty public constructor
    }

    public LastCampFragment(int position) {
        this.position = position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static LastCampFragment newInstance() {
        LastCampFragment fragment = new LastCampFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.last_camp_card, container, false);

        rpgName         = view.findViewById(R.id.rpgNameTxt);
        campName        = view.findViewById(R.id.nomeCampanhaTxt);
        playerNun       = view.findViewById(R.id.campPlayersTxt);
        characterName   = view.findViewById(R.id.characterName);
        characterNvl    = view.findViewById(R.id.nivelValue);
        pvStat          = view.findViewById(R.id.pvStat);
        higherStat1     = view.findViewById(R.id.higherStat_1);
        higherStat2     = view.findViewById(R.id.higherStat_2);
        higherStat3     = view.findViewById(R.id.higherStat_3);

        rpgName         .setText(LastCampList.lastCampCardList.get(position).getRpgName());
        campName        .setText(LastCampList.lastCampCardList.get(position).getCampName());
        playerNun       .setText(LastCampList.lastCampCardList.get(position).getPlayerNun());
        characterName   .setText(LastCampList.lastCampCardList.get(position).getCharacterName());
        characterNvl    .setText(LastCampList.lastCampCardList.get(position).getCharacterNvl());
        pvStat          .setText(LastCampList.lastCampCardList.get(position).getPvStat());
        higherStat1     .setText(LastCampList.lastCampCardList.get(position).getHigherStat1());
        higherStat2     .setText(LastCampList.lastCampCardList.get(position).getHigherStat2());
        higherStat3     .setText(LastCampList.lastCampCardList.get(position).getHigherStat3());



        return view;
    }
}