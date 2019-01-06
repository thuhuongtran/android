package vtcac.thuhuong.mytrip;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import vtcac.thuhuong.mytrip.base.BaseActivity;
import vtcac.thuhuong.mytrip.traveldetail.SectionsPagerAdapter;
import vtcac.thuhuong.mytrip.traveldetail.TravelDetailBaseFragment;

public class TravelDetailActivity extends BaseActivity {

    private static final String TAG = TravelDetailActivity.class.getSimpleName();
    private SectionsPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_detail);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        // create the adapter that will return a fragment for each of the three
        // primary sections of the activity
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        // use setupWithViewPager(ViewPager) to link a TabLayout with a ViewPager.
        // The individual tabs in the TabLayout will be automatically populated.
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TravelDetailBaseFragment fragment = (TravelDetailBaseFragment) mViewPagerAdapter
                        .getRegisterdFragment(mViewPager.getCurrentItem());
                fragment.requestAddItem();
            }
        });
        mToolbarLayout = findViewById(R.id.toolbar_layout);
        mSubtitle = findViewById(R.id.subtitle_txt);

        long travelId = getIntent().getLongExtra(REQKEY_TRAVEL_ID, 0);
        Log.d(TAG, "onCreate: travelId = "+travelId);
    }

}
