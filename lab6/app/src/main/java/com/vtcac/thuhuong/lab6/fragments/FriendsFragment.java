package com.vtcac.thuhuong.lab6.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.vtcac.thuhuong.lab6.R;
import com.vtcac.thuhuong.lab6.activities.AddEditContactActivity;
import com.vtcac.thuhuong.lab6.activities.SendSMSEmailActivity;
import com.vtcac.thuhuong.lab6.adapter.ContactAdapter;
import com.vtcac.thuhuong.lab6.beans.BeanContact;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {
    public static final int REQUEST_CODE_EDIT_ADD = 9;
    public static final int REQUEST_CODE_SEND_SMS = 10;

    private ListView lvContacts;
    private ArrayList<BeanContact> contacts = new ArrayList<>();
    private ContactAdapter contactAdapter;
    private ImageView ivAddContacts;

    public static int selectedPosition = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // list contacts - friends tab
        lvContacts = getActivity().findViewById(R.id.lvContacts);

        contacts.add(new BeanContact("phone", "Mr.A"));
        contacts.add(new BeanContact("phone", "Mr.B"));
        contacts.add(new BeanContact("email", "Mr.C"));
        contacts.add(new BeanContact("phone", "Mr.D"));
        contacts.add(new BeanContact("email", "Mr.E"));
        contactAdapter = new ContactAdapter(contacts, getContext());
        lvContacts.setAdapter(contactAdapter);

        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                selectedPosition = position;
                // set up to show popup menu options
                ImageView ivItemOptions = view.findViewById(R.id.ivItemOptions);
                final PopupMenu popupMenu = new PopupMenu(getContext(), ivItemOptions);
                popupMenu.getMenuInflater().inflate(R.menu.menu_item_options, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.mniEditContact:
                                Intent intentEdit = new Intent(getContext(), AddEditContactActivity.class);
                                intentEdit.putExtra("action", "edit");
                                intentEdit.putExtra("edit_name", contacts.get(position).name);
                                startActivityForResult(intentEdit, REQUEST_CODE_EDIT_ADD);
                                break;
                            case R.id.mniSendSMS:
                                Intent intentSend = new Intent(getContext(), SendSMSEmailActivity.class);
                                startActivityForResult(intentSend, REQUEST_CODE_SEND_SMS);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        // click on add contact button
        ivAddContacts = getActivity().findViewById(R.id.ivAddContact);
        ivAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddEditContactActivity.class);
                intent.putExtra("action", "add");
                startActivityForResult(intent, REQUEST_CODE_EDIT_ADD);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_EDIT_ADD:
                    if (data != null) {
                        String action = data.getStringExtra("action");
                        switch (action) {
                            case "edit_result":
                                String newName = data.getStringExtra("new_name");
                                if(newName.length()>2){
                                    contacts.set(selectedPosition, new BeanContact("phone", newName));
                                }
                                break;
                            case "add_result":
                                String addName = data.getStringExtra("add_name");
                                if(addName.length()>2){
                                    contacts.add(new BeanContact("phone", addName));
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
