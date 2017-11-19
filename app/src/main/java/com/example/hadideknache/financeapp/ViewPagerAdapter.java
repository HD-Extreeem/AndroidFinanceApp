package com.example.hadideknache.financeapp;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.hadideknache.financeapp.Fragments.ExpenditureFragment;
import com.example.hadideknache.financeapp.Fragments.IncomeFragment;
import com.example.hadideknache.financeapp.Fragments.OverViewFragment;


/**
 * This class is an adapter for handling the transactions between the tab movments and change content of the tabs
 * Created by Hadi Deknache on 2017-09-17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private Controller controller;

    public ViewPagerAdapter(android.support.v4.app.FragmentManager fragmentManager, Controller controller) {
        super(fragmentManager);
        this.controller=controller;
    }


    /**
     * Method overriden for getting the item, in other words the fragments inside
     * @param position the position of the tab that is being clicked
     * @return the fragment content
     */
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

    /**
     * Method overridden to set title to tabs
     * @param pos the tab position
     * @return the title of the tab at that position
     */
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
