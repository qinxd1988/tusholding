package com.base.qinxd.library.network;

import android.content.Context;

import com.base.qinxd.library.activity.BaseActivity;
import com.base.qinxd.library.entity.BaseEntity;
import com.base.qinxd.library.utils.ContextUtil;
import com.base.qinxd.library.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 15:32
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public abstract class Api<T> implements Callback<T> {

    private Context mContext;

    protected ApiCallBack<T> mCallBack;

    protected boolean isShowLoading;

    public Api(Context context) {

        this.mContext = context;

    }

    protected void showLoading() {

        if (mContext instanceof BaseActivity && isShowLoading) {

            ((BaseActivity) mContext).showLoading();

        }

    }

    protected void dismissLoading() {

        if (mContext instanceof BaseActivity) {

            ((BaseActivity) mContext).dismissLoading();

        }

    }

    public Map<String, String> postParams() {

        Map<String, String> map = new HashMap<>();

        return map;

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        dismissLoading();

        if (mCallBack == null) {

            throw new NullPointerException("mCallback == null");

        }

        if (mContext instanceof BaseActivity) {

            if (!ContextUtil.isAcivityActive((BaseActivity) mContext)) {

                return;

            }

        }

        if (response != null && response.body() != null) {

            if (((BaseEntity) response.body()).isSuccess()) {

                mCallBack.onSuccess((T) response.body());

            } else {

                mCallBack.onError(((BaseEntity) response.body()).getErrormsg());

            }

        } else {

            mCallBack.onFailure();

        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        dismissLoading();

        if (mCallBack == null) {

            throw new NullPointerException("mCallback == null");

        }

        if (mContext instanceof BaseActivity) {

            if (!ContextUtil.isAcivityActive((BaseActivity) mContext)) {

                return;

            }

        }

        mCallBack.onFailure();

    }

    /**
     * 设置是否显示加载框
     *
     * @param isShowLoading
     * @return
     */
    public Api setShowLoading(boolean isShowLoading) {

        this.isShowLoading = isShowLoading;

        return this;

    }

    /**
     * 设置回调函数
     *
     * @param callBack
     * @return
     */
    public Api setApiCallBack(ApiCallBack<T> callBack) {

        this.mCallBack = callBack;

        return this;

    }

    public abstract Call<T> call();

    /**
     * 进行网络请求
     */
    public void enqueue() {

        showLoading();

        Call<T> call = call();

        HttpUtils.putCall(mContext, "", call);

        call.enqueue(this);

    }

}
