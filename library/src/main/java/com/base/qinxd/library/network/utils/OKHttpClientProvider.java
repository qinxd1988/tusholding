package com.base.qinxd.library.network.utils;

import android.content.Context;

import com.base.qinxd.library.network.OkHttpInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/11 12:00
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class OKHttpClientProvider {

    static OkHttpClient okHttpClient;

    public static OkHttpClient okHttpClient(final Context context) {

        if (okHttpClient == null) {

            synchronized (OKHttpClientProvider.class) {

                if (okHttpClient == null) {

                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(new OkHttpInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(8, TimeUnit.SECONDS)
                            .writeTimeout(8, TimeUnit.SECONDS)
                            .build();

                    okHttpClient = client;

                }

            }

        }

        return okHttpClient;

    }

}
