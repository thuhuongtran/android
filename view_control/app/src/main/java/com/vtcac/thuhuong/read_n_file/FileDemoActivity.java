package com.vtcac.thuhuong.read_n_file;

import android.Manifest;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vtcac.thuhuong.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDemoActivity extends AppCompatActivity {
    EditText etText;
    Button btnSave;
    Button btnLoad;
    Button btnAskPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_demo);

        etText = findViewById(R.id.etText);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnAskPermission = findViewById(R.id.btnAskPermission);

        btnAskPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(FileDemoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 89);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etText.getText().toString();
                String filePath = Environment.getExternalStorageDirectory().getPath() + "/"
                        + Environment.DIRECTORY_DOWNLOADS + "/" + "data.txt";
                File textFile = new File(filePath);
                FileWriter fw = null;
                try {
                    fw = new FileWriter(textFile);
                    fw.write(text);
                    fw.flush();

                    Toast.makeText(getBaseContext(), "File saved at " + filePath, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filePath = Environment.getExternalStorageDirectory().getPath() + "/"
                        + Environment.DIRECTORY_DOWNLOADS + "/" + "data.txt";
                FileReader fr = null;
                try {
                    fr = new FileReader(filePath);
                    int byteCharacter = -1;
                    String result = "";
                    while ((byteCharacter = fr.read()) != -1) {
                        result += (char)byteCharacter + " ";
                    }
                    etText.setText(result);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 89){
            if (ActivityCompat.checkSelfPermission(FileDemoActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE ) == android.content.pm.PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getBaseContext(), "Granted permisson", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
