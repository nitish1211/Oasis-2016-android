package com.kingtvarshin.oasis2016new.tabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kingtvarshin.oasis2016new.Adapter.Schedule_cardAdapter;
import com.kingtvarshin.oasis2016new.Eventonclick;
import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 11-10-2016.
 */

public class Tab_22 extends Fragment {

    private ArrayList<String> eventname;
    private ArrayList<String> time;
    private ArrayList<String> location;
    private ArrayList<String> desc;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_22, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view_tab22);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        eventname = new ArrayList<>();
        eventname.add("Vikrant Singh");
        eventname.add("Aditya Raj Agarwal");

        time = new ArrayList<>();
        time.add("8:00 PM");
        time.add("9:00 PM");

        location = new ArrayList<>();
        location.add("+91-7240105044");
        location.add("+91-8826248944");

        desc = new ArrayList<>();
        desc.add("+91-7240105044");
        desc.add("+91-8826248944");

        RecyclerView.Adapter adapter = new Schedule_cardAdapter(getContext(),eventname,time,location);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    Intent i=new Intent(getActivity(),Eventonclick.class);
                    i.putExtra("eventtitle",eventname.get(position));
                    startActivity(i);
                    Toast.makeText(getContext(), eventname.get(position), Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        return rootView;
    }

//    private void dialContactPhone(final String phoneNumber) {
//        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
//    }

}