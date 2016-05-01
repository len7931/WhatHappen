package com.example.wxl.myapplication.activity;

import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.wxl.myapplication.R;
import com.example.wxl.myapplication.adapter.PagerAdaper;
import com.example.wxl.myapplication.module.NewsTopic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.navi_catergory)
    NavigationView mNaviCatergory;
    @BindView(R.id.navi_userops)
    NavigationView mNaviUserOps;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawer;
    @BindView(R.id.tablayout)
    TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initTabLayout(NewsTopic.getTopics());
    }

    private void initTabLayout(List<String> topics) {
        PagerAdaper pageAdapter = new PagerAdaper(getSupportFragmentManager(), topics);
        mViewpager.setAdapter(pageAdapter);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTab.setupWithViewPager(mViewpager);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        // TODO setHomeIcon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mDrawer.openDrawer(mNaviCatergory);
        } else if (id == R.id.opt_userops) {
            mDrawer.openDrawer(mNaviUserOps);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }
}