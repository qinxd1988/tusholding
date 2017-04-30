package com.TusFinancial.Credit.api;

import android.content.Context;

import com.TusFinancial.Credit.entity.LoginEntity;
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
 * 时间：2017/4/30 13:35
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：微信登录
 */
public class ApiWxLogin extends Api<LoginEntity> {

    private String code;

    private ApiStore mApiStore;

    public ApiWxLogin(Context context) {
        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiWxLogin setCode(String code) {

        this.code = code;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        map.put("code", code);

        return map;
    }

    @Override
    public final Call<LoginEntity> call() {
        return mApiStore.wxLogin(postParams());
    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/wx/login.shtml")
        Call<LoginEntity> wxLogin(@FieldMap Map<String, String> postParams);

    }

}
