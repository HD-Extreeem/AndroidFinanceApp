package com.example.hadideknache.financeapp.Fragments;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hadideknache.financeapp.Controller;
import com.example.hadideknache.financeapp.Expenditure;
import com.example.hadideknache.financeapp.R;
import com.example.hadideknache.financeapp.RecyclerExpenditureAdapter;

import java.util.ArrayList;


/**
 * This class represent the fragment of the expenditure tab
 * Shows expenditures under the expenditure tab, representing different inputs made by the user
 *  Created by Hadi Deknache on 2017-09-17.
 */
public class ExpenditureFragment extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private EditText etSearchDateFromExp,etSearchDateToExp;
    private Button btnSearchExp,btnSearchResetExp;
    private static Controller controller;

    public ExpenditureFragment() {
        // Required empty public constructor
    }

    /**
     * Overriden method for saving the information i.ex upon rotating the phone
     * @param outState bundle where to save the data to
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("dateToExp",etSearchDateToExp.getText().toString());
        outState.putString("dateFromExp",etSearchDateFromExp.getText().toString());
        super.onSaveInstanceState(outState);
    }
    /**
     * Constructor for creating the fragment used for the tabs
     * @param str tag for the fragment
     * @return ExpenditureFragment instance
     */
    public static ExpenditureFragment newInstance(String str) {
        ExpenditureFragment expFrag = new ExpenditureFragment();
        Bundle args = new Bundle();
        args.putString("expFrag", str);
        expFrag.setArguments(args);
        return expFrag;
    }

    /**
     * Overriden method which uses the view to register components and listeners for buttons
     * Initiates the components and fills list if expenditures are availble for specific user
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_expenditure_framgent, container, false);
        init(view);
        regBtn();
        if (savedInstanceState!=null) {
            etSearchDateToExp.setText(savedInstanceState.getString("dateToExp"));
            etSearchDateFromExp.setText(savedInstanceState.getString("dateFromExp"));
        }
        fillRecyclerView(controller.getAllExpenditures());
        return view;
    }
    /**
     * Method for registering the buttons whne pressed
     */
    private void regBtn() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.showDialog(false, null);
            }
        });
        ButtonListener listener = new ButtonListener();
        btnSearchResetExp.setOnClickListener(listener);
        btnSearchExp.setOnClickListener(listener);
        etSearchDateFromExp.setOnClickListener(listener);
        etSearchDateToExp.setOnClickListener(listener);
    }
    /**
     * Method for registering the components upon creating the view and inflating it
     * @param view the view which contains the components for gui
     */
    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewExp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fab = (FloatingActionButton) view.findViewById(R.id.fabExpenditure);
        etSearchDateFromExp = (EditText) view.findViewById(R.id.etSearchDateFromExp);
        etSearchDateToExp = (EditText) view.findViewById(R.id.etSearchDateToExp);
        btnSearchExp  = (Button) view.findViewById(R.id.btnSearchExp);
        btnSearchResetExp = (Button) view.findViewById(R.id.btnSearchResetExp);


    }

    /**
     * Fills list with pojo expenditureobject each row presents a income object
     * @param expenditure the expenditures for the user
     */
    public void fillRecyclerView(Expenditure expenditure[]) {
        ArrayList<Expenditure> arr = new ArrayList<>();
        for (int i = 0; i < expenditure.length; i++) {
            arr.add(expenditure[i]);
        }
        RecyclerExpenditureAdapter adapter = new RecyclerExpenditureAdapter(getActivity(), arr,controller);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview
    }

    /**
     * Method for setting the instance to the controller
     * @param controller instance for the controller class
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * This inner class is used for listenening to button press occuring in the gui of this fragment
     * handles the actionspress whn clicking on search,refresh and date to/from
     */
    private class ButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnSearchExp:
                    Expenditure[] exp = new Expenditure[0];
                    if (etSearchDateToExp.length()<1||etSearchDateFromExp.length()<1){
                        controller.chartInfo("Some fields are empty");
                    }
                    else {
                        exp = controller.getExpanditureBetween(etSearchDateFromExp.getText().toString(),etSearchDateToExp.getText().toString());
                        fillRecyclerView(exp);
                    }

                    break;
                case R.id.btnSearchResetExp:
                    fillRecyclerView(controller.getAllExpenditures());
                    etSearchDateToExp.getText().clear();
                    etSearchDateFromExp.getText().clear();
                    break;
                case R.id.etSearchDateFromExp:
                    controller.showDatePickFrom(etSearchDateFromExp);
                    break;
                case R.id.etSearchDateToExp:
                    controller.showDatePickTo(etSearchDateToExp);
                    break;
            }
        }
    }
}