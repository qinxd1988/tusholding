package com.base.qinxd.library.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2016/12/20 10:07
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public abstract class BasePagerAdapter extends PagerAdapter {

    protected Context mContext;

    private View mCurrentView;

    public BasePagerAdapter(Context context) {

        this.mContext = context;

    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View)object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

}
