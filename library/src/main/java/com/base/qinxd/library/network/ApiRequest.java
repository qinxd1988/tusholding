package com.base.qinxd.library.network;

import android.content.Context;

import com.base.qinxd.library.network.utils.Const;
import com.base.qinxd.library.network.utils.OKHttpClientProvider;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 12:38
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class ApiRequest {

    private static final ApiRequest mInstance = new ApiRequest();

    public static ApiRequest getInstance() {

        return mInstance;

    }

    private ApiRequest() {
    }

    public <T> T create(Context context, Class<T> service) {

        return create(context, service, Const.BASE_URL);

    }

    /**
     * @param context
     * @param service
     * @param baseUrl
     * @param <T>
     * @return
     */
    public <T> T create(Context context, Class<T> service, String baseUrl) {

        OkHttpClient client = OKHttpClientProvider.okHttpClient(context);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(service);

    }

}
