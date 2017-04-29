package com.TusFinancial.Credit.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.TusFinancial.Credit.JinDiaoApplication;
import com.TusFinancial.Credit.R;
import com.TusFinancial.Credit.bean.ModuleBean;
import com.TusFinancial.Credit.bean.TemplateBean;
import com.TusFinancial.Credit.utils.Constants;
import com.base.qinxd.library.image.DisplayOption;
import com.base.qinxd.library.image.ImageLoaderFactory;
import com.base.qinxd.library.image.SimpleImageLoadingListener;
import com.base.qinxd.library.utils.CollectionUtils;

import java.util.ArrayList;

/**
 * Created by xd on 2017/4/25.
 */

public class GridHolder extends RecyclerView.ViewHolder {

    private Context mContext;

    public GridLayout gridLayout;

    private DisplayOption option;

    public GridHolder(Context context) {

        super(LayoutInflater.from(context).inflate(R.layout.grid_layout, null));

        this.mContext = context;

        gridLayout = (GridLayout) itemView;

    }

    public GridHolder bindData(Object object) {

        if (object instanceof TemplateBean) {

            final TemplateBean bean = (TemplateBean) object;

            if (CollectionUtils.isListEmpty(bean.list)) {

                gridLayout.removeAllViews();

            }

            if (bean.type == 2) {//一行三个

                bind3Data(bean.list);

            } else if (bean.type == 3) {//一行四个

                bind4Data(bean.list);

            }

        }

        return this;

    }

    private void bind3Data(ArrayList<ModuleBean> list) {

        gridLayout.setColumnCount(3);

        if (!CollectionUtils.isListEmpty(list)) {

            operateData(list);

            ViewGroup.LayoutParams params = gridLayout.getLayoutParams();

            params.width = ViewGroup.LayoutParams.MATCH_PARENT;

            int margin = Math.round(JinDiaoApplication.DENSITY * 15);

            int row = gridLayout.getRowCount();

            int allMargin = (row - 1) * margin;

            int allPadding = 2 * margin;

            int childHeight = Math.round(JinDiaoApplication.DENSITY * 65);

            params.height = allMargin + allPadding + row * childHeight;

            gridLayout.setLayoutParams(params);

            int size = list.size();

            for (int i = 0; i < size; i++) {

                final ModuleBean module = list.get(i);

                View childView = gridLayout.getChildAt(i);

                GridLayout.LayoutParams childParams = (GridLayout.LayoutParams) childView.getLayoutParams();

                childParams.width = Math.round(JinDiaoApplication.WIDTH / 3);

                childView.setLayoutParams(childParams);

                final ImageView imageView = (ImageView) childView.findViewById(R.id.item_image);

                LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();

                imageParams.width = Math.round(JinDiaoApplication.DENSITY * 40);

                imageParams.height = imageParams.width;

                imageView.setLayoutParams(imageParams);

                if (option == null) {

                    option = DisplayOption.createTransparentOption();

                }

                ImageLoaderFactory.getImageLoader().with(mContext)
                        .displayImage(imageView, module.imgUrl, option,
                                new SimpleImageLoadingListener() {

                                    @Override
                                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                                        if (imageView != null) {

                                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                        }

                                    }
                                });

                TextView textView = (TextView) childView.findViewById(R.id.item_text);

                textView.setText(module.title);

                if (!TextUtils.isEmpty(module.linkUrl)) {

                    childView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String url = operateUrl(module.linkUrl);

                            TransferHelper.onTransfer(mContext, url, module.isNeedLogin);

                        }
                    });

                }

            }

        }


    }

    private void bind4Data(ArrayList<ModuleBean> list) {

        gridLayout.setColumnCount(4);

        if (!CollectionUtils.isListEmpty(list)) {

            operateData(list);

            int size = list.size();

            ViewGroup.LayoutParams params = gridLayout.getLayoutParams();

            params.width = ViewGroup.LayoutParams.MATCH_PARENT;

            int margin = Math.round(JinDiaoApplication.DENSITY * 15);

            int row = size % 4 == 0 ? size / 4 : size / 4 + 1;

            int allMargin = (row - 1) * margin;

            int allPadding = 2 * margin;

            int childHeight = Math.round(JinDiaoApplication.DENSITY * 48);

            params.height = allMargin + allPadding + row * childHeight;

            gridLayout.setLayoutParams(params);

            for (int i = 0; i < size; i++) {

                final ModuleBean module = list.get(i);

                View childView = gridLayout.getChildAt(i);

                GridLayout.LayoutParams childParams = (GridLayout.LayoutParams) childView.getLayoutParams();

                childParams.width = Math.round(JinDiaoApplication.WIDTH / 4);

                if (i > 3) {

                    childParams.topMargin = margin;

                } else {

                    childParams.topMargin = 0;

                }

                childView.setLayoutParams(childParams);

                final ImageView imageView = (ImageView) childView.findViewById(R.id.item_image);

                LinearLayout.LayoutParams imageParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();

                imageParams.width = Math.round(JinDiaoApplication.DENSITY * 22);

                imageParams.height = imageParams.width;

                imageView.setLayoutParams(imageParams);

                if (option == null) {

                    option = DisplayOption.createTransparentOption();

                }

                ImageLoaderFactory.getImageLoader().with(mContext)
                        .displayImage(imageView, module.imgUrl, option,
                                new SimpleImageLoadingListener() {

                                    @Override
                                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                                        if (imageView != null) {

                                            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                        }

                                    }
                                });

                TextView textView = (TextView) childView.findViewById(R.id.item_text);

                textView.setText(module.title);

                if (!TextUtils.isEmpty(module.linkUrl)) {

                    childView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String url = operateUrl(module.linkUrl);

                            TransferHelper.onTransfer(mContext, url, module.isNeedLogin);

                        }
                    });

                }

            }

        }

    }

    private void operateData(ArrayList<ModuleBean> list) {

        if (!CollectionUtils.isListEmpty(list)) {

            int size = list.size();

            int count = gridLayout.getChildCount();

            int diff = Math.abs(size - count);

            if (size > count) {

                for (int i = 0; i < diff; i++) {

                    View childView = View.inflate(mContext, R.layout.image_text_item_layout, null);

                    gridLayout.addView(childView);

                }

            } else if (size < count) {

                gridLayout.removeViews(size, diff);

            }

        }

    }

    private String operateUrl(String url) {

        if (url.contains("&token=")) {

            Uri uri = Uri.parse(url);

            String data = uri.getQueryParameter(Constants.TOKEN);

            //使用本地token值替换
            if (TextUtils.isEmpty(data)) {

                url = url.replace("&token=", "&token=" + JinDiaoApplication.TOKEN);

            } else {

                url = url.replace(data, JinDiaoApplication.TOKEN);

            }

        }

        return url;

    }

}
