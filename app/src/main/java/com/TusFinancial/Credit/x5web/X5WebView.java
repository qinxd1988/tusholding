package com.TusFinancial.Credit.x5web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class X5WebView extends WebView {

    private OnReceivedTitleListener mOnReceivedTitleListener;

    private OnInvokeJSListener mOnInvokeJSListener;

    private WebViewClient client = new WebViewClient() {

        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;

        }

    };

    private WebChromeClient mChromeClient = new WebChromeClient() {

        private boolean isJSInvoke;

        @Override
        public void onReceivedTitle(WebView view, String title) {

            super.onReceivedTitle(view, title);

            if (mOnReceivedTitleListener != null) {

                mOnReceivedTitleListener.receivedTitle(title);

            }

        }

        @Override
        public void onProgressChanged(WebView webView, int progress) {

            if (progress >= 30 && !isJSInvoke) {//30%的时候进行js注入

                isJSInvoke = true;

                if (mOnInvokeJSListener != null) {

                    mOnInvokeJSListener.invokeJs();

                }

            } else {

                isJSInvoke = false;

            }

            super.onProgressChanged(webView, progress);

        }

    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0, AttributeSet arg1) {

        super(arg0, arg1);

        this.setWebViewClient(client);

        this.setWebChromeClient(mChromeClient);

        // WebStorage webStorage = WebStorage.getInstance();
        initWebViewSettings();

        this.getView().setClickable(true);

    }

    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }

    public X5WebView(Context arg0) {
        super(arg0);
        setBackgroundColor(85621);
    }

    public interface OnReceivedTitleListener {

        void receivedTitle(String title);

    }

    public X5WebView setOnReceivedTitleListener(OnReceivedTitleListener listener) {

        this.mOnReceivedTitleListener = listener;

        return this;

    }

    public interface OnInvokeJSListener {

        void invokeJs();

    }

    public X5WebView setOnInvokeJSListener(OnInvokeJSListener listener) {

        this.mOnInvokeJSListener = listener;

        return this;

    }

}
