package com.kaiqi.love_family.comment;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断是否联网 如果为True则表示当前Android手机已经联网，可能是WiFi或GPRS、HSDPA等等，具体的可以通过
 * ConnectivityManager 类的getActiveNetworkInfo() 方法判断详细的接入方式。
 * 
 * 同时要在manifest里面加个权限 :android.permission.ACCESS_NETWORK_STATE
 * 
 *<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
 * 
 * @author 李鑫 -18580544821
 * 
 */

public class Network_judge {
	public static boolean judge(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isAvailable()) {
			// 当前有可用网络
			return false;
		} else {
			// 当前无可用网络
			return true;
		}
	}
}