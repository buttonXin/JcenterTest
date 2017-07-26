package com.dopool.mymapclear;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class MacReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String mac = intent.getStringExtra("mac");
        Toast.makeText(context , "mapAPP  "+ mac , Toast.LENGTH_SHORT ).show();
    }
}
