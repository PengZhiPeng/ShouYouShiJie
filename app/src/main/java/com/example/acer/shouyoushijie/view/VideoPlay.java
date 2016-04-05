package com.example.acer.shouyoushijie.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.acer.shouyoushijie.R;

/**
 * webview播放视频。
 * Created by acer on 2016/3/30.
 */
public class VideoPlay extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
        dialog.setMessage("正在加载...");
        dialog.show();

        initWebView();

        Intent intent = getIntent();
        if (intent != null) {
            String link = intent.getStringExtra("link");
            webView.loadUrl(link);
        }
    }

    private void initWebView() {
        webView = (WebView) findViewById(R.id.id_webview);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (dialog.isShowing())
                    dialog.dismiss();// FIXME: 加载完成后对话框仍存在，需要手动点击屏幕任意处消失。
            }
        });
        webView.setWebChromeClient(new WebChromeClient());

        WebSettings ws = webView.getSettings();
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        ws.setLoadWithOverviewMode(true);
        ws.setJavaScriptEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
        webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
