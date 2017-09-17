package com.example.hadideknache.financeapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<String> arr;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<String> arr){
        this.context=context;
        this.arr=arr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.recyclerrow, parent, false);

        ViewHolder mainHolder = new ViewHolder(group) {
            @Override
            public String toString() {
                return super.toString();
            }
        };


        return mainHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder hold = (ViewHolder) holder;
        hold.row.setText(arr.get(position));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }
}
