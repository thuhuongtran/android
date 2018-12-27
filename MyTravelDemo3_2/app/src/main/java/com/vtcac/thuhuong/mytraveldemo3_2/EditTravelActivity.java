package com.vtcac.thuhuong.mytraveldemo3_2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.material.snackbar.Snackbar;
import com.vtcac.thuhuong.mytraveldemo3_2.base.BaseActivity;
import com.vtcac.thuhuong.mytraveldemo3_2.utils.MyDate;
import com.vtcac.thuhuong.mytraveldemo3_2.utils.MyString;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public class EditTravelActivity extends BaseActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText mTitltEt;
    private static final String TAG = EditTravelActivity.class.getSimpleName();
    private long mStartDt;
    private long mEndDt;
    private Place mPlace;
    private EditText mTitleEt;
    private EditText mStartDtEt;
    private EditText mEndDtEt;
    private EditText mPlaceEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_travel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitltEt = findViewById(R.id.title_et);
        mStartDtEt = findViewById(R.id.title_et);
        mEndDtEt = findViewById(R.id.end_dt);
        findViewById(R.id.start_dt).setOnClickListener(this);
        findViewById(R.id.start_dt_layout).setOnClickListener(this);
        mStartDtEt.setOnClickListener(this);
        mPlaceEt = findViewById(R.id.place_et);
        findViewById(R.id.place_layout).setOnClickListener(this);
        mPlaceEt.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                validate();
                break;
            case R.id.start_dt_layout:
            case R.id.start_dt: {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setTag(view.getId());
                if (mEndDt > 0) {
                    dpd.getDatePicker().setMaxDate(mEndDt);
                }
                dpd.show();
            }
            break;
            case R.id.end_dt_layout:
            case R.id.end_dt: {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(this, this,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dpd.getDatePicker().setTag(view.getId());
                if (mStartDt > 0) {
                    dpd.getDatePicker().setMaxDate(mStartDt);
                }
                dpd.show();
            }
            break;
            case R.id.place_layout:
            case R.id.place_et:
                AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                        .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                        .build();
                try {
                    Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .setFilter(typeFilter)
                            .build(this);
                    startActivityForResult(intent, REQCD_PLACE_AUTOCOMPLETE);
                } catch (GooglePlayServicesRepairableException e) {
                    // indicates that Google Play Services is either not installed
                    // or not up to date
                    // Promp the user to corrent the issue
                    GoogleApiAvailability.getInstance().getErrorDialog(this,
                            e.getConnectionStatusCode(), 0 /*requestCode*/).show();
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    // Indicates that Google Play Services is not available
                    // and the problem is not easily resovable.
                    String message = "Google Play Services is not available: " +
                            GoogleApiAvailability.getInstance().getErrorString(e.errorCode);
                    Log.e(TAG, message);
                    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();

                }
                break;

        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Object tag = view.getTag();
        Calendar calendar = Calendar.getInstance();
        if (tag.equals(R.id.start_dt)) {
            calendar.set(year, month, dayOfMonth, 0, 0, 0);
            if (mEndDt > 0 && mEndDt < calendar.getTimeInMillis()) return;
            mStartDt = calendar.getTimeInMillis();
            mStartDtEt.setText(MyDate.getString(calendar.getTime()));
        }
    }

    private void validate() {
        String title = mTitltEt.getText().toString();
        if (MyString.isEmpty(title)) {
            Snackbar.make(mTitleEt, "Travel Title is empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if(mPlace == null){
            Snackbar.make(mTitleEt, "You can not let empty city",Snackbar.LENGTH_SHORT).show();
        }
        if (mStartDt == 0) {
            Snackbar.make(mTitleEt, "Start date is empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (mEndDt == 0) {
            Snackbar.make(mTitleEt, "End date is empty", Snackbar.LENGTH_SHORT).show();
            return;
        }
        // TODO add a new travel
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case REQCD_PLACE_AUTOCOMPLETE: {
                mPlace = PlaceAutocomplete.getPlace(this, data);
                Log.i(TAG, "Place: "+mPlace);
                mPlaceEt.setText(mPlace.getName());
            }
            break;
        }
    }
}
