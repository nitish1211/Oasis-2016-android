package com.kingtvarshin.oasis2016new.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingtvarshin.oasis2016new.R;

/**
 * Created by lenovo on 12-10-2016.
 */

public class Tabeventonclick extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.tab_eventonclick,container,false);

        return v;
    }

    }
