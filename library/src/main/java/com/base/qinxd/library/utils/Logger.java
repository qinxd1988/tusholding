package com.base.qinxd.library.utils;

import android.util.Log;

/**
 * @author xd
 * @describe 日志工具类
 * @time 2015年3月21日 下午12:30:02
 */
public class Logger {

    private static final String TAG = Logger.class.getSimpleName();

    public static void e(String tag, String msg) {

        if ("true".equals(AppConfigUtils.IS_DEBUG)) {
            Log.e(tag, msg);
        }

    }

    public static void i(String tag, String msg) {
        if ("true".equals(AppConfigUtils.IS_DEBUG)) {
            Log.i(tag, msg);
        }

    }

    public static void v(String tag, String msg) {
        if ("true".equals(AppConfigUtils.IS_DEBUG)) {
            Log.v(tag, msg);
        }

    }

    public static void w(String tag, String msg) {
        if ("true".equals(AppConfigUtils.IS_DEBUG)) {
            Log.w(tag, msg);
        }

    }


    public static void d(String tag, String msg) {
        if ("true".equals(AppConfigUtils.IS_DEBUG)) {
            Log.d(tag, msg);
        }

    }

    public static void e(String msg){

        e(TAG,msg);

    }

    public static void d(String msg){

        d(TAG,msg);

    }

    public static void i(String msg){

        i(TAG,msg);

    }

}
