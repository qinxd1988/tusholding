package com.TusFinancial.Credit.loginRegister.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.api.ApiLogin;
import com.TusFinancial.Credit.loginRegister.ui.activity.RegisterActivity;
import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.network.ApiCallBack;
import com.base.qinxd.library.ui.activity.BaseImpActivity;
import com.base.qinxd.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/14 11:34
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：登录页面
 */
public class LoginActivity extends BaseImpActivity {

    @BindView(R.id.jindiao_num_edit_text)
    AppCompatEditText phoneEditText;

    @BindView(R.id.jindiao_password_edit_text)
    AppCompatEditText passwordEditText;

    @Override
    public int getLayoutResId() {
        return R.layout.jindiao_login_layout;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        mTitleTextView.setText("登录");

        ButterKnife.bind(this);

    }

    @OnClick(R.id.jindiao_register_text)
    void registerOnClick() {

        Intent intent = new Intent(this, RegisterActivity.class);

        startActivity(intent);

    }

    @OnClick(R.id.jindiao_forget_password_text)
    void forgetPasswordClick() {

        Intent intent = new Intent(this, ForgetPasswordActivity.class);

        startActivity(intent);

    }

    @OnClick({R.id.jindiao_phone_quick_login_text, R.id.jindiao_wechat_login_text})
    void phoneQuickClick(View view) {

        switch (view.getId()) {

            case R.id.jindiao_phone_quick_login_text: {

                Intent intent = new Intent(this, PhoneQuickLoginActivity.class);

                startActivity(intent);

            }

            break;

            case R.id.jindiao_wechat_login_text:


                break;

        }

    }

    @OnClick(R.id.jindiao_login_text)
    void onLoginClick() {

        if (checkPhoneNum() && checkPassWord()) {

            login();

        }

    }

    private boolean checkPhoneNum() {

        return true;

    }

    private boolean checkPassWord() {

        return true;

    }

    private void login() {

        String mobile = phoneEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();

        ApiLogin apiLogin = new ApiLogin(this);

        apiLogin.setUserName(mobile)
                .setPassWord(password)
                .setShowLoading(true)
                .setApiCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity response) {

                    }

                    @Override
                    public void onError(String err_msg) {

                        ToastUtils.showToast(LoginActivity.this, err_msg);

                    }

                    @Override
                    public void onFailure() {

                    }

                });

        apiLogin.enqueue();

    }

}
