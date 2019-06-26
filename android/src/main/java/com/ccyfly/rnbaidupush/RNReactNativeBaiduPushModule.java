
package com.ccyfly.rnbaidupush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.util.Log;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;

public class RNReactNativeBaiduPushModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  static public String DidReceiveMessage = "DidReceiveMessage";
  static public String DidOpenMessage = "DidOpenMessage";
  static public String channelId = "";
  static public String userId = "";
  static public String BaiduNotificationsRegistered = "baiduNotificationsRegistered";
  static public String BaiduNotificationReceived = "baiduNotificationReceived";

  private RNBaiduPushNotificationJsDelivery mJsDelivery;

  public RNReactNativeBaiduPushModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    mJsDelivery = new RNBaiduPushNotificationJsDelivery(reactContext);
    registerNotificationsRegistration();
  }

  @Override
  public String getName() {
    return "RNReactNativeBaiduPushModule";
  }

  private void registerNotificationsRegistration() {
    IntentFilter intentFilter = new IntentFilter(
        getReactApplicationContext().getPackageName() + ".RNBaiduPushNotificationRegisteredToken");

    getReactApplicationContext().registerReceiver(new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        String userId = intent.getStringExtra("userId");
        String channelId = intent.getStringExtra("channelId");
        WritableMap params = Arguments.createMap();
        params.putString("userId", userId);
        params.putString("channelId", channelId);

        mJsDelivery.sendEvent("baiduNotificationsRegistered", params);
      }
    }, intentFilter);
  }

  @ReactMethod
  public void testPrint(String message) {
    Log.d("RNReactNativeBaiduPushModule", message);
  }
}