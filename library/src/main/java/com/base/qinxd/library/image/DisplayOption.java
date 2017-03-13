package com.base.qinxd.library.image;

import com.base.qinxd.library.R;
import com.bumptech.glide.request.target.Target;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2017/2/28 16:19
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：图片加载参数
 */
public class DisplayOption {

    /**
     * 加载中的资源id
     */
    public int loadingResId;
    /**
     * 加载失败的资源id
     */
    public int loadErrorResId;

    /**
     * 默认不显示缩略图
     */
    public float thumbnail = 1.0f;

    public boolean isPreFitXY = true;

    public boolean isCenterCrop = false;

    public boolean asBitmap = false;

    /**
     * 是否为圆形图片
     */
    public boolean isCircle = false;

    /**
     * 圆角图片半径
     */
    public int roundCorner = 0;

    public int width = Target.SIZE_ORIGINAL;

    public int height = Target.SIZE_ORIGINAL;

    public static DisplayOption createDefaultDisplayOption() {

        DisplayOption option = new DisplayOption();

        option.loadingResId = R.drawable.base_drawable_a0a0a0;

        option.loadErrorResId = R.drawable.base_drawable_a0a0a0;

        return option;

    }

    public static DisplayOption createTransparentOption() {

        DisplayOption option = new DisplayOption();

        option.loadErrorResId = R.drawable.base_transparent;

        option.loadingResId = R.drawable.base_transparent;

        return option;

    }

    public DisplayOption setAsBitmap(boolean asBitmap) {

        this.asBitmap = asBitmap;

        return this;

    }

    public DisplayOption setThumbnail(float thumbnail) {


        this.thumbnail = thumbnail;

        return this;

    }

    public DisplayOption setCenterCrop(boolean isCenterCrop) {

        this.isCenterCrop = isCenterCrop;

        return this;

    }

    public DisplayOption setSize(int width, int height) {

        this.width = width;

        this.height = height;

        return this;

    }

}
