package com.develivery.cem.deliveryservice.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.develivery.cem.deliveryservice.activity.ApprovedFragment;
import com.develivery.cem.deliveryservice.activity.OnWayFragment;
import com.develivery.cem.deliveryservice.activity.DeliveredFragment;

/**
 * Created by cem on 17.04.2017.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position ==0) {
            return new ApprovedFragment();
        } else if (position == 1) {
            return new OnWayFragment();
        } else return new DeliveredFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
