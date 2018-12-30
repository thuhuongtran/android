package com.vtcac.thuhuong.custom_list_view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtcac.thuhuong.R;
import com.vtcac.thuhuong.custom_list_view.utils.Contact;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> contacts;
    Context context;
    SharedPreferences sp;



    public ContactAdapter(ArrayList<Contact> contacts, Context context) {
        this.context = context;
        this.contacts = contacts;
        this.sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public int getCount() { // return numbers of elements shown in list - trả về số phần tử hiển thị
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return contacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View rowview = inflater.inflate(R.layout.item_contact, viewGroup, false);
        TextView tvName = rowview.findViewById(R.id.tvName);
        TextView tvPhone = rowview.findViewById(R.id.tvPhone);
        ImageView ivType = rowview.findViewById(R.id.ivType);

        // full mode - short mode - persistence - save user's preference
        boolean full_mode = sp.getBoolean("full_mode", true);
        if(full_mode){
            tvPhone.setVisibility(View.VISIBLE);
            ivType.setVisibility(View.VISIBLE);
        }
        else{
            tvPhone.setVisibility(View.GONE);
            ivType.setVisibility(View.GONE);
        }

        Contact contact = contacts.get(i);
        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhone());
        if(contact.getType().endsWith("facebook")){
            ivType.setImageResource(R.mipmap.facebook);
        }
        else if(contact.getType().endsWith("phone")){
            ivType.setImageResource(R.mipmap.old_typical_phone);
        }
        return rowview;
    }

}
