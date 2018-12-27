package com.vtcac.thuhuong.lab8;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomePageActivity extends AppCompatActivity {
    TextView tvWelcome;
    ImageView ivSignOut;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        tvWelcome = findViewById(R.id.tvWelcome);

        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sp.edit();
        String userName = sp.getString("username", null);
        String pass = sp.getString("userpass", null);
        if (userName != null || !userName.equals("")) {
            tvWelcome.setText("Welcome " + userName + " to my App");
        }
        Log.d("welcome page", "WelcomePageActivity.onCreate: username, " + userName + " pass, " + pass);

        //ivSignOut
        ivSignOut = findViewById(R.id.ivSignOut);
        ivSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog confirmDialog = new AlertDialog.Builder(WelcomePageActivity.this)
                        .setTitle("Do you want to sign out ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // sign out
                                editor.putString("username", null);
                                editor.commit();
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create();
                confirmDialog.show();
            }
        });

    }

}
