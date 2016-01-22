package com.allen.lifthelper.wechatnews.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.allen.lifthelper.R;
import com.allen.lifthelper.activity.BaseActivity;

/**
 * Created by Allen on 2016/1/22.
 */
public class WechatNewDetailsActivity extends BaseActivity {
    private WebView webView;
    private String url;
    private String title;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        setToolbar();
        progressBar = (ProgressBar) findViewById(R.id.loading);
        webView = (WebView) findViewById(R.id.wechatnews_details_wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);

            }
        });

        webView.loadUrl(url);

    }

    private void setToolbar() {
        getToolbar().setTitle(title).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_wechatnews_details;
    }
}
