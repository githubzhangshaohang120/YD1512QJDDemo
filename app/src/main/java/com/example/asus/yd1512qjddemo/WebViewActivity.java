package com.example.asus.yd1512qjddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //接受地址
        Intent intent=getIntent();
        String detailurl = intent.getStringExtra("detailUrl");
        initView();
        mWv.loadUrl(detailurl);
    }

    private void initView() {
        mWv = (WebView) findViewById(R.id.wv);

        WebSettings settings = mWv.getSettings();
        //支持js
        settings.setJavaScriptEnabled(true);
    }
}
