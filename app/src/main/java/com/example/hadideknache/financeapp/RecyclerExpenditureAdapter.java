package com.example.hadideknache.financeapp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class RecyclerExpenditureAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Expenditure> arrExpenditures;
    private Context context;
    private Controller controller;

    public RecyclerExpenditureAdapter(Context context, ArrayList<Expenditure> arr,Controller controller) {
        this.context=context;
        this.arrExpenditures=arr;
        this.controller = controller;
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
        final ViewHolder hold = (ViewHolder) holder;
        hold.category.setText(arrExpenditures.get(position).getCategory());
        hold.cost.setText(arrExpenditures.get(position).getCost());
        hold.date.setText(arrExpenditures.get(position).getDate());
        hold.time.setText(arrExpenditures.get(position).getTime());
        hold.title.setText(arrExpenditures.get(position).getTitle());

        switch (holder.category.getText().toString()){
            case "Travel":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.travel,0,0,0);
                break;
            case "Food":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.food,0,0,0);
                break;
            case "Accommodation":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.accommodation,0,0,0);
                break;
            case "Leisure":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.leisure,0,0,0);
                break;
            case "Other":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.tag,0,0,0);
                break;
        }

        hold.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showDescriptionDialog(false, hold.date,hold.time,hold.cost,hold.category,hold.title);
                //Toast.makeText(v.getContext(),hold.title.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrExpenditures.size();
    }
}
