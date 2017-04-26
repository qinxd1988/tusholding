package com.TusFinancial.Credit.browse.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

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

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        if(mFragment == null){

            mFragment = BrowseFragment.newInstance(url);

        }

        operateFragment(mFragment,R.id.jindiao_replace_layout);

    }

    public void setTitle(String title){

        mTitleTextView.setText(title);

    }

}
