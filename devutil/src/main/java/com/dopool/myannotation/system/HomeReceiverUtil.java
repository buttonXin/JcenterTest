package com.dopool.myannotation.system;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.dopool.myannotation.log.L;


/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class HomeReceiverUtil {


    private static BroadcastReceiver mHomeReceiver = null;

    private static  HomeKeyListener homeKeyListener ;
    /**
     * 添加home的广播
     * @param context
     */
    public static void registerHomeKeyReceiver(Context context , final HomeKeyListener listener) {
        L.d("注册home的广播");
        mHomeReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                homeFinish(intent , context , listener);
            }
        };
        final IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);

        context.registerReceiver(mHomeReceiver, homeFilter);
    }

    /**
     * 注销home的广播
     * @param context
     */
    public static void unregisterHomeKeyReceiver(Context context) {
        L.d( "销毁home的广播");
        if (null != mHomeReceiver) {
            context.unregisterReceiver(mHomeReceiver);
            mHomeReceiver = null ;
            L.d("已经走了一遍");
        }
    }


    static final String SYSTEM_DIALOG_REASON_KEY = "reason";

    static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";


    private static void homeFinish(Intent intent, Context context, HomeKeyListener listener) {

        String action = intent.getAction();

        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {

            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);

            if (reason != null && reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {

                if (listener != null){
                    listener.homeKey();
                }
            }
        }
    }


    public interface HomeKeyListener{

        void homeKey();
    }

}
