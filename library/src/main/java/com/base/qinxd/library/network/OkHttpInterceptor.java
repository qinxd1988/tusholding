package com.base.qinxd.library.network;

import com.base.qinxd.library.utils.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 12:23
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：打印网络请求log
 */
public class OkHttpInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        // 获得Connection，内部有route、socket、handshake、protocol方法
        Connection connection = chain.connection();

        // 如果Connection为null，返回HTTP_1_1，否则返回connection.protocol()
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;

        // 比如: --> POST http://121.40.227.8:8088/api http/1.1
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;

        Logger.i(requestStartMessage);

        // 打印 Response
        Response response;

        try {

            response = chain.proceed(request);

        } catch (Exception e) {

            throw e;

        }

        ResponseBody responseBody = response.body();

        long contentLength = responseBody.contentLength();

        if (bodyEncoded(response.headers())) {

        } else {

            BufferedSource source = responseBody.source();

            source.request(Long.MAX_VALUE); // Buffer the entire body.

            Buffer buffer = source.buffer();

            Charset charset = UTF8;

            if (contentLength != 0) {

                // 获取Response的body的字符串 并打印
                Logger.i(buffer.clone().readString(charset));

            }

        }
        return response;
    }

    private boolean bodyEncoded(Headers headers) {

        String contentEncoding = headers.get("Content-Encoding");

        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");

    }

}
