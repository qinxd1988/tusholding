package com.TusFinancial.Credit.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by xd on 2017/4/25.
 * 跳转中心
 *
 */

public class TransferHelper {

    public static void onTransfer(Context context, String url) {

        onTransfer(context, url, null);

    }

    public static void onTransfer(Context context, String url, String title) {

        if (TextUtils.isEmpty(url)) {

            return;

        }

        if (context != null) {

            if (url.startsWith("http")) {


            } else {

                Intent intent = new Intent();

                Uri uri = Uri.parse(url);

                intent.setData(uri);

                context.startActivity(intent);

            }

        }

    }

}
