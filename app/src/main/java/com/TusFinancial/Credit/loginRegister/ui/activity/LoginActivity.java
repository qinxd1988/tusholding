package com.TusFinancial.Credit.loginRegister.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.api.ApiLogin;
import com.TusFinancial.Credit.entity.LoginEntity;
import com.TusFinancial.Credit.event.LoginFinishEvent;
import com.TusFinancial.Credit.helper.TransferHelper;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.network.ApiCallBack;
import com.base.qinxd.library.ui.activity.BaseImpActivity;
import com.base.qinxd.library.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    String mData;

    SharedPreferences spfs;

    @Override
    public int getLayoutResId() {
        return R.layout.jindiao_login_layout;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        initIntentData(getIntent());

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        mTitleTextView.setText("登录");

        ButterKnife.bind(this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        initIntentData(intent);

    }

    @Override
    public boolean isSupportEventBus() {

        return true;

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginFinishEvent event) {

        if (event != null) {

            //如果是网页需要登录的，登录后url直接替换登录后的token进行页面跳转
            if (mData.startsWith("http")) {

                mData = mData.replace("&token=", "&token=" + JinDiaoApplication.TOKEN);

                TransferHelper.onTransfer(LoginActivity.this, mData, false);

            }

            finish();

        }

    }

    private void initIntentData(Intent intent) {

        mData = intent.getStringExtra(Constants.EXTRA);

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

        String mobile = phoneEditText.getText().toString().trim();

        if (TextUtils.isEmpty(mobile)) {

            ToastUtils.showToast(this, "请输入手机号");

            return false;

        }

        return true;

    }

    private boolean checkPassWord() {

        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {

            ToastUtils.showToast(this, "请输入密码");

        }

        return true;

    }

    private void login() {

        final String mobile = phoneEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();

        ApiLogin apiLogin = new ApiLogin(this);

        apiLogin.setUserName(mobile)
                .setPassWord(password)
                .setApiCallBack(new ApiCallBack<LoginEntity>() {
                    @Override
                    public void onSuccess(LoginEntity response) {

                        if (response != null && response.data != null) {

                            if (!TextUtils.isEmpty(mData)
                                    && !TextUtils.isEmpty(response.data.token)) {

                                if (spfs == null) {

                                    spfs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                                }

                                //写入文件
                                spfs.edit().putString(Constants.TOKEN, response.data.token).commit();

                                spfs.edit().putString(Constants.MOBILE, mobile).commit();

                                //将token赋值全局变量
                                JinDiaoApplication.TOKEN = response.data.token;

                                JinDiaoApplication.MOBILE = mobile;

                            }

                            LoginFinishEvent event = new LoginFinishEvent();

                            event.bean = response.data;

                            //发送登录成功订阅事件
                            EventBus.getDefault().post(event);

                        }

                    }

                    @Override
                    public void onError(LoginEntity response, String err_msg) {

                        if (response != null && response.data != null) {

                            if ("1".equals(response.data.isVerfyCode)) {

                                // TODO: 2017/4/29  需要极验验证

                            }

                        }

                    }

                    @Override
                    public void onFailure() {

                    }

                });

        apiLogin.enqueue();

    }

}
