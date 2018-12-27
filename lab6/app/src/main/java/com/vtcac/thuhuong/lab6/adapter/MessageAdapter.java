package com.vtcac.thuhuong.lab6.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vtcac.thuhuong.lab6.R;
import com.vtcac.thuhuong.lab6.beans.BeanMessage;

import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter{

    private ArrayList<BeanMessage> beanMessages;
    Context context;

    public MessageAdapter(ArrayList<BeanMessage> beanMessages, Context context) {
        this.beanMessages = beanMessages;
        this.context = context;
    }
    @Override
    public int getCount() {
        return beanMessages.size();
    }

    @Override
    public Object getItem(int position) {
        return beanMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.item_message, parent, false);
        BeanMessage msg = beanMessages.get(position);
        TextView tvMsgName = rowView.findViewById(R.id.tvMsgName);
        TextView tvMsgText = rowView.findViewById(R.id.tvMsgtext);
        ImageView ivMsgType = rowView.findViewById(R.id.ivMsgType);
        tvMsgName.setText(msg.name);
        tvMsgText.setText(msg.msgText);
        switch (msg.type){
            case "sms":
                ivMsgType.setImageResource(R.drawable.ic_phone_android);
                break;
            case "email":
                ivMsgType.setImageResource(R.drawable.ic_email);
                break;
        }
        if (position % 2 == 0) {
            tvMsgText.setBackgroundResource(R.drawable.bg_even_friend);
            tvMsgText.setTextColor(ContextCompat.getColor(context, R.color.textAbove));
        }
        else {
            tvMsgText.setBackgroundResource(R.drawable.bg_odd_msg);
            tvMsgText.setTextColor(ContextCompat.getColor(context, R.color.textWhite));
        }
        return rowView;
    }
}
