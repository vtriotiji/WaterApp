package com.vtrio.waterapp.data;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.util.Log;

import com.vtrio.waterapp.BR;

import java.util.ArrayList;

/**
 * Created by Tiji on 30-05-2018.
 */

public class ProductItems extends BaseObservable {

    private String productItemTitleName;
    private String productItemPrice;
    private int productItemImage;
    private String productItemDescription;
    private ObservableInt productItemQty;
    private ArrayList<ProductItems> cartItem;


    public ProductItems(String productItemTitleName, String productItemPrice, int productItemImage, String productItemDescription, ObservableInt productItemQty) {
        this.productItemTitleName = productItemTitleName;
        this.productItemPrice = productItemPrice;
        this.productItemImage = productItemImage;
        this.productItemDescription = productItemDescription;
        this.productItemQty = productItemQty;
    }


    public String getProductItemTitleName() {
        return productItemTitleName;
    }

    public void setProductItemTitleName(String productItemTitleName) {
        this.productItemTitleName = productItemTitleName;
    }

    public String getProductItemPrice() {
        return productItemPrice;
    }

    public void setProductItemPrice(String productItemPrice) {
        this.productItemPrice = productItemPrice;
    }

    public int getProductItemImage() {
        return productItemImage;
    }

    public void setProductItemImage(int productItemImage) {
        this.productItemImage = productItemImage;
    }

    public String getProductItemDescription() {
        return productItemDescription;
    }

    public void setProductItemDescription(String productItemDescription) {
        this.productItemDescription = productItemDescription;
    }


    @Bindable
    public ObservableInt getProductItemQty() {
        return productItemQty;

    }


    private void setProductItemQty(ObservableInt productItemQty) {
        Log.d("WaterApp", "setProductItemQty called");
        if (this.productItemQty != productItemQty) {
            this.productItemQty = productItemQty;
            notifyPropertyChanged(BR.productItemQty);
        }

    }


    public ArrayList<ProductItems> getCartItem() {
        return cartItem;
    }

    public void setCartItem(ArrayList<ProductItems> cartItem) {
        this.cartItem = cartItem;
    }
}
