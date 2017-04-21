package com.base.qinxd.library.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by xd on 2017/4/21.
 */

public abstract class BaseImpActivity extends BaseActivity {

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResId());

    }

    public abstract int getLayoutResId();

}
