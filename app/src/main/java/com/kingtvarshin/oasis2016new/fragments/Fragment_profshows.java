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

import com.kingtvarshin.oasis2016new.Adapter.Developers_cardAdapter;
import com.kingtvarshin.oasis2016new.Adapter.Profshow_cardAdapter;
import com.kingtvarshin.oasis2016new.Profshowdetail;
import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;

/**
 * Created by lenovo on 11-10-2016.
 */

public class Fragment_profshows extends Fragment {

    private ArrayList<String> profshowname;
    private ArrayList<Integer> profshowimgurl;
    private ArrayList<String> profshowlocation;
    private ArrayList<String> profshowtime;
    private ArrayList<String> profshowdesc;
    private ArrayList<String> profshowdate;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profshows, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view_profshow);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        profshowname = new ArrayList<>();
        profshowname.add("Classical Show");
        profshowname.add("Amit Trivedi");
        profshowname.add("Red Bull Tour Bus");
        profshowname.add("N20 Comedy Show");
        profshowname.add("Beat Thrills");
        profshowname.add("Hip Hop Live");
        profshowname.add("StoryBaazi");

        profshowimgurl = new ArrayList<>();
        profshowimgurl.add(R.drawable.eventmanager2);
        profshowimgurl.add(R.drawable.eventmanager2);
        profshowimgurl.add(R.drawable.eventmanager2);
        profshowimgurl.add(R.drawable.eventmanager2);
        profshowimgurl.add(R.drawable.eventmanager2);
        profshowimgurl.add(R.drawable.eventmanager2);
        profshowimgurl.add(R.drawable.blog);

        profshowlocation = new ArrayList<>();
        profshowlocation.add("Audi");
        profshowlocation.add("SR Lawns");
        profshowlocation.add("SR Lawns");
        profshowlocation.add("Audi");
        profshowlocation.add("Rotunda");
        profshowlocation.add("Rotunda");
        profshowlocation.add("Nab Audi");

        profshowtime = new ArrayList<>();
        profshowtime.add("TBA");
        profshowtime.add("TBA");
        profshowtime.add("TBA");
        profshowtime.add("TBA");
        profshowtime.add("11:00 PM");
        profshowtime.add("11:30 PM");
        profshowtime.add("3:00 PM");

        profshowdesc = new ArrayList<>();
        profshowdesc.add("The classical show will feature an enthralling flute\n" +
                "Jugalbandi concert between Pt. Ronu Majumdar and Flute Jayant.\n" +
                "They will be accompanied by Pt. Ram Kumar Mishra on the tabla\n" +
                "and Sri Neyveli Venkatesh on the Mridangam. Panditji is a Sangeet\n" +
                "Natak Akademi award recipient and Jayanth, a child prodigy, is an\n" +
                "A grade artist in All India Radio and Doordarshan.");
        profshowdesc.add("Play the moments, pause the memories, stop the pain,\n" +
                "rewind the happiness. This Oasis, get ready to be hit by the\n" +
                "explosive expression of humanity by Amit Trivedi Live at the Coke\n" +
                "Studio Night, with music that releases you from the tyranny of\n" +
                "conscious thought and frees your soul to the eternity.");
        profshowdesc.add("The Red Bull Tour Bus is riding all the way to BITS Pilani\n" +
                "this OASIS, so let their beats run your stream. Feel the chilling of\n" +
                "your spine, see the jaws drop, let it GIVE YOU WINGS. It's ZAEDEN\n" +
                "and SOUND AVTAR with EDM this time!");
        profshowdesc.add("This Oasis, prepare to get murdered as we have the Paavam Boy\n" +
                "Kenneth Sebastian coming down to Pilani! It's going to be a hysterical\n" +
                "evening as BuzzFeed's No. 1 Young Indian 2016 makes you LMAO\n" +
                "and ROFL!Kenny Sebastian is the current face of standup comedy in\n" +
                "India.\n" +
                "So get ready to get your funny bones tickled in a 2 hours laughter ride\n" +
                "as we refuse to spare you even a moment of acedia.");
        profshowdesc.add("Beat Thrills is a live concert by a Punjabi Singer Jassimran Singh\n" +
                "Keer, a ludhiana based singer coming here this OASIS with a DJ.\n" +
                "With some popular songs of his own, mixed with some very\n" +
                "popular tracks from other popular artists, get ready to dance to\n" +
                "the beats of most trending punjabi songs and be enthralled by the\n" +
                "singer's voice and rocking beat mixing by the DJ.");
        profshowdesc.add("HipHop Live is a live concert which sees performances from the\n" +
                "best hip-hop artists from all over the country. Rappers coming\n" +
                "down for this year are Naezy, Smokey, SlyckTwoshadez, Prabh\n" +
                "Deep , Ace along with Kinga Rhymes and Ninja from Mumbai's\n" +
                "Finest , Poetik Justis, Quixotic, Cizzy, Encore Abj and more.");
        profshowdesc.add("Divya Prakash Dubey, one of the eminent writers of “Nayi Waali\n" +
                "Hindi” is coming down this Oasis. The author of three bestselling\n" +
                "books ‘Terms & Condition Applied”, “Masala Chai” and “Musafir\n" +
                "cafe” is here in the town with all his chit-chat and experience\n" +
                "about his journey from an Engineer to a best selling author.\n" +
                "So, Be ready to be mesmerised by the non-classical way of\n" +
                "storytelling and experience zest of the purest form of art in its\n" +
                "crude form over a cup of  Masala Chai ");

        profshowdate = new ArrayList<>();
        profshowdate.add("20th October");
        profshowdate.add("21st October");
        profshowdate.add("22nd October");
        profshowdate.add("23rd October");
        profshowdate.add("21st October");
        profshowdate.add("22nd October");
        profshowdate.add("22nd October");

        RecyclerView.Adapter adapter = new Profshow_cardAdapter(getContext(),profshowname,profshowimgurl);
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
                    profDetails(position);
                    Toast.makeText(getContext(), profshowname.get(position), Toast.LENGTH_SHORT).show();
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

    public void profDetails(int profNum)
    {
        Intent i=new Intent(getActivity(),Profshowdetail.class);
        i.putExtra("profNum",profNum);
        i.putExtra("name",profshowname.get(profNum));
        i.putExtra("location",profshowlocation.get(profNum));
        i.putExtra("Image",profshowimgurl.get(profNum));
        i.putExtra("time",profshowtime.get(profNum));
        i.putExtra("desc",profshowdesc.get(profNum));
        i.putExtra("date",profshowdate.get(profNum));
        startActivity(i);
    }

}