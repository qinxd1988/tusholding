package com.TusFinancial.Credit.api;

import android.content.Context;
import android.text.TextUtils;

import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.network.Api;
import com.base.qinxd.library.network.ApiRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xd on 2017/4/23.
 */

public class ApiRegAccount extends Api<BaseEntity> {

    ApiStore mApiStore;

    private String phone;

    private String password;

    private String phoneVerify;

    public ApiRegAccount(Context context) {
        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiRegAccount setPhone(String phone) {

        this.phone = phone;

        return this;

    }

    public ApiRegAccount setPassword(String password) {

        this.password = password;

        return this;

    }

    public ApiRegAccount setPhoneVerify(String phoneVerify) {

        this.phoneVerify = phoneVerify;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(phone)) {

            map.put("phone", phone);

        }

        if (!TextUtils.isEmpty(phoneVerify)) {

            map.put("phoneVerify", phoneVerify);

        }

        if (!TextUtils.isEmpty(password)) {

            map.put("password", password);

        }

        return map;

    }

    @Override
    public final Call<BaseEntity> call() {

        return mApiStore.regAccount(postParams());

    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/regist/regAccount.shtml")
        Call<BaseEntity> regAccount(@FieldMap Map<String, String> postParams);

    }

}
