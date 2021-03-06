package com.TusFinancial.Credit.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.TusFinancial.Credit.bean.TemplateBean;
import com.TusFinancial.Credit.helper.HomeAdapterHelper;
import com.base.qinxd.library.adapter.HeaderFooterRecyclerViewAdapter;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/25 18:50
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class HomeAdapter extends HeaderFooterRecyclerViewAdapter<Object, Object, Object> {

    private HomeAdapterHelper mHomeAdapterHelper;

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getContentItemViewType(int position) {

        Object object = mContentList.get(position);

        if (object instanceof TemplateBean) {

            return ((TemplateBean) object).type;

        }

        return super.getContentItemViewType(position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {

        if (mHomeAdapterHelper == null) {

            mHomeAdapterHelper = new HomeAdapterHelper(mContext);

        }

        return mHomeAdapterHelper.onCreateItemViewHolder(parent, contentViewType);

    }

    @Override
    protected RecyclerView.ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        return null;
    }

    @Override
    protected void onBindHeaderItemViewHolder(RecyclerView.ViewHolder headerViewHolder, int position) {

    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) {

        if (mHomeAdapterHelper != null) {

            mHomeAdapterHelper.onBindItemViewHolder(contentViewHolder, mContentList.get(position));

        }

    }

    @Override
    protected void onBindFooterItemViewHolder(RecyclerView.ViewHolder footerViewHolder, int position) {

    }

}
