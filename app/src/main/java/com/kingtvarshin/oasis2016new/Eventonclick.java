package com.kingtvarshin.oasis2016new;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kingtvarshin.oasis2016new.Model.EventModel;

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

public class Eventonclick extends AppCompatActivity {

    private CollapsingToolbarLayout ctbl = null;
    String myParam;
    TextView location;
    TextView time;
    TextView date;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventonclick);

    Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
    setSupportActionBar(toolbar);
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        myParam = extras.getString("eventtitle");

    } else {
        //..oops!
    }


    ctbl = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
    setTitle(myParam);


//        dynamicToolbarColor();

    toolbarTextAppearnce();

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();//What to do on back clicked
        }
    });


        new JSONTask().execute("http://bits-oasis.org/2016/events/summary/");
        location = (TextView)findViewById(R.id.locationtf);
        time = (TextView)findViewById(R.id.timetf);
        description = (TextView)findViewById(R.id.descripstiontf);
        date = (TextView)findViewById(R.id.datetf);
//        location.setText("Gym Grounds");
//        time.setText("8:00 PM");


}

    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.eventmanager2);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                ctbl.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                ctbl.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
            }
        });
    }

    private void toolbarTextAppearnce() {
        ctbl.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        ctbl.setExpandedTitleTextAppearance(R.style.expandedappbar);

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

                for (int i=0; i<parentArray.length(); i++){
                    JSONObject categoryObject = parentArray.getJSONObject(i);
                    EventModel eventModel = new EventModel();
                    eventModel.setCategory(categoryObject.getString("category"));
                    JSONArray finalArray = categoryObject.getJSONArray("events");

                    Log.v("Length of array", String.valueOf(finalArray.length()));

                    for (int j=0; j<finalArray.length(); j++){
                        JSONObject finalObject = finalArray.getJSONObject(j);
                        if(finalObject.getString("name").equals(myParam)) {
                            eventModel.setContent(finalObject.getString("appcontent"));
                            eventModel.setEvent(finalObject.getString("name"));
                            eventModel.setId(finalObject.getInt("id"));
                            eventModel.setKernel(finalObject.getBoolean("is_kernel"));
                            eventModel.setShortDesc(finalObject.getString("short_desc"));
                            eventModel.setLocation(finalObject.getString("venue"));
                            eventModel.setTime(finalObject.getString("time"));
                            eventModel.setDate(String.valueOf(eventModel.getId() % 5 + 19));
                            Log.v("Event Name", String.valueOf(j) + " - " + eventModel.getEvent() + " - " + eventModel.getDate() + " - " + eventModel);
                            eventModelList.add(eventModel);
                        }
                    }
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

//            name = (TextView) findViewById(R.id.nametf);
            description.setText(result.get(0).getContent());
            location.setText(result.get(0).getLocation());
            time.setText(result.get(0).getTime());
            date.setText("Date: "+result.get(0).getDate()+" Oct");
        }
    }




}
