package com.vtrio.waterapp.adapters;

import android.app.Activity;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vtrio.waterapp.R;
import com.vtrio.waterapp.data.ProductItems;
import com.vtrio.waterapp.databinding.ProductItemBinding;
import com.vtrio.waterapp.databinding.ProductItemsChildBinding;
import com.vtrio.waterapp.listeners.QuantityHandler;
import com.vtrio.waterapp.ui.ProductActivity;
import com.vtrio.waterapp.utils.Constants;
import com.vtrio.waterapp.utils.HelveticaNeueFontHelper;

import java.util.ArrayList;


/**
 * Created by Tiji on 21-05-2018.
 */

public class ProductExpandableListAdapter extends BaseExpandableListAdapter {

    private Activity contextActivity;
    private ArrayList<ProductItems> productItemsArrayList;
    private ProductItemBinding productItemsBinding;

    public ProductExpandableListAdapter(ProductActivity productActivity, ArrayList<ProductItems> productItemsArrayList) {
        this.contextActivity = productActivity;
        this.productItemsArrayList = productItemsArrayList;
    }


    @Override
    public int getGroupCount() {
        Log.d("WaterApp", "getGroupCount : " + this.productItemsArrayList.size());
        return this.productItemsArrayList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        Log.d("WaterApp", "getChildCount ");
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        Log.d("WaterApp", "getGroup");
        return this.productItemsArrayList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        Log.d("WaterApp", "getChild");
        return this.productItemsArrayList.get(i).getProductItemDescription();

    }


    @Override
    public long getGroupId(int i) {
        Log.d("WaterApp", "getGroupId : " + i);
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        Log.d("WaterApp", "getChildId : " + i1);
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final ProductItems productItems = productItemsArrayList.get(i);

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            productItemsBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_items, viewGroup, false);
            HelveticaNeueFontHelper.applyHelveticaneuemedFont(contextActivity, productItemsBinding.productTitleTextView);
            productItemsBinding.productTitleTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_64_pt));
            view = productItemsBinding.getRoot();
            HelveticaNeueFontHelper.applyHelveticaneuemedFont(contextActivity, productItemsBinding.productPriceTextView);
            productItemsBinding.productPriceTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_72_pt));
            HelveticaNeueFontHelper.applyHelveticaneuemedFont(contextActivity, productItemsBinding.qtyTextView);
            productItemsBinding.qtyTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_104_pt));
            productItemsBinding.setHandler(new QuantityHandler() {
                @Override
                public void onQuantityIncrement() {
                    changeQuantity(productItemsBinding, productItems.getProductItemQty(), Constants.ADDITION);
                    Toast.makeText(contextActivity, "quantityIncrement : ", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onQuantityDecrement() {
                    changeQuantity(productItemsBinding, productItems.getProductItemQty(), Constants.SUBTRACTION);
                    Toast.makeText(contextActivity, "quantityDecrement : ", Toast.LENGTH_LONG).show();
                }
            });
        }
        productItemsBinding.setProductItems(productItems);
        Log.d("WaterApp", "@position : " + i + "productItem quantity : " + productItems.getProductItemQty().get());

        return view;
    }


    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            final ProductItemsChildBinding productItemsChildBinding = DataBindingUtil.inflate(layoutInflater, R.layout.product_items_child, viewGroup, false);
            HelveticaNeueFontHelper.applyHelveticaneuelightFont(contextActivity, productItemsChildBinding.productDescriptionTextView);
            productItemsChildBinding.productDescriptionTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_64_pt));
            productItemsChildBinding.setProductItems(productItemsArrayList.get(i));
            view = productItemsChildBinding.getRoot();
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {
    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }


    private void changeQuantity(final ProductItemBinding productItemsBinding, ObservableInt observableIntQty, String operand) {


        switch (operand) {
            case Constants.ADDITION:
                if (observableIntQty.get() >= 0) {
                    observableIntQty.set(observableIntQty.get() + 1);
                }
                break;
            case Constants.SUBTRACTION:
                if (observableIntQty.get() > 0) {
                    observableIntQty.set(observableIntQty.get() - 1);
                }
                break;

        }

        contextActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("WaterApp", "before executePendingBindings");
                Log.d("WaterApp", "has PendingBindings : " + productItemsBinding.hasPendingBindings());

                Log.d("WaterApp", "after executePendingBindings");
            }
        });

    }


}
