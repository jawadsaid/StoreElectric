package com.example.jawad.DrinkTonight.Drawer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jawad on 3/25/2016.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SendMailFragment();
            case 1:
                return new RecievedMailFragment();
            case 2:
                return new HomeFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Send Mail";
            case 1:
                return "Recieved Mail";
            case 2:
                return "Settings";

        }
        return null;
    }
}
