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
public class ExpenditureFragment extends Fragment {
    private String item;//String for tab title
    private static RecyclerView recyclerView;
    private static final String ARG_SECTION_STR = "section_str";
    public ExpenditureFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_expenditure_framgent, container, false);
        init(view);
        fillRecyclerView();
        return view;
    }
    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewExp);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//Linear Items
    }

    //Setting recycler view
    private void fillRecyclerView() {

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add("ItemEXP " + i);//Adding items to recycler view
        }
        RecyclerAdapter adapter = new RecyclerAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(adapter);// set adapter on recyclerview

    }
}
