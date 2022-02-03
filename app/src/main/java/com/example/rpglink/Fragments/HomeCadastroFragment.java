package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rpglink.Adapters.CardCampAdapter;
import com.example.rpglink.Adapters.RpgListAdapter;
import com.example.rpglink.Helpers.CardCampList;
import com.example.rpglink.Helpers.DepthPageTransformer;
import com.example.rpglink.Helpers.HorizontalSpaceItemDecoration;
import com.example.rpglink.R;
import com.example.rpglink.models.CardCampanhasPop;

import java.util.ArrayList;
import java.util.List;

public class HomeCadastroFragment extends Fragment {

    private RecyclerView recyclerView;
    private RpgListAdapter adapter;
    private List<String> lista = new ArrayList<>();
    private ViewPager2 viewPager2;
    private CardCampAdapter adapterVP;
    private CardCampFragment fragmentAmigos;

    public HomeCadastroFragment() {
    }

    public static HomeCadastroFragment newInstance() {
        HomeCadastroFragment fragment = new HomeCadastroFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_c, container, false);

        criarTitulos();
        if(CardCampList.cardCampanhasPopList.size() == 0){ criarCardList(); }

        fragmentAmigos = new CardCampFragment();

        recyclerView = view.findViewById(R.id.rpgListView);

        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RpgListAdapter(lista);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(32));

        adapterVP = new CardCampAdapter(this);

        viewPager2 = view.findViewById(R.id.ViewPagerHome);
        viewPager2.setAdapter(adapterVP);
        viewPager2.setPageTransformer(new DepthPageTransformer());

        return view;
    }

    public void criarTitulos(){
        this.lista.add("D&D");
        this.lista.add("CyberPunk");
        this.lista.add("Call of Cthulhu");
        this.lista.add("Tormenta");
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