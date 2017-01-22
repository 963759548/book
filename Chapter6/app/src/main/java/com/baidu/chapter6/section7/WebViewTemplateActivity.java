package com.baidu.chapter6.section7;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baidu.chapter6.R;
import com.daimajia.numberprogressbar.NumberProgressBar;

public class WebViewTemplateActivity extends AppCompatActivity {

    /**
     * 传输的网址
     */
    public static final String URL = "url";
    /**
     * 网页标题
     */
    private static final String TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initView();
        initData();
    }


    String url;
    String title;

    //初始化传递的信息
    private void init() {
        Intent intent = getIntent();
        url = intent.getStringExtra(URL);
        title = intent.getStringExtra(TITLE);

        //为了演示方便 给url和title直接赋值
        if(TextUtils.isEmpty(url)) {
            url = "http://www.baidu.com";
            title = "百度";
        }
    }

    private WebView mWebView;
    private NumberProgressBar progressBar;

    //初始化界面
    private void initView() {
        setContentView(R.layout.activity_web_view_template);
        mWebView = (WebView) findViewById(R.id.web_view);
        progressBar= (NumberProgressBar) findViewById(R.id.progressbar);
        if (!TextUtils.isEmpty(title)) {
            //设置actionBar的title
            setTitle(title);
        }
    }

    protected void initData() {
        mWebView.getSettings().setBuiltInZoomControls(false); // 放大缩小按钮
        //mWebView.getSettings().setJavaScriptEnabled(true); // JS允许
        mWebView.setWebChromeClient(new WebChromeClient()); // Chrome内核
        //    mWebView.setInitialScale(100);// 设置缩放比例
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true); //
        settings.setAppCacheEnabled(true); //缓存
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//算法
        settings.setSupportZoom(true); //支持缩放
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            // 超链接的时候
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(url);
                return true;
            }
        });
        if (!TextUtils.isEmpty(url)) {
            loadUrl(url);
        }

        //碰到需要下载的链接,跳转到浏览器进行下载
        mWebView.setDownloadListener(new MyWebViewDownLoadListener());
    }


    private class MyWebViewDownLoadListener implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype,
                                    long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //设置进度
            progressBar.setProgress(newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        // 收到标题了
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    /**
     * 加载网页
     *
     * @param url 要显示的网页
     */
    public void loadUrl(String url) {
        if (mWebView != null) {
            mWebView.loadUrl(url);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) mWebView.destroy();
    }

    @Override
    protected void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) mWebView.onResume();
    }

    /**
     * @param context 上下文
     * @param url     网址
     * @param title   标题
     * @return intent
     */
    public static Intent newIntent(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewTemplateActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        return intent;
    }
}
