package com.TusFinancial.Credit.helper;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.browse.ui.activity.BrowseActivity;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.utils.Logger;

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

    /**
     * webview 重定向url处理逻辑
     * <p>
     * tusc://websdk?action=login&params={json}&callback=xxxxx
     * tusc://native?pageName=XXXX&url=URLEncode(XXX)&key=xxxx
     *
     * @param url
     */
    public static String overrideUrl(Context context, String url) {

        String callback = "";

        if (!TextUtils.isEmpty(url)) {

            if (url.startsWith("tel")) {

                onTransfer(context, url, false);

                return "";

            }

            Uri uri = Uri.parse(url);

            if (uri != null) {

                String host = "";

                String query = uri.getQuery();

                String replace = "";

                if (uri.getHost().equals("native")) {//调用本地页面逻辑

                    host = uri.getQueryParameter("pageName");

                    replace = "pageName=" + host;

                } else if (uri.getHost().equals("websdk")) {

                    //tusc://websdk?action=login&params={json}&callback=xxxxx

                    host = uri.getQueryParameter("action");

                    replace = "action=" + host;

                    callback = uri.getQueryParameter("callback");

                }

                if (!TextUtils.isEmpty(query)) {

                    if (query.contains("&" + replace)) {

                        query = query.replace("&" + replace, "");

                    } else if (query.contains(replace)) {

                        query = query.replace(replace, "");

                    }

                }

                if (!TextUtils.isEmpty(host) && context != null) {

                    String newUrl = "jindiao://" + host;

                    if (!TextUtils.isEmpty(query)) {

                        newUrl += ("?" + query);

                    }

                    onTransfer(context, newUrl, false);

                }

            } else {

                Logger.e(url);

            }

        }

        return callback;

    }

}
