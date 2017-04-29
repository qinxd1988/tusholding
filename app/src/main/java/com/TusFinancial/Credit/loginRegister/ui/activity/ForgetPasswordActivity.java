package com.TusFinancial.Credit.loginRegister.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.api.ApiFindPassWord;
import com.TusFinancial.Credit.api.ApiSendVeirfyCode;
import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.network.ApiCallBack;
import com.base.qinxd.library.ui.activity.BaseImpActivity;
import com.base.qinxd.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xd on 2017/4/23.
 * <p>
 * 忘记密码
 */

public class ForgetPasswordActivity extends BaseImpActivity {

    @BindView(R.id.jindiao_num_edit_text)
    AppCompatEditText mobileEditText;

    @BindView(R.id.jindiao_register_send_code_text)
    AppCompatTextView sendCodeText;

    @BindView(R.id.jindiao_password_edit_text)
    AppCompatEditText passwordEditText;

    @BindView(R.id.jindiao_find_password_code_edit_text)
    AppCompatEditText codeEditText;

    private int count = 60;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (count > 0) {

                if (sendCodeText != null) {

                    sendCodeText.setText(count + "秒后重发");

                }

                mHandler.sendEmptyMessageDelayed(0, 1000);

                count--;

            } else {

                count = 60;

                if (sendCodeText != null) {

                    sendCodeText.setEnabled(true);

                    sendCodeText.setText("发送验证码");

                }

            }

        }

    };

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

    @OnClick({R.id.jindiao_register_send_code_text, R.id.jindiao_find_password_text})
    void onClick(View view) {

        switch (view.getId()) {

            case R.id.jindiao_register_send_code_text:

                if (checkMobile()) {

                    loadVeirfyCode();

                }

                break;

            case R.id.jindiao_find_password_text:

                if (checkMobile() && checkCode() && checkPassword()) {

                    findPassWord();

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

        api.setBusinessType(ApiSendVeirfyCode.SEND_BY_FIND_PWD)
                .setMobile(mobile)
                .setShowLoading(true)
                .setApiCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity entity) {

                        if (entity != null) {

                            mHandler.sendEmptyMessage(0);

                            ToastUtils.showToast(ForgetPasswordActivity.this, entity.msg);

                        }

                    }

                    @Override
                    public void onError(BaseEntity entity, String err_msg) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });

        sendCodeText.setEnabled(false);

        api.enqueue();

    }

    private void findPassWord() {

        ApiFindPassWord api = new ApiFindPassWord(this);

        final String mobile = mobileEditText.getText().toString().trim();

        String code = codeEditText.getText().toString().trim();

        String password = passwordEditText.getText().toString().trim();

        api.setPhone(mobile)
                .setNewPwd(password)
                .setPhoneVerify(code)
                .setApiCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity response) {

                        if (response != null) {

                            ToastUtils.showToast(ForgetPasswordActivity.this, response.msg);

                            finish();

                        }

                    }

                    @Override
                    public void onError(BaseEntity response, String err_msg) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });

        api.enqueue();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mHandler != null) {

            mHandler.removeCallbacksAndMessages(null);

            mHandler = null;

        }

    }

}
