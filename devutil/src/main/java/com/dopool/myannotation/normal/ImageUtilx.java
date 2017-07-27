package com.dopool.myannotation.normal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class ImageUtilx {


    /**
     * 给出url，获取视频的第一帧,好像M3U8的地址获取不了...
     */
    public static Bitmap getVideoFirstFrame(String url_ban_m3u8_best_on_thread) {
        Bitmap bitmap = null;
        //MediaMetadataRetriever 是android中定义好的一个类，提供了统一
        //的接口，用于从输入的媒体文件中取得帧和元数据；
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据文件路径获取缩略图
            retriever.setDataSource(url_ban_m3u8_best_on_thread, new HashMap<String, String>());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }


    /**
     * 将图片进行压缩
     */
    public static Bitmap getLowMemoryBitmap(Context ctx, File filePath) {

        Bitmap bitmap;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath.getPath(), options);

        int bmpWidth = options.outWidth;
        int bmpHeight = options.outHeight;

        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int screenWidth = outMetrics.widthPixels;
        int screenHeight = outMetrics.heightPixels;

        options.inSampleSize = 1;
        if (bmpWidth > bmpHeight) {
            if (bmpWidth > screenWidth)
                options.inSampleSize = bmpWidth / screenWidth;
        } else {
            if (bmpHeight > screenHeight)
                options.inSampleSize = bmpHeight / screenHeight;
        }
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeFile(filePath.getPath(), options);
        return bitmap;

    }



}
