package com.TusFinancial.Credit.loginRegister.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.TusFinancial.Credit.R;
import com.base.qinxd.library.ui.activity.BaseImpActivity;

import butterknife.ButterKnife;

/**
 * Created by xd on 2017/4/23.
 *
 * 忘记密码
 *
 */

public class ForgetPasswordActivity extends BaseImpActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.jindiao_forget_password_layout;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        mTitleTextView.setText("忘记密码");

        ButterKnife.bind(this);

    }

}
