package com.TusFinancial.Credit;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.utils.AppConfigUtils;
import com.base.qinxd.library.utils.ScreenUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloader;

/**
 * Created by xd on 2017/4/22.
 */

public class JinDiaoApplication extends Application {

    public static int WIDTH;

    public static int HEIGHT;

    public static float DENSITY;

    public static String TOKEN = "";

    public static String MOBILE = "";

    public static String WECHAT_CODE = "";

    SharedPreferences spfs;

    //测试化境是否成功

    @Override
    public void onCreate() {
        super.onCreate();

        spfs = PreferenceManager.getDefaultSharedPreferences(this);

        WIDTH = ScreenUtils.getScreenWidth(this);

        HEIGHT = ScreenUtils.getScreenHeight(this);

        DENSITY = ScreenUtils.getDensty(this);

        TOKEN = spfs.getString(Constants.TOKEN, "");

        MOBILE = spfs.getString(Constants.MOBILE, "");

        AppConfigUtils.initConfig(this);

        QbSdk.initX5Environment(getApplicationContext(), null);

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        TbsDownloader.needDownload(getApplicationContext(), false);

    }

}
