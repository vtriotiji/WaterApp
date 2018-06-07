package com.vtrio.waterapp.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by Tiji on 30-05-2018.
 */

public class CustomSetters {

    @BindingAdapter("imgSrc")
    public static void setImageSrc(ImageView productItemImageView, int resUrl) {
        productItemImageView.setImageDrawable(productItemImageView.getContext().getResources().getDrawable(resUrl));

    }

    public static void setProductQty(){

    }
}
