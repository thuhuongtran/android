package com.vtcac.thuhuong.view_control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.vtcac.thuhuong.R;
public class ViewControlActivity extends AppCompatActivity {
    Button btnRegister;
    EditText etEmail;
    EditText etPass;
    EditText etPhone;
    CheckBox cbAgree;
    RadioButton rbMale;
    RadioButton rbFemale;
    SeekBar sbAge;
    RatingBar rbRate;
    ToggleButton tbOnOff;
    Switch swSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_control);

        btnRegister = findViewById(R.id.btnRegister);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etPhone = findViewById(R.id.etPhone);
        cbAgree = findViewById(R.id.cbAgree);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFeMale);
        sbAge = findViewById(R.id.sbAge);
        rbRate = findViewById(R.id.rbRate);
        tbOnOff = findViewById(R.id.tbOnOff);
        swSwitch = findViewById(R.id.swSwitch);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String pass = etPass.getText().toString();
                String phone = etPhone.getText().toString();
                boolean agree = cbAgree.isChecked();
                boolean checkFemale = rbFemale.isChecked();
                String gender = checkFemale ? "Female" : "Male";
                int age = sbAge.getProgress();
                double rate = rbRate.getRating();
                String BatTat = tbOnOff.isChecked() ? "On" : "Off";
                String DongMo = swSwitch.isChecked() ? "Open" : "Close";

                String result = "Email " + email + "\nPassword " + pass + "\nPhone Number "
                        + phone +"\nAgree "+agree+"\nGender " + gender+"\nage "+age+"\nrate "+rate+
                        "\nOnOff "+BatTat+"\nCloseOpen "+DongMo;

                Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
                }
        });
    }
}
