package com.TusFinancial.Credit.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * 动态管理的Adapter。概念参照{@link android.support.v4.app.FragmentPagerAdapter}
 * 每次都会创建新view，销毁旧View。节省内存消耗性能
 * <p/>
 * <p>Subclasses only need to implement {@link #getView(ViewGroup, int)} and {@link #getCount()} to
 * have a working adapter.
 */
public abstract class DynamicPagerAdapter<T> extends PagerAdapter {

    protected Context mContext;

    protected ArrayList<T> mList = new ArrayList<T>();

    public DynamicPagerAdapter(Context context) {

        this.mContext = context;

    }

    public T getItemObject(int position) {

        if (position < mList.size()) {

            return mList.get(position);

        } else {

            return null;

        }

    }

    public void refreshData(List<T> list) {

        if (list == null) {

            return;

        }

        mList.clear();

        mList.addAll(list);

        notifyDataSetChanged();

    }

    public void appendData(List<T> list) {

        if (list == null) {

            return;

        }

        mList.ensureCapacity(mList.size() + list.size());

        mList.addAll(list);

        notifyDataSetChanged();

    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = getView(container, position);
        container.addView(itemView);
        return itemView;
    }

    public abstract View getView(ViewGroup container, int position);

    @Override
    public int getCount() {
        return mList.size();
    }

}
