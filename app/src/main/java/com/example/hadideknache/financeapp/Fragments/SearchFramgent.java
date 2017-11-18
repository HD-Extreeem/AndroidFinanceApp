package com.example.hadideknache.financeapp.Fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hadideknache.financeapp.BarCode;
import com.example.hadideknache.financeapp.Controller;
import com.example.hadideknache.financeapp.MainActivity;
import com.example.hadideknache.financeapp.R;
import com.google.zxing.qrcode.encoder.QRCode;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFramgent extends Fragment {
    Controller controller;
    public boolean scanStarted=false;
    static final String BARCODE = "com.google.zxing.client.android.SCAN";
    String contents,format;
    public Boolean scanning=false;
    TextView scan;
    public SearchFramgent() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (scanStarted){
            outState.putString("content",contents);
            outState.putString("format",format);
            outState.putString("scantext",scan.getText().toString());
        }
        outState.putBoolean("scanning",scanning);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState!=null){
            contents=savedInstanceState.getString("content");
            format=savedInstanceState.getString("format");
            scanning=savedInstanceState.getBoolean("scanning");
            if (scanning){
                controller.showDialog(false,contents);
                controller.chartInfo("Content:" + contents + " Format:" + format);
                scan.setText(savedInstanceState.getString("scantext"));
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_search_framgent, container, false);
        scan = (TextView) view.findViewById(R.id.scan);
        scan();
        return view;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void scan(){
        try {

            if(!scanStarted) {
                Intent intent = new Intent(BARCODE);
                intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
                startActivityForResult(intent, 0);
                scanStarted=true;
            }
        } catch ( ActivityNotFoundException e) {
            controller.chartInfo("BarCodeReader not installed, need to be installed!");
            scan.setText("BarCodeReader not installed, you need to install Barcode Scanner by ZXING!");
        }
    }
    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent intent ) {

        if( requestCode == 0 ){
            if( resultCode == RESULT_OK ){
                scanning=true;
                //controller.switchFragment(R.id.mainViewFrag);
                contents = intent.getStringExtra( "SCAN_RESULT" );
                format =  intent.getStringExtra( "SCAN_RESULT_FORMAT" );
                controller.showDialog(false,contents);
                controller.chartInfo("Content:" + contents + " Format:" + format);
                scan.setText("Item succesfully scanned!\nContent:" + contents + " Format:" + format);
            }
        }
        else{
            scan.setText("Failed to scan this specific item!Try again?!");
        }

    }


    public void setStillScanning(boolean scanningfinished) {
        this.scanning = scanningfinished;
    }
}
