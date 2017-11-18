package com.example.hadideknache.financeapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView row;
    TextView category;
    TextView time;
    TextView date;
    TextView cost;
    TextView title;
    CardView cardView;

    public ViewHolder(View itemView) {
        super(itemView);
        //row = (TextView) itemView.findViewById(R.id.card);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        title = (TextView) itemView.findViewById(R.id.title);
        category = (TextView) itemView.findViewById(R.id.category);
        time  = (TextView) itemView.findViewById(R.id.time);
        date = (TextView) itemView.findViewById(R.id.date);
        cost = (TextView) itemView.findViewById(R.id.cost);
    }

}
