package com.example.hadideknache.financeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by hadideknache on 2017-09-13.
 */

public class ItemAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;

    public ItemAdapter(Context context, String[] object){
        super(context,R.layout.row,object);
        inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if (convertView == null){
            convertView = (TextView)inflater.inflate(R.layout.row,parent,false);
            holder = new ViewHolder();
            holder.row = (TextView)convertView.findViewById(R.id.row);

            convertView.setTag(holder);}
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.row.setText(this.getItem(position));
        return convertView;
    }

    private class ViewHolder {
        TextView row;
    }
}
