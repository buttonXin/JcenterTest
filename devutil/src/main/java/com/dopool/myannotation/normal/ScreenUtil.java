package com.dopool.myannotation.normal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class ScreenUtil {


    /**
     * 获取屏幕的真实宽高（包括状态栏）
     */
    public static int getScreenWidth(Activity activity){
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        return point.x;
    }

    public static int getScreenHeight(Activity activity){
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getRealSize(point);
        return point.y;
    }
}
