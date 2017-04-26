package com.TusFinancial.Credit.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.TusFinancial.Credit.JinDiaoApplication;

/**
 * Created by xd on 2017/4/25.
 * 跳转中心
 */

public class TransferHelper {

    public static void onTransfer(Context context, String url, boolean isNeedLogin) {

        onTransfer(context, url, isNeedLogin, null);

    }

    public static void onTransfer(Context context, String url, boolean isNeedLogin, String title) {

        if (TextUtils.isEmpty(url)) {

            return;

        }

        if (isNeedLogin && TextUtils.isEmpty(JinDiaoApplication.TOKEN)) {

            url = "jindiao://login";

        }

        if (context != null) {

            if (url.startsWith("http")) {


            } else {

                try {

                    Intent intent = new Intent();

                    Uri uri = Uri.parse(url);

                    intent.setData(uri);

                    context.startActivity(intent);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        }

    }

}
