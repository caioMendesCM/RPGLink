package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rpglink.Helpers.CardCampList;
import com.example.rpglink.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardCampFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardCampFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView rpgName, campanhaName, mestreName, friendName, playerNum;

    int position;

    public CardCampFragment() {
        // Required empty public constructor
    }

    public CardCampFragment(int position) {
        this.position = position;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment CardCampAmigosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardCampFragment newInstance() {
        CardCampFragment fragment = new CardCampFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setPosition(int position){
        this.position = position;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_camp, container, false);

            rpgName = view.findViewById(R.id.rpgNameTxt);
            campanhaName = view.findViewById(R.id.nomeCampanhaTxt);
            mestreName = view.findViewById(R.id.nomeMestreCampanhaTxt);
            friendName = view.findViewById(R.id.nomeAmigoCampanhaTxt);
            playerNum = view.findViewById(R.id.campPlayersTxt);

            rpgName.setText(CardCampList.cardCampanhasPopList.get(position).getRpgName());
            campanhaName.setText(CardCampList.cardCampanhasPopList.get(position).getCampanhaName());
            mestreName.setText(CardCampList.cardCampanhasPopList.get(position).getMestreName());
            friendName.setText(CardCampList.cardCampanhasPopList.get(position).getFriendName());
            playerNum.setText(CardCampList.cardCampanhasPopList.get(position).getPlayerNun());

        return view;
    }
}