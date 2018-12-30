package com.vtcac.thuhuong.dialog;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import com.vtcac.thuhuong.R;

import java.util.ArrayList;

public class AddNewContactActivity extends AppCompatActivity {
    Spinner spPhoneType; // spinner đè lên thằng để click
    ArrayList<String> phoneType = new ArrayList<>();

    Button btnTune;
    Button btnGroup;
    Button btnSend;
    TextView tvTime;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        spPhoneType = findViewById(R.id.spPhoneType);
        phoneType.add("Home");
        phoneType.add("Work");
        phoneType.add("Company");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_spinner_dropdown_item, phoneType);
        spPhoneType.setAdapter(adapter);

        // Tune button---------------single choice
        btnTune = findViewById(R.id.btnTune);
        btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] tunes = new String[]{"Song 1", "Song 2", "Song 3", "Song 4"};
                AlertDialog dialogTune = new AlertDialog.Builder(AddNewContactActivity.this)

//                        .setMessage("Please select your contact ringtune")

                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "Click OK", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setTitle("Set Ringtune")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setSingleChoiceItems(tunes, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                dialogTune.show();
            }
        });

        // Button group --------------------- multiple choice
        btnGroup = findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] groups = new String[]{"Family", "Friend", "Comapny", "FaceBook"};
                final boolean[] checked = new boolean[]{false, true, false, true};
                AlertDialog dialogGroup = new AlertDialog.Builder(AddNewContactActivity.this)
                        .setTitle("Select group")
                        .setMultiChoiceItems(groups, checked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                Toast.makeText(getBaseContext(),i+":"+b,Toast.LENGTH_LONG).show();
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String result = "";
                                for(int j=0;j<checked.length;j++){
                                    if(checked[j]){
                                        result = result+groups[j]+" ";
                                    }
                                }
                                btnGroup.setText(result);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                dialogGroup.show();
            }
        });
        //------------button Send-------------
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(AddNewContactActivity.this, btnSend);
                popupMenu.getMenuInflater().inflate(R.menu.new_contact_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        // ----------TimePicker Dialog
        tvTime = findViewById(R.id.tvTime);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNewContactActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                String time = i + ":" + i1;
                                tvTime.setText(time);
                            }
                        }, 8, 20, true);
                timePickerDialog.show();
            }
        });
        //----------DatePicker Dialog-------
        tvDate = findViewById(R.id.tvDate);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewContactActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                String date = i+"/"+i1+1+"/"+i2;
                                tvDate.setText(date);
                            }
                        }, 2016, 9, 3);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mnInflater = getMenuInflater();
        mnInflater.inflate(R.menu.new_contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mniSave:
                Toast.makeText(getBaseContext(), "Save", Toast.LENGTH_LONG).show();
                break;
            case R.id.mniCancel:
                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
