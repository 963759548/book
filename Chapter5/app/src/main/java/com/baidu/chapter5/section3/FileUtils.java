package com.baidu.chapter5.section3;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by yull on 04/19.
 */
public class FileUtils {
    // 缓存的文件
    public static final String CACHE_DIR = "cache";
    // 保存图片的路径
    public static final String ICON_DIR = "icon";
    // 下载文件的路径
    public static final String DOWNLOAD_DIR = "download";
    // 保存到SD卡的根目录
    private static final String ROOT_DIR = "likeDev";

    public static String getCacheDir(Context context) {
        return getDir(context,CACHE_DIR);
    }

    public static String getIconDir(Context context) {
        return getDir(context,ICON_DIR);
    }

    public static String getDownloadDir(Context context) {
        return getDir(context,DOWNLOAD_DIR);
    }


    public static String getDir(Context context,String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath(context));
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }
    // 返回SD卡路径
    private static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator +
                ROOT_DIR +
                File.separator;
    }

    /**
     * 判断sd卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }
    // 创建文件夹
    private static boolean createDirs(String path) {
        File file = new File(path);
        return !(!file.exists() || !file.isDirectory()) || file.mkdirs();
    }

    private static String getCachePath(Context context) {
        File f = context.getCacheDir();
        return f.getAbsolutePath() + File.separator;
    }
}
