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

public class ApiSendVeirfyCode extends Api<BaseEntity> {

//    SEND_BY_REGIST_ACCOUNT --注册账号
//    SEND_BY_FIND_PWD --找回密码
//    SEND_BY_MODIFY_PWD -- 修改密码
//    SEND_BY_BIND_WX -- 绑定微信

    public final static String SEND_BY_REGIST_ACCOUNT = "SEND_BY_REGIST_ACCOUNT";

    public final static String SEND_BY_FIND_PWD = "SEND_BY_FIND_PWD";

    public final static String SEND_BY_MODIFY_PWD = "SEND_BY_MODIFY_PWD";

    public final static String SEND_BY_BIND_WX = "SEND_BY_BIND_WX";

    private ApiStore mApiStore;

    private String businessType;

    private String mobile;

    public ApiSendVeirfyCode(Context context) {
        super(context);

        // 初始化api
        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiSendVeirfyCode setBusinessType(String type) {

        this.businessType = type;

        return this;

    }

    public ApiSendVeirfyCode setMobile(String mobile) {

        this.mobile = mobile;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(businessType)) {

            map.put("businessType", businessType);

        }

        if (!TextUtils.isEmpty(mobile)) {

            map.put("mobile", mobile);

        }

        return map;

    }

    @Override
    public final Call<BaseEntity> call() {

        return mApiStore.sendVeirfyCode(postParams());

    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/message/sendVeirfyCode.shtml")
        Call<BaseEntity> sendVeirfyCode(@FieldMap Map<String, String> postParams);

    }

}
