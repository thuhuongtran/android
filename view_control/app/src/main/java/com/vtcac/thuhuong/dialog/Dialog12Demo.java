package com.vtcac.thuhuong.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.vtcac.thuhuong.R;

import java.util.ArrayList;

public class Dialog12Demo extends AppCompatActivity {
    ListView lvCotacts;
    ArrayList<String> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog12_demo);

        lvCotacts = findViewById(R.id.lvContacts);
        contacts.add("Mr A");
        contacts.add("Mr B");
        contacts.add("Mr C");
        contacts.add("Mr D");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, contacts);
        lvCotacts.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddNewContactActivity.class);
                startActivity(intent);
            }
        });

    }

}

