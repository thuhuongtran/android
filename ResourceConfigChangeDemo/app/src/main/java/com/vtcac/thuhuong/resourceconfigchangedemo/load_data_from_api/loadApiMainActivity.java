package com.vtcac.thuhuong.resourceconfigchangedemo.load_data_from_api;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.vtcac.thuhuong.resourceconfigchangedemo.R;

import java.util.ArrayList;
import java.util.Random;

public class loadApiMainActivity extends AppCompatActivity {
    ProgressDialog pbLoading;
    ArrayList<User> users = new ArrayList<>();
    ListView lsvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_api_main);
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomId = random.nextInt(20);
                User user = new User(randomId, "mr random@mail.com", "Mr " + randomId);
                users.add(user);
                Toast.makeText(getBaseContext(), "added " + user.id, Toast.LENGTH_SHORT).show();
                // notify if data set change
            }
        });

        pbLoading = new ProgressDialog(this);
        pbLoading.setMax(100);
        pbLoading.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSonData();
            }

        });

//        lsvUsers = findViewById(R.id.lv.);
       lsvUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               users.remove(position);
               // notify if data set change
               return false;
           }
       });
    }

    private void getJSonData() {
        new GetJSonAsync().execute();
    }

    class GetJSonAsync extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbLoading.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int progress = i;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onProgressUpdate(progress);
                    }
                });
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pbLoading.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getBaseContext(), "LOADED", Toast.LENGTH_SHORT).show();
            pbLoading.dismiss();
        }
    }
}
