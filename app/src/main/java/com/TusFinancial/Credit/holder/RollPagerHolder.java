package com.TusFinancial.Credit.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.adapter.WheelAdapter;
import com.TusFinancial.Credit.bean.TemplateBean;
import com.base.qinxd.library.utils.CollectionUtils;
import com.base.qinxd.library.widget.RollPagerView;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2016/11/10 14:19
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class RollPagerHolder extends RecyclerView.ViewHolder {

    public RollPagerView rollPagerView;

    private Context mContext;

    public RollPagerHolder(Context context) {

        super(LayoutInflater.from(context).inflate(R.layout.roll_pager_view_layout, null));

        this.mContext = context;

        if (itemView instanceof RollPagerView) {

            this.rollPagerView = (RollPagerView) itemView;

        }

    }

    /**
     * 绑定轮播图数据
     *
     * @param object
     */
    public void bindData(Object object) {

        if (object instanceof TemplateBean) {

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();

            if (params == null) {

                params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            }

            params.height = Math.round(JinDiaoApplication.WIDTH * 1f / 75 * 30);

            itemView.setLayoutParams(params);

            final TemplateBean bean = (TemplateBean) object;

            if (bean != null) {

                if (!CollectionUtils.isListEmpty(bean.list)) {

                    WheelAdapter adapter = (WheelAdapter) rollPagerView.getAdapter();

                    if (adapter == null) {

                        adapter = new WheelAdapter(mContext);

                        rollPagerView.setAdapter(adapter);

                    }

                    adapter.refreshData(bean.list);

                }

                int current = rollPagerView.getCurrent();

                rollPagerView.getmViewPager().setCurrentItem(current);

            }

        }

    }

}
