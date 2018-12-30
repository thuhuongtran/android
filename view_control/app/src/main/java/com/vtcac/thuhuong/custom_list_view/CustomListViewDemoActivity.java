package com.vtcac.thuhuong.custom_list_view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.vtcac.thuhuong.R;
import com.vtcac.thuhuong.custom_list_view.PersistenceDemo.AddNewContactActivityDemo;
import com.vtcac.thuhuong.custom_list_view.utils.Contact;

import java.util.ArrayList;

public class CustomListViewDemoActivity extends AppCompatActivity {

    static final int REQUEST_ADD_CONTACT = 9;

    ArrayList<Contact> contacts = new ArrayList();
    ListView lsvContact;
    Button btnCreatNewContact;
//    ContactAdapter contactAdapter;


// view full mode and short mode
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view_demo);

        lsvContact = findViewById(R.id.lsvContact);
        contacts.add(new Contact("Mr. A", "facebook", "0978 662 922"));
        contacts.add(new Contact("Mr. B", "phone", "0987 654 333"));
        contacts.add(new Contact("Mr. C", "phone", "0987 645 211"));
        contactAdapter = new ContactAdapter(contacts, getBaseContext());
        lsvContact.setAdapter(contactAdapter);

        // create new contact
        btnCreatNewContact = findViewById(R.id.btnCreateNewContact);
        btnCreatNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddNewContactActivityDemo.class);
                startActivityForResult(intent, REQUEST_ADD_CONTACT);
            }
        });
        // get new created contact
//        Intent receiveIntent = getIntent();
//        String new_name = receiveIntent.getStringExtra("new_name");
//        String new_phone = receiveIntent.getStringExtra("new_phone");
//        if(new_name!=null)
//            contacts.add(new Contact(new_name, "phone", new_phone));
//        contactAdapter.notifyDataSetChanged();

        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sp.edit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            switch (requestCode) {
                case REQUEST_ADD_CONTACT:
                    String new_name = data.getStringExtra("new_name");
                    String new_phone = data.getStringExtra("new_phone");
                    contacts.add(new Contact(new_name, "phone", new_phone));
                    contactAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_settting_save_user_preference, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mniFull:
                editor.putBoolean("full_mode", true);
                editor.commit();
                contactAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "View full mode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mniShort:
                editor.putBoolean("full_mode", false);
                editor.commit();
                contactAdapter.notifyDataSetChanged();
                Toast.makeText(getBaseContext(), "View Short mode", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);


    }
}
