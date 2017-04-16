package com.TusFinancial.Credit.login.ui.activity;

import com.TusFinancial.Credit.api.ApiLogin;
import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.network.ApiCallBack;
import com.base.qinxd.library.ui.activity.BaseActivity;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/14 11:34
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：登录页面
 */
public class LoginActivity extends BaseActivity {

    private void loadData() {

        ApiLogin apiLogin = new ApiLogin(this);

        apiLogin.setUserName("")
                .setPassWord("")
                .setShowLoading(true)
                .setApiCallBack(new ApiCallBack<BaseEntity>() {
                    @Override
                    public void onSuccess(BaseEntity response) {

                    }

                    @Override
                    public void onError(String err_msg) {

                    }

                    @Override
                    public void onFailure() {

                    }
                });

        apiLogin.enqueue();

    }


}
