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
public class Event_cardAdapter extends RecyclerView.Adapter<Event_cardAdapter.ViewHolder> {

    private ArrayList<String> eventtitle;
    private ArrayList<String> eventtime;
    private ArrayList<String> eventlocation;
    private Context mContext;

    public Event_cardAdapter(Context context,ArrayList<String> eventtitle, ArrayList<String> eventtime, ArrayList<String> eventlocation) {
        this.mContext = context;
        this.eventtitle = eventtitle;
        this.eventtime = eventtime;
        this.eventlocation = eventlocation;
    }

    @Override
    public Event_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Event_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_Eventtitle.setText(eventtitle.get(i));
        viewHolder.tv_Eventtime.setText(eventtime.get(i));
        viewHolder.tv_Eventlocation.setText(eventlocation.get(i));
        setAnimation(viewHolder.card, i);
//        viewHolder.card.setAlpha(.3f);
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
        return eventtitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_Eventtitle;
        private TextView tv_Eventtime;
        private TextView tv_Eventlocation;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_Eventtitle = (TextView)view.findViewById(R.id.textVieweventname);
            tv_Eventtime = (TextView) view.findViewById(R.id.textVieweventtime);
            tv_Eventlocation = (TextView) view.findViewById(R.id.textViewlocation);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}