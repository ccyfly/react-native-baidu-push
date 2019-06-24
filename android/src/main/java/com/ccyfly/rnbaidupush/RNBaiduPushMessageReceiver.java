package com.ccyfly.rnbaidupush;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.facebook.react.bridge.ReactApplicationContext;

import java.util.List;

public class RNBaiduPushMessageReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int errorCode, String appid,
                       String userId, String channelId, String requestId) {
        Intent intent = new Intent(context.getPackageName() + ".RNBaiduPushNotificationRegisteredToken");
        intent.putExtra("userId", userId);
        intent.putExtra("channelId", channelId);
        context.sendBroadcast(intent);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String message, String customContentString) {

    }

    @Override
    public void onNotificationClicked(Context context, String title, String description, String customContentString) {
        Log.d("百度推送", "收到通知消息");
        //发送通知

        Boolean isForeground = !isAppIsInBackground(context);

        final Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", description);
        if (customContentString != null) bundle.putString("extraData", customContentString);
//            BGBaiDuPushModule.myPush.sendMsg(title,description,customContentString,BGBaiDuPushModule.DidReceiveMessage);
        bundle.putBoolean("foreground", isForeground);
        bundle.putBoolean("userInteraction", true);
        RNBaiduPushNotificationJsDelivery jsDelivery = new RNBaiduPushNotificationJsDelivery((ReactApplicationContext)context);
        jsDelivery.notifyNotification(bundle);
    }

    @Override
    public void onNotificationArrived(Context context, String title, String description, String customContentString) {
        Log.d("百度推送", "收到通知消息");
        //发送通知

        Boolean isForeground = !isAppIsInBackground(context);

        final Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("message", description);
        if (customContentString != null) bundle.putString("extraData", customContentString);
//            BGBaiDuPushModule.myPush.sendMsg(title,description,customContentString,BGBaiDuPushModule.DidReceiveMessage);
        bundle.putBoolean("foreground", isForeground);
        bundle.putBoolean("userInteraction", false);
        RNBaiduPushNotificationJsDelivery jsDelivery = new RNBaiduPushNotificationJsDelivery((ReactApplicationContext)context);
        jsDelivery.notifyNotification(bundle);
    }

    //判断是否在后台
    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                //前台程序
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }
}
