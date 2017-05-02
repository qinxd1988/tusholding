package com.TusFinancial.Credit.browse.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.browse.ui.fragment.BrowseFragment;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.ui.activity.BaseImpActivity;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/26 14:25
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class BrowseActivity extends BaseImpActivity {

    private String url;

    @Override
    public int getLayoutResId() {

        return R.layout.jindiao_replace_layout;

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        url = getIntent().getStringExtra(Constants.URL);

        Uri uri = getIntent().getData();

        if (uri != null) {

            String tempUrl = uri.getQueryParameter(Constants.URL);

            if (!TextUtils.isEmpty(tempUrl)) {

                url = Uri.decode(tempUrl);

            }

        }

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        if (mFragment == null) {

            mFragment = BrowseFragment.newInstance(url);

        }

        operateFragment(mFragment, R.id.jindiao_replace_layout);

    }

    public void setTitle(String title) {

        mTitleTextView.setText(title);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        boolean flag = false;

        if (mFragment != null) {

            flag = mFragment.onKeyDown(keyCode, event);

        }

        if (flag) {

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }

}
