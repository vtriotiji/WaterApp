package com.vtrio.waterapp.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vtrio.waterapp.R;
import com.vtrio.waterapp.utils.HelveticaNeueFontHelper;

import org.w3c.dom.Text;


/**
 * Created by Tiji on 25-05-2018.
 */

public class OrderSummarListAdapter extends BaseAdapter {

    Activity contextActivity;
    TextView productTitleTextView, productPriceTextView, qtyTextView;

    public OrderSummarListAdapter(Activity contextActivity) {
        this.contextActivity = contextActivity;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = contextActivity.getLayoutInflater().inflate(R.layout.ordersummaritems, viewGroup, false);
            productTitleTextView = (TextView) view.findViewById(R.id.productTitleTextView);
            HelveticaNeueFontHelper.applyHelveticaneuelightFont(contextActivity, productTitleTextView);
            productTitleTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_64_pt));
            productPriceTextView = (TextView) view.findViewById(R.id.productPriceTextView);
            HelveticaNeueFontHelper.applyHelveticaneuelightFont(contextActivity, productPriceTextView);
            productPriceTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_64_pt));

            qtyTextView = (TextView) view.findViewById(R.id.qtyTextView);
            HelveticaNeueFontHelper.applyHelveticaneuelightFont(contextActivity, qtyTextView);
            qtyTextView.setTextSize(contextActivity.getResources().getDimension(R.dimen.dim_72_pt));
        }
        return view;
    }
}
