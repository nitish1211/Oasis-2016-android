package com.kingtvarshin.oasis2016new.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingtvarshin.oasis2016new.R;

/**
 * Created by lenovo on 14-09-2016.
 */
public class Fragment_howtoreach extends Fragment {

    Typeface tf1;
    TextView t1,t2,t3,t4,t5,t6,t7,t8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_howtoreach, container, false);

        t1 = (TextView)rootView.findViewById(R.id.textView9);
        t2 = (TextView)rootView.findViewById(R.id.textView10);
        t3 = (TextView)rootView.findViewById(R.id.textView11);
        t4 = (TextView)rootView.findViewById(R.id.textView12);
        t5 = (TextView)rootView.findViewById(R.id.textView13);
        t6 = (TextView)rootView.findViewById(R.id.textView14);
        t7 = (TextView)rootView.findViewById(R.id.textView15);
        t8 = (TextView)rootView.findViewById(R.id.textView16);

        tf1 = Typeface.createFromAsset(getContext().getAssets(),"fonts/RobotoReguler.ttf");

        t1.setTypeface(tf1);
        t2.setTypeface(tf1);
        t3.setTypeface(tf1);
        t4.setTypeface(tf1);
        t5.setTypeface(tf1);
        t6.setTypeface(tf1);
        t7.setTypeface(tf1);
        t8.setTypeface(tf1);

        return rootView;
    }

}