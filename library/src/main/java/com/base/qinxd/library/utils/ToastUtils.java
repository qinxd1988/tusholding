package com.base.qinxd.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xd on 2017/4/23.
 */

public class ToastUtils {

    public static void showToast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

    }

}
