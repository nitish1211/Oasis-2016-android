package com.kingtvarshin.oasis2016new.Adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 05-09-2016.
 */
public class Eventselect_cardAdapter extends RecyclerView.Adapter<Eventselect_cardAdapter.ViewHolder> {


    public ArrayList<String> selectedStrings = new ArrayList<String>();
    private ArrayList<String> eventid;
    private ArrayList<String> eventcatogary;
    private ArrayList<String> eventname;

    public Eventselect_cardAdapter(ArrayList<String> eventid,ArrayList<String> eventcatogary, ArrayList<String> eventname) {
        this.eventid = eventid;
        this.eventcatogary = eventcatogary;
        this.eventname = eventname;
    }

    @Override
    public Eventselect_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_eventselector, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Eventselect_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_Eventcatogary.setText(eventcatogary.get(i));
        viewHolder.tv_Eventname.setText(eventname.get(i));
        viewHolder.tv_Eventname.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            selectedStrings.add(eventname.toString());
                        }else{
                            selectedStrings.remove(eventname.toString());
                        }
                    }
                }
        );
        if(i<=4){
            viewHolder.card.setCardBackgroundColor(Color.BLUE);}
        else if(i>=5)
            viewHolder.card.setCardBackgroundColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return eventcatogary.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_Eventcatogary;
        private CheckBox tv_Eventname;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_Eventcatogary = (TextView)view.findViewById(R.id.textView4);
            tv_Eventname = (CheckBox) view.findViewById(R.id.checkBox);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}