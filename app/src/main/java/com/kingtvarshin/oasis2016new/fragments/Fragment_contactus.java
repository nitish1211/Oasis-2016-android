package com.kingtvarshin.oasis2016new.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.kingtvarshin.oasis2016new.Adapter.Contactus_cardAdapter;
import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 14-09-2016.
 */
public class Fragment_contactus extends Fragment {

    private ArrayList<String> name;
    private ArrayList<String> post;
    private ArrayList<String> mail;
    private ArrayList<String> number;
    private Context context;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view_contactus);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        name = new ArrayList<>();
        name.add("Aditya Shetty");
        name.add("Aditya Ruia");
        name.add("Mudit Marda");
        name.add("Tarun Kumar");
        name.add("Ketul Mathuria");
        name.add("Abhishek Prasad");
        name.add("Nalin Mujumdar");

        post = new ArrayList<>();
        post.add("Registration and Other Enquiries");
        post.add("Sponsorship and Marketing");
        post.add("Controls");
        post.add("Website and Online Payments");
        post.add("Reception and Accommodation");
        post.add("Art, Design and Publicity");
        post.add("Stage Controls");

        mail = new ArrayList<>();
        mail.add("pcr@bits-oasis.org");
        mail.add("sponsorship@bits-oasis.org");
        mail.add("controls@bits-oasis.org");
        mail.add("webmaster@bits-oasis.org");
        mail.add("recnacc@bits-oasis.org");
        mail.add("adp@bits-oasis.org");
        mail.add("stagecontrols@bits-oasis.org");

        number = new ArrayList<>();
        number.add("+91-7240105153");
        number.add("+91-9619617661");
        number.add("+91-8875544546");
        number.add("+91-9460973751");
        number.add("+91-9694874277");
        number.add("+91-8504004462");
        number.add("+91-8504001914");

        RecyclerView.Adapter adapter = new Contactus_cardAdapter(getContext(),name,post,mail,number);
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
                    dialContactPhone(number.get(position));
                    Toast.makeText(getContext(), name.get(position), Toast.LENGTH_SHORT).show();
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

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

}