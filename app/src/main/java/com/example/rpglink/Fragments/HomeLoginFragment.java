package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.rpglink.Adapters.CardCampAdapter;
import com.example.rpglink.Adapters.LastCampAdapter;
import com.example.rpglink.Helpers.CardCampList;
import com.example.rpglink.Helpers.DepthPageTransformer;
import com.example.rpglink.Helpers.LastCampList;
import com.example.rpglink.R;
import com.example.rpglink.models.CardCampanhasPop;
import com.example.rpglink.models.LastCampCard;

public class HomeLoginFragment extends Fragment {

    private ViewPager2 lastCampViewPager2, popCampViewPager2;
    private CardCampAdapter popAdapter;
    private LastCampAdapter lastAdapter;
    private FrameLayout menu;

    public HomeLoginFragment() {
        // Required empty public constructor
    }

    public static HomeLoginFragment newInstance(String param1, String param2) {
        HomeLoginFragment fragment = new HomeLoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home_l, container, false);

        criarLastCardList();

        if(CardCampList.cardCampanhasPopList.size() == 0){
            criarCardList();
        }

        lastAdapter         = new LastCampAdapter(this);
        popAdapter          = new CardCampAdapter(this);

        lastCampViewPager2  = view.findViewById(R.id.lastCampCard);
        popCampViewPager2   = view.findViewById(R.id.popCampCard);

        lastCampViewPager2.setAdapter(lastAdapter);
        popCampViewPager2.setAdapter(popAdapter);

        lastCampViewPager2.setPageTransformer(new DepthPageTransformer());
        popCampViewPager2.setPageTransformer(new DepthPageTransformer());

        menu = view.findViewById(R.id.menuBtn);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_homeLoginFragment_to_menuHamburguerFragment);
            }
        });

        return view;
    }

    public void criarLastCardList(){

        LastCampCard a = new LastCampCard();

        a.setRpgName("teste 1");
        a.setCampName("campanha teste 1");
        a.setPlayerNun("4");
        a.setCharacterName("Nome teste 1");
        a.setCharacterNvl("1");
        a.setPvStat("PV: 8");
        a.setHigherStat1("Con: 6");
        a.setHigherStat2("Str: 5");
        a.setHigherStat3("Car: 4");

        LastCampCard b = new LastCampCard();

        b.setRpgName("teste 2");
        b.setCampName("campanha teste 2");
        b.setPlayerNun("6");
        b.setCharacterName("Nome teste 2");
        b.setCharacterNvl("1");
        b.setPvStat("PV: 4");
        b.setHigherStat1("Int: 6");
        b.setHigherStat2("Car: 5");
        b.setHigherStat3("Agi: 4");

        LastCampCard c = new LastCampCard();

        c.setRpgName("teste 3");
        c.setCampName("campanha teste 3");
        c.setPlayerNun("3");
        c.setCharacterName("Nome teste 3");
        c.setCharacterNvl("1");
        c.setPvStat("PV: 6");
        c.setHigherStat1("Agi: 6");
        c.setHigherStat2("str: 5");
        c.setHigherStat3("Con: 4");

        LastCampList.lastCampCardList.add(a);
        LastCampList.lastCampCardList.add(b);
        LastCampList.lastCampCardList.add(c);


    }

    public void criarCardList(){

        CardCampanhasPop COC = new CardCampanhasPop();

        COC.setRpgName("Call Of Cthulhu");
        COC.setCampanhaName("A Ordem Paranormal");
        COC.setMestreName("Cellbit");
        COC.setFriendName("Felps");
        COC.setPlayerNun("3");

        CardCampanhasPop DED = new CardCampanhasPop();

        DED.setRpgName("Dungeons & Dragons");
        DED.setCampanhaName("NERDCAST RPG");
        DED.setMestreName("Leonel Caldela");
        DED.setFriendName("Azagal");
        DED.setPlayerNun("4");

        CardCampanhasPop CP = new CardCampanhasPop();

        CP.setRpgName("CyberPunk");
        CP.setCampanhaName("Uma Qualquer");
        CP.setMestreName("Eu");
        CP.setFriendName("Tu");
        CP.setPlayerNun("10");

        CardCampList.cardCampanhasPopList.add(COC);
        CardCampList.cardCampanhasPopList.add(DED);
        CardCampList.cardCampanhasPopList.add(CP);

    }
}