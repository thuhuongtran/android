package com.vtcac.thuhuong.intentremakedemo;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lsvContacts;
    ArrayList<String> contacts = new ArrayList<>();
    Button btnAdd;
    Button btnUpdate;

    int selectedPosition = -1;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lsvContacts = findViewById(R.id.lsvContact);
        contacts.add("Mr A");
        contacts.add("Mr B");
        contacts.add("Mr C");
        contacts.add("Mr D");
        adapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, contacts);
        lsvContacts.setAdapter(adapter);

        // click on item them move to item detail
        lsvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                String name = contacts.get(position);

                Intent intent = new Intent(getBaseContext(), ContactDetailActivity.class);
                intent.putExtra("user_name", name);
                startActivityForResult(intent, 0); // reqquestCode =99 --> add activity
                Toast.makeText(getBaseContext(), "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        // add new contact
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // before
                /*contacts.add("Mr E");
                adapter.notifyDataSetChanged();*/

                //after
                Intent intent = new Intent(getBaseContext(), ContactDetailActivity.class);
                startActivityForResult(intent, 99);
            }
        });

       /* btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = 1;
                contacts.set(selectedPosition, "Mr Update");
                adapter.notifyDataSetChanged();

            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RESULT_OK:
                if (requestCode == 99) {
                    // add
                    String newName = data.getStringExtra("new_name");
                    contacts.add(newName);
                    adapter.notifyDataSetChanged();
                } else if (requestCode == 0) {
                    String newName = data.getStringExtra("new_name");
                    contacts.set(selectedPosition, newName);
                    adapter.notifyDataSetChanged();
                }
//                Toast.makeText(getBaseContext(), newName, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
