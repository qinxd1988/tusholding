package com.TusFinancial.Credit.browse.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.browse.ui.activity.BrowseActivity;
import com.TusFinancial.Credit.event.LoginFinishEvent;
import com.TusFinancial.Credit.event.LoginOutEvent;
import com.TusFinancial.Credit.helper.TransferHelper;
import com.TusFinancial.Credit.utils.Constants;
import com.TusFinancial.Credit.x5web.X5WebView;
import com.base.qinxd.library.network.utils.Const;
import com.base.qinxd.library.ui.fragment.BaseFragment;
import com.base.qinxd.library.utils.ContextUtil;
import com.base.qinxd.library.utils.ToastUtils;
import com.google.gson.JsonObject;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URLEncoder;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/26 14:23
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class BrowseFragment extends BaseFragment {

    private String mUrl;

    X5WebView mWebView;

    private String mCallBack;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Bundle bundle = getArguments();

        if (bundle != null) {

            mUrl = bundle.getString(Constants.URL);

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.jindiao_browse_layout, null);

        mWebView = (X5WebView) view.findViewById(R.id.browse_web_view);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initX5WebView();

        loadData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mWebView != null) {

            mWebView.destroy();

        }

    }

    public static BrowseFragment newInstance(String url) {

        Bundle bundle = new Bundle();

        bundle.putString(Constants.URL, url);

        BrowseFragment fragment = new BrowseFragment();

        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    protected boolean isSupportEventBus() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginFinishEvent event) {

        if (event != null && !TextUtils.isEmpty(mCallBack)) {

            if (mWebView != null) {
                JsonObject object = new JsonObject();

                String  token = event.bean.token;

                object.addProperty("status", token!=null ? 1 : 0);

                object.addProperty("result", token);

                String data = Uri.encode(object.toString());

                mWebView.loadUrl("javascript:window.tusAppBridge.notify({callback:" + mCallBack + ", data:" + data + "})");

                ToastUtils.showToast(getContext(),
                        "登陆完城---------mCallBack:" + mCallBack + ",data:" + data,Toast.LENGTH_LONG);
            }

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginOutEvent event) {


    }

    private void initX5WebView() {

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (!TextUtils.isEmpty(url)) {

                    if (url.startsWith("http")) {

                        return super.shouldOverrideUrlLoading(view, url);

                    } else {

                        mCallBack = TransferHelper.overrideUrl(getContext(), url);

                        return true;

                    }

                }

                return super.shouldOverrideUrlLoading(view, url);

            }

        });

        mWebView.setOnReceivedTitleListener(new X5WebView.OnReceivedTitleListener() {
            @Override
            public void receivedTitle(final String title) {

                if (getActivity() instanceof BrowseActivity) {

                    if (ContextUtil.isAcivityActive(getActivity())) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                ((BrowseActivity) getActivity()).setTitle(title);

                            }

                        });

                    }

                }

            }
        });

        mWebView.setOnInvokeJSListener(new X5WebView.OnInvokeJSListener() {
            @Override
            public void invokeJs() {

                invokeJSToWebView();

            }

        });

    }

    private void loadData() {
        mWebView.loadUrl(mUrl);
    }

    private void invokeJSToWebView() {

        mWebView.loadUrl("javascript:function loadScript(url, callback)" +
                "{" +
                "    var head = document.getElementsByTagName('head')[0];" +
                "    var script = document.createElement('script');" +
                "    script.type = 'text/javascript';" +
                "    script.src = url;" +
                "    script.onreadystatechange = callback;" +
                "    script.onload = function initJs(){" +
                "    window.tusAppBridge.setScheme(tusc);" +
                "    window.tusAppBridge.initData(null);" +
                "    window.tusAppBridge.initReadyEvent(null);" +
                "    alert('success');" +
                "};" +
                "    head.appendChild(script);" +
                "}");
//        mWebView.loadUrl("javascript:loadScript('file:///android_asset/tusApp.js','callback')");
        mWebView.loadUrl("javascript:loadScript('" + Const.BASE_URL + "/web/src/js/common/tusApp.js','callback')");
        mWebView.loadUrl("javascript:window.WSBridge={platform:'Android'}");//需要给js客户端类型标识

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mWebView.canGoBack()) {

                mWebView.goBack();

                return true;

            } else {

                return false;

            }

        }

        return super.onKeyDown(keyCode, event);

    }

}
