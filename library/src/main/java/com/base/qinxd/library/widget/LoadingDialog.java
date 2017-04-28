package com.base.qinxd.library.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;

import com.base.qinxd.library.R;


/**
 * @author xd
 * @describe 加载对话框
 * @name com.wenwanmi.library.widget.LoadingDialog.java
 * @time 2015年3月19日 下午3:49:10
 */
public class LoadingDialog extends Dialog implements DialogInterface.OnDismissListener {

    public static final String TAG = "LoadingDialog";

//    private GifImageView gifImageView;
//
//    private GifDrawable drawable;

    // private TextView textView;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_style);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, R.style.dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LayoutParams lp = getWindow().getAttributes();
//
//        lp.flags = LayoutParams.FLAG_DIM_BEHIND;
//
//        lp.dimAmount = 0f;

        setContentView(R.layout.base_loading_dialog_layout);

    }

    public static LoadingDialog show(Context context, String message, boolean cancelable, OnCancelListener cancelListener) {
        LoadingDialog loadingDialog = new LoadingDialog(context);
        loadingDialog.setCancelable(cancelable);
        loadingDialog.setOnCancelListener(cancelListener);
        loadingDialog.show();
        // loadingDialog.setMessage(message);
        return loadingDialog;
    }


    // 解决4.0以上系统加载时点击屏幕会取消情况
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

//        if (drawable != null) {
//
//            drawable.stop();
//
//        }

    }
}
