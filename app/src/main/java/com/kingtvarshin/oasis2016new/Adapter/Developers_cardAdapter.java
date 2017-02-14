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
public class Developers_cardAdapter extends RecyclerView.Adapter<Developers_cardAdapter.ViewHolder> {

    private ArrayList<String> name;
    private ArrayList<String> work;
    private int lastPosition=-1;
    private Context mContext;

    public Developers_cardAdapter(Context context,ArrayList<String> name, ArrayList<String> work) {
        this.mContext = context;
        this.name = name;
        this.work = work;
    }

    @Override
    public Developers_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_developer, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Developers_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(name.get(i));
        viewHolder.tv_work.setText(work.get(i));
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
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_work;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.nametf);
            tv_work = (TextView) view.findViewById(R.id.worktf);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}