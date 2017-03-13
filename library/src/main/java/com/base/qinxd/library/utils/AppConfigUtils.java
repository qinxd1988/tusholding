package com.base.qinxd.library.utils;

import android.content.Context;

import java.util.Properties;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 12:27
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：Properties文件操作类,app的开关配置初始化和获取
 */
public class AppConfigUtils {

    public static String IS_DEBUG = "";

    public static void initConfig(Context context) {

        isDebug(context);

    }

    /**
     * 获取debug
     * @param context
     * @return
     */
    public static boolean isDebug(Context context) {

        if (!("".equals(IS_DEBUG))) {

            return Boolean.valueOf(IS_DEBUG);

        }

        IS_DEBUG = getConfigProperties(context, "IS_DEBUG");

        return Boolean.valueOf(IS_DEBUG);

    }

    private static String getConfigProperties(Context c, String key) {
        String value = null;
        Properties properties = new Properties();
        try {
            properties.load(c.getAssets().open("config.properties"));
            value = properties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (value != null) {
            value = value.trim();
        }
        return value;
    }

}
