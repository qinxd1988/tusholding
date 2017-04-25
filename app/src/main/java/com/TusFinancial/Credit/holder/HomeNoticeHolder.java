package com.TusFinancial.Credit.holder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.bean.ModuleBean;
import com.TusFinancial.Credit.bean.TemplateBean;
import com.TusFinancial.Credit.helper.TransferHelper;
import com.base.qinxd.library.utils.CollectionUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xd on 2017/4/25.
 */

public class HomeNoticeHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    private Animation inAnimation;

    private Animation outAnimation;

    @BindView(R.id.home_notice_view_flipper)
    public ViewFlipper viewFlipper;

    public HomeNoticeHolder(Context context) {

        super(LayoutInflater.from(context).inflate(R.layout.home_notice_layout, null));

        this.mContext = context;

        ButterKnife.bind(this, itemView);

    }

    public HomeNoticeHolder bindData(Object object) {

        if (object instanceof TemplateBean) {

            TemplateBean bean = (TemplateBean) object;

            if (!CollectionUtils.isListEmpty(bean.list)) {

                int size = bean.list.size();

                int count = viewFlipper.getChildCount();

                int diff = Math.abs(size - count);

                if (size > count) {//添加

                    for (int i = 0; i < diff; i++) {

                        ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT);

                        TextView textView = new TextView(mContext);

                        textView.setTextSize(10);

                        textView.setTextColor(Color.BLACK);

                        textView.setLines(1);

                        textView.setGravity(Gravity.CENTER_VERTICAL);

                        textView.setEllipsize(TextUtils.TruncateAt.END);

                        viewFlipper.addView(textView, params);

                    }

                } else if (size < count) {//删除

                    viewFlipper.removeViews(size, diff);

                }

                for (int i = 0; i < size; i++) {

                    final ModuleBean module = bean.list.get(i);

                    TextView textView = (TextView) viewFlipper.getChildAt(i);

                    textView.setText(module.title);

                    if (!TextUtils.isEmpty(module.linkUrl)) {

                        textView.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                TransferHelper.onTransfer(mContext, module.linkUrl);

                            }

                        });

                    }

                }

                if (inAnimation == null) {

                    inAnimation = AnimationUtils.loadAnimation(mContext,
                            R.anim.push_up_in);

                }

                viewFlipper.setInAnimation(inAnimation);

                if (outAnimation == null) {

                    outAnimation = AnimationUtils.loadAnimation(mContext,
                            R.anim.push_up_out);

                }

                viewFlipper.setOutAnimation(outAnimation);

                if (!viewFlipper.isFlipping()) {

                    viewFlipper.startFlipping();

                }

            }

        }

        return this;

    }

}
