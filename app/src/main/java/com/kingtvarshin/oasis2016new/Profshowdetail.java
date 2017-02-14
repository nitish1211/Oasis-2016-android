package com.kingtvarshin.oasis2016new;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lenovo on 14-10-2016.
 */

public class Profshowdetail extends AppCompatActivity {

    private CollapsingToolbarLayout ctbl = null;
    String myParam;
    String loc,tim,dat,desc;
    Integer img;
    TextView location,time,date,description;
    ImageView iv;
    Drawable d;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profshowdetail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        location = (TextView)findViewById(R.id.locationtf);
        time = (TextView)findViewById(R.id.timetf);
        description = (TextView)findViewById(R.id.descripstiontf);
        date = (TextView)findViewById(R.id.datetf);
        iv = (ImageView)findViewById(R.id.collapsing_toolbar_iv);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            myParam = extras.getString("name");
            loc = extras.getString("location");
            tim = extras.getString("time");
            dat = extras.getString("date");
            desc = extras.getString("desc");
            img = extras.getInt("Image");
        }
        else
        {
            //..oops!
        }



        ctbl = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ctbl.setTitle(myParam);

//        dynamicToolbarColor();

        toolbarTextAppearnce();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//What to do on back clicked
            }
        });

        description.setText(desc);
        location.setText(loc);
        time.setText(tim);
        date.setText(dat);
//        iv.setImageResource(img);
        iv.setBackground(getDrawable(img));

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


}
