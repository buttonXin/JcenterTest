package com.dopool.myannotation.system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.TextUtils;

import com.dopool.myannotation.log.L;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class SystemUtilx {


    //获取MAC地址
    public static String getLocalMacAddress(Context context) {
        String mac = "";
        WifiManager wifi = (WifiManager) context.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        if (info != null) {
            mac = info.getMacAddress();
        }
        return mac;
    }

    /**
     * 获取当前的外界设备HDMI是否打开
     */
    public static boolean getHDMI_isOpen() {

        // The file '/sys/devices/virtual/switch/hdmi/state' holds an int -- if it's 1 then an HDMI device is connected.
        // An alternative file to check is '/sys/class/switch/hdmi/state' which exists instead on certain devices.
        File switchFile = new File("/sys/devices/virtual/switch/hdmi/state");
        if (!switchFile.exists()) {
            switchFile = new File("/sys/class/switch/hdmi/state");
        }
        try {
            Scanner switchFileScanner = new Scanner(switchFile);
            int switchValue = switchFileScanner.nextInt();
            switchFileScanner.close();
            return switchValue > 0;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 调节音量
     */

    public static void changeVolume(Context context ,int index) {
        L.d("当前音量 " + index);
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        float percentage = index / 100f;
        int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int realIndex = (int) (percentage * maxVolume);

        if (realIndex >= maxVolume){
            realIndex = maxVolume ;
        }
        if (realIndex <= 0 ){
            realIndex = 0 ;
        }
        am.setStreamVolume(AudioManager.STREAM_MUSIC, realIndex, 0);
    }

    /**
     * 启动设置到自己的app下

  public static void startAppSettings(Activity activity , int requestCode) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, requestCode);
    }*/

    public static void startAppSettings(Context context ) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
