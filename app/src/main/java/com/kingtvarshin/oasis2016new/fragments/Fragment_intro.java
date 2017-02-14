package com.kingtvarshin.oasis2016new.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kingtvarshin.oasis2016new.R;

/**
 * Created by lenovo on 14-09-2016.
 */
public class Fragment_intro extends Fragment {

    Button register,skip;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);

        register = (Button)rootView.findViewById(R.id.button4);
        skip = (Button)rootView.findViewById(R.id.button5);

        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, new Fragment_register());
                        ft.commit();
                        //write code here
                    }
                }
        );

        skip.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, new Fragment_about());
                        ft.commit();
                        //write your code here
                    }
                }
        );

        return rootView;
    }

}