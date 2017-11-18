package com.example.hadideknache.financeapp.Fragments;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.hadideknache.financeapp.Controller;
import com.example.hadideknache.financeapp.R;
import static android.app.Activity.RESULT_OK;


/**
 * This class handles the functions used for searching for a specific item
 * Also implements the function to scan ean codes using the zxing library
 * Created by Hadi Deknache on 2017-09-21.
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

    /**
     * Overridden method used for saving information that is important if ther occured i.ex a rotation
     * puts the information to the bundle oustate
     * @param outState bundle to save information to
     */
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

    /**
     * Overridden method is used for checking if there was recently a rotation
     * restores the information if application was "disturbed"
     * @param view view of application gui containing components
     * @param savedInstanceState bundle holding the data which is saved
     */
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
    /**
     * Overriden method which uses the view to register components
     * Initiates the components and prepares the scanning process
     */
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

    /**
     * Method for setting the instance to the controller
     * @param controller instance for the controller class
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * This method is used for scanning an item
     * Starts an intent forwarded to zxing application which prompt the application a barcode of type
     * ean
     * Afterwards on success calls later onActivityResult
     */
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

    /**
     * Method is called upon user successfully scanned an object which return to application
     * @param requestCode code which tells success or not
     * @param resultCode code which tells if scanning was successful/failed
     * @param intent intent containing the information of scanned object
     */
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
            scan.setText("Failed to scan this specific item!\n Try again?!");
        }

    }
}
