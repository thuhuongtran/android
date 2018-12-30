package com.vtcac.thuhuong.sqllitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etPrice;
    Button btnInsert;

    EditText etUpdateName;
    EditText etUpdatePrice;
    EditText etUpdateID;
    Button btnUpdate;

    EditText etDeleteID;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //insert
        etName = findViewById(R.id.etInsertName);
        etPrice = findViewById(R.id.etInsertPrice);
        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etName.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());
                //run db
                SQLHelper sqlHelper = new SQLHelper(getBaseContext());
                sqlHelper.insertProduct(newName, price);
            }
        });

        // update
        etUpdateName = findViewById(R.id.etUpdateName);
        etUpdatePrice = findViewById(R.id.etUpdatePrice);
        etUpdateID = findViewById(R.id.etUpdateID);

        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etUpdateName.getText().toString();
                int price = Integer.parseInt(etUpdatePrice.getText().toString());
                int id = Integer.parseInt(etUpdateID.getText().toString());
                //run db
                SQLHelper sqlHelper = new SQLHelper(getBaseContext());
                sqlHelper.updateProduct(id,newName, price);
            }
        });

        //delete
        etDeleteID = findViewById(R.id.etDeleteID);

        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etDeleteID.getText().toString());
                //run db
                SQLHelper sqlHelper = new SQLHelper(getBaseContext());
                sqlHelper.deleteProduct(id);
            }
        });

      /*  // get All
        SQLHelper sqlHelper = new SQLHelper(getBaseContext());
        sqlHelper.getAll();
*/
        // get by price
        SQLHelper sqlHelper = new SQLHelper(getBaseContext());
        sqlHelper.getProductByPrice(30,"fantaa");
    }
}
