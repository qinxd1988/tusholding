package com.TusFinancial.Credit.helper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.holder.HomeNoticeHolder;
import com.TusFinancial.Credit.holder.RollPagerHolder;

/**
 * Created by xd on 2017/4/25.
 */

public class HomeAdapterHelper {

    private Context mContext;

    public HomeAdapterHelper(Context context) {

        this.mContext = context;

    }

    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;

        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        params.bottomMargin = Math.round(JinDiaoApplication.DENSITY * 5);

        switch (viewType) {

            case 1:

                holder = new HomeNoticeHolder(mContext);

                params.height = Math.round(JinDiaoApplication.DENSITY * 20);

                break;

            case 2://一行显示3个

            case 3://一行显示4个

                holder = new GridHolder(mContext);

                break;

            case 4://轮播图

                holder = new RollPagerHolder(mContext);

                break;

            default:

                holder = new HomeNoticeHolder(mContext);

                params.height = Math.round(JinDiaoApplication.DENSITY * 20);

                break;

        }

        if (holder != null) {

            holder.itemView.setLayoutParams(params);

        }

        return holder;

    }

    public void onBindItemViewHolder(RecyclerView.ViewHolder viewHolder, Object object) {

        if (viewHolder instanceof HomeNoticeHolder) {

            ((HomeNoticeHolder) viewHolder).bindData(object);

        } else if (viewHolder instanceof GridHolder) {

            ((GridHolder) viewHolder).bindData(object);

        } else if (viewHolder instanceof RollPagerHolder) {

            ((RollPagerHolder) viewHolder).bindData(object);

        }

    }

}
