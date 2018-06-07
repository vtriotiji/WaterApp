package com.vtrio.waterapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.vtrio.waterapp.R;
import com.vtrio.waterapp.adapters.ProductExpandableListAdapter;
import com.vtrio.waterapp.data.ProductItems;
import com.vtrio.waterapp.databinding.ActivityProductBinding;
import com.vtrio.waterapp.utils.HelveticaNeueFontHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<ProductItems> productItemsArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityProductBinding activityProductBinding = DataBindingUtil.setContentView(this, R.layout.activity_product);

        setSupportActionBar(activityProductBinding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        HelveticaNeueFontHelper.applyHelveticaneuelightFont(this, activityProductBinding.toolbarTextView);
        activityProductBinding.setToolBarTitle(getResources().getString(R.string.select_product));
        activityProductBinding.toolbarTextView.setTextSize(getResources().getDimension(R.dimen.dim_72_pt));
        HelveticaNeueFontHelper.applyHelveticaneuemedFont(this, activityProductBinding.productContentLayout.totalLabelTextView);
        activityProductBinding.productContentLayout.totalLabelTextView.setTextSize(getResources().getDimension(R.dimen.dim_72_pt));
        activityProductBinding.productContentLayout.setTotalAmountLabel(getResources().getString(R.string.Total));
        activityProductBinding.productContentLayout.totalTextView.setTextSize((getResources().getDimension(R.dimen.dim_72_pt)));
        HelveticaNeueFontHelper.applyHelveticaneuemedFont(this, activityProductBinding.productContentLayout.totalTextView);
        activityProductBinding.productContentLayout.addcartButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(this));
        activityProductBinding.productContentLayout.addcartButton.setTextSize(getResources().getDimension(R.dimen.dim_40_pt));
        activityProductBinding.productContentLayout.setAddcartLabel(getResources().getString(R.string.ADD_CART));
        activityProductBinding.productContentLayout.checkoutButton.setTypeface(HelveticaNeueFontHelper.getHelveticaneuelightTypeface(this));
        activityProductBinding.productContentLayout.checkoutButton.setTextSize(getResources().getDimension(R.dimen.dim_40_pt));
        activityProductBinding.productContentLayout.setCheckOutLabel(getResources().getString(R.string.CHECKOUT));

        ProductExpandableListAdapter productExpandableListAdapter = new ProductExpandableListAdapter(this, getProductItems());
        activityProductBinding.productContentLayout.productExpandableList.setAdapter(productExpandableListAdapter);
        activityProductBinding.productContentLayout.productExpandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {

            }
        });
        activityProductBinding.productContentLayout.checkoutButton.setOnClickListener(this);

    }

    private ArrayList<ProductItems> getProductItems() {
        for (int i = 0; i < 10; i++) {
            ObservableInt observableInt = new ObservableInt();
            observableInt.set(0);
            ProductItems productItems = new ProductItems("WaterCan", "50", R.drawable.watercan, "description here", observableInt);
            productItemsArrayList.add(productItems);
        }
        return productItemsArrayList;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkoutButton:
                Intent checkOutIntent = new Intent(this, CheckOutActivity.class);
                startActivity(checkOutIntent);
                break;
        }

    }
}
