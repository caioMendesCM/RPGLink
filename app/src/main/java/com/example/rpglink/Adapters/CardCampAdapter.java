package com.example.rpglink.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rpglink.Fragments.CardCampFragment;
import com.example.rpglink.Helpers.CardCampList;

public class CardCampAdapter extends FragmentStateAdapter{

    public CardCampAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public CardCampAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public CardCampAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new CardCampFragment(position);
    }

    @Override
    public int getItemCount() {
        return CardCampList.cardCampanhasPopList.size();
    }
}
