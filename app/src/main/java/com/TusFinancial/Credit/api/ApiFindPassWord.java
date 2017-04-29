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
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/29 18:21
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：找回密码
 */
public class ApiFindPassWord extends Api<BaseEntity> {

    private String phone;

    private String phoneVerify;

    private String newPwd;

    private ApiStore mApiStore;

    public ApiFindPassWord(Context context) {

        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiFindPassWord setPhone(String phone) {

        this.phone = phone;

        return this;

    }

    public ApiFindPassWord setPhoneVerify(String phoneVerify) {

        this.phoneVerify = phoneVerify;

        return this;

    }

    public ApiFindPassWord setNewPwd(String newPwd) {

        this.newPwd = newPwd;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(phone)) {

            map.put("phone", phone);

        }

        if (!TextUtils.isEmpty(newPwd)) {

            map.put("newPwd", newPwd);

        }

        if (!TextUtils.isEmpty(phoneVerify)) {

            map.put("phoneVerify", phoneVerify);

        }

        return map;

    }


    @Override
    public final Call<BaseEntity> call() {

        return mApiStore.findPwd(postParams());

    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/findPassword/findLoginPwdByPhone.shtml")
        Call<BaseEntity> findPwd(@FieldMap Map<String, String> postParams);

    }

}
