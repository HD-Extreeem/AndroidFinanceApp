package com.example.hadideknache.financeapp.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * This class represents the overview when the application successfully loging
 * shows the user piechart and useful information required for the user
 *  Created by Hadi Deknache on 2017-09-18.
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
    /**
     * Constructor for creating the fragment used for the tabs
     * @param str tag for the fragment
     * @return OverviewFragment instance
     */
    public static OverViewFragment newInstance(String str) {
        OverViewFragment ovrFrag = new OverViewFragment();
        Bundle args = new Bundle();
        args.putString("ovrFrag", str);
        ovrFrag.setArguments(args);
        return ovrFrag;
    }
    /**
     * Overriden method which uses the view to register components and listeners for buttons
     * Initiates the components and fills information as balance,total and piechart info
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate and populate
        setRetainInstance(true);
        view = inflater.inflate(R.layout.fragment_over_view, container, false);
        init(view);
        controller.setWelcomeName(tvCardName);
        return view;
    }
    /**
     * Method for registering the components upon creating the view and inflating it
     * Also handling the piechart and updating it
     * @param view the view which contains the components for gui
     */
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

    /**
     * Method for updating the chart when new information added as incomes/expenditures
     */
    public void updateChart(){

        controller.updateChartView(entries,tvBalance,tvSpent,tvTot,labels);
    }

    /**
     * Method for setting the instance to the controller
     * @param controller instance for the controller class
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }
}
