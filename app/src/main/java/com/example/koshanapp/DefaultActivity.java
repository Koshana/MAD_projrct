package com.example.koshanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class DefaultActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    tabLayout1 tabLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        tabLayout = findViewById(R.id.NavBar);
        viewPager = findViewById(R.id.ViewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Guiders"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayoutAdapter=new tabLayout1(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(tabLayoutAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
