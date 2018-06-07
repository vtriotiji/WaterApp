package com.vtrio.waterapp.ui;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.vtrio.waterapp.R;
import com.vtrio.waterapp.adapters.OrderSummarListAdapter;
import com.vtrio.waterapp.utils.HelveticaNeueFontHelper;

public class CheckOutActivity extends AppCompatActivity {


    private Toolbar toolbar;
    TextView deliveryAddressLabelTextView, landmarkLabelTextView, totalLabelTextView, totalAmountTextView;
    Button cancelButton, placeOrderButton;
    ListView orderSummaryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        deliveryAddressLabelTextView = (TextView) findViewById(R.id.deliveryAddressLabelTextView);
        landmarkLabelTextView = (TextView) findViewById(R.id.landmarkLabelTextView);
        orderSummaryListView = (ListView) findViewById(R.id.orderSummaryListView);
        totalLabelTextView = (TextView) findViewById(R.id.totalLabelTextView);
        HelveticaNeueFontHelper.applyHelveticaneuelightFont(this, totalLabelTextView);
        totalLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_64_pt));
        totalAmountTextView = (TextView) findViewById(R.id.qtyTextView);
        HelveticaNeueFontHelper.applyHelveticaneuelightFont(this, totalAmountTextView);
        totalAmountTextView.setTextSize(getResources().getDimension(R.dimen.dim_64_pt));
        HelveticaNeueFontHelper.applyHelveticaneuelightFont(this, deliveryAddressLabelTextView);
        deliveryAddressLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_64_pt));
        HelveticaNeueFontHelper.applyHelveticaneuelightFont(this, landmarkLabelTextView);
        landmarkLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_64_pt));
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(this));
        cancelButton.setTextSize(getResources().getDimension(R.dimen.dim_40_pt));
        placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        placeOrderButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(this));
        placeOrderButton.setTextSize(getResources().getDimension(R.dimen.dim_40_pt));


        OrderSummarListAdapter orderSummarListAdapter = new OrderSummarListAdapter(this);
        orderSummaryListView.setAdapter(orderSummarListAdapter);


    }





//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
//
}
