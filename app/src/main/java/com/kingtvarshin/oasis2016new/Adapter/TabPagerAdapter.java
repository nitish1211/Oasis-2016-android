package com.kingtvarshin.oasis2016new.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kingtvarshin.oasis2016new.tabs.Tab_19;
import com.kingtvarshin.oasis2016new.tabs.Tab_20;
import com.kingtvarshin.oasis2016new.tabs.Tab_21;
import com.kingtvarshin.oasis2016new.tabs.Tab_22;
import com.kingtvarshin.oasis2016new.tabs.Tab_23;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public CharSequence getPageTitle(int position) {

        if (position == 0)
        {
            return "19th Oct";
        }
        if (position == 1)
        {
            return "20th Oct";
        }
        if (position == 2)
        {
            return "21th Oct";
        }
        if (position == 3)
        {
            return "22th Oct";
        }
        if (position == 4)
        {
            return "23th Oct";
        }

        return null;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new Tab_19();
            case 1:
                return new Tab_20();
            case 2:
                return new Tab_21();
            case 3:
                return new Tab_22();
            case 4:
                return new Tab_23();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 5;
    }

}
