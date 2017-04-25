package com.base.qinxd.library.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.base.qinxd.library.R;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/4/25 18:27
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：空数据适配器
 */
public class EmptyHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public TextView emptyTextView;

    public EmptyHolder(Context context) {

        super(LayoutInflater.from(context).inflate(R.layout.base_empty_layout, null));

        this.mContext = context;

        emptyTextView = (TextView) itemView.findViewById(R.id.empty_text);

    }

    public EmptyHolder bindData(String descText, int imgResId) {

        emptyTextView.setText(descText);

        emptyTextView.setCompoundDrawablesWithIntrinsicBounds(0, imgResId, 0, 0);

        return this;

    }

}
