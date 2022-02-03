package com.example.rpglink.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.rpglink.Fragments.LastCampFragment;
import com.example.rpglink.Helpers.LastCampList;

public class LastCampAdapter extends FragmentStateAdapter {
    public LastCampAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public LastCampAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    public LastCampAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new LastCampFragment(position);
    }

    @Override
    public int getItemCount() {
        return LastCampList.lastCampCardList.size();
    }
}
