package com.TusFinancial.Credit.api;

import android.content.Context;

import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.network.Api;
import com.base.qinxd.library.network.ApiRequest;

import java.net.URLEncoder;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
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
public class ApiLogin extends Api<BaseEntity> {

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

        map.put("userName", URLEncoder.encode(username));

        map.put("password", password);

        return map;
    }

    @Override
    public final Call<BaseEntity> call() {

        return mApiStore.login(postParams());

    }

    private interface ApiStore {

        @POST("login/userLogin.shtml")
        Call<BaseEntity> login(@FieldMap Map<String, String> postParams);

    }

}
