package com.TusFinancial.Credit.api;

import android.content.Context;
import android.text.TextUtils;

import com.TusFinancial.Credit.event.UserEntity;
import com.base.qinxd.library.network.Api;
import com.base.qinxd.library.network.ApiRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/5/8 17:09
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class ApiUser extends Api<UserEntity> {

    private String token;

    private ApiStore mApiStore;

    public ApiUser(Context context) {

        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiUser setToken(String token) {

        this.token = token;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(token)) {

            map.put("token", token);

        }

        return map;

    }

    @Override
    public final Call<UserEntity> call() {

        return mApiStore.getUser(postParams());

    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/user/queryInfo.shtml")
        Call<UserEntity> getUser(@FieldMap Map<String, String> postParams);

    }

}
