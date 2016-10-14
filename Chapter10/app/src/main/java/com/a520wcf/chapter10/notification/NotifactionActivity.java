package com.a520wcf.chapter10.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.a520wcf.chapter10.R;

public class NotifactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifaction);
    }


    /**普通通知*/
    public void commonNotification(View v) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //首先创建Builder 对象，用PendingIntent 控制跳转，这里跳转到网页
        Notification.Builder builder = new Notification.Builder(this);
        //跳转到本书地址
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://book.520wcf.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        // builder可以添加各种属性
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);//小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
        notificationManager.notify(0, builder.build());
    }
    /**折叠通知*/
    @TargetApi(Build.VERSION_CODES.N)
    public void foldNotification(View v) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
        builder.setCustomBigContentView(remoteViews);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://book.520wcf.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠式通知");

        // RemoteViews remoteViews2 = new RemoteViews(getPackageName(), R.layout.view_fold2);
        //builder.setCustomContentView(remoteViews);
        //用RemoteViews来创建自定义Notification视图

        Notification notification = builder.build();
        //指定展开时的视图 7.0无展开效果 使用builder.setCustomContentView(remoteViews);
        //notification.bigContentView = remoteViews;
        notificationManager.notify(1, notification);
    }
    /**悬挂通知*/
    public void hangNotification(View v) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://book.520wcf.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.foldleft);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");
        //设置点击跳转
        Intent hangIntent = new Intent();
        //hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //hangIntent.setClass(this, MainActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);
        notificationManager.notify(2, builder.build());
    }
}
