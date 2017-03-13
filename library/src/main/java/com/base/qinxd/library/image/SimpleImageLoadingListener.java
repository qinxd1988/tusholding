package com.base.qinxd.library.image;

import android.graphics.Bitmap;
import android.view.View;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：16/3/28 15:10
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：图片加载状态默认实现
 */
public class SimpleImageLoadingListener implements ImageLoadingListener {

    @Override
    public void onLoadingStarted(String imageUri, View view) {

    }

    @Override
    public void onLoadingFailed(String imageUri, View view) {

    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {

    }

}
