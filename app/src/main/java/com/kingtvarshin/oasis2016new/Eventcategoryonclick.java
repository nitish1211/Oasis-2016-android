package com.kingtvarshin.oasis2016new;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.kingtvarshin.oasis2016new.Adapter.Event_cardAdapter;
import com.kingtvarshin.oasis2016new.Model.EventModel;

import java.util.ArrayList;

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
 * Created by lenovo on 17-10-2016.
 */

public class Eventcategoryonclick extends AppCompatActivity {

    private ArrayList<String> eventtitle = new ArrayList<>();
    private ArrayList<String> eventtime = new ArrayList<>();
    private ArrayList<String> eventlocation = new ArrayList<>();
    private Context context;
    RecyclerView recyclerView;
    String myParam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventcategoryonclick);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_media_previous);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            myParam = extras.getString("eventcatogary");
        }
        else
        {
            //..oops!
        }
        setTitle(myParam);


        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view_eventcategoryonclick);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        eventtitle = new ArrayList<>();
//        eventtitle.add("Aditya Shetty");
//        eventtitle.add("Aditya Ruia");
//        eventtitle.add("Mudit Marda");
//        eventtitle.add("Tarun Kumar");
//        eventtitle.add("Ketul Mathuria");
//        eventtitle.add("Abhishek Prasad");
//        eventtitle.add("Nalin Mujumdar");
//
//        eventtime = new ArrayList<>();
//        eventtime.add("Registration and Other Enquiries");
//        eventtime.add("Sponsorship and Marketing");
//        eventtime.add("Controls");
//        eventtime.add("Website and Online Payments");
//        eventtime.add("Reception and Accommodation");
//        eventtime.add("Art, Design and Publicity");
//        eventtime.add("Stage Controls");
//
//        eventlocation = new ArrayList<>();
//        eventlocation.add("Registration and Other Enquiries");
//        eventlocation.add("Sponsorship and Marketing");
//        eventlocation.add("Controls");
//        eventlocation.add("Website and Online Payments");
//        eventlocation.add("Reception and Accommodation");
//        eventlocation.add("Art, Design and Publicity");
//        eventlocation.add("Stage Controls");

        new JSONTask().execute("http://bits-oasis.org/2016/events/summary/");

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(Eventcategoryonclick.this, new GestureDetector.SimpleOnGestureListener() {

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
                    Intent i=new Intent(Eventcategoryonclick.this,Eventonclick.class);
                    i.putExtra("eventtitle",eventtitle.get(position));
                    startActivity(i);
                    Toast.makeText(Eventcategoryonclick.this, eventtitle.get(position), Toast.LENGTH_SHORT).show();
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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//What to do on back clicked
            }
        });
    }

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

                JSONObject categoryObject;
                int i;
                for(i=0; i<parentArray.length()&& !parentArray.getJSONObject(i).getString("category").equals(myParam); i++);
//                    if(parentArray.getJSONObject(i).getString("category") == myParam)
//                        k=i;

                Log.d("value of k", String.valueOf(i));
                categoryObject = parentArray.getJSONObject(i);
                JSONArray finalArray = categoryObject.getJSONArray("events");

                Log.v("Length of array", String.valueOf(finalArray.length()));

                for (int j=0; j<finalArray.length(); j++){
                    JSONObject finalObject = finalArray.getJSONObject(j);
                    EventModel eventModel = new EventModel();
                    eventModel.setCategory(categoryObject.getString("category"));
                    eventModel.setContent(finalObject.getString("content"));
                    eventModel.setEvent(finalObject.getString("name"));
                    eventModel.setId(finalObject.getInt("id"));
                    eventModel.setKernel(finalObject.getBoolean("is_kernel"));
                    eventModel.setShortDesc(finalObject.getString("short_desc"));
                    eventModel.setLocation(finalObject.getString("venue"));
                    eventModel.setDate(String.valueOf(eventModel.getId() % 5 + 19));
                    eventModel.setTime(finalObject.getString("time"));
                    Log.v("Event Name", String.valueOf(j) + " - " + eventModel.getEvent() + " - " + eventModel.getDate() + " - " + eventModel);
                    eventModelList.add(eventModel);

                }

                Log.d("Event model list", eventModelList.toString());

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

            Log.d("list in result", result.toString());

            //TODO set list in adapter

            for(int i=0; i<result.size(); i++){
                eventtitle.add(result.get(i).getEvent());
                eventtime.add(result.get(i).getTime());
                eventlocation.add(result.get(0).getLocation());
            }
            final RecyclerView.Adapter adapter = new Event_cardAdapter(getApplicationContext(),eventtitle,eventtime, eventlocation);
            recyclerView.setAdapter(adapter);
        }
    }

}

