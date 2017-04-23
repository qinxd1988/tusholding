package com.TusFinancial.Credit.loginRegister.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.TusFinancial.Credit.R;
import com.base.qinxd.library.ui.activity.BaseImpActivity;

import butterknife.ButterKnife;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/14 11:36
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：手机快速登录页面
 */
public class PhoneQuickLoginActivity extends BaseImpActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.jindiao_phone_quick_login_layout;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        mTitleTextView.setText("手机快捷登录");

        ButterKnife.bind(this);

    }

}
