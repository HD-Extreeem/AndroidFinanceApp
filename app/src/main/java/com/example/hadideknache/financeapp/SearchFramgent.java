package com.example.hadideknache.financeapp;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFramgent extends Fragment {
    Controller controller;

    public SearchFramgent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_framgent, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
