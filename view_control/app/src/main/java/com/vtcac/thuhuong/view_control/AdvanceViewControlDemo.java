package com.vtcac.thuhuong.view_control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.vtcac.thuhuong.R;

public class AdvanceViewControlDemo extends AppCompatActivity {
    TextView tvTime;
    TimePicker tpTimePicker;
    DatePicker dpDatePicker;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_view_control_demo);

        tvTime = findViewById(R.id.tvTime);
        tpTimePicker = findViewById(R.id.timePicker);

        tpTimePicker.setIs24HourView(true);
        tpTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String ampm = i>=12 ? "PM":"AM";
                String time = i + ":" + i1+" "+ampm;
                tvTime.setText(time);
            }
        });

        tvDate = findViewById(R.id.tvDate);
        dpDatePicker = findViewById(R.id.datePicker);

        dpDatePicker.init(2016, 3, 3, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String date = i + "/" + i1 + "/" + i2;
                tvDate.setText(date);
            }
        });
    }
}
