package com.example.hadideknache.financeapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class RecyclerIncomeAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Income> arrIncome;
    private Context context;
    private Controller controller;

    public RecyclerIncomeAdapter(Context context, ArrayList<Income> arr,Controller controller){
        this.context=context;
        this.arrIncome=arr;
        this.controller=controller;

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

        //hold.row.setText(arrIncome.get(p);
        hold.category.setText(arrIncome.get(position).getCategory());
        hold.cost.setText(arrIncome.get(position).getEarn());
        hold.date.setText(arrIncome.get(position).getDate());
        hold.time.setText(arrIncome.get(position).getTime());
        hold.title.setText(arrIncome.get(position).getTitle());
        switch (holder.category.getText().toString()){
            case "Salary":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.income,0,0,0);
                break;
            case "Refund":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.refund,0,0,0);
                break;
            case "Grant":
                hold.category.setCompoundDrawablesWithIntrinsicBounds(R.drawable.grant,0,0,0);
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
        return arrIncome.size();
    }

}
