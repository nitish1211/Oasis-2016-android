package com.kingtvarshin.oasis2016new.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 05-09-2016.
 */
public class Updates_cardAdapter extends RecyclerView.Adapter<Updates_cardAdapter.ViewHolder> {

    private ArrayList<String> updates;
    private int lastPosition=-1;
    private Context mContext;

    public Updates_cardAdapter(Context context, ArrayList<String> updates) {
        this.mContext = context;
        this.updates = updates;
    }

    @Override
    public Updates_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_updates, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Updates_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_updates.setText(updates.get(i));
//        viewHolder.card.setAlpha(.3f);
        setAnimation(viewHolder.card, i);
//        if(i<=4){
//        viewHolder.card.setCardBackgroundColor(Color.BLUE);}
//        else if(i>=5)
//            viewHolder.card.setCardBackgroundColor(Color.RED);
    }

    private void setAnimation(FrameLayout container, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.home_fade);
        container.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return updates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_updates;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_updates = (TextView)view.findViewById(R.id.updatestf);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}