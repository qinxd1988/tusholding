package com.base.qinxd.library.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.base.qinxd.library.utils.ScreenUtils;


public class PointHintView extends LinearLayout implements HintView {

    private int length = 0;

    private int lastPosition = 0;

    private GradientDrawable dot_normal;

    private GradientDrawable dot_focus;

    private int currentPosition;

    public PointHintView(Context context) {
        super(context);
    }

    public PointHintView(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public void initView(int length, int gravity) {

        setOrientation(HORIZONTAL);

        switch (gravity) {

            case 0:

                setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);

                break;

            case 1:

                setGravity(Gravity.CENTER);

                break;

            case 2:

                setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);

                break;
            
        }

        this.length = length;

        if (dot_focus == null) {

            dot_focus = new GradientDrawable();

        }

        dot_focus.setColor(Color.WHITE);

        dot_focus.setCornerRadius(ScreenUtils.dip2px(getContext(), 4));

        dot_focus.setSize(ScreenUtils.dip2px(getContext(), 8), ScreenUtils.dip2px(getContext(), 8));

        if (dot_normal == null) {

            dot_normal = new GradientDrawable();

        }

        dot_normal.setColor(Color.WHITE);

        dot_normal.setAlpha(125);

        dot_normal.setCornerRadius(ScreenUtils.dip2px(getContext(), 4));

        dot_normal.setSize(ScreenUtils.dip2px(getContext(), 8), ScreenUtils.dip2px(getContext(), 8));

        int childCount = getChildCount();

        int diff = Math.abs(childCount - length);

        if (childCount > length) {//删除

            removeViews(length, diff);

        } else if (childCount < length) {//添加

            for (int i = 0; i < diff; i++) {

                ImageView imageView = new ImageView(getContext());

                LayoutParams dotlp = new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);

                dotlp.setMargins(10, 0, 10, 0);

                imageView.setLayoutParams(dotlp);

                imageView.setBackground(dot_normal);

                addView(imageView);

            }

        }

        setCurrent(currentPosition);

    }

    @Override
    public void setCurrent(int current) {

        if (current < 0 || current > length - 1) {

            return;

        }

        currentPosition = current;

        getChildAt(lastPosition).setBackground(dot_normal);

        getChildAt(current).setBackground(dot_focus);

        lastPosition = current;

    }

}
