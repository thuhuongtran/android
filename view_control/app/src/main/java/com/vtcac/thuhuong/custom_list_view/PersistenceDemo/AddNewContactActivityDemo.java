package com.vtcac.thuhuong.custom_list_view.PersistenceDemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vtcac.thuhuong.R;
import com.vtcac.thuhuong.custom_list_view.CustomListViewDemoActivity;
import com.vtcac.thuhuong.custom_list_view.utils.Contact;

public class AddNewContactActivityDemo extends AppCompatActivity {
    EditText etName;
    EditText etPhone;
    Button btnAdd;

    SharedPreferences sp; // --> save status
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact_demo);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnOK);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CustomListViewDemoActivity.class);
                intent.putExtra("new_name", etName.getText().toString());
                intent.putExtra("new_phone", etPhone.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        // sharedPreference
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = sp.edit();
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // save name to sharedPreference
                editor.putString("typing_name", charSequence.toString());
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // get name from shared prefernces then display
        String typing_name = sp.getString("typing_name", "");
        etName.setText(typing_name);

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // save name to sharedPreference
                editor.putString("typing_phone", charSequence.toString());
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // get name from shared prefernces then display
        String typing_phone = sp.getString("typing_phone", "0000");
        etPhone.setText(typing_phone);

    }
}
