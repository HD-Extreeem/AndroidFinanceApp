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
import com.example.hadideknache.financeapp.Income;
import com.example.hadideknache.financeapp.R;
import com.example.hadideknache.financeapp.RecyclerIncomeAdapter;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment {
    private String item;//String for tab title
    //var static innan!
    private RecyclerView recyclerView;
    private static Controller controller;
    private String dateFrom,dateTo;
    private FloatingActionButton fab;
    private Button btnSearchDateInc,btnSearchDateReset;
    private EditText etSearchDateFromInc,etSearchDateToInc;
    RecyclerIncomeAdapter adapter;

    public IncomeFragment() {
        // Required empty public constructor
    }
    public static IncomeFragment newInstance(String str) {
        IncomeFragment incFrag = new IncomeFragment();
        Bundle args = new Bundle();
        args.putString("incFrag", str);
        incFrag.setArguments(args);
        return incFrag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("FromInc",etSearchDateFromInc.getText().toString());
        outState.putString("ToInc",etSearchDateToInc.getText().toString());
        //outState.putBoolean("dialog",isShowDialog);
        //outState.putBoolean("isSearch",isSearch);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState!=null){
            dateFrom = savedInstanceState.getString("FromInc");
            dateTo = savedInstanceState.getString("ToInc");
            etSearchDateFromInc.setText(savedInstanceState.getString("FromInc"));
            etSearchDateToInc.setText(savedInstanceState.getString("ToInc"));
            controller.setEditText(etSearchDateFromInc,etSearchDateToInc,dateFrom,dateTo);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View view= inflater.inflate(R.layout.fragment_income, container, false);
        init(view);
        regBtn();
        fillRecyclerView(controller.getAllIncomes());
        return view;
    }

    private void regBtn() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.showDialog(true, null);
            }
        });
        ButtonListener listener = new ButtonListener();
        btnSearchDateReset.setOnClickListener(listener);
        btnSearchDateInc.setOnClickListener(listener);
        etSearchDateFromInc.setOnClickListener(listener);
        etSearchDateToInc.setOnClickListener(listener);
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewInc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = (FloatingActionButton) view.findViewById(R.id.fabIncome);
        btnSearchDateInc = (Button) view.findViewById(R.id.btnSearchInc);
        btnSearchDateReset = (Button) view.findViewById(R.id.btnSearchResetInc);
        etSearchDateFromInc = (EditText) view.findViewById(R.id.etDateSearchFromInc);
        etSearchDateToInc  = (EditText) view.findViewById(R.id.etSearchDateToInc);
        controller.setInstance(this);
    }

    public void fillRecyclerView(Income income[]) {

        ArrayList<Income> arr = new ArrayList<>();
        for (int i = 0; i < income.length; i++) {
            arr.add(income[i]);
        }
        adapter = new RecyclerIncomeAdapter(getActivity(), arr,controller);
        recyclerView.setAdapter(adapter);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }



    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnSearchInc:
                    Income[] inc = new Income[0];
                    if (etSearchDateFromInc.length()<1||etSearchDateToInc.length()<1){
                        controller.chartInfo("Some fields are empty");
                    }
                    else {
                        inc = controller.getIncomeBetween(etSearchDateFromInc.getText().toString(), etSearchDateToInc.getText().toString());
                        fillRecyclerView(inc);
                    }
                    break;
                case R.id.btnSearchResetInc:
                    fillRecyclerView(controller.getAllIncomes());
                    etSearchDateToInc.getText().clear();
                    etSearchDateFromInc.getText().clear();
                    break;

                case R.id.etDateSearchFromInc:
                    controller.showDatePickFrom(etSearchDateFromInc);

                    break;
                case R.id.etSearchDateToInc:
                    controller.showDatePickTo(etSearchDateToInc);
                    break;

            }
        }
    }




}
