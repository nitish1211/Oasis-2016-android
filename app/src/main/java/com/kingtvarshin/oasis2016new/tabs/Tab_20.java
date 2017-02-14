package com.kingtvarshin.oasis2016new.tabs;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kingtvarshin.oasis2016new.Adapter.Schedule_cardAdapter;
import com.kingtvarshin.oasis2016new.Eventonclick;
import com.kingtvarshin.oasis2016new.Model.EventModel;
import com.kingtvarshin.oasis2016new.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 11-10-2016.
 */

public class Tab_20 extends Fragment {

    private ArrayList<String> eventname = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> location = new ArrayList<>();
    RecyclerView recyclerView;
    private Context context;
    ProgressBar loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab_20, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view_tab20);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        loading = (ProgressBar) rootView.findViewById(R.id.loading);

        new JSONTask().execute("http://bits-oasis.org/2016/events/summary/");

        loading.setVisibility(View.GONE);
//        eventname = new ArrayList<>();
//        eventname.add("Vikrant Singh");
//        eventname.add("Aditya Raj Agarwal");
//
//        time = new ArrayList<>();
//        time.add("Registration and Other Enquiries");
//        time.add("Registration and Other Enquiries");
//
//        location = new ArrayList<>();
//        location.add("+91-7240105044");
//        location.add("+91-8826248944");
//
//        RecyclerView.Adapter adapter = new Schedule_cardAdapter(getContext(),eventname,time,location);
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
                    getActivity().setTitle(eventname.get(position));
                    Intent i=new Intent(getActivity(),Eventonclick.class);
                    i.putExtra("eventtitle",eventname.get(position));
                    startActivity(i);

//                    dialContactPhone(number.get(position));
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

    public class JSONTask extends AsyncTask<String, String, List<EventModel>> {

        @Override
        protected List<EventModel> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader((stream)));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                //TODO feed data here

                String finalJSON = buffer.toString();

                JSONArray parentArray = new JSONArray(finalJSON);

                List<EventModel> eventModelList = new ArrayList<>();

                for (int i=0; i<parentArray.length(); i++){
                    JSONObject categoryObject = parentArray.getJSONObject(i);
                    EventModel eventModel = new EventModel();
                    eventModel.setCategory(categoryObject.getString("category"));
                    JSONArray finalArray = categoryObject.getJSONArray("events");

                    Log.v("Length of array", String.valueOf(finalArray.length()));

                    for (int j=0; j<finalArray.length(); j++){
                        JSONObject finalObject = finalArray.getJSONObject(j);

                        if(finalObject.getInt("id")%5 + 19 == 20 ) {
                            eventModel.setContent(finalObject.getString("content"));
//                          eventModel.setContent("Some random shit");
                            eventModel.setEvent(finalObject.getString("name"));
                            eventModel.setId(finalObject.getInt("id"));
                            eventModel.setKernel(finalObject.getBoolean("is_kernel"));
                            eventModel.setShortDesc(finalObject.getString("short_desc"));
                            eventModel.setLocation(String.valueOf(finalObject.getBoolean("is_kernel")));
                            eventModel.setDate(String.valueOf(finalObject.getString("date")));
                            Log.v("Event Name", String.valueOf(j) + " - " + eventModel.getEvent() + " - " + eventModel.getDate());
                            eventModelList.add(eventModel);
                        }
                    }
                }
                return eventModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<EventModel> result) {
            super.onPostExecute(result);
            //TODO set list in adapter

            for(int i=0; i<result.size(); i++){
                eventname.add(result.get(i).getEvent());
                time.add(result.get(i).getCategory());
                location.add(result.get(i).getLocation());
            }

            Log.v("event name", eventname.toString());
            Log.v("time", time.toString());
            Log.v("Location", location.toString());

            RecyclerView.Adapter adapter = new Schedule_cardAdapter(getContext(),eventname,time,location);
            recyclerView.setAdapter(adapter);

        }
    }

}