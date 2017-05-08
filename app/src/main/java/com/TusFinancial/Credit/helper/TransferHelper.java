package com.TusFinancial.Credit.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.browse.ui.activity.BrowseActivity;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.utils.ContextUtil;
import com.base.qinxd.library.utils.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by xd on 2017/4/25.
 * 跳转中心
 */

public class TransferHelper {

    public static final String SCHEME = "jindiao";

    public static final String SCHEME_SYMBOL = "://";

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

            url = SCHEME + SCHEME_SYMBOL + "login";

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

                    //js通知登录超时时，应清空APP当前登录态
                    if (host != null && host.equals("login")) {

                        JinDiaoApplication.TOKEN = null;

                        JinDiaoApplication.WECHAT_CODE = null;

                    }

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

                    String newUrl = SCHEME + SCHEME_SYMBOL + host;

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

    /**
     * js调用native应用
     *
     * @param activity
     * @param action
     * @param params   json 对象
     * @param callback
     */
    public static void invokeJSOperate(Activity activity, String action, String params, String callback) {

        if (ContextUtil.isAcivityActive(activity)) {

            if (!TextUtils.isEmpty(action)) {

                String url = SCHEME + SCHEME_SYMBOL + action;

                if (!TextUtils.isEmpty(params)) {

                    params = Uri.decode(params);

                    if (params != null) {

                        params = toAnnotationStr(params);

                        if (!TextUtils.isEmpty(params)) {

                            url += ("?" + params);

                        }

                    }

                }

                onTransfer(activity, url, false);

            }

        }

    }

    /**
     * 将json格式的字符串解析成String字符串平装<li>
     * json格式：String objString = "{aa=1&bb=2&cc=3";
     */
    @SuppressWarnings({"unchecked"})
    private static String toAnnotationStr(String json) {

        // HashMap<String, String> data = new HashMap<String, String>();
        // 将json字符串转换成jsonObject
        if (!TextUtils.isEmpty(json)) {

            JSONObject jsonObject = null;

            try {

                jsonObject = new JSONObject(json);

                Iterator it = jsonObject.keys();

                StringBuilder strBuilder = new StringBuilder();

                while (it.hasNext()) {

                    String key = String.valueOf(it.next());

                    String value = (String) jsonObject.get(key);

                    if (!TextUtils.isEmpty(value)) {

                        value = Base64.encodeToString(value.getBytes(), Base64.DEFAULT);

                    }

                    strBuilder.append(key).append("=")
                            .append(Uri.encode(value))
                            .append("&");

                }
                return strBuilder.toString();

            } catch (JSONException e) {

                e.printStackTrace();

                return null;

            }


        } else {

            return null;

        }

    }

}
