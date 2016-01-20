package com.allen.lifthelper.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allen.lifthelper.R;
import com.allen.lifthelper.adapter.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 16/1/20.
 */
public class HomeActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData();
        init();
    }

    private void setData() {
        list.add("生活常用");
        list.add("影视娱乐");
        list.add("旅游出行");
        list.add("金融服务");
        list.add("医药健康");
    }

    private void init() {
        mRecyclerView = (RecyclerView) findViewById(R.id.home_recyclerview);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        MyListAdapter myListAdapter = new MyListAdapter(list);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myListAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }
}
