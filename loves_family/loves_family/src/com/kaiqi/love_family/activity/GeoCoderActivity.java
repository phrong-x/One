/**
 * 
 */
package com.kaiqi.love_family.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.kaiqi.love_family.R;

/**
 * 
 * @author 李鑫 18290545819
 * 
 */
public class GeoCoderActivity extends Activity implements
		OnGetGeoCoderResultListener {
	GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	BaiduMap mBaiduMap = null;
	MapView mMapView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geocoder);
		Intent intent=getIntent();
		//Bundle bundle=intent.getExtras();
		String companyname=intent.getStringExtra("companyname");
		String address=intent.getStringExtra("address");
		//String companyname=bundle.getString("companynanme");
		//String address = bundle.getString("address");
		Log.i("result", companyname+address);
		CharSequence titleLable = companyname+"的位置";
		setTitle(titleLable);

		// 地图初始化
		mMapView = (MapView) findViewById(R.id.mapofgeocoder);
		mBaiduMap = mMapView.getMap();

		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();

		mSearch.setOnGetGeoCodeResultListener(this);
		
		int index = 0;
		if (address.contains("省")) {
			index = address.indexOf("省");
		} else if (address.contains("市")) {
			index = address.indexOf("市");
		}
		mSearch.geocode(new GeoCodeOption().city(address.substring(0, index-1))
				.address(address.substring(index + 1, address.length() - 1)));
	}
	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		mSearch.destroy();
		super.onDestroy();
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(GeoCoderActivity.this, "抱歉，未能找到结果",
					Toast.LENGTH_LONG).show();
			return;
		}
		mBaiduMap.clear();
		mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.icon_marka)));
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
				.getLocation()));
	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {

	}

}
