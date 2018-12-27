package com.vtcac.thuhuong.lab6.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.vtcac.thuhuong.lab6.R;
import com.vtcac.thuhuong.lab6.fragments.FriendsFragment;
import com.vtcac.thuhuong.lab6.fragments.MessageFragment;
import com.vtcac.thuhuong.lab6.fragments.TabAdapter;

public class MainActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    FriendsFragment friendsFragment;
    MessageFragment messageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(friendsFragment = new FriendsFragment(), "Friends");
        adapter.addFragment(messageFragment = new MessageFragment(), "Messages");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
