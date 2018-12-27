package com.vtcac.thuhuong.lab3_lesson3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.vtcac.thuhuong.lab3_lesson3.exc3.AlarmDay;
import com.vtcac.thuhuong.lab3_lesson3.exc3.AlarmDayAdapter;

import java.util.ArrayList;

public class AlarmExercise3Activity extends AppCompatActivity {
    ArrayList<AlarmDay> alarmsDays = new ArrayList<>();
    ArrayList<AlarmDay> alarmsDays1 = new ArrayList<>();

    ListView lsvAlarms;
    ListView lsvAlarm1;
    Button btnTomorrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_exercise3);

        lsvAlarms = findViewById(R.id.lsvAlarms);
        lsvAlarm1 = findViewById(R.id.lsvAlarms1);

        alarmsDays.add(new AlarmDay("alarm", "07:00", "EVERY DAY",
                "In 16 hours", true));
        alarmsDays.add(new AlarmDay("gift", "18:15", "Friend's birthday ONE TIME",
                "In 1 day", true));

        alarmsDays1.add(new AlarmDay("coffee", "08:30", "Weekends SAT, SUN",
                "In 3 days", true));

        AlarmDayAdapter alarmDayAdapter = new AlarmDayAdapter(alarmsDays,getBaseContext());
        lsvAlarms.setAdapter(alarmDayAdapter);

        AlarmDayAdapter alarmDayAdapter1 = new AlarmDayAdapter(alarmsDays1,getBaseContext());
        lsvAlarm1.setAdapter(alarmDayAdapter1);

        btnTomorrow = findViewById(R.id.btnTomorrow);
        btnTomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), TomorrowActvityExc3.class);
                startActivity(intent);
            }
        });
    }
}
