package com.dopool.mymapclear;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.dopool.myannotation.log.L;
import com.dopool.myannotation.normal.AppUtilx;
import com.dopool.myannotation.normal.FileUtilx;
import com.dopool.myannotation.normal.NetWorkUtilx;
import com.dopool.myannotation.normal.ScreenUtil;
import com.dopool.myannotation.normal.Utilx;
import com.dopool.myannotation.system.HomeReceiverUtil;
import com.dopool.myannotation.system.SystemUtilx;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "https://dev-bsmeta.dopool.com/cp_name/adres/res/cctv/17a8567c63ae4de78e2c5b9ebfa9de77.mp4";
    public static final String URL_m3u8 = "http://dev-dopool-web.dopool.com/cp_name/xiazai/2016-04-15/7813/2/index_2.m3u8";
    ConstraintLayout img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        img = (ConstraintLayout) findViewById(R.id.img);

        img.setBackground(ContextCompat.getDrawable(this , R.drawable.tmf1));

        FileUtilx.saveDataToSP(this , "666" , "this is content");

        L.e(/*"文件大小 = " + FileUtilx.getSDTotalMSize()
                + " 剩余文件大小 = " +FileUtilx.getSDAvailableMSize()
                +*/ " 保存文件 = " + FileUtilx.saveDataToSDFile("xxxxxx" ,"testDir" ,"test.txt" , false)
                +" 读取文件内容 " + FileUtilx.readSDFileContent("testDir" ,"test.txt" )

                +" versionName "+ AppUtilx.getVersionName(this)
                +" versionCode " + AppUtilx.getVersionCode(this)
                +" mac = " + SystemUtilx.getLocalMacAddress(this)
                +" hdmi = " + SystemUtilx.getHDMI_isOpen()
                +" ping = " + NetWorkUtilx.ping()
                + " sp = " +FileUtilx.readDataFromSP(this , "666" , "default")

                +"\n screenWigth = "+ ScreenUtil.getScreenWidth(this)
                +"\n screenHeight = "+ ScreenUtil.getScreenHeight(this)
                +"\n time " + Utilx.curTimeFormat("yyyy_MM_dd")
        );

        HomeReceiverUtil.registerHomeKeyReceiver(this, new HomeReceiverUtil.HomeKeyListener() {
            @Override
            public void homeKey() {
                Utilx.showToastLong(getApplicationContext() , "ok!!!!!");
            }
        });



        findViewById(R.id.text_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  SystemUtilx.changeVolume(getApplicationContext() , count);
                count +=10 ;

                if (count > 150){
                    count = 0 ;
                }
                if (count % 20 == 0){
                    DialogUtilx.showDialog(MainActivity.this , true , "加载中...");
                }else {
                    DialogUtilx.dismissDialog();
                }*/

                SystemUtilx.startAppSettings(getApplicationContext());
            }
        });

  /*      new Thread(new Runnable() {
            @Override
            public void run() {
                L.e("image = " + ImageUtilx.getVideoFirstFrame(URL));
            }
        }).start();*/


    }


    private int count = 0 ;
    @Override
    protected void onDestroy() {
        super.onDestroy();

        HomeReceiverUtil.unregisterHomeKeyReceiver(this);
    }
}
