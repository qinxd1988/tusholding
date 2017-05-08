package com.TusFinancial.Credit.api;

import android.content.Context;
import android.text.TextUtils;

import com.TusFinancial.Credit.entity.BannerEntity;
import com.base.qinxd.library.network.Api;
import com.base.qinxd.library.network.ApiRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xd on 2017/5/3.
 */

public class ApiBanners extends Api<BannerEntity> {

    private String type;

    ApiStore mApiStore;

    public ApiBanners(Context context) {
        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class);

    }

    public ApiBanners setType(String type) {

        this.type = type;

        return this;

    }

    @Override
    public Map<String, String> postParams() {

        Map<String, String> map = super.postParams();

        if (!TextUtils.isEmpty(type)) {

            map.put("type", type);

        }

        return map;

    }

    @Override
    public Call<BannerEntity> call() {
        return mApiStore.getBanners(postParams());
    }

    private interface ApiStore {

        @FormUrlEncoded
        @POST("/homePage/getBanners.shtml")
        Call<BannerEntity> getBanners(@FieldMap Map<String, String> postParams);

    }

}
