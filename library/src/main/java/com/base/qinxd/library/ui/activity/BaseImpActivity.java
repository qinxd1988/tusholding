package com.base.qinxd.library.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.qinxd.library.R;
import com.base.qinxd.library.ui.fragment.BaseFragment;

/**
 * Created by xd on 2017/4/21.
 */

public abstract class BaseImpActivity extends BaseActivity {

    public Toolbar mToolbar;

    public TextView mTitleTextView;

    public RelativeLayout parentLayout;

    public FragmentManager mFragmentManager;

    protected BaseFragment mFragment;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

        initData(savedInstanceState);

        parentLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.base_imp_layout, null);

        mToolbar = (Toolbar) parentLayout.findViewById(R.id.toolbar);

        mTitleTextView = (TextView) parentLayout.findViewById(R.id.toolbar_title);

        if (isSupportToolbar()) {

            mToolbar.setVisibility(View.VISIBLE);

            setSupportActionBar(mToolbar);

            if (isSupportBack()) {

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                getSupportActionBar().setDisplayShowTitleEnabled(false);

                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();

                    }
                });

            }

        }

        View childView = LayoutInflater.from(this).inflate(getLayoutResId(), null);

        RelativeLayout.LayoutParams childViewParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        if (isSupportToolbar()) {

            childViewParams.addRule(RelativeLayout.BELOW, R.id.toolbar);

        }

        parentLayout.addView(childView, childViewParams);

        setContentView(parentLayout);

        initView();

    }

    public abstract int getLayoutResId();

    public abstract void initData(@Nullable Bundle savedInstanceState);

    public abstract void initView();

    protected boolean isSupportToolbar() {

        return true;

    }

    protected boolean isSupportBack() {

        return true;

    }

    public void operateFragment(BaseFragment fragment, int resId) {

        mFragmentManager.beginTransaction()
                .replace(resId, fragment)
                .disallowAddToBackStack()
                .commitAllowingStateLoss();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mFragment != null) {

            mFragment.onActivityResult(requestCode, resultCode, data);

        }

    }
}
