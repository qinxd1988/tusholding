package com.base.qinxd.library.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/2/28 16:10
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：图片加载模块封装
 */
public interface ImageLoaderWrapper {

    String DRAWABLE = "drawable://";

    String FILE = "file://";

    String NETWORK = "http";

    ImageLoaderWrapper with(Context context);

    /**
     * 显示图片
     *
     * @param imageView 显示图片的ImageView
     * @param imageUrl  图片资源的URL
     * @param option    显示参数设置
     */
    void displayImage(ImageView imageView, String imageUrl, DisplayOption option);

    /**
     * 显示图像
     *
     * @param imageView
     * @param imageUrl
     * @param option
     * @param loadingListener
     */
    void displayImage(ImageView imageView, String imageUrl,
                      DisplayOption option, ImageLoadingListener loadingListener);

    /**
     * 显示图像
     *
     * @param imageView
     * @param imageUrl
     * @param option
     * @param loadingListener
     * @param progressListener 加载进度
     */
    void displayImage(ImageView imageView, String imageUrl,
                      DisplayOption option, ImageLoadingListener loadingListener,
                      ImageLoadingProgressListener progressListener);


    /**
     * 加载图片
     *
     * @param width
     * @param height
     * @param imageUrl
     * @param option
     * @param loadingListener
     * @param progressListener
     */
    void loadImage(int width, int height, String imageUrl,
                   DisplayOption option, ImageLoadingListener loadingListener,
                   ImageLoadingProgressListener progressListener);

    /**
     * 清空disk 缓存
     */
    void clearDiskCache();

    /**
     * 清空缓存
     */
    void clearMemoryCache();

}
