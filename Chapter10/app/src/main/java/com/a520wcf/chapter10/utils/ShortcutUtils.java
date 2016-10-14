package com.a520wcf.chapter10.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Parcelable;

import com.a520wcf.chapter10.R;

/**
 * Created by apple on 16/10/8.
 */

public class ShortcutUtils {
    /**
     * 创建快捷方式
     * @param context 上下文
     * @param shortcutName  快捷方式的名称
     * @param iconRes  设置快捷方式图片Res 如 R.drawable.xxx / R.mipmap.xxx
     * @param actionIntent 设置快捷方式动作
     * @param allowRepeat 是否允许重复创建
     */
    public static void addShortcut(Context context, String shortcutName,
                                   int iconRes, Intent actionIntent, boolean allowRepeat){
        Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //是否允许重复创建
        shortcutintent.putExtra("duplicate",allowRepeat);
        //快捷方式的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        //设置快捷方式图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(context.getApplicationContext(), iconRes);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        //设置快捷方式动作
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        //向系统发送广播
        context.sendBroadcast(shortcutintent);

    }
    /**
     * 创建快捷方式
     * @param context 上下文
     * @param shotcutName 快捷方式的名称
     * @param bitmap 设置快捷方式图片
     * @param actionIntent 设置快捷方式动作
     * @param allowRepeat 是否允许重复创建
     */
    public static void addShortcut(Context context, String shotcutName,
                                   Bitmap bitmap, Intent actionIntent, boolean allowRepeat){
        Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        //是否允许重复创建
        shortcutintent.putExtra("duplicate",allowRepeat);
        //快捷方式的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shotcutName);
        //设置快捷方式图片
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
        //设置快捷方式动作
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        //向系统发送广播
        context.sendBroadcast(shortcutintent);

    }
    /**
     * 删除快捷键
     *
     */
    public static void deleteShortcut(Context context, String name
            ,Intent actionIntent,boolean allowRepeat){
        Intent shortcutintent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        //是否循环删除
        shortcutintent.putExtra("duplicate",allowRepeat);
        //快捷方式的名称
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        //设置快捷方式动作
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        //向系统发送广播
        context.sendBroadcast(shortcutintent);
    }
}
