package com.example.hadideknache.financeapp.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hadideknache.financeapp.Controller;
import com.example.hadideknache.financeapp.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OverViewFragment extends Fragment {
    private static Controller controller;
    TextView user;
    private View view;
    TextView tvCardName,tvSpent,tvTot,tvBalance;
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
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate and populate
        view = inflater.inflate(R.layout.fragment_over_view, container, false);
        init(view);
        controller.setWelcomeName(tvCardName);
        Log.v("Checkar","OverviewFragment nu!");
        //View view =  inflater.inflate(R.layout.fragment_over_view, container, false);
        return view;
    }

    private void init(View view) {
        user = (TextView) view.findViewById(R.id.user);
        pieChart = (PieChart) view.findViewById(R.id.pieChart);
        tvCardName = (TextView) view.findViewById(R.id.tvCardName);
        tvBalance = (TextView) view.findViewById(R.id.tvBalance);
        tvSpent = (TextView) view.findViewById(R.id.tvSpent);
        tvTot = (TextView) view.findViewById(R.id.tvTot) ;

        pieChart.getDescription().setEnabled(false);
        pieChart.setUsePercentValues(true);
        labels.add("Food");
        labels.add("Leisure");
        labels.add("Travel");
        labels.add("Accommodation");
        labels.add("Other");

        int colors[] = {Color.parseColor("#F5B041"),
                        Color.parseColor("#00ACC1"),
                        Color.parseColor("#039BE5"),
                        Color.parseColor("#C0CA33"),
                        Color.parseColor("#D81B60")};
        updateChart();
        PieDataSet dataset = new PieDataSet(entries,"");
        dataset.setColors(colors);
        dataset.setSliceSpace(3f);
        dataset.setValueTextSize(12f);

        PieData data = new PieData(dataset);
        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(false);
        pieChart.invalidate();

    }
    public void updateChart(){

        controller.updateChartView(entries,tvBalance,tvSpent,tvTot,labels);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
