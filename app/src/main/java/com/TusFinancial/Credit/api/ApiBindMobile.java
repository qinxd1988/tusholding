package com.TusFinancial.Credit.api;

import android.content.Context;
import android.text.TextUtils;

import com.TusFinancial.Credit.entity.RegisterEntity;
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
 * 时间：2017/4/30 17:02
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：绑定手机号
 */
public class ApiBindMobile extends Api<RegisterEntity> {

    private ApiStore mApiStore;

    private String mobile;

    private String openId;

    private String passwd;

    private String vcode;

    public ApiBindMobile(Context context) {
        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiBindMobile setMobile(String mobile) {

        this.mobile = mobile;

        return this;

    }

    public ApiBindMobile setPassWord(String passwd) {

        this.passwd = passwd;

        return this;

    }

    public ApiBindMobile setVCode(String vcode) {

        this.vcode = vcode;

        return this;

    }

    public ApiBindMobile setOpenId(String openId) {

        this.openId = openId;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(mobile)) {

            map.put("mobile", mobile);

        }

        if (!TextUtils.isEmpty(openId)) {

            map.put("openId", openId);

        }

        if (!TextUtils.isEmpty(passwd)) {

            map.put("passwd", passwd);

        }

        if (!TextUtils.isEmpty(vcode)) {

            map.put("vcode", vcode);

        }

        return map;

    }

    @Override
    public Call<RegisterEntity> call() {

        return mApiStore.bindMobile(postParams());

    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/wx/bind.shtml")
        Call<RegisterEntity> bindMobile(@FieldMap Map<String, String> postParams);

    }

}
