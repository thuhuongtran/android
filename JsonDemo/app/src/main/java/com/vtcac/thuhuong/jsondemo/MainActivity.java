package com.vtcac.thuhuong.jsondemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String json = "{\"id\":1,\"name\":\"coca\",\"price\":20}";
    String jsonArray = "[ { \"id\":1, \"name\":\"cocacla\", \"price\":58 }, { \"id\":2, \"name\":\"pepsi\", \"price\":98 }, { \"id\":3, \"name\":\"redbull\", \"price\":46 } ]";
    TextView tvResult;
    TextView tvREsultArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        tvREsultArray = findViewById(R.id.tvResultArray);
       /* getJsonProject();
        getJsonArray();*/


        //way 1
//        new DoGetData(" https://demo8383015.mockable.io/getProduct ").execute();
        new DoGetData().execute("https://demo8383015.mockable.io/getProduct"
               /* "https://demo8383015.mockable.io/getProduct",
                "https://demo8383015.mockable.io/getProduct",
                "https://demo8383015.mockable.io/getProduct"*/);
    }

    class DoGetData extends AsyncTask<String, Integer, ArrayList<Product>> {
        String urlLink;
        String result;
        ProgressDialog pbLoading;

      /*  public DoGetData(String urlLink) {
            this.urlLink = urlLink;
        }*/

        // update UI thread
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading = new ProgressDialog(MainActivity.this);
            pbLoading.setMessage("Connecting to server");
            pbLoading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pbLoading.setCancelable(true);
            pbLoading.setMax(100);
            pbLoading.setCanceledOnTouchOutside(false);
            pbLoading.show();
        }

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            urlLink = params[0]; //AsyncTask<String, Integer, Void> demo
            ArrayList<Product> products = new ArrayList<>();
            try {
                //way 1: fast progress

                URL url = new URL(urlLink);
                URLConnection conn = url.openConnection();

                int totalLength = conn.getContentLength();
                int loadedByte = 0;
                int progress;

                InputStream inputStream = conn.getInputStream();
                result = "";
                int byteCharacter;
                while ((byteCharacter = inputStream.read()) != -1) {
                    result += (char) byteCharacter;
                    loadedByte++;
                    // tinh %so byte load duoc
                    progress = loadedByte * 100 / totalLength;
                    publishProgress(progress);
//               Thread.sleep(3000);
                    //way 2: slower progress
                   /* for (int i = 1; i <= 100; i++) {
                        publishProgress(i);
                        Thread.sleep(100);
                    }*/

//                    Log.d("json api", "MainActivity.onCreate: " + result);

                }
                JSONArray jProductArray = new JSONArray(jsonArray);
                int length = jProductArray.length();


                for (int i = 0; i < length; i++) {
                    JSONObject jProduct = jProductArray.getJSONObject(i);
                    int id = jProduct.getInt("id");
                    String name = jProduct.getString("name");
                    int price = jProduct.getInt("price");

                    Product product = new Product(id, name, price);
                    products.add(product);
//                    result += "\n ID " + id + " Name " + name + " Price " + price;
                }
                Log.d("json api", "MainActivity.onCreate: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return products;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progress = values[0];
            pbLoading.setProgress(progress);
        }

        @Override
        protected void onPostExecute(ArrayList<Product> values) {
            super.onPostExecute(values);
            ArrayList<Product> products = values;
            Log.d("json api", "DoGetData.onPostExecute: "+products);
            pbLoading.dismiss();
            tvREsultArray.setText(result);
        }
    }

    private void getJsonArray() {
        try {
            String result = "";
            JSONArray jProductArray = new JSONArray(jsonArray);
            int length = jProductArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jProduct = jProductArray.getJSONObject(i);
                int id = jProduct.getInt("id");
                String name = jProduct.getString("name");
                int price = jProduct.getInt("price");
                result += "\n ID " + id + " Name " + name + " Price " + price;
            }
            tvREsultArray.setText(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJsonProject() {
        try {
            JSONObject jProduct = new JSONObject(json);
            int id = jProduct.getInt("id");
            String name = jProduct.getString("name");
            int price = jProduct.getInt("price");

            tvResult.setText("ID " + id + "Name " + name + "Price " + price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
