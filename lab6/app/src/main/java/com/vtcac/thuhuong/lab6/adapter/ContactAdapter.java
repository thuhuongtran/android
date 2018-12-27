package com.vtcac.thuhuong.lab6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtcac.thuhuong.lab6.R;
import com.vtcac.thuhuong.lab6.beans.BeanContact;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {

    private ArrayList<BeanContact> contacts;
    private Context context;

    public ContactAdapter(ArrayList<BeanContact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.item_contact, parent, false);
        final BeanContact contact = contacts.get(position);
        TextView tvContactName = rowView.findViewById(R.id.tvContactName);
        tvContactName.setText(contact.name);
        ImageView ivContactType = rowView.findViewById(R.id.ivContactType);
        switch (contact.type) {
            case "phone":
                ivContactType.setImageResource(R.drawable.ic_phone_android);
                break;
            case "email":
                ivContactType.setImageResource(R.drawable.ic_email);
                break;
        }
        return rowView;
    }

}
