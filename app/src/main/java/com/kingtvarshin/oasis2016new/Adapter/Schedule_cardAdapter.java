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
public class Schedule_cardAdapter extends RecyclerView.Adapter<Schedule_cardAdapter.ViewHolder> {

    private ArrayList<String> eventname;
    private ArrayList<String> time;
    private ArrayList<String> location;
    private int lastPosition=-1;
    private Context mContext;

    public Schedule_cardAdapter(Context context, ArrayList<String> eventname, ArrayList<String> time, ArrayList<String> location) {
        this.mContext = context;
        this.eventname = eventname;
        this.time = time;
        this.location = location;
    }

    @Override
    public Schedule_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_schedule, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Schedule_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_eventname.setText(eventname.get(i));
        viewHolder.tv_time.setText(time.get(i));
        viewHolder.tv_location.setText(location.get(i));
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
        return eventname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_eventname;
        private TextView tv_time;
        private TextView tv_location;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_eventname = (TextView)view.findViewById(R.id.namesc);
            tv_time = (TextView)view.findViewById(R.id.timesc);
            tv_location = (TextView) view.findViewById(R.id.locsc);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}