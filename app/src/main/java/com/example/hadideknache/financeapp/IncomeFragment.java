package com.example.hadideknache.financeapp;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeFragment extends Fragment  {
    private String item;//String for tab title
    private static RecyclerView recyclerView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_income, container, false);
        init(view);
        fillRecyclerView();
        return view;
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewInc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items
    }

    //Setting recycler view
    private void fillRecyclerView() {

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add("ItemInc " + i);//Adding items to recycler view
        }
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }

}
