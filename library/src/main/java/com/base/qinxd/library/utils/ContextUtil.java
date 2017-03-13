package com.base.qinxd.library.utils;

import android.app.Activity;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：16/4/27 11:39
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：上下文工具类
 */
public class ContextUtil {

    /**
     * 判断activity是否活跃
     *
     * @param activity
     * @return
     */
    public static boolean isAcivityActive(Activity activity) {

        return activity != null && !activity.isFinishing();

    }

}
