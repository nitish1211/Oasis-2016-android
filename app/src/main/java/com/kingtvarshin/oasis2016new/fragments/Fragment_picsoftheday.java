package com.kingtvarshin.oasis2016new.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kingtvarshin.oasis2016new.R;
import com.squareup.picasso.Picasso;

/**
 * Created by lenovo on 11-10-2016.
 */

public class Fragment_picsoftheday extends Fragment {

    ProgressBar loading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_picoftheday, container, false);
        final String[] links = {"http://bits-oasis.org/gauss1.jpg",
                "http://bits-oasis.org/gauss2.jpg", "http://bits-oasis.org/gauss3.jpg", "http://bits-oasis.org/gauss4.jpg"};
        ImageView pic1=(ImageView) view.findViewById(R.id.pic1);
        ImageView pic2=(ImageView) view.findViewById(R.id.pic2);
        ImageView pic3=(ImageView) view.findViewById(R.id.pic3);
        ImageView pic4=(ImageView) view.findViewById(R.id.pic4);
        loading=(ProgressBar) view.findViewById(R.id.loading);
        loading.setMax(100);

        loading.setProgress(40);

        Picasso.with(getActivity()).load(links[0]).into(pic1,new com.squareup.picasso.Callback()
        {
            @Override
            public void onSuccess() {
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                loading.setVisibility(View.GONE);
                Toast.makeText(getActivity(),"Failed to Load Images",Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(getActivity()).load(links[1]).into(pic2);
        Picasso.with(getActivity()).load(links[2]).into(pic3);
        Picasso.with(getActivity()).load(links[3]).into(pic4);


        return view;
    }


}