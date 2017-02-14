package com.kingtvarshin.oasis2016new.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.kingtvarshin.oasis2016new.Adapter.Eventselect_cardAdapter;
import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 14-09-2016.
 *
 */
public class Fragment_eventselect extends Fragment {

    private ArrayList<String> eventid = new ArrayList<String>();
    private ArrayList<String> eventcatogary = new ArrayList<String>();
    private ArrayList<String> eventname = new ArrayList<String>();
    Button register;
    Firebase mRef, mRef2;
    RecyclerView recyclerView;

    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone_no";
    public static final String KEY_EMAIL = "email_id";
    public static final String KEY_COLLEGE = "college";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_CITY = "city";
    private static final String REGISTER_URL = "http://bits-oasis.org/2016/androidjson/";
    RecyclerView.Adapter adapter;

    private String name, gender, college, city, phoneno, email, head, year;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_eventselect, container, false);

//        CardView card = (CardView)rootView.findViewById(R.id.card);

        Bundle arguments = getArguments();
        name = arguments.getString("Name");
        gender = arguments.getString("Gender");
        college = arguments.getString("College");
        city = arguments.getString("City");
        phoneno = arguments.getString("PhoneNo");
        email = arguments.getString("Email");
        head = arguments.getString("Head");
        year = arguments.getString("Year");

        TextView textView = (TextView) rootView.findViewById(R.id.textViewTest);
        textView.setText(name+gender+college+city+phoneno+email+head+year);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view_eventselector);
        register = (Button)rootView.findViewById(R.id.button3);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "Test", Toast.LENGTH_LONG).show();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                                    }
                                }){
                            @Override
                            protected Map<String,String> getParams(){
                                Map<String,String> params = new HashMap<String, String>();
                                params.put("username", "androiduser");
                                params.put("password", "asdfghjkl");
                                params.put(KEY_NAME,name);
                                params.put(KEY_EMAIL, email);
                                params.put(KEY_PHONE, phoneno);
                                params.put(KEY_COLLEGE, college);
                                params.put(KEY_GENDER, gender);
                                params.put(KEY_CITY, city);
                                params.put("events", "8,2,64");
                                params.put("head_of_dept", head);
                                params.put("year_of_study", year);
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                        requestQueue.add(stringRequest);


                    }
                }
        );

//        card.setCardBackgroundColor(Color.BLUE);

        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        new LongOperation().execute("");

//        Log.v("adapter executed", "false");
//
//        RecyclerView.Adapter adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
//        recyclerView.setAdapter(adapter);
//
//        Log.v("adapter executed", "true");
//
//
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
//
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//            });
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if (child != null && gestureDetector.onTouchEvent(e)) {
//                    int position = rv.getChildAdapterPosition(child);
////                    dialContactPhone(number.get(position));
//                    Toast.makeText(getContext(), eventcatogary.get(position), Toast.LENGTH_SHORT).show();
//                }
//
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

        return rootView;
    }


    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            Firebase.setAndroidContext(getActivity());

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/dance");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/dance");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Dance");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/art");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/art");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Art");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/drama");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/drama");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Drama");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/misc");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/misc");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Misc");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/music");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/music");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Music");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/online");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/online");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Online");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/oratory");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/oratory");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Oratory");


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef = new Firebase("https://oasis-81d10.firebaseio.com/event/quizzing");
            mRef2 = new Firebase("https://oasis-81d10.firebaseio.com/event/id/quizzing");

            mRef2.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventid.add(value);
//                    adapter.notifyDataSetChanged();

                    Log.v("array list of IDs", eventid.toString());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

            mRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String value = dataSnapshot.getValue(String.class);
                    eventname.add(value);
                    eventcatogary.add("Quizzing");
                    Log.v("array list of Events", eventname.toString());
                    Log.v("array list of Category", eventcatogary.toString());


                    adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

//            eventid = new ArrayList<>();
//            eventid.add("Dance");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("Drama");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("");
//            eventid.add("");
//
//            eventcatogary = new ArrayList<>();
//            eventcatogary.add("Dance");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("Drama");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("");
//            eventcatogary.add("");
//
//            eventname = new ArrayList<>();
//            eventname.add("Choreo*");
//            eventname.add("Street Dance*");
//            eventname.add("Tandav");
//            eventname.add("Desert Duel*");
//            eventname.add("Razzmatazz*");
//            eventname.add("");
//            eventname.add("FashP*");
//            eventname.add("Mime*");
//            eventname.add("Stage play*");
//            eventname.add("Street Play*");
//            eventname.add("Metamorphosis*");
//            eventname.add("Apt To Act");

//            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.interrupted();
//                }
//            }

            Log.v("event name array list after doInBackground", eventname.toString());

            return null;
        }

        @Override
        protected void onPostExecute(String result) {


            RecyclerView.Adapter adapter = new Eventselect_cardAdapter(eventid,eventcatogary, eventname);
            recyclerView.setAdapter(adapter);

            Log.v("adapter executed", "true");

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
//                    dialContactPhone(number.get(position));
                        Toast.makeText(getContext(), eventcatogary.get(position), Toast.LENGTH_SHORT).show();
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
        }

        @Override
        protected void onPreExecute() {

            Log.v("pre execute", "it takes place here");
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}