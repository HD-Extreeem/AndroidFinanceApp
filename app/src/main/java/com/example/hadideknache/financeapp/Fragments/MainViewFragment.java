package com.example.hadideknache.financeapp.Fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hadideknache.financeapp.Controller;
import com.example.hadideknache.financeapp.R;
import com.example.hadideknache.financeapp.ViewPagerAdapter;

import static android.app.Activity.RESULT_OK;

/**
 * This class is the head of the tabs which holds all tabs and outer layout that contains the 3 tabs
 * and the drawer etc...
 *  Created by hadideknache on 2017-09-17.
 */

public class MainViewFragment extends android.support.v4.app.Fragment {

    Controller controller;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    public MainViewFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        init(view);
        return view;
    }


    /**
     * Method for registering the components upon creating gui
     * @param view the view which contains the components for gui
     */
    private void init(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.ViewPager);
        adapter = new ViewPagerAdapter(getChildFragmentManager(),controller);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * Method for setting the instance to the controller
     * @param controller instance for the controller class
     */
    public void setController(Controller controller) {
        this.controller = controller;

    }






}
