package com.dopool.myannotation.normal;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class DialogUtilx {

    private static ProgressDialog progressDialog;

    /**
     * 显示dialog
     */
    public static void showDialog(Context context ,boolean cancelable , String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressDialog = new ProgressDialog(context, android.R.style.Theme_Material_Light_Dialog);
        }else {
            progressDialog = new ProgressDialog(context, android.R.style.Theme_DeviceDefault_Light_Dialog);
        }
        progressDialog.setCancelable(cancelable);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();

    }
}
