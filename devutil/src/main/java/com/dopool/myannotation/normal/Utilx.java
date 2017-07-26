package com.dopool.myannotation.normal;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class Utilx {


    private static Toast mToast ;

    /**
     * 吐司
     * @param context
     * @param content
     */
    public static void showToastShort(Context context , String content){
        if (mToast == null){
            mToast = Toast.makeText(context , content , Toast.LENGTH_SHORT);
        }else {
            mToast.setText(content);
        }
        mToast.show();
    }

    public static void showToastLong(Context context , String content){
        if (mToast == null){
            mToast = Toast.makeText(context , content , Toast.LENGTH_LONG);
        }else {
            mToast.setText(content);
        }
        mToast.show();
    }

    /**
     * 事件格式转换
     * @param yyyy_MM_dd_hh_mm_ss_EEEE
     * @return
     */
    public static  String  curTimeFormat(String yyyy_MM_dd_hh_mm_ss_EEEE){

        SimpleDateFormat formatter = new SimpleDateFormat(yyyy_MM_dd_hh_mm_ss_EEEE);
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return formatter.format(curDate);
    }




}
