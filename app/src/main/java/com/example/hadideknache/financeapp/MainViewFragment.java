package com.example.hadideknache.financeapp;


import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class MainViewFragment extends Fragment {
    android.support.v4.app.FragmentManager fragManager;
    Controller controller;
    TextView user;
    private PieChart pieChart;

    private ArrayList<String> labels = new ArrayList<String>();
    ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

    public MainViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_view, container, false);
        init(view);
        //regButton();
        return view;
    }



    private void init(View view) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.ViewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragManager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
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

    public void setController(Controller controller, android.support.v4.app.FragmentManager fragmentManager) {
        this.controller = controller;
        this.fragManager = fragmentManager;

    }


}
