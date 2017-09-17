package com.example.hadideknache.financeapp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import static android.R.attr.entries;
import static com.example.hadideknache.financeapp.R.id.pieChart;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends Fragment {
    Controller controller;
    TextView user;
    private PieChart pieChart;

    private ArrayList<String> labels = new ArrayList<String>();
    ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

    public OverViewFragment() {
        // Required empty public constructor
    }
    public static OverViewFragment newInstance(String str) {
        OverViewFragment ovrFrag = new OverViewFragment();
        Bundle args = new Bundle();
        args.putString("ovrFrag", str);
        ovrFrag.setArguments(args);
        return ovrFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_over_view, container, false);
        init(view);
        return view;
    }
    private void init(View view) {
        user = (TextView) view.findViewById(R.id.user);
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
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
