package com.example.myapplicationlotr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public  TabLayout tabLayout;
    public  ViewPager viewPager;

    public String Gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Settings"));
        tabLayout.addTab(tabLayout.newTab().setText("Gas"));
        tabLayout.addTab(tabLayout.newTab().setText("Smoke"));
        tabLayout.addTab(tabLayout.newTab().setText("Temp"));
        tabLayout.addTab(tabLayout.newTab().setText("Ultraviolet"));

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0 :
                        Settings setting = new Settings();
                        return setting;
                    case 1:
                        Gas gas = new Gas();
                        return gas;
                    case 2:
                        Smoke smoke = new Smoke();
                        return smoke;
                    case 3:
                        Temp temp = new Temp();
                        return temp;
                    case 4:
                        Ultra ultra = new Ultra();
                        return ultra;
                    default:
                        return null;

                }
            }

            @Override
            public int getCount() {
                return tabLayout.getTabCount();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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