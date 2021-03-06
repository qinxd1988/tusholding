package com.base.qinxd.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.base.qinxd.library.R;
import com.base.qinxd.library.utils.ScreenUtils;

import java.lang.reflect.Field;

/**
 * 支持轮播和提示的的viewpager
 */
public class RollPagerView extends RelativeLayout implements OnPageChangeListener {

    private RollViewPager mViewPager;
    private PagerAdapter mAdapter;
    private long mRecentTouchTime;
    //播放延迟
    private int delay;

    //hint位置
    private int gravity;

    //hint颜色
    private int color;

    //hint透明度
    private int alpha;

    private View mHintView;

    private int current;

    private boolean isTouch;

    private InternalHandler mHandler;

    public RollPagerView(Context context) {
        this(context, null);
    }

    public RollPagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public RollPagerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }


    /**
     * 读取提示形式  和   提示位置   和    播放延迟
     *
     * @param attrs
     */
    private void initView(AttributeSet attrs) {
        if (mViewPager != null) {
            removeView(mViewPager);
        }

        TypedArray type = getContext().obtainStyledAttributes(attrs, R.styleable.RollViewPager);
        gravity = type.getInteger(R.styleable.RollViewPager_rollviewpager_hint_gravity, 1);
        int mode = type.getInteger(R.styleable.RollViewPager_rollviewpager_hint_mode, -1);
        delay = type.getInt(R.styleable.RollViewPager_rollviewpager_play_delay, 0);
        color = type.getColor(R.styleable.RollViewPager_rollviewpager_hint_color, Color.BLACK);
        alpha = type.getInt(R.styleable.RollViewPager_rollviewpager_hint_alpha, 0);
        mViewPager = new RollViewPager(getContext());
        mViewPager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        addView(mViewPager);

        mViewPager.requestFocus();

        mViewPager.addOnPageChangeListener(this);

        type.recycle();

        initHint(mode);
    }

    private void stopPlay() {

        if (mHandler != null) {

            mHandler.removeCallbacksAndMessages(null);

        }

    }

    /**
     * 开始播放 仅当view正在显示 且 触摸等待时间过后 播放
     */
    private void startPlay() {

        if (delay <= 0 || mAdapter.getCount() <= 1) {

            return;

        }

        if (mHandler == null) {

            mHandler = new InternalHandler();

        }

        stopPlay();

        mHandler.sendEmptyMessageDelayed(0, delay);

    }


    /**
     * 设置提示view
     *
     * @param mode
     */
    private void initHint(int mode) {
        switch (mode) {
            case 1:
                initHint(new TextHintView(getContext()));
                break;
            case 0:
                initHint(new PointHintView(getContext()));
                break;
            default:
                break;
        }
    }

    private void initHint(HintView hintview) {
        if (mHintView != null) {
            removeView(mHintView);
        }

        if (hintview == null || !(hintview instanceof HintView)) {
            return;
        }

        mHintView = (View) hintview;
        loadHintView();
    }

    /**
     * 加载hintview的容器
     */
    private void loadHintView() {

        addView(mHintView);

        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(getContext(), 24));

        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        mHintView.setLayoutParams(lp);

        GradientDrawable gd = new GradientDrawable();

        gd.setColor(color);

        gd.setAlpha(alpha);

        mHintView.setBackgroundDrawable(gd);

        ((HintView) mHintView).initView(mAdapter == null ? 0 : mAdapter.getCount(), gravity);

    }


    /**
     * 设置viewager滑动动画持续时间，貌似有效率问题
     *
     * @param during
     */
    public void setAnimationDurtion(final int during) {
        try {
            // viePager平移动画事件
            Field mField = ViewPager.class.getDeclaredField("mScroller");
            mField.setAccessible(true);
            Scroller mScroller = new Scroller(getContext(),
                    new AccelerateInterpolator()) {

                @Override
                public void startScroll(int startX, int startY, int dx,
                                        int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, during);
                }

                @Override
                public void startScroll(int startX, int startY, int dx,
                                        int dy) {
                    super.startScroll(startX, startY, dx, dy, during);
                }
            };
            mField.set(mViewPager, mScroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置提示view的透明度
     *
     * @param alpha 0为全透明  255为实心
     */
    public void setHintAlpha(int alpha) {
        this.alpha = alpha;
        initHint((HintView) mHintView);
    }

    /**
     * 支持自定义hintview 只需new一个实现HintView的View传进来 会自动将你的view添加到本View里面。重新设置LayoutParams。
     *
     * @param hintview
     */
    public void setHintView(HintView hintview) {
        if (!(hintview instanceof View)) {
            throw new IllegalArgumentException("HintView should be a View");
        }
        this.mHintView = (View) hintview;

        initHint(hintview);
    }

    /**
     * 取真正的Viewpager
     *
     * @return
     */
    public ViewPager getmViewPager() {
        return mViewPager;
    }

    /**
     * 设置Adapter
     *
     * @param adapter
     */
    public void setAdapter(PagerAdapter adapter) {

        mViewPager.setAdapter(adapter);

        mAdapter = adapter;

        dataSetChanged();

        adapter.registerDataSetObserver(new JPagerObserver());

    }

    public PagerAdapter getAdapter() {

        if (mViewPager != null) {

            return mViewPager.getAdapter();

        }

        return null;

    }

    /**
     * 用来实现adapter的notifyDataSetChanged通知HintView变化
     */
    private class JPagerObserver extends DataSetObserver {
        @Override
        public void onChanged() {

            dataSetChanged();

        }

        @Override
        public void onInvalidated() {

            dataSetChanged();

        }

    }

    private void dataSetChanged() {

        startPlay();

        if (mHintView != null) {

            if (mAdapter.getCount() > 1) {

                mHintView.setVisibility(VISIBLE);

            } else {

                mHintView.setVisibility(GONE);

            }

            ((HintView) mHintView).initView(mAdapter.getCount(), gravity);

        }

    }


    /**
     * 为了实现触摸时和过后一定时间内不滑动
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        mRecentTouchTime = System.currentTimeMillis();

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:

            case MotionEvent.ACTION_MOVE:

                isTouch = true;

                stopPlay();

                break;

            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:

                isTouch = false;

                dataSetChanged();

                break;

        }

        return super.dispatchTouchEvent(ev);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int position) {

        if (mAdapter != null) {

            current = position;

            if (mHintView != null) {

                ((HintView) mHintView).setCurrent(current);

            }

        }

    }

    public int getCurrent() {

        return current;

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        stopPlay();

    }

    private class InternalHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (mViewPager != null && !isTouch) {

                int cur = mViewPager.getCurrentItem() + 1;

                if (cur >= mAdapter.getCount()) {

                    cur = 0;

                }

                mViewPager.setCurrentItem(cur);

                sendEmptyMessageDelayed(0, delay);

            }

        }

    }

}