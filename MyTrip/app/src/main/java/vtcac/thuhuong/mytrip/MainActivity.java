package vtcac.thuhuong.mytrip;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import vtcac.thuhuong.mytrip.base.BaseActivity;
import vtcac.thuhuong.mytrip.base.MyApplication;
import vtcac.thuhuong.mytrip.base.TravelSort;
import vtcac.thuhuong.mytrip.entity.Travel;
import vtcac.thuhuong.mytrip.main.TravelListAdapter;
import vtcac.thuhuong.mytrip.main.TravelViewModel;

public class MainActivity extends BaseActivity implements TravelListAdapter.ListItemClickListener {
    private static String TAG = MainActivity.class.getSimpleName();
    private TravelViewModel mTravelViewModel;
    private TravelListAdapter mTravelListAdapter;
    private final Observer<List<Travel>> mTravelObserver = new Observer<List<Travel>>() {
        @Override
        public void onChanged(List<Travel> travels) {
            Log.d(TAG, "onChanged: travels.size=" + travels.size());
            mTravelListAdapter.setList(travels);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab button
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                startActivityForResult(new Intent(MainActivity.this, EditTravelActivity.class)
                        , REQCD_TRAVEL_ADD);
            }
        });

        // travel-view-model
        mTravelViewModel = ViewModelProviders.of(this).get(TravelViewModel.class);
        mTravelViewModel.getAllTravels().observe(this, mTravelObserver);
        // save sort option in shared-preference
        mTravelViewModel.setTravelSort(((MyApplication) getApplication()).getTravelSort());

        // mtravel-list-adapter
        mTravelListAdapter = new TravelListAdapter(this);
        mTravelListAdapter.setListItemClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(mTravelListAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get listener to recyclerView whenever user scroll
        // when user scroll list item down then hide fab button
        // and scroll up show fab button
        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // dy is the value changed whenever user scroll
                // scroll down then dy is positive
                // scroll up then dy is negative
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        TravelSort travelSort = ((MyApplication) getApplication()).getTravelSort();
        switch (travelSort) {
            case DEFAULT:
                menu.findItem(R.id.action_sort_default).setChecked(true);
                break;
            case TITLE_ASC:
                menu.findItem(R.id.action_sort_travel_asc).setChecked(true);
                break;
            case TITLE_DESC:
                menu.findItem(R.id.action_sort_travel_desc).setChecked(true);
                break;
            case START_ASC:
                menu.findItem(R.id.action_sort_start_asc).setChecked(true);
                break;
            case START_DESC:
                menu.findItem(R.id.action_sort_start_desc).setChecked(true);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_sort_default:
                mTravelViewModel.setTravelSort(TravelSort.DEFAULT);
                item.setChecked(true);
                return true;
            case R.id.action_sort_travel_asc:
                mTravelViewModel.setTravelSort(TravelSort.TITLE_ASC);
                item.setChecked(true);
                return true;
            case R.id.action_sort_travel_desc:
                mTravelViewModel.setTravelSort(TravelSort.TITLE_DESC);
                item.setChecked(true);
                return true;
            case R.id.action_sort_start_asc:
                mTravelViewModel.setTravelSort(TravelSort.START_ASC);
                item.setChecked(true);
                return true;
            case R.id.action_sort_start_desc:
                mTravelViewModel.setTravelSort(TravelSort.START_DESC);
                item.setChecked(true);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode);
        Log.d(TAG, "onActivityResult: resultCode=" + resultCode);
        Log.d(TAG, "onActivityResult: data=" + data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case REQCD_TRAVEL_ADD: {
                Travel travel = (Travel) data.getExtras().getSerializable(REQKEY_TRAVEL);
                Log.d(TAG, "onActivityResult: travel=" + travel);
                // insert a new travel
                mTravelViewModel.insert(travel);
            }
            break;
            case REQCD_TRAVEL_EDIT: {
                Travel travel = (Travel) data.getExtras().getSerializable(REQKEY_TRAVEL);
                Log.d(TAG, "onActivityResult: travel=" + travel);
                if (REQACTION_DEL_TRAVEL.equals(data.getAction())) {
                    mTravelViewModel.delete(travel);
                } else {
                    mTravelViewModel.update(travel);
                }
            }
            break;
        }

    }

    @Override
    public void onListItemClick(View view, int position, Travel travel) {
        Log.d(TAG, "onListItemClick: position=" + position);
        Log.d(TAG, "onListItemClick: travel=" + travel);
// call TravelDetailActivity
        Intent intent = new Intent(MainActivity.this, TravelDetailActivity.class);
        intent.putExtra(REQKEY_TRAVEL_ID, travel.getId());
        startActivity(intent);
    }

}
