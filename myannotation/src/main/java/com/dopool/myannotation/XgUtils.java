package com.dopool.myannotation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2017/2/17 0017.
 */

public class XgUtils {

    private static Toast mToast ;

    /**
     * view.findViewById 省去步骤
     * 这是在fragmen中
     * @param target
     * @param view
     */
    public static void bindView(Object target , View view){
        Field[] fields = target.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0){
            for (Field field : fields) {
                BindView myAno = field.getAnnotation(BindView.class);
                if (myAno != null){
                    int id = myAno.value()  ;
                    field.setAccessible(true);
                    try {
                        field.set(target , view.findViewById(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * view.findViewById 省去步骤
     * 这是在activity中
     * @param target
     */
    public static void bindView(Object target ){
        View view;
        if (target instanceof Activity){
            view = ((Activity)target).getWindow().getDecorView();
        }else {return;}

        Field[] fields = target.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0){
            for (Field field : fields) {
                BindView myAno = field.getAnnotation(BindView.class);
                if (myAno != null){
                    int id = myAno.value()  ;
                    //如果字段是私有的,那么必须要对这个字段设置
                    field.setAccessible(true);
                    try {
                        field.set(target , view.findViewById(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 吐司
     * @param context
     * @param text
     */
    public static void showToast(Context context , String text){

        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }



}