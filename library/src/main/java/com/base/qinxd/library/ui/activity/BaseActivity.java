package com.base.qinxd.library.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.base.qinxd.library.utils.HttpUtils;
import com.base.qinxd.library.widget.LoadingDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/13 15:38
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    private EventBus mEventBus;

    private boolean isSupportEventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isSupportEventBus()) {

            mEventBus = EventBus.getDefault();

            mEventBus.register(this);

        }

    }

    public boolean isSupportEventBus() {

        return false;

    }

    public void showLoading() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()) {

                    if (mLoadingDialog == null) {

                        mLoadingDialog = LoadingDialog.show(BaseActivity.this, "", true, new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {

                            }
                        });

                    }

                    if (!mLoadingDialog.isShowing()) {

                        mLoadingDialog.show();

                    }

                }

            }

        });

    }

    public void dismissLoading() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (mLoadingDialog != null && mLoadingDialog.isShowing()) {

                    mLoadingDialog.dismiss();

                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        dismissLoading();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        HttpUtils.cancel(this);

        if (mEventBus != null) {

            mEventBus.unregister(this);

        }

    }
}
