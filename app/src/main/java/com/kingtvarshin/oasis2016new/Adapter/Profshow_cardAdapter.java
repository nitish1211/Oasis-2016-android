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
import android.widget.ImageView;
import android.widget.TextView;

import com.kingtvarshin.oasis2016new.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lenovo on 05-09-2016.
 */
public class Profshow_cardAdapter extends RecyclerView.Adapter<Profshow_cardAdapter.ViewHolder> {

    private ArrayList<String> profshowname;
    private ArrayList<Integer> profshowimgurl;
    private int lastPosition=-1;
    private Context mContext;

    public Profshow_cardAdapter(Context context, ArrayList<String> profshowname, ArrayList<Integer> profshowimgurl) {
        this.mContext = context;
        this.profshowname = profshowname;
        this.profshowimgurl = profshowimgurl;
    }

    @Override
    public Profshow_cardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_profshow, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Profshow_cardAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_profshowname.setText(profshowname.get(i));
        viewHolder.tv_profshowimgurl.setImageResource(profshowimgurl.get(i));
//        viewHolder.card.setAlpha(.3f);
//        Picasso.with(viewHolder.tv_profshowimgurl.getContext()).load(profshowimgurl.get(i)).into(viewHolder.tv_profshowimgurl);
        setAnimation(viewHolder.card, i);
//        if(i<=4){
//        viewHolder.card.setCardBackgroundColor(Color.BLUE);}
//        else if(i>=5)
//            viewHolder.card.setCardBackgroundColor(Color.RED);
    }

    private void setAnimation(FrameLayout container, int position) {
        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
        container.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return profshowname.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_profshowname;
        private ImageView tv_profshowimgurl;
        CardView card;
        public ViewHolder(View view) {
            super(view);

            tv_profshowname = (TextView)view.findViewById(R.id.profshownametf);
            tv_profshowimgurl = (ImageView) view.findViewById(R.id.profshowimgurltf);

            card = (CardView)view.findViewById(R.id.card);
        }
    }

}