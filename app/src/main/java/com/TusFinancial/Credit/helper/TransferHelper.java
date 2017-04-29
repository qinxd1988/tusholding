package com.TusFinancial.Credit.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.browse.ui.activity.BrowseActivity;
import com.TusFinancial.Credit.utils.Constants;

/**
 * Created by xd on 2017/4/25.
 * 跳转中心
 */

public class TransferHelper {

    public static void onTransfer(Context context, String url, boolean isNeedLogin) {

        onTransfer(context, url, isNeedLogin, null);

    }

    public static void onTransfer(Context context, String url, boolean isNeedLogin, String title) {

        String data = "";

        if (TextUtils.isEmpty(url)) {

            return;

        }

        if (isNeedLogin && TextUtils.isEmpty(JinDiaoApplication.TOKEN)) {

            data = url;

            url = "jindiao://login";

        }

        if (context != null) {

            if (url.startsWith("http")) {//是网页的 启动浏览器

                Intent intent = new Intent(context, BrowseActivity.class);

                intent.putExtra(Constants.URL, url);

                context.startActivity(intent);

            } else {

                try {

                    Intent intent = new Intent();

                    Uri uri = Uri.parse(url);

                    intent.setData(uri);

                    if (!TextUtils.isEmpty(data)) {

                        intent.putExtra(Constants.EXTRA, data);

                    }

                    context.startActivity(intent);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

    }

}
