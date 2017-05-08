package com.TusFinancial.Credit.api;

import android.content.Context;
import android.text.TextUtils;

import com.TusFinancial.Credit.entity.RecommendEntity;
import com.base.qinxd.library.network.Api;
import com.base.qinxd.library.network.ApiRequest;
import com.base.qinxd.library.network.utils.Const;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/5/8 11:14
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class ApiHomeRecommend extends Api<RecommendEntity> {

    ApiStore mApiStore;

    private String appid = "J8KMZCESXG";

    private String columnid;

    private String uid;

    private String fmt = "json";

    private String platform = "android";

    public ApiHomeRecommend(Context context) {

        super(context);

        mApiStore = ApiRequest.getInstance().create(context, ApiStore.class, Const.HOME_RECOMMEND_URL);

    }

    public ApiHomeRecommend setUid(String uid) {

        this.uid = uid;

        return this;

    }

    public ApiHomeRecommend setColumnid(String columnid) {

        this.columnid = columnid;

        return this;

    }

    @Override
    public Map<String, String> getParams() {

        Map<String, String> map = super.getParams();

        if (!TextUtils.isEmpty(appid)) {

            map.put("appid", appid);

        }

        if (!TextUtils.isEmpty(columnid)) {

            map.put("columnid", columnid);

        }

        if (!TextUtils.isEmpty(uid)) {

            map.put("uid", uid);

        }

        if (!TextUtils.isEmpty(fmt)) {

            map.put("fmt", fmt);

        }

        if (!TextUtils.isEmpty(platform)) {

            map.put("platform", platform);

        }

        return map;
    }

    @Override
    public final Call<RecommendEntity> call() {

        return mApiStore.getHomeRecommend(getParams());

    }

    private interface ApiStore {

        @GET("/v3/data/feed")
        Call<RecommendEntity> getHomeRecommend(@QueryMap Map<String, String> getParams);

    }

}
