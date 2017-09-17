package com.example.hadideknache.financeapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hadideknache on 2017-09-17.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView row;
    public ViewHolder(View itemView) {
        super(itemView);
        row = (TextView) itemView.findViewById(R.id.card);
    }
}
