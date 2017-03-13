package com.base.qinxd.library.image;

import android.view.View;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：16/3/28 12:08
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：图片加载进度监听
 */
public interface ImageLoadingProgressListener {

    /**
     * Is called when image loading progress changed.
     *
     * @param imageUri
     *         Image URI
     * @param view
     *         View for image. Can be <b>null</b>.
     * @param current
     *         Downloaded size in bytes
     * @param total
     *         Total size in bytes
     */
    void onProgressUpdate(String imageUri, View view, int current, int total);

}
