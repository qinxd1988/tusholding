package com.base.qinxd.library.image;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/2/28 16:38
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class ImageLoaderFactory {

    private static ImageLoaderWrapper ourInstance = new GlideImageLoader();

    public static ImageLoaderWrapper getImageLoader() {

        if (ourInstance == null) {

            synchronized (ImageLoaderFactory.class) {

                ourInstance = new GlideImageLoader();

            }

        }

        return ourInstance;
    }

    private ImageLoaderFactory() {
    }

}
