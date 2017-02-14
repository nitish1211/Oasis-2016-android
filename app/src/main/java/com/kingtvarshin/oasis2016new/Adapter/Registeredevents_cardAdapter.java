package com.kingtvarshin.oasis2016new.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 05-09-2016.
 */
public class Registeredevents_cardAdapter extends RecyclerView.Adapter<Registeredevents_cardAdapter.ViewHolder> {

    private ArrayList<String> eventtitle;

    public Registeredevents_cardAdapter(ArrayList<String> eventtitle) {
        this.eventtitle = eventtitle;
    }

    @Override
    public Registeredevents_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_registeredevents, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Registeredevents_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_eventtitle.setText(eventtitle.get(i));
//        viewHolder.tv_Eventicon.setText(eventicon.get(i));
//        Picasso.with(viewHolder.tv_Sponsoricon.getContext()).load(eventtitle.get(i)).into(viewHolder.tv_Sponsoricon);
//        if(i<=4){
//        viewHolder.card.setCardBackgroundColor(Color.BLUE);}
//        else if(i>=5)
//            viewHolder.card.setCardBackgroundColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return eventtitle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_eventtitle;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_eventtitle = (TextView)view.findViewById(R.id.eventtitle);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}