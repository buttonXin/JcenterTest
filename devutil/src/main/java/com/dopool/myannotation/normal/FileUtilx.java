package com.dopool.myannotation.normal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class FileUtilx {


/*    *
     * 获得sd卡剩余容量，即可用大小
     *返回以 M 为单位-Xlint:deprecation*/

    @Deprecated
    public static long getSDAvailableMSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return  blockSize * availableBlocks /1024/1024;
    }

 /*   *
     * 获得SD卡总大小 返回 M
     */
    @Deprecated
    public static long getSDTotalMSize() {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return  blockSize * totalBlocks/1024/1024;
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     */
    public static double getFileOrFilesMSize(String filePath) {
        File file = new File(filePath);
        long blockSize = 0;
        try {
            if (file.isDirectory()) {
                blockSize = getFileSizes(file);
            } else {
                blockSize = getFileSize(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("获取文件大小", "获取失败!");
        }
        return blockSize / 1048576;
    }

    /**
     * 获取指定文件夹
     */
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSizes(flist[i]);
            } else {
                size = size + getFileSize(flist[i]);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     */
    public static long getFileSize(File file) throws IOException {
        long size = 0;
        FileInputStream fis = null;

        try {
            if (file.exists()) {
                fis = new FileInputStream(file);
                size = fis.available();
                fis.close();
            } else {
                file.createNewFile();
                Log.e("获取文件大小", "文件不存在!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (fis!=null){
                fis.close();
            }
        }
        return size;
    }

    /**
     * 将文件目录进行改名
     */
    public boolean fileRenameTo(File oldFile , String parent ,String child){
        if (! oldFile.exists()) return  false ;
        return oldFile.renameTo(new File(parent , child));
    }

    /**
     * 存储本地文件additional true 表示在后面追加， false 表示覆盖之前的
     */
    public static File saveDataToSDFile(String content,String parent ,
                                        String fileName, boolean additional ) {


        File sdCard = Environment.getExternalStorageDirectory();

        File file = new File(sdCard, parent);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1 = new File(file, fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file1, additional);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException mE) {
            mE.printStackTrace();
        }finally {
            try {
                if (fw != null )
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file1;
    }

    /**
     * 存储本地文件
     */
    public static File saveDataToSDFile(String content,String parent ,
                                        String fileName ) {
        File sdCard = Environment.getExternalStorageDirectory();

        File file = new File(sdCard, parent);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1 = new File(file, fileName);
        FileWriter fw = null;
        try {
            fw = new FileWriter(file1);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException mE) {
            mE.printStackTrace();
        }finally {
            try {
                if (fw != null )
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file1;
    }

    /**
     * 这是将文件夹内容全部读取出来
     */
    public static String readSDFileContent(String parent , String fileName) {
        File response = new File(Environment.getExternalStorageDirectory() + File.separator + parent, fileName);
        if (!response.exists()) {
            Log.e(" read file --> ", "readSDFileContent:  fail" );
            return "";
        }
        String filePath = response.getAbsolutePath();
        BufferedReader br = null;
        String line ;
        StringBuilder sb = new StringBuilder();

        // 根据文件路径创建缓冲输入流
        try {
            br = new BufferedReader(new FileReader(filePath));
            // 循环读取文件的每一行将其 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                // 此处根据实际需要获取某些行的内容
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    /**
     * 保存内容到sp
     */
    public static void saveDataToSP(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("sharePref", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    /**
     * 得到内容从sp
     */
    public static String readDataFromSP(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences("sharePref", Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }
}
