package com.simplexu.good.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.simplexu.good.R;
import com.simplexu.good.model.CollectBean;
import com.simplexu.good.utils.CollectDB;
import com.simplexu.good.utils.ShareUtils;

public class DataActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebView;
    private TextView mTextView;

    private String Url;
    private String Desc;
    private String Who;
    private String PublishedAt;
    private String Type;

    private CollectBean collectBean;
    private boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        mTextView = (TextView) findViewById(R.id.tv);

        getData();
        initToolbar();
        initWebView();
    }

    private void getData() {
        Intent intent = getIntent();
        Desc = intent.getStringExtra("Desc");
        PublishedAt = intent.getStringExtra("PublishedAt");
        Url = intent.getStringExtra("Url");
        Who = intent.getStringExtra("Who");
        Type = intent.getStringExtra("Type");

        collectBean = new CollectBean(Url,Desc,Who,PublishedAt,Type);
        isFavourite = CollectDB.getInstance(this).isFavourite(collectBean);
    }

    private void initToolbar() {
        //Toolbar的设置
        mToolbar = (Toolbar) findViewById(R.id.data_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Desc);

    }

    private void initWebView() {
        //WebView的设置
        mWebView = (WebView) findViewById(R.id.wv_data);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setAppCacheEnabled(true);

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(Url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_share:
                ShareUtils.shareText(this,Desc+"-"+Url);
                break;
            case R.id.action_collect:
                if (!isFavourite){
                    CollectDB.getInstance(this).saveCollect(collectBean);
                    Snackbar.make(mTextView,"文章成功收藏",Snackbar.LENGTH_SHORT).show();
                    isFavourite = true;
                }else {
                    Snackbar.make(mTextView,"亲，已经在你的收藏栏",Snackbar.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
