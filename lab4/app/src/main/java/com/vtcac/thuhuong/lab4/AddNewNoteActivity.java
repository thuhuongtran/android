package com.vtcac.thuhuong.lab4;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewNoteActivity extends AppCompatActivity {

    // set item to spinner
    Spinner spType;
    ArrayList<String> jobType = new ArrayList<>();
    TextView tvTime;
    TextView tvDate;

    TextView tvTags;
    TextView tvDays;

    Button btnTune;
    MenuItem mniDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        //set item to spinner
        spType = findViewById(R.id.spType);
        jobType.add("Work");
        jobType.add("Other");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                R.layout.my_dropdown_item, jobType);
        spType.setAdapter(adapter);

        // tvTime
        tvTime = findViewById(R.id.tvTime);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        tvTime.setText(time);
                    }
                }, 11, 11, true);
                timePickerDialog.show();
            }
        });
        // tvDate
        tvDate = findViewById(R.id.tvDate);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewNoteActivity.this,
                        new DatePickerDialog.OnDateSetListener(){

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String date = dayOfMonth+"/"+month+"/"+year;
                                tvDate.setText(date);
                            }
                        }, 2011, 11, 11);
                datePickerDialog.show();
            }
        });

        //tvTags
        tvTags = findViewById(R.id.tvTags);
        tvTags.setOnClickListener(new View.OnClickListener() {
            final String[] tags = new String[]{"Family", "Game", "Android", "VTC", "Friend"};
            final boolean[] checkedTags = new boolean[]{true, false, true, false, true};
            String result = "";
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(AddNewNoteActivity.this)
                        .setTitle("Choose Tags")
                        .setMultiChoiceItems(tags, checkedTags, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                String tempResult = "";
                                for(int i=0;i<tags.length;i++){
                                    if (checkedTags[i]) {
                                        tempResult += tags[i]+", ";
                                    }
                                }
                                result = tempResult;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (result != null || !result.equals("")) {
                                    tvTags.setText(result.trim().substring(0,result.length()-2));
                                }
                                Toast.makeText(getBaseContext(),"OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(),"Cancel",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        //tvDays
        tvDays = findViewById(R.id.tvDays);
        tvDays.setOnClickListener(new View.OnClickListener() {
            final String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday","Sunday"};
            final boolean[] checkedDays = new boolean[]{true, true, false, false, false,true,false};
            String result = "";
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(AddNewNoteActivity.this)
                        .setTitle("Choose Tags")
                        .setMultiChoiceItems(days, checkedDays, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                String tempResult ="";
                                for(int i=0;i<days.length;i++){
                                    if (checkedDays[i]) {
                                        tempResult += days[i].substring(0,3)+", ";
                                    }
                                }
                                result = tempResult;
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(result!=null || !result.equals("")){
                                    tvDays.setText(result.trim().substring(0,result.length()-2));
                                }
                                Toast.makeText(getBaseContext(),"OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getBaseContext(),"Cancel",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });

        // btnTune
        btnTune = findViewById(R.id.btnTune);
        btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(AddNewNoteActivity.this, btnTune);
                popupMenu.getMenuInflater().inflate(R.menu.menu_tune,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // menu item DETAIL
                        switch (item.getItemId()){
                            case R.id.mniDetail:
                                String[] tuneDetails = new String[]{"Nexus Tune","Winphone Tune","Peep Tune",
                                "Nokia Tune", "Samsung Tune"};
                                AlertDialog alertDialog = new AlertDialog.Builder(AddNewNoteActivity.this)
                                        .setTitle("")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(),"OK", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getBaseContext(),"Cancel",Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .setSingleChoiceItems(tuneDetails, 1, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                            }
                                        })
                                        .create();
                                alertDialog.show();
                                break;
                            case R.id.mniFile:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save_cancel, menu);
        return true;
    }
}
