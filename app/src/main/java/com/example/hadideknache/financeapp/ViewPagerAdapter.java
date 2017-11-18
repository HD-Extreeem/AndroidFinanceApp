package com.example.hadideknache.financeapp;

import android.os.Parcelable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hadideknache.financeapp.Fragments.ExpenditureFragment;
import com.example.hadideknache.financeapp.Fragments.IncomeFragment;
import com.example.hadideknache.financeapp.Fragments.OverViewFragment;


/**
 * Created by hadideknache on 2017-09-17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private Controller controller;

    public ViewPagerAdapter(android.support.v4.app.FragmentManager fragmentManager, Controller controller) {
        super(fragmentManager);
        this.controller=controller;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return OverViewFragment.newInstance("OverView");
            case 1:
                return ExpenditureFragment.newInstance("Expenditure");
            case 2:
                return IncomeFragment.newInstance("Income");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int pos) {
        String title = null;
        if (pos == 0)
        {
            title = "OverView";
        }
        else if (pos == 1)
        {
            title = "Expenditure";
        }
        else if (pos == 2)
        {
            title = "Income";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
