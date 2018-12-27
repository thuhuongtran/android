package com.vtcac.thuhuong.lab3_lesson3.exc3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.vtcac.thuhuong.lab3_lesson3.R;

import java.util.ArrayList;

public class AlarmDayAdapter extends BaseAdapter{
    ArrayList<AlarmDay> alarmDays;
    Context context;

    public AlarmDayAdapter(ArrayList<AlarmDay> alarmsDays, Context baseContext) {
        this.alarmDays = alarmsDays;
        this.context = baseContext;
    }

    @Override
    public int getCount() {
        return alarmDays.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmDays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowview = inflater.inflate(R.layout.item_alarm_day, parent, false);
        ImageView ivType = rowview.findViewById(R.id.ivType);
        TextView tvHour = rowview.findViewById(R.id.tvHour);
        TextView tvCountDown = rowview.findViewById(R.id.tvCountdown);
        TextView tvNumsOfAlarm = rowview.findViewById(R.id.tvNumsOfAlarm);
        Switch swOnOff = rowview.findViewById(R.id.swOnOff);

        AlarmDay alarmDay = alarmDays.get(position);
        tvHour.setText(alarmDay.getHour());
        tvCountDown.setText(alarmDay.getCountdownTimer());
        tvNumsOfAlarm.setText(alarmDay.getAlarmNums());
        switch (alarmDay.getType()){
            case "alarm":
                ivType.setImageResource(R.mipmap.set_alarm);
                break;
            case "gift":
                ivType.setImageResource(R.mipmap.gift);
                break;
            case "coffee":
                ivType.setImageResource(R.mipmap.coffee_cup);
                break;
        }
        return rowview;
    }
/*
    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(context, v);
        popupMenu.inflate(R.menu.menu_exc3);
    }*/
}
