package com.example.aditisharma.webviewlaunch;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openWebView(View view)
    {
        mEdit   = (EditText)findViewById(R.id.url_text_view);

        String vidUrl = mEdit.getText().toString();
        Intent intent = new Intent(MainActivity.this, MyWebView.class);
        intent.putExtra("urlProvided", vidUrl);
        startActivity(intent);
    }
}
