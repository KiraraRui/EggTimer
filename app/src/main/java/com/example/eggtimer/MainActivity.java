package com.example.eggtimer;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eggtimer.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    public void startTimer(View view) {
    }

    public void softBoiledButton(View view) {
    }

    public void mediumBoiledButton(View view) {
    }

    public void hardBoiledButton(View view) {
    }


    public void onButtonEggSelectedClicked(View view) {
        switch (view.getId()) {
            case R.id.softBoiledButton:

                break;
            case R.id.mediumBoiledButton:

                break;
            case R.id.hardBoiledButton:

                break;
            default:
                throw new RuntimeException("Unknow button ID");

        }
    }




}