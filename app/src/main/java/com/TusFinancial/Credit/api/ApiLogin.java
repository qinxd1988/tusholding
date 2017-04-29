package com.TusFinancial.Credit.api;

import android.content.Context;
import android.text.TextUtils;

import com.TusFinancial.Credit.entity.LoginEntity;
import com.base.qinxd.library.network.Api;
import com.base.qinxd.library.network.ApiRequest;

import java.net.URLEncoder;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 15:55
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class ApiLogin extends Api<LoginEntity> {

    private ApiStore mApiStore;

    public String username;

    public String password;

    public ApiLogin(Context context) {

        super(context);

        // 初始化api
        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiLogin setUserName(String username) {

        this.username = username;

        return this;

    }

    public ApiLogin setPassWord(String password) {

        this.password = password;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(username)) {

            map.put("userName", URLEncoder.encode(username));

        }

        if (!TextUtils.isEmpty(password)) {

            map.put("password", password);

        }

        return map;
    }

    @Override
    public final Call<LoginEntity> call() {

        return mApiStore.login(postParams());

    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/login/userLogin.shtml")
        Call<LoginEntity> login(@FieldMap Map<String, String> postParams);

    }

}
