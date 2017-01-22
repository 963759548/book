package com.baidu.chapter6.section7;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.baidu.chapter6.R;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        // webViewSetting();
        //webView加载网页
        //webView.loadUrl("http://www.baidu.com");
        webView.loadUrl("file:///android_asset/test.html");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void webViewSetting() {
        WebSettings webSettings = webView.getSettings();

        //支持获取手势焦点，输入用户名、密码或其他
        webView.requestFocusFromTouch();

        //支持js,这样有潜在的安全隐患,无需要,建议关闭
        webSettings.setJavaScriptEnabled(true);

        //提高渲染的优先级，高版本废弃了。
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        //  设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        webSettings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        //若下面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。


        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //支持多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式


        //----缓存
        //根据网页中cache-control决定是否从网络上取数据。
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载


        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能

        String cacheDirPath = getCacheDir().getAbsolutePath();
        //设置  Application Caches 缓存目录
        //每个 Application 只调用一次 WebSettings.setAppCachePath()
        webSettings.setAppCachePath(cacheDirPath);

        //Android 5.0上 Webview 默认不允许加载 Http 与 Https 混合内容,加下下面的配置就解决了.
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

    }

    WebViewClient mWebViewClient = new WebViewClient() {
        //在网页上的所有加载都经过这个方法,这个函数我们可以做很多操作。
        //比如获取url，查看url.contains(“add”)，进行添加操作
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        // 21版本引入 同上
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        //这个事件就是开始载入页面调用的，我们可以设定一个loading的页面，告诉用户程序在等待网络响应。
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        //在页面加载结束时调用。同样道理，我们可以关闭loading 条，切换程序动作。
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        // 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        // 拦截替换网络请求数据,  API 11开始引入，API 21弃用
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            return super.shouldInterceptRequest(view, url);
        }

        // 拦截替换网络请求数据,  从API 21开始引入
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        //报告错误信息 API 21 废弃
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        //报告错误信息 API 21 引用
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        //接收http 错误信息
        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
        }

        //应用程序重新请求网页数据
        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            super.onFormResubmission(view, dontResend, resend);
        }

        //更新历史记录
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }

        //重写此方法可以让webview处理https请求。
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
        }


        //获取返回信息授权请求
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        //Key事件未被加载时调用
        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            super.onUnhandledKeyEvent(view, event);
        }

        // WebView发生改变时调用
        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
        }

    };
    WebChromeClient mWebChromeClient = new WebChromeClient() {

        //获得网页的加载进度
        @Override
        public void onProgressChanged(WebView view, int newProgress) {

        }

        //获取Web页中的title用来设置自己界面中的title
        //当加载出错的时候，比如无网络，这时onReceiveTitle中获取的标题为 找不到该网页,
        //因此建议当触发onReceiveError时，不要使用获取到的title
        @Override
        public void onReceivedTitle(WebView view, String title) {

        }

        //获取Web页icon
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            //
        }

        //创建窗口
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            //
            return true;
        }

        //关闭窗口
        @Override
        public void onCloseWindow(WebView window) {
        }

        //处理alert弹出框，html 弹框的一种方式
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            //
            return true;
        }

        //处理confirm弹出框
        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult
                result) {
            //
            return true;
        }

        //处理prompt弹出框
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            //
            return true;
        }
    };

    void webViewMethod() {
        webView.goBack();//后退
        webView.goForward();//前进

        //以当前的index为起始点前进或者后退到历史记录中指定的steps， 如果steps为负数则为后退，正数则为前进
        // webView.goBackOrForward(intsteps);


        webView.canGoForward();//判断是否可以前进
        webView.canGoBack(); //判断是否可以后退

        webView.clearCache(true);//清除网页访问留下的缓存，由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        webView.clearHistory();//清除当前webview访问的历史记录，只会webview访问历史记录里的所有记录除了当前访问记录.
        webView.clearFormData();//这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据。

        webView.onResume(); //激活WebView为活跃状态，能正常执行网页的响应
        webView.onPause();//当页面被失去焦点被切换到后台不可见状态，需要执行onPause动过， onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。

        webView.pauseTimers();//当应用程序被切换到后台我们使用了webview， 这个方法不仅仅针对当前的webview而是全局的全应用程序的webview，它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。
        webView.resumeTimers();//恢复pauseTimers时的动作。

        webView.destroy();//销毁，关闭了Activity时，音乐或视频，还在播放。就必须销毁
    }
    //按键事件
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //当点击的返回键 && webView可以回退
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();  //回退界面
            return true;
        }
        //没有点击返回键或者webView不能回退就处理默认的事件
        return super.onKeyDown(keyCode, event);
    }
}