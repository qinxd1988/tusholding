package com.TusFinancial.Credit.loginRegister.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.api.ApiRegAccount;
import com.TusFinancial.Credit.api.ApiSendVeirfyCode;
import com.TusFinancial.Credit.bean.LoginBean;
import com.TusFinancial.Credit.entity.RegisterEntity;
import com.TusFinancial.Credit.event.LoginFinishEvent;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.network.ApiCallBack;
import com.base.qinxd.library.ui.activity.BaseImpActivity;
import com.base.qinxd.library.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/14 11:36
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：注册页面
 */
public class RegisterActivity extends BaseImpActivity {

    @BindView(R.id.jindiao_num_edit_text)
    AppCompatEditText mobileEditText;

    @BindView(R.id.jindiao_register_code_edit_text)
    AppCompatEditText codeEditText;

    @BindView(R.id.jindiao_password_edit_text)
    AppCompatEditText passwordEditText;

    @BindView(R.id.jindiao_register_text)
    AppCompatTextView registerText;

    @BindView(R.id.jindiao_register_send_code_text)
    AppCompatTextView sendCodeText;

    SharedPreferences spfs;

    @Override
    public int getLayoutResId() {
        return R.layout.jindiao_register_layout;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initView() {

        mToolbar.setBackgroundResource(R.color.color_373b3e);

        mTitleTextView.setText("注册");

        ButterKnife.bind(this);

    }

    @OnClick({R.id.jindiao_register_send_code_text, R.id.jindiao_register_text})
    void onClick(View view) {

        switch (view.getId()) {

            case R.id.jindiao_register_send_code_text:

                if (checkMobile()) {

                    loadVeirfyCode();

                }

                break;

            case R.id.jindiao_register_text:

                if (checkMobile() && checkCode() && checkPassword()) {

                    register();

                }

                break;

        }

    }

    boolean checkMobile() {

        String mobile = mobileEditText.getText().toString().trim();

        if (TextUtils.isEmpty(mobile)) {

            ToastUtils.showToast(this, "请输入手机号码！");

            return false;

        }

        return true;

    }

    boolean checkCode() {

        String code = codeEditText.getText().toString().trim();

        if (TextUtils.isEmpty(code)) {

            ToastUtils.showToast(this, "请输入验证码！");

            return false;

        }

        return true;

    }

    boolean checkPassword() {

        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {

            ToastUtils.showToast(this, "请输入密码！");

            return false;

        }

        return true;

    }

    private void loadVeirfyCode() {

        ApiSendVeirfyCode api = new ApiSendVeirfyCode(this);

        String mobile = mobileEditText.getText().toString().trim();

        api.setBusinessType(ApiSendVeirfyCode.SEND_BY_REGIST_ACCOUNT)
                .setMobile(mobile)
                .setShowLoading(true)
                .setApiCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity entity) {

                        if (entity != null) {

                            ToastUtils.showToast(RegisterActivity.this, entity.msg);

                        }

                    }

                    @Override
                    public void onError(BaseEntity entity, String err_msg) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });

        api.enqueue();

    }

    private void register() {

        final String mobile = mobileEditText.getText().toString().trim();

        String code = codeEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();

        ApiRegAccount api = new ApiRegAccount(this);

        api.setPassword(password)
                .setPhone(mobile)
                .setPhoneVerify(code)
                .setApiCallBack(new ApiCallBack<RegisterEntity>() {
                    @Override
                    public void onSuccess(RegisterEntity response) {

                        if (response != null && response.data != null) {

                            if (!TextUtils.isEmpty(response.data)) {

                                if (spfs == null) {

                                    spfs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                                }

                                //写入文件
                                spfs.edit().putString(Constants.TOKEN, response.data).commit();

                                spfs.edit().putString(Constants.MOBILE, mobile).commit();

                                //将token赋值全局变量
                                JinDiaoApplication.TOKEN = response.data;

                                JinDiaoApplication.MOBILE = mobile;

                            }

                            LoginFinishEvent event = new LoginFinishEvent();

                            LoginBean bean = new LoginBean();

                            bean.token = response.data;

                            event.bean = bean;

                            //发送登录成功订阅事件
                            EventBus.getDefault().post(event);

                            finish();

                        }

                    }

                    @Override
                    public void onError(RegisterEntity entity, String err_msg) {

                    }

                    @Override
                    public void onFailure() {

                    }

                });

        api.enqueue();

    }

}
