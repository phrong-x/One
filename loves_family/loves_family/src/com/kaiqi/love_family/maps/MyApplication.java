package com.kaiqi.love_family.maps;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.kaiqi.love_family.view.image.ImageCache;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

public class MyApplication extends Application {

	// herper open
	private ImageCache mImageCache;
	private ExecutorService mExecutorService;
	private final int CORE_POOL_SIZE = 5;
	private ArrayList<WeakReference<OnLowMemoryListener>> mLowMemoryListeners;
	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = null;
	public TextView locationaddr;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		SDKInitializer.initialize(this);
		mLowMemoryListeners = new ArrayList<WeakReference<OnLowMemoryListener>>();
		mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
		myListener = new MyLocationListener();
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
	}

	public SQLiteDatabase getDB() {
		return null;
	}

	// ///////////////AsynImageView ʹ��////////////////////////////////
	public interface OnLowMemoryListener {

		public void onLowMemoryReceived();
	}

	public ImageCache getImageCache() {
		if (mImageCache == null) {
			mImageCache = new ImageCache(this);
		}
		return mImageCache;
	}

	private final ThreadFactory sThreadFactory = new ThreadFactory() {

		private final AtomicInteger mCount = new AtomicInteger(1);

		public Thread newThread(Runnable r) {
			return new Thread(r, "GreenDroid thread #"
					+ mCount.getAndIncrement());
		}
	};

	public ExecutorService getExecutor() {
		if (mExecutorService == null) {
			mExecutorService = Executors.newFixedThreadPool(CORE_POOL_SIZE,
					sThreadFactory);
		}
		return mExecutorService;
	}

	public void registerOnLowMemoryListener(OnLowMemoryListener listener) {
		if (listener != null) {
			mLowMemoryListeners.add(new WeakReference<OnLowMemoryListener>(
					listener));
		}
	}

	/**
	 * Remove a previously registered listener
	 * 
	 * @param listener
	 *            The listener to unregister
	 * @see OnLowMemoryListener
	 */
	public void unregisterOnLowMemoryListener(OnLowMemoryListener listener) {
		if (listener != null) {
			int i = 0;
			while (i < mLowMemoryListeners.size()) {
				final OnLowMemoryListener l = mLowMemoryListeners.get(i).get();
				if (l == null || l == listener) {
					mLowMemoryListeners.remove(i);
				} else {
					i++;
				}
			}
		}
	}

	// ///////////////AsynImageView end////////////////////////////////
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return;
			StringBuilder sb=new StringBuilder(256);
			sb.append(location.getProvince());
			sb.append(location.getCity());
			sb.append(location.getDistrict());
			if (locationaddr != null)
				locationaddr.setText(location.getAddrStr());
		}


	}
}
