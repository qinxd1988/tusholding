package com.base.qinxd.library.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by qinxudong on 15/7/27.
 */
public class RollViewPager extends ViewPager {

    private int mTouchSlop;

    private float mPrevX;

    public RollViewPager(Context context) {
        super(context);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public RollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (getParent() != null) {

            getParent().requestDisallowInterceptTouchEvent(true);

        }

        return super.dispatchTouchEvent(event);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mPrevX = MotionEvent.obtain(event).getX();

                break;

            case MotionEvent.ACTION_MOVE:

                final float eventX = event.getX();

                float xDiff = Math.abs(eventX - mPrevX);

                if (xDiff < mTouchSlop) {

                    if (getParent() != null) {

                        getParent().requestDisallowInterceptTouchEvent(false);

                        return false;

                    }

                } else {

                    super.onInterceptTouchEvent(event);

                    return true;

                }

        }

        return super.onInterceptTouchEvent(event);

    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//
//        if (getParent() != null) {
//
//            getParent().requestDisallowInterceptTouchEvent(false);
//
//        }
//
//        return super.onTouchEvent(ev);
//
//    }

}
