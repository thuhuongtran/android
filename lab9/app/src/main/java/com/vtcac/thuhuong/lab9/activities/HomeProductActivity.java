package com.vtcac.thuhuong.lab9.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.vtcac.thuhuong.lab9.utils.Product;
import com.vtcac.thuhuong.lab9.adapter.ProductAdapter;
import com.vtcac.thuhuong.lab9.R;

import java.util.ArrayList;

public class HomeProductActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_INSERT = 23;
    public static final int REQUEST_CODE_UPDATE_DELETE = 24;
    ListView lvProducts;
    ImageView ivAddNewProduct;

    ArrayList<Product> products = new ArrayList<>();
    ProductAdapter productAdapter;

    int selectedPosition = -1;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_product);

        lvProducts = findViewById(R.id.lvProducts);
        // add product item into product list
        products.add(new Product("Pepsi", "Drink", 23, "$ 1.5"));
        products.add(new Product("Sting Red", "Drink", 34, "$ 1.2"));
        products.add(new Product("Bim Bim Snack", "Food", 40, "$ 0.8"));
        products.add(new Product("Chocopie", "Food", 05, "$ 5.2"));
        products.add(new Product("Sting Yellow", "Category", 27, "$ 1.1"));
        products.add(new Product("Merino", "Ice Cream", 40, "$ 0.4"));
        products.add(new Product("Cocola", "Drink", 16, "$ 0.9"));
        productAdapter = new ProductAdapter(products, getBaseContext());
        lvProducts.setAdapter(productAdapter);

        // Add new product activity
        // set on hover change color //----------
        ivAddNewProduct = findViewById(R.id.ivAddNewProduct);
        ivAddNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddNewProductActivity.class);
                intent.putExtra("update_product", (Product) null);
                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });

        // update product
        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Product prod = products.get(position);
                Intent intent = new Intent(getBaseContext(), AddNewProductActivity.class);
                intent.putExtra("update_product", prod);
                intent.putExtra("upd_id", String.valueOf(position));
                startActivityForResult(intent, REQUEST_CODE_UPDATE_DELETE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case REQUEST_CODE_INSERT:
                        Product newProd = (Product) data.getSerializableExtra("new_product");
                        if (newProd != null) {
                            products.add(newProd);
                        }
                        productAdapter.notifyDataSetChanged();
                        break;
                    case REQUEST_CODE_UPDATE_DELETE:
                        String action = data.getStringExtra("action");
                        switch (action){
                            case "update":
                                Product updProd = (Product) data.getSerializableExtra("upd_product");
                                if(updProd==null){

                                }
                                else if (updProd.prodName.length() > 2 && updProd.prodPrice.length() > 2) {
                                    products.set(selectedPosition, updProd);
                                }
                                break;
                            case "delete":
                                int id = Integer.parseInt(data.getStringExtra("del_prod_id"));
                                products.remove(id);
                                break;
                        }
                        productAdapter.notifyDataSetChanged();
                        break;
                }
                break;
        }
    }

}
