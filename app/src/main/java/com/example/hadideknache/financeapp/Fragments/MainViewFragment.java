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
 * A simple {@link Fragment} subclass.
 */

public class MainViewFragment extends android.support.v4.app.Fragment {

    Controller controller;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;

    public MainViewFragment() {
        // Required empty public constructor

    }
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    /*public static MainViewFragment newInstance(String str) {
        MainViewFragment mainFrag = new MainViewFragment();
        Bundle args = new Bundle();
        args.putString("incFrag", str);
        mainFrag.setArguments(args);
        return mainFrag;
    }*/


    /*@Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState!=null) {
            viewPager.setCurrentItem(savedInstanceState.getInt("tabpos"));

        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        init(view);
        //regButton();
        return view;
    }


    private void init(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.ViewPager);
        adapter = new ViewPagerAdapter(getChildFragmentManager(),controller);
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    /*user = (TextView) view.findViewById(R.id.user);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        labels.add("Food");
        labels.add("Leisure");
        labels.add("Travel");
        labels.add("Accommodation,");
        labels.add("Other");

        for (int i = 0; i < 5 ; i++) {
            entries.add(new PieEntry((i+17), labels.get(i)));
        }

        int colors[] = {Color.parseColor("#F5B041"),Color.parseColor("#16A085"),Color.parseColor("#0078CB"),Color.parseColor("#D7DBDD"),Color.parseColor("#EC7063")};
        PieDataSet dataset = new PieDataSet(entries,"");
        dataset.setColors(colors);
        dataset.setSliceSpace(3f);
        dataset.setValueTextSize(12f);
        PieData data = new PieData(dataset);
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(false);

        //controller.setUserSettings();
    }*/

    public void setController(Controller controller) {
        this.controller = controller;

    }






}
