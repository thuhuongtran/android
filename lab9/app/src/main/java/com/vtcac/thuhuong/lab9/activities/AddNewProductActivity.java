package com.vtcac.thuhuong.lab9.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vtcac.thuhuong.lab9.R;
import com.vtcac.thuhuong.lab9.data.SQLHelper;
import com.vtcac.thuhuong.lab9.utils.Product;

import java.util.ArrayList;

public class AddNewProductActivity extends AppCompatActivity {
    Spinner spCategory;
    ArrayList<String> arrCategory = new ArrayList<>();

    EditText etProdName;
    EditText etProdPrice;
    EditText etProdQuantity;
    ImageView ivBackHome;
    Button btnAddUpdate;

    TextView tvHeader;
    ImageView ivDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        spCategory = findViewById(R.id.spCategory);
        etProdName = findViewById(R.id.etProdName);
        etProdPrice = findViewById(R.id.etProdPrice);
        etProdQuantity = findViewById(R.id.etProdQuantity);
        ivBackHome = findViewById(R.id.ivBackHome);
        tvHeader = findViewById(R.id.tvHeader);
        ivDelete = findViewById(R.id.ivDelete);
        btnAddUpdate = findViewById(R.id.btnAddUpdate);

        // click on arrow_back then back to home screen
        ivBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddNewProductActivity.class);
                intent.putExtra("new_product", (Product) null);
                intent.putExtra("action", "");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        // add category
        arrCategory.add("Drink");
        arrCategory.add("Food");
        arrCategory.add("Category");
        arrCategory.add("Ice Cream");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.my_spinner_dropdown_menu, arrCategory);
        spCategory.setAdapter(arrayAdapter);

        final SQLHelper sqlHelper = new SQLHelper(getBaseContext());
        final Intent intent = new Intent(getBaseContext(), AddNewProductActivity.class);

        // receive intent from home
        final Intent receiveIntent = getIntent();
        Product updateProd = (Product) receiveIntent.getSerializableExtra("update_product");
        if (updateProd != null) {
            final int id = Integer.parseInt(receiveIntent.getStringExtra("upd_id"));
            tvHeader.setText("Update Product");
            btnAddUpdate.setText("Update");
            ivDelete.setVisibility(View.VISIBLE);
            etProdName.setText(updateProd.prodName);
            etProdPrice.setText(updateProd.prodPrice);
            etProdQuantity.setText(String.valueOf(updateProd.prodQuantity));
            spCategory.setSelection(arrCategory.indexOf(updateProd.prodType));

            btnAddUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get values
                    String prodType = spCategory.getSelectedItem().toString();
                    String prodName = etProdName.getText().toString();
                    String prodPrice = etProdPrice.getText().toString();
                    int prodQuantity = Integer.parseInt(etProdQuantity.getText().toString());

                    sqlHelper.updateProduct(id, prodName, prodType, prodQuantity, prodPrice);
                    Log.d("update product ", "AddUpdateProductActivity.onItemSelect: name "
                            + prodName + " type " + prodType + " price " + prodPrice + " quantity " + prodQuantity);
                    Toast.makeText(getBaseContext(), "Updated product successfully.", Toast.LENGTH_SHORT).show();
                    intent.putExtra("action", "update");
                    intent.putExtra("upd_product", new Product(prodName, prodType, prodQuantity, prodPrice));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

            // delete product action
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(AddNewProductActivity.this)
                            .setTitle("Do you want to delete this product?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    sqlHelper.deleteProduct(id);
                                    Log.d("delete product ", "AddUpdateActivity.ivDelete id " + id);
                                    Toast.makeText(getBaseContext(), "Deleted product successfully.", Toast.LENGTH_SHORT).show();
                                    intent.putExtra("action", "delete");
                                    intent.putExtra("del_prod_id", String.valueOf(id));
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    intent.putExtra("action", "");
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
                            })
                            .create();
                    alertDialog.show();
                }
            });

        } else {
            // Add and update product action
            btnAddUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String prodType = spCategory.getSelectedItem().toString();
                    String prodName = etProdName.getText().toString();
                    String prodPrice = "$ " + etProdPrice.getText().toString();
                    String prodQuantity = etProdQuantity.getText().toString();
                    if (prodName.length() > 2 && prodPrice.length() > 2 && prodQuantity.length() > 2) {
                        sqlHelper.insertProduct(prodName, prodType, Integer.parseInt(prodQuantity), prodPrice);
                        Log.d("add new product ", "AddNewProductActivity.onClickListener: name "
                                + prodName + " type " + prodType + " price " + prodPrice + " quantity " + prodQuantity);
                        Toast.makeText(getBaseContext(), "Inserted new product successfully.", Toast.LENGTH_SHORT).show();
                    }
                    intent.putExtra("new_product", new Product(prodName, prodType, Integer.parseInt(prodQuantity), prodPrice));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }


       /* new EditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_product, menu);
        return true;
    }
}
