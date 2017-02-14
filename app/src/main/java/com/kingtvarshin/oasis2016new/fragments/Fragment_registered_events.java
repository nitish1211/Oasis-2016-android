package com.kingtvarshin.oasis2016new.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kingtvarshin.oasis2016new.Adapter.Registeredevents_cardAdapter;
import com.kingtvarshin.oasis2016new.Eventonclick;
import com.kingtvarshin.oasis2016new.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by lenovo on 14-09-2016.
 */
public class Fragment_registered_events extends Fragment {

    RecyclerView recyclerView;
    private Button button_send_push;
    private EditText editText_get_email;
    ArrayList<String> arrayList = new ArrayList<String>();;
    RecyclerView.Adapter adapter;
    public static final String REGISTER_URL = "http://bits-oasis.org/2016/android_login/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registered_events, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view_registeredevents);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Registeredevents_cardAdapter(arrayList);
        recyclerView.setAdapter(adapter);


        button_send_push = (Button) rootView.findViewById(R.id.button2);
        editText_get_email = (EditText) rootView.findViewById(R.id.editText6);

        final String[] email = {editText_get_email.getText().toString()};
        Log.v("email 1", "trial - " + email[0]);

        button_send_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("button pressed", "here");
                email[0] = editText_get_email.getText().toString();
                Log.v("email", "trial - " + email[0]);

//                RecyclerView.Adapter adapter = new Registeredevents_cardAdapter(arrayList);
                recyclerView.setAdapter(adapter);

                registerUser(email[0]);
            }
        });

//        RecyclerView.Adapter adapter = new Registeredevents_cardAdapter(arrayList);
//        recyclerView.setAdapter(adapter);

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
                    i.putExtra("eventtitle",arrayList.get(position));
                    startActivity(i);
                    Toast.makeText(getContext(), arrayList.get(position), Toast.LENGTH_SHORT).show();
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

    public void registerUser(final String email){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        if(response.toString()!="[\"Invalid Email\"]"){
                        String stringResponse = response;
                        StringTokenizer stringTokenizer = new StringTokenizer(stringResponse, ",");
                        while (stringTokenizer.hasMoreTokens()) {
                            arrayList.add(stringTokenizer.nextToken());
                            adapter.notifyDataSetChanged();
                        }
                        Toast.makeText(getActivity(),response.toString(), Toast.LENGTH_LONG).show();
                        Log.v("arraylist after tap : ", arrayList.toString());

//                        }
//                        else
//                            Toast.makeText(getActivity(),"Response message: "+  response.toString(), Toast.LENGTH_LONG).show();
                        Log.v("arraylistafteronRespons", arrayList.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                params.put("email_id", email);
                Log.v("params put", "here");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}