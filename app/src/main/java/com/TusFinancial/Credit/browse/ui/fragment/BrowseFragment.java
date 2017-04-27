package com.TusFinancial.Credit.browse.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.browse.ui.activity.BrowseActivity;
import com.TusFinancial.Credit.utils.Constants;
import com.TusFinancial.Credit.x5web.X5WebView;
import com.base.qinxd.library.ui.fragment.BaseFragment;
import com.base.qinxd.library.utils.ContextUtil;

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

    public static BrowseFragment newInstance(String url) {

        Bundle bundle = new Bundle();

        bundle.putString(Constants.URL, url);

        BrowseFragment fragment = new BrowseFragment();

        fragment.setArguments(bundle);

        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Bundle bundle = getArguments();

        //测试提交
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

    private void initX5WebView() {

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

    }

    private void loadData() {

        mWebView.loadUrl(mUrl);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mWebView != null) {

            mWebView.destroy();

        }

    }
}
