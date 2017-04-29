package com.base.qinxd.library.network;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 14:17
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public interface ApiCallBack<T> {

    // 请求数据成功
    void onSuccess(T response);
    // 请求数据错误

    void onError(T response, String err_msg);

    // 网络请求失败
    void onFailure();

}
