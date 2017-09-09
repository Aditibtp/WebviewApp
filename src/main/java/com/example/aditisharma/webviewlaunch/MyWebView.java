package com.example.aditisharma.webviewlaunch;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by aditi.sharma on 09/09/17.
 */

public class MyWebView extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_screen);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        Bundle bundle = getIntent().getExtras();
        final String urlProvided = bundle.getString("urlProvided");
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        //mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        mWebView.setWebChromeClient(new WebChromeClient());
        //mWebView.loadUrl("http://www.html5rocks.com/");
        mWebView.loadUrl("file:///android_asset/www/htmltest.html");
        // Stop local links and redirects from opening in browser instead of WebView
        mWebView.setWebViewClient(new MyAppWebViewClient(){
            public void onPageFinished(WebView view, String url){
                mWebView.loadUrl("javascript:getUrl('" + urlProvided + "')");
            }
        });

        mWebView.addJavascriptInterface(new WebAppInterface(this), "Test");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
