package com.kingtvarshin.oasis2016new.fragments;

import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingtvarshin.oasis2016new.Adapter.Schedule_cardAdapter;
import com.kingtvarshin.oasis2016new.Model.EventModel;
import com.kingtvarshin.oasis2016new.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kingtvarshin.oasis2016new.R.id.loading;

/**
 * Created by lenovo on 11-10-2016.
 */

public class Fragment_eventsnow extends Fragment {

    private ArrayList<String> eventname = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> location = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_eventsnow, container, false);

        new JSONTask().execute("http://bits-oasis.org/2016/events/summary/");

        return rootView;
    }

    public class JSONTask extends AsyncTask<String, String, List<EventModel>> {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<EventModel> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                Calendar c = Calendar.getInstance();
                c.setTimeZone(TimeZone.getTimeZone("GMT+05:30"));
                int minutes = c.get(Calendar.MINUTE);
                int hours = c.get(Calendar.HOUR_OF_DAY);
                int date = c.get(Calendar.DATE);

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
                    JSONArray finalArray = categoryObject.getJSONArray("events");
                    eventModel.setCategory(categoryObject.getString("category"));

                    Log.v("Length of array", String.valueOf(finalArray.length()));

                    for (int j=0; j<finalArray.length(); j++){
                        JSONObject finalObject = finalArray.getJSONObject(j);

                        Log.d("event date and time", finalObject.getString("date") + " - " + finalObject.getString("time"));

                        if(finalObject.getString("date").equals("TBA"))
                            return null;

                        if(Integer.parseInt(finalObject.getString("date").substring(0,1)) - date == 0) {
                            if (Integer.parseInt(finalObject.getString("time").substring(0, 1)) - hours <= 4) {
                                eventModel.setContent(finalObject.getString("content"));
                                eventModel.setEvent(finalObject.getString("name"));
                                eventModel.setId(finalObject.getInt("id"));
                                eventModel.setKernel(finalObject.getBoolean("is_kernel"));
                                eventModel.setShortDesc(finalObject.getString("short_desc"));
                                eventModel.setLocation(finalObject.getString("venue"));
                                eventModel.setTime(finalObject.getString("time"));
                                eventModel.setDate(finalObject.getString("date"));
                                Log.v("Event Name", String.valueOf(j) + " - " + eventModel.getEvent() + " - " + eventModel.getDate() + " - " + eventModel);
                                eventModelList.add(eventModel);
                            }
                        }
                        else if ((Integer.parseInt(finalObject.getString("date").substring(0,1)) - date == 1) && (Integer.parseInt(finalObject.getString("time").substring(0,1)) - (hours-24) <=4)){
                            eventModel.setContent(finalObject.getString("content"));
                            eventModel.setEvent(finalObject.getString("name"));
                            eventModel.setId(finalObject.getInt("id"));
                            eventModel.setKernel(finalObject.getBoolean("is_kernel"));
                            eventModel.setShortDesc(finalObject.getString("short_desc"));
                            eventModel.setLocation(finalObject.getString("venue"));
                            eventModel.setTime(finalObject.getString("time"));
                            eventModel.setDate(finalObject.getString("date"));
                            Log.v("Event Name", String.valueOf(j) + " - " + eventModel.getEvent() + " - " + eventModel.getDate() + " - " + eventModel);
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
            if(result!=null)
                for(int i=0; i<result.size(); i++){
                    eventname.add(result.get(i).getEvent());
                    time.add(result.get(i).getCategory());
                    location.add(result.get(i).getLocation());
                }
        }
    }
}