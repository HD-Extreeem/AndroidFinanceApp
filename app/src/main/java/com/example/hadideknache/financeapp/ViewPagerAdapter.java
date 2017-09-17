package com.example.hadideknache.financeapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import android.widget.TextView;
/**
 * Created by hadideknache on 2017-09-17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    OverViewFragment overViewFragment = new OverViewFragment();
    ExpenditureFragment expenditureFragment = new ExpenditureFragment();
    IncomeFragment incomeFragment = new IncomeFragment();

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return overViewFragment.newInstance("OverView");
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
}
