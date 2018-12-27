package com.vtcac.thuhuong.lab6_button.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.vtcac.thuhuong.lab6_button.R;
import com.vtcac.thuhuong.lab6_button.adapter.ContactAdapter;
import com.vtcac.thuhuong.lab6_button.utils.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // friend tab
    public static final int REQUEST_CODE_EDIT_ADD = 9;
    public static final int REQUEST_CODE_SEND_SMS = 10;
    private ListView lvContacts;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private ContactAdapter contactAdapter;
    private ImageView ivAddContacts;

    private int selectedPosition = -1;
    private RelativeLayout rlFriendTab;
    private RelativeLayout rlMessageTab;
    // message tab
//    private ArrayList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlFriendTab = findViewById(R.id.rlFriendTab);
        rlMessageTab = findViewById(R.id.rlMessageTab);
        rlFriendTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rlMessageTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlFriendTab.setVisibility(View.INVISIBLE);
                rlMessageTab.setVisibility(View.VISIBLE);
            }
        });
        // list contacts - friends tab
        lvContacts = findViewById(R.id.lvContacts);

        contacts.add(new Contact("phone", "Mr.A"));
        contacts.add(new Contact("phone", "Mr.B"));
        contacts.add(new Contact("email", "Mr.C"));
        contacts.add(new Contact("phone", "Mr.D"));
        contacts.add(new Contact("email", "Mr.E"));
        contactAdapter = new ContactAdapter(contacts, MainActivity.this);
        lvContacts.setAdapter(contactAdapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                selectedPosition = position;
                // set up to show popup menu options
                ImageView ivItemOptions = view.findViewById(R.id.ivItemOptions);
                final PopupMenu popupMenu = new PopupMenu(MainActivity.this, ivItemOptions);
                popupMenu.getMenuInflater().inflate(R.menu.menu_item_options, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mniEditContact:
                                Intent intentEdit = new Intent(getBaseContext(), AddEditContactActivity.class);
                                intentEdit.putExtra("action", "edit");
                                intentEdit.putExtra("edit_name", contacts.get(position).name);
                                startActivityForResult(intentEdit, MainActivity.REQUEST_CODE_EDIT_ADD);
                                break;
                            case R.id.mniSendSMS:
                                Intent intentSend = new Intent(getBaseContext(), SendSMSActivity.class);
                                startActivityForResult(intentSend, MainActivity.REQUEST_CODE_SEND_SMS);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // click on add contact button
        ivAddContacts = findViewById(R.id.ivAddContact);
        ivAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddEditContactActivity.class);
                intent.putExtra("action", "add");
                startActivityForResult(intent, MainActivity.REQUEST_CODE_EDIT_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_EDIT_ADD:
                    if (data != null) {
                        String action = data.getStringExtra("action");
                        switch (action) {
                            case "edit_result":
                                String newName = data.getStringExtra("new_name");
                                if (newName.length() > 2) {
                                    contacts.set(selectedPosition, new Contact("phone", newName));
                                }
                                break;
                            case "add_result":
                                String addName = data.getStringExtra("add_name");
                                if (addName.length() > 2) {
                                    contacts.add(new Contact("phone", addName));
                                }
                                break;

                        }
                        contactAdapter.notifyDataSetChanged();
                    }
                    break;
                case REQUEST_CODE_SEND_SMS:
                    break;
            }
        }
    }
}
