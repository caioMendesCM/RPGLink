package com.example.rpglink.config;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.animation.BounceInterpolator;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.rpglink.R;

/**
 * Created by root on 6/7/16.
 */
public class ItemAnimator extends SimpleItemAnimator {

    Context context;

    public ItemAnimator(Context context) {

        this.context = context;
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {

        Animator set = AnimatorInflater.loadAnimator(context,
                R.animator.zoom_in);
        set.setInterpolator(new BounceInterpolator());
        set.setTarget(holder.itemView);
        set.start();

        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        return false;
    }

    @Override
    public void runPendingAnimations() {

    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
