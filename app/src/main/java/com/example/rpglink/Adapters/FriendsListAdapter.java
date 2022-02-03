package com.example.rpglink.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rpglink.R;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.MyViewHolder> {

    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView profilePic;
        private final FrameLayout actionButton;
        private final TextView userName, friendsNumber, campaignNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.friendsCardProfilePic);
            actionButton = itemView.findViewById(R.id.friendsCardActionBtn);
            userName = itemView.findViewById(R.id.friendsCardUsername);
            friendsNumber = itemView.findViewById(R.id.friendsCardFriendsNumber);
            campaignNumber = itemView.findViewById(R.id.friendsCardCampaignNumber);
        }

        public ImageView getProfilePic(){
            return profilePic;
        }

        public FrameLayout getActionButton() {
            return actionButton;
        }

        public TextView getUserName() {
            return userName;
        }

        public TextView getFriendsNumber() {
            return friendsNumber;
        }

        public TextView getCampaignNumber() {
            return campaignNumber;
        }
    }

    public FriendsListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

}
