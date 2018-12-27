package com.vtcac.thuhuong.lab9.utils;

import java.io.Serializable;

public class Product implements Serializable{
    public String prodName;
    public String prodType;
    public int prodQuantity;
    public String prodPrice;

    public Product(String prodName, String prodType, int prodQuantity, String prodPrice) {
        this.prodName = prodName;
        this.prodType = prodType;
        this.prodQuantity = prodQuantity;
        this.prodPrice = prodPrice;
    }
}
