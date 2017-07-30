package com.example.khanh.listenwritedemo.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by chungbn on 06/08/2015.
 */
public class ImageLoaderUtils {
    private static LruCache<String, Bitmap> mCache;
    private static ImageLoader imageLoader;

    public static ImageLoader getImageLoaderUtils(Context context) {
        if (imageLoader == null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            int availMemInBytes = am.getMemoryClass() * 1024 * 104;
            mCache = new LruCache<String, Bitmap>(availMemInBytes / 8) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
            imageLoader = new ImageLoader(Volley.newRequestQueue(context), new ImageLoader.ImageCache() {
                @Override
                public Bitmap getBitmap(String url) {
                    if (url.contains("file://")) {
                        return BitmapFactory.decodeFile(url.substring(url.indexOf("file://") + 7));
                    } else {
                        return mCache.get(url);
                    }
                }

                @Override
                public void putBitmap(String url, Bitmap bitmap) {
                    mCache.put(url, bitmap);
                }
            });
        }
        return imageLoader;
    }

    public static LruCache<String, Bitmap> getLruCache(Context context){
        if(mCache == null){
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            int availMemInBytes = am.getMemoryClass() * 1024 * 104;
            mCache = new LruCache<String, Bitmap>(availMemInBytes / 8) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }
        return mCache;
    }
}
