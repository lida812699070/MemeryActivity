package com.example.memeryactivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class H5WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5_webview);
        WebView webView = findViewById(R.id.webView);
        initUI(webView);
        final View decorView = H5WebViewActivity.this.getWindow().getDecorView();
        decorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        int previousKeyboardHeight = -1;
        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            final View decorView = H5WebViewActivity.this.getWindow().getDecorView();
            decorView.getWindowVisibleDisplayFrame(rect);
            int displayHeight = rect.bottom;
            int height = decorView.getHeight();
            int keyboardHeight = height - displayHeight;
            if (previousKeyboardHeight != keyboardHeight) {
                boolean hide = (double) displayHeight / height > 0.8;
                Log.e("tag1", "softKeybardHeight = " + keyboardHeight + "--- visible = " +  !hide);
            }
            previousKeyboardHeight = height;
        }
    };

    private void initUI(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);
        webView.loadUrl("https://www.baidu.com");
    }


}
