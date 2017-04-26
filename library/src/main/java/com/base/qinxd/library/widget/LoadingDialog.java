package com.base.qinxd.library.widget;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;

/**
 * Created by xd on 2017/4/26.
 */

public class LoadingDialog extends AppCompatDialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}
