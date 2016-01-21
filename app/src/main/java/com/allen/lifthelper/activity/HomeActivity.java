package com.allen.lifthelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.allen.lifthelper.R;
import com.allen.lifthelper.adapter.MyListAdapter;
import com.allen.lifthelper.bean.ListDataBean;
import com.allen.lifthelper.common.ListData;
import com.allen.lifthelper.robot.activity.RobotActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 16/1/20.
 */
public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private List<ListDataBean> listDataBeans = new ArrayList<>();
    private MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_recyclerview);
        setDrawerLayou();
        setRecyclerView();
    }

    private void setDrawerLayou() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, getmToolbar(), R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setRecyclerView() {
        if (listDataBeans.size() >= 0) {
            listDataBeans.clear();
        }
        listDataBeans = ListData.getData(ListData.Common_life, ListData.Common_life_icon);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myListAdapter = new MyListAdapter(listDataBeans);
        myListAdapter.setmOnItemClickListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(HomeActivity.this, RobotActivity.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (listDataBeans.size() >= 0) {
            listDataBeans.clear();
        }
        if (id == R.id.nav_common_life) {
            listDataBeans = ListData.getData(ListData.Common_life, ListData.Common_life_icon);
        } else if (id == R.id.nav_financial_services) {
            listDataBeans = ListData.getData(ListData.Financial_services, ListData.Common_life_icon);
        } else if (id == R.id.nav_move_fun) {
            listDataBeans = ListData.getData(ListData.Move_fun, ListData.Common_life_icon);
        } else if (id == R.id.nav_tourism_travel) {
            listDataBeans = ListData.getData(ListData.Tourism_travel, ListData.Common_life_icon);
        } else if (id == R.id.nav_medical_health) {
            listDataBeans = ListData.getData(ListData.Medical_health, ListData.Common_life_icon);
        } else if (id == R.id.nav_knowledge) {
            listDataBeans = ListData.getData(ListData.knowledge, ListData.Common_life_icon);
        }

        myListAdapter.notifyDataSetChanged();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
