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
import com.example.hadideknache.financeapp.Income;
import com.example.hadideknache.financeapp.R;
import com.example.hadideknache.financeapp.RecyclerIncomeAdapter;

import java.util.ArrayList;



/**
 * This is the fragment which handles the income tabfragment
 * Fragment contains the list of the incomes made by the user
 * Also includes search function
 * Created by hadideknache on 2017-09-17.
 */
public class IncomeFragment extends Fragment {
    private String item;//String for tab title
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

    /**
     * Constructor for creating the fragment used for the tabs
     * @param str tag for the fragment
     * @return incomefragment instance
     */
    public static IncomeFragment newInstance(String str) {
        IncomeFragment incFrag = new IncomeFragment();
        Bundle args = new Bundle();
        args.putString("incFrag", str);
        incFrag.setArguments(args);
        return incFrag;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("FromInc",etSearchDateFromInc.getText().toString());
        outState.putString("ToInc",etSearchDateToInc.getText().toString());
        super.onSaveInstanceState(outState);
    }

    /**
     * Overriden method for restoring the content if available
     **/
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

    /**
     * Overriden method which uses the view to register components and listeners for buttons
     * Initiates the components and fills list if incomes are availble for specific user
     */
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

    /**
     * Method for registering the buttons whne pressed
     */
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

    /**
     * Method for registering the components upon creating the view and inflating it
     * @param view the view which contains the components for gui
     */
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

    /**
     * Fills list with incomeobjects each row presents a income object
     * @param income the incomes for the user
     */
    public void fillRecyclerView(Income income[]) {
        ArrayList<Income> arr = new ArrayList<>();
        for (int i = 0; i < income.length; i++) {
            arr.add(income[i]);
        }
        adapter = new RecyclerIncomeAdapter(getActivity(), arr,controller);
        recyclerView.setAdapter(adapter);
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
