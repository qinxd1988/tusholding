package com.base.qinxd.library.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/3/14 11:57
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class BaseFragment extends Fragment {

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    /**
     * 懒加载
     */
    protected boolean isLazyLoad;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {

            isVisible = true;

            onVisible();

        } else {

            isVisible = false;

            onInvisible();

        }

    }

    /**
     * 可见
     */
    protected void onVisible() {


    }

    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {

    }

}
