package com.base.qinxd.library.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

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

    protected EventBus mEventBus;

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

    /**
     * 是否支持事件订阅
     *
     * @return
     */
    protected boolean isSupportEventBus() {

        return false;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isSupportEventBus()) {

            mEventBus = EventBus.getDefault();

            mEventBus.register(this);

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return false;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mEventBus != null) {

            mEventBus.unregister(this);

        }

    }

}
