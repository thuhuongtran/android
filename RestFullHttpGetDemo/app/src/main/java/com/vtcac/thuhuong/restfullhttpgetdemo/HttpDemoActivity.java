package com.vtcac.thuhuong.restfullhttpgetdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HttpDemoActivity extends AppCompatActivity {
    Button btnGet;
    EditText etCreateName;
    EditText etCreatePrice;
    Button btnCreate;

    EditText etUpdateID;
    EditText etUpdateName;
    EditText etUpdatePrice;
    Button btnUpdate;

    EditText etDeleteID;
    Button btnDelete;

    final String API = "https://demo8383015.mockable.io/getProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demo);

        etCreateName = findViewById(R.id.etName);
        etCreatePrice = findViewById(R.id.etPrice);
        btnCreate = findViewById(R.id.btnCreate);

        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DoGetProduct().execute();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etCreateName.getText().toString();
                int price = Integer.parseInt(etCreatePrice.getText().toString());

                // init new product
                Product product = new Product(0, name, price);
                //convert product => json
                JSONArray jProducts = new JSONArray();
                JSONObject jProduct = new JSONObject();
                try {
                    jProduct.put("name", product.name);
                    jProduct.put("price", product.price);

                    // add to json array
                    jProducts.put(jProduct);
//                    Log.d("json api", "Json converted from Product object: " + jProduct.toString());
                    Log.d("json api", "Json converted from Product object: " + jProducts.toString());
                    new DoCreateProduct().execute(jProducts.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // update product
        etUpdateID = findViewById(R.id.etUpdateID);
        etUpdateName = findViewById(R.id.etUpdateName);
        etUpdatePrice = findViewById(R.id.etUpdatePrice);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(etUpdateID.getText().toString());
                String name = etUpdateName.getText().toString();
                int price = Integer.parseInt(etUpdatePrice.getText().toString());

                // init new product
                Product product = new Product(id, name, price);
                //convert product => json
                JSONArray jProducts = new JSONArray();
                JSONObject jProduct = new JSONObject();
                try {
                    jProduct.put("id", product.id);
                    jProduct.put("name", product.name);
                    jProduct.put("price", product.price);

                    // add to json array
                    jProducts.put(jProduct);
//                    Log.d("json api", "Json converted from Product object: " + jProduct.toString());
                    Log.d("UPDATE json api", "Json converted from Product object: " + jProducts.toString());
                    new DoUpdateProduct().execute(jProducts.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // delete product
        etDeleteID = findViewById(R.id.etDeleteID);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deleteID = Integer.parseInt(etDeleteID.getText().toString());
                new DoDeleteProduct().execute(deleteID);
            }
        });

    }

    class DoGetProduct extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(API);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                InputStream is = httpURLConnection.getInputStream();
                int byteCharacter;
                String result = "";
                while ((byteCharacter = is.read()) != -1) {
                    result += (char) byteCharacter;
                }
//                Log.d("json api", "HttpDemoActivity.doInBackground: "+result);
                // parse json to array product

                ArrayList<Product> products = new ArrayList<>();
                JSONArray jProductAray = new JSONArray(result);
                for (int i = 0; i < jProductAray.length(); i++) {
                    JSONObject jProduct = jProductAray.getJSONObject(i);
                    int id = jProduct.getInt("id");
                    String name = jProduct.getString("name");
                    int price = jProduct.getInt("price");

                    // create new product
                    Product product = new Product(id, name, price);
                    products.add(product);

                    String productData = String.format("ID: %d, Name: %s, Price: %d", id, name, price);
//                    Log.d("json api", "DoGetProduct.doInBackground: " + productData);
                    Log.d("json api", "DoGetProduct.doInBackground: " + products.size());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class DoCreateProduct extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String jsonData = params[0];
            URL url = null;
            try {
                url = new URL(API);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                // send data
                DataOutputStream dos = (DataOutputStream) httpURLConnection.getOutputStream();
                dos.writeBytes(jsonData);

                // receive data response
                InputStream is = httpURLConnection.getInputStream();
                String result = "";
                int byteChareacter;
                while ((byteChareacter = is.read()) != -1) {
                    result += (char) byteChareacter;
                }
                Log.d("json api ", "DoCreateProduct.doInBackground Json return: " + result);
                is.close();
                dos.close();
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class DoUpdateProduct extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String jsonData = params[0];
            URL url = null;
            try {
                url = new URL(API);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("PUT");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();
                // send data
                DataOutputStream dos = (DataOutputStream) httpURLConnection.getOutputStream();
                dos.writeBytes(jsonData);

                // receive data response
                InputStream is = httpURLConnection.getInputStream();
                String result = "";
                int byteChareacter;
                while ((byteChareacter = is.read()) != -1) {
                    result += (char) byteChareacter;
                }
                Log.d("UPDATE json api ", "DoUpdateProduct.doInBackground Json return: " + result);
                is.close();
                dos.close();
                httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class DoDeleteProduct extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            int id = params[0];
            try {
                URL url = new URL(String.format("%s%d", API, id));
//                URL url = new URL(API);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("DELETE");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();

                InputStream is = httpURLConnection.getInputStream();
                int byteCharacter;
                String result = "";
                while ((byteCharacter = is.read()) != -1) {
                    result += (char) byteCharacter;
                }
                Log.d("json api", result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
