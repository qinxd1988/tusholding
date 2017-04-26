package com.TusFinancial.Credit;

import android.app.Application;

import com.base.qinxd.library.utils.AppConfigUtils;
import com.base.qinxd.library.utils.ScreenUtils;

/**
 * Created by xd on 2017/4/22.
 */

public class JinDiaoApplication extends Application {

    public static int WIDTH;

    public static int HEIGHT;

    public static float DENSITY;

    public static String TOKEN;

    @Override
    public void onCreate() {
        super.onCreate();

        WIDTH = ScreenUtils.getScreenWidth(this);

        HEIGHT = ScreenUtils.getScreenHeight(this);

        DENSITY = ScreenUtils.getDensty(this);

        AppConfigUtils.initConfig(this);

    }

}
