package com.example.hadideknache.financeapp.Fragments;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * A simple {@link Fragment} subclass.
 */
public class ExpenditureFragment extends Fragment {
    private String item;//String for tab title

    //Var static here! recylerview
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private EditText etSearchDateFromExp,etSearchDateToExp;
    private Button btnSearchExp,btnSearchResetExp;
    private static Controller controller;

    public ExpenditureFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("dateToExp",etSearchDateToExp.getText().toString());
        outState.putString("dateFromExp",etSearchDateFromExp.getText().toString());
        super.onSaveInstanceState(outState);
    }
    public static ExpenditureFragment newInstance(String str) {
        ExpenditureFragment expFrag = new ExpenditureFragment();
        Bundle args = new Bundle();
        args.putString("expFrag", str);
        expFrag.setArguments(args);
        return expFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_expenditure_framgent, container, false);
        init(view);
        regBtn();

        fillRecyclerView(controller.getAllExpenditures());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null) {
            etSearchDateToExp.setText(savedInstanceState.getString("dateToExp"));
            etSearchDateFromExp.setText(savedInstanceState.getString("dateFromExp"));
        }
    }

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

    //Setting recycler view
    public void fillRecyclerView(Expenditure expenditure[]) {
        ArrayList<Expenditure> arr = new ArrayList<>();
        for (int i = 0; i < expenditure.length; i++) {
            arr.add(expenditure[i]);
        }
        RecyclerExpenditureAdapter adapter = new RecyclerExpenditureAdapter(getActivity(), arr,controller);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

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