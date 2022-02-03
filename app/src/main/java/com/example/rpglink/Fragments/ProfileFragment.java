package com.example.rpglink.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.rpglink.R;
import com.example.rpglink.models.LastCampCard;


public class ProfileFragment extends Fragment {

    private FrameLayout lastCampaignFrame, playerCardFrame;
    private LastCampFragment lastCampaignFragment;
    private PlayerCardFragment playerCardFragment;
    private ImageView profilePicImgVw;
    private Button friends;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        lastCampaignFrame       = view.findViewById(R.id.profileLastCampaignCardFrame);
        lastCampaignFragment    = new LastCampFragment(1);
        playerCardFrame         = view.findViewById(R.id.playerCardFrame);
        playerCardFragment      = new PlayerCardFragment();
        profilePicImgVw         = view.findViewById(R.id.profilePicImgVw);
        profilePicImgVw.setImageResource(R.drawable.profile_picture);

        friends = view.findViewById(R.id.friendsBtn);
        friends.setOnClickListener(v -> openFriendsList());

        //getActivity().getSupportFragmentManager().beginTransaction().add(R.id.profileLastCampaignCardFrame,lastCampaignFragment);
        getChildFragmentManager().beginTransaction().add(R.id.profileLastCampaignCardFrame,lastCampaignFragment).commit();

        //getActivity().getSupportFragmentManager().beginTransaction().add(R.id.playerCardFrame,playerCardFragment);
        getChildFragmentManager().beginTransaction().add(R.id.playerCardFrame,playerCardFragment).commit();

        return view;
    }

    private void openFriendsList(){
        Navigation.findNavController(getActivity().findViewById(R.id.navController)).navigate(R.id.action_profileFragment_to_FriendsListFragment);
    }
}