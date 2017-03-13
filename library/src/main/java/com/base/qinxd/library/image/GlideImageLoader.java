package com.base.qinxd.library.image;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 作者：qinxudong
 * <p/>
 * 时间：2016/12/8 09:57
 * <p/>
 * 邮箱：qinxd1988@163.com
 * <p/>
 * 描述：
 */
public class GlideImageLoader implements ImageLoaderWrapper {

    RequestManager manager;

    Glide mGlide;

    ViewPropertyAnimation.Animator animationObject = new ViewPropertyAnimation.Animator() {
        @Override
        public void animate(View view) {
            // if it's a custom view class, cast it here
            // then find subviews and do the animations
            // here, we just use the entire view for the fade animation
            view.setAlpha(0f);

            ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);

            fadeAnim.setDuration(600);

            fadeAnim.start();

        }
    };

    @Override
    public ImageLoaderWrapper with(Context context) {

        mGlide = Glide.get(context);

        manager = Glide.with(context);

        return this;

    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl,
                             DisplayOption option) {

        displayImage(imageView, imageUrl, option, null);

    }

    @Override
    public void displayImage(ImageView imageView, String imageUrl,
                             DisplayOption option,
                             ImageLoadingListener loadingListener) {

        displayImage(imageView, imageUrl, option, loadingListener, null);

    }

    @Override
    public void displayImage(final ImageView imageView, final String imageUrl,
                             DisplayOption option,
                             final ImageLoadingListener loadingListener,
                             final ImageLoadingProgressListener progressListener) {

        if (TextUtils.isEmpty(imageUrl)) {

            imageView.setImageResource(option.loadingResId);

            return;

        }

        if (imageUrl.startsWith(DRAWABLE)) {//读取资源文件

            String drawableRes = imageUrl.replace(DRAWABLE, "");

            Integer resId = Integer.valueOf(drawableRes);

            operateDisplay(imageView, resId, imageUrl, option, loadingListener, progressListener);

        } else if (imageUrl.startsWith(FILE)) {//读取本地文件

            String fileRes = imageUrl.replace(FILE, "");

            File file = new File(fileRes);

            if (file.exists()) {

                operateDisplay(imageView, file, imageUrl, option, loadingListener, progressListener);

            }

        } else if (imageUrl.startsWith("http")) {//读取网络图片

            operateDisplay(imageView, imageUrl, imageUrl, option, loadingListener, progressListener);

        }

    }

    @Override
    public void loadImage(int width, int height, final String imageUrl,
                          DisplayOption option, final ImageLoadingListener loadingListener,
                          ImageLoadingProgressListener progressListener) {

        DrawableTypeRequest<String> builder = manager.load(imageUrl);

        builder.downloadOnly(width, height);

        builder.asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                if (loadingListener != null) {

                    loadingListener.onLoadingComplete(imageUrl, null, resource);

                }

            }

            @Override
            public void onLoadStarted(Drawable placeholder) {

                if (loadingListener != null) {

                    loadingListener.onLoadingStarted(imageUrl, null);

                }

            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {

                if (loadingListener != null) {

                    loadingListener.onLoadingFailed(imageUrl, null);

                }

            }

        });

    }

    private <T> DrawableTypeRequest<T> operateDisplay(final ImageView imageView,
                                                      T t,
                                                      final String imageUrl,
                                                      DisplayOption option,
                                                      final ImageLoadingListener loadingListener,
                                                      final ImageLoadingProgressListener progressListener) {

        DrawableTypeRequest<T> builder = manager.load(t);

        if (option == null) {

            option = DisplayOption.createTransparentOption();

        }

        builder.placeholder(option.loadingResId)
                .error(option.loadErrorResId)
                .animate(animationObject);

        if (option.thumbnail < 1f && option.thumbnail > 0) {

            builder.thumbnail(option.thumbnail);

        }

        if (option.isPreFitXY) {

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }

        int width = imageView.getWidth();

        int height = imageView.getHeight();

        if (width != 0 && height != 0) {

            builder.override(width, height);

        }

        if (option.isCenterCrop) {

            builder.centerCrop();

        }

        if (option.asBitmap) {

            builder.asBitmap().into(new SimpleTarget<Bitmap>(option.width, option.height) {

                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {

                    if (loadingListener != null) {

                        loadingListener.onLoadingComplete(imageUrl, imageView, resource);

                    }

                    if (resource != null && !resource.isRecycled()) {

                        imageView.setImageBitmap(resource);

                    }

                }

                @Override
                public void onLoadStarted(Drawable placeholder) {

                    if (loadingListener != null) {

                        loadingListener.onLoadingStarted(imageUrl, imageView);

                    }

                }

                @Override
                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                    if (loadingListener != null) {

                        loadingListener.onLoadingFailed(imageUrl, imageView);

                    }
                }
            });

            return builder;

        } else if (option.isCircle) {//圆形图片

            builder.bitmapTransform(new CropCircleTransformation(mGlide.getBitmapPool()));

        } else if (option.roundCorner > 0) {//圆角图片

            builder.bitmapTransform(new RoundedCornersTransformation(mGlide.getBitmapPool(),
                    option.roundCorner, 0));

        }

        if (loadingListener != null) {

            builder.listener(new RequestListener<T, GlideDrawable>() {
                @Override
                public boolean onException(Exception e,
                                           T model,
                                           Target<GlideDrawable> target,
                                           boolean isFirstResource) {

                    if (loadingListener != null) {

                        loadingListener.onLoadingFailed(imageUrl, imageView);

                    }

                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource,
                                               T model,
                                               Target<GlideDrawable> target,
                                               boolean isFromMemoryCache,
                                               boolean isFirstResource) {
                    if (loadingListener != null) {

                        loadingListener.onLoadingComplete(imageUrl, imageView, null);

                    }

                    return false;

                }

            });

            loadingListener.onLoadingStarted(imageUrl, imageView);

        }

        builder.into(imageView);

        return builder;

    }

    @Override
    public void clearDiskCache() {

        if (mGlide == null) {

            throw new NullPointerException("please call with(*) method !");

        }

        mGlide.clearDiskCache();

    }

    @Override
    public void clearMemoryCache() {

        if (mGlide == null) {

            throw new NullPointerException("please call with(*) method !");

        }

        mGlide.clearMemory();

    }

}
