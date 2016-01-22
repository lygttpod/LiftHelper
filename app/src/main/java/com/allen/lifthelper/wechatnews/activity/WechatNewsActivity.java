package com.allen.lifthelper.wechatnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.allen.lifthelper.R;
import com.allen.lifthelper.activity.BaseActivity;
import com.allen.lifthelper.common.UrlData;
import com.allen.lifthelper.history.adapter.HistoryListAdapter;
import com.allen.lifthelper.history.bean.HistoryBean;
import com.allen.lifthelper.history.itemhelper.DividerItemDecoration;
import com.allen.lifthelper.utils.LogUtil;
import com.allen.lifthelper.wechatnews.adapter.WechatNewsListAdapter;
import com.allen.lifthelper.wechatnews.bean.WechatNewsBean;
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
public class WechatNewsActivity extends BaseActivity {
    private RecyclerView wechatnews_rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WechatNewsListAdapter adapter;
    private List<WechatNewsBean.ResultEntity.ListEntity> resultEntities = new ArrayList<>();

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
        getToolbar().setTitle("微信精选").setDisplayHomeAsUpEnabled(true);
    }

    private void setRequest() {
        class wechatnewsCallBack extends Callback<WechatNewsBean> {
            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public WechatNewsBean parseNetworkResponse(Response response) throws IOException {
                String string = response.body().string();
                LogUtil.d("allen", string);
                WechatNewsBean wechatNewsBean = new Gson().fromJson(string, WechatNewsBean.class);
                return wechatNewsBean;
            }

            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(WechatNewsBean response) {
                for (WechatNewsBean.ResultEntity.ListEntity resultEntity : response.getResult().getList()) {
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
        OkHttpUtils.get().url(UrlData.wechatnews_url + 1).build().execute(new wechatnewsCallBack());
    }

    private void initview() {

        wechatnews_rv = (RecyclerView) findViewById(R.id.history_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_layout);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(listener);
        setRecyclerView();
    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new WechatNewsListAdapter(resultEntities, this);
        adapter.setmOnItemClickListener(new WechatNewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(WechatNewsActivity.this, WechatNewDetailsActivity.class);
                intent.putExtra("url", resultEntities.get(position).getUrl());
                intent.putExtra("title", resultEntities.get(position).getTitle());
                startActivity(intent);
            }
        });
        wechatnews_rv.setLayoutManager(layoutManager);
        wechatnews_rv.setAdapter(adapter);
        wechatnews_rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_history;
    }
}
