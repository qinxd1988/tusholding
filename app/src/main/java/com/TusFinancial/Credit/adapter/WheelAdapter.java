package com.TusFinancial.Credit.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.TusFinancial.Credit.bean.ModuleBean;
import com.TusFinancial.Credit.helper.TransferHelper;
import com.base.qinxd.library.image.DisplayOption;
import com.base.qinxd.library.image.ImageLoaderFactory;
import com.base.qinxd.library.image.SimpleImageLoadingListener;

/**
 * Created by qinxudong on 15/7/24.
 */
public class WheelAdapter extends DynamicPagerAdapter<ModuleBean> {

    public WheelAdapter(Context context) {

        super(context);

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public View getView(ViewGroup container, int position) {

        final ImageView imageView = new ImageView(mContext);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        imageView.setLayoutParams(params);

        final ModuleBean bean = mList.get(position);

        if (bean != null) {

            ImageLoaderFactory.getImageLoader().with(mContext).displayImage(imageView, bean.imgUrl,
                    DisplayOption.createDefaultDisplayOption(),
                    new SimpleImageLoadingListener() {
                        @Override
                        public void onLoadingStarted(String imageUri, View view) {
                            super.onLoadingStarted(imageUri, view);

                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                        }

                        @Override
                        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                            super.onLoadingComplete(imageUri, view, loadedImage);

                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                        }
                    });

        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransferHelper.onTransfer(mContext, bean.linkUrl, false);

            }
        });

        return imageView;
    }


}
