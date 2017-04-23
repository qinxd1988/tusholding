package com.base.qinxd.library.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLoading() {


    }

    public void dismissLoading() {

    }

    @Override
    protected void onStop() {
        super.onStop();

        dismissLoading();

    }

}
