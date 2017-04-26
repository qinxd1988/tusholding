package com.base.qinxd.library.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by qinxudong on 15/7/27.
 */
public class RollViewPager extends ViewPager {

    public RollViewPager(Context context) {
        super(context);
    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        super.dispatchTouchEvent(ev);
//        requestDisallowInterceptTouchEvent();
//        return true;
//    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            getParent().requestDisallowInterceptTouchEvent(true);

        }

        return super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_UP) {

            getParent().requestDisallowInterceptTouchEvent(false);

        }

        return super.onTouchEvent(ev);

    }

}
