package com.allen.lifthelper.history.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.allen.lifthelper.R;
import com.allen.lifthelper.activity.BaseActivity;
import com.allen.lifthelper.common.UrlData;
import com.allen.lifthelper.history.adapter.HistoryListAdapter;
import com.allen.lifthelper.history.bean.HistoryBean;
import com.allen.lifthelper.history.itemhelper.DividerItemDecoration;
import com.allen.lifthelper.robot.bean.GetMsgBean;
import com.allen.lifthelper.utils.LogUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2016/1/22.
 */
public class HistortActivity extends BaseActivity {
    private RecyclerView history_rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HistoryListAdapter adapter;
    private List<HistoryBean.ResultEntity> resultEntities = new ArrayList<>();

    SwipeRefreshLayout.OnRefreshListener listener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            resultEntities.clear();
            setRequest();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initview();
        //setRequest();
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh();
    }

    private void initToolbar() {
        getToolbar().setTitle("历史上的今天").setDisplayHomeAsUpEnabled(true);
    }

    private void setRequest() {
        class historyCallBack extends Callback<HistoryBean> {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public HistoryBean parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                LogUtil.d("allen", string);
                HistoryBean historyBean = new Gson().fromJson(string, HistoryBean.class);
                return historyBean;
            }

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(HistoryBean response) {
                for (HistoryBean.ResultEntity resultEntity : response.getResult()) {
                    resultEntities.add(resultEntity);
                }

            }

            @Override
            public void onAfter() {
                super.onAfter();
                adapter.notifyDataSetChanged();
//                swipeRefreshLayout.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                });
                swipeRefreshLayout.setRefreshing(false);
            }
        }
        OkHttpUtils.get().url(UrlData.history_url + "month=" + 1 + "&day=" + 22).build().execute(new historyCallBack());
    }

    private void initview() {

        history_rv = (RecyclerView) findViewById(R.id.history_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(listener);
        setRecyclerView();
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new HistoryListAdapter(resultEntities, this);
        history_rv.setLayoutManager(layoutManager);
        history_rv.setAdapter(adapter);
        history_rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_history;
    }
}
