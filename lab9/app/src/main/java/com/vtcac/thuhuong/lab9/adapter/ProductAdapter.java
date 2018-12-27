package com.vtcac.thuhuong.lab9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vtcac.thuhuong.lab9.R;
import com.vtcac.thuhuong.lab9.utils.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.item_product, parent, false);

        Product product = products.get(position);
        TextView tvProdName = rowView.findViewById(R.id.tvProdName);
        tvProdName.setText(product.prodName);
        TextView tvProdType = rowView.findViewById(R.id.tvProdType);
        tvProdType.setText(product.prodType);
        TextView tvProdQuantity = rowView.findViewById(R.id.tvQuantity);
        tvProdQuantity.setText(String.valueOf(product.prodQuantity));
        TextView tvProdPrice = rowView.findViewById(R.id.tvPrice);
        tvProdPrice.setText(String.valueOf(product.prodPrice));

        return rowView;
    }
}
