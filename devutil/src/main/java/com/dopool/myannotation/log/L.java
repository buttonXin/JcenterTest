package com.dopool.myannotation.log;

import android.util.Log;

import com.dopool.myannotation.ConstantUtilx;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class L {


    private static final String TAG = Thread.currentThread().getName();

    public static void d(String text){
        if (ConstantUtilx.IS_DEBUG){
            Log.d("thread = "+TAG , text);
        }
    }

    public static void e(String text){
        if (ConstantUtilx.IS_DEBUG){
            Log.e("thread = "+TAG , text);
        }
    }





}
