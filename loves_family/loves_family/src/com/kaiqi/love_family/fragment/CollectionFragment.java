package com.kaiqi.love_family.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.activity.Call_recording;

public class CollectionFragment extends Activity {

	LayoutInflater inflater;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.collection);

		ListView listCollectItem = (ListView) findViewById(R.id.listCollectItem);
		listCollectItem.setAdapter(adapter);
	}

	// 为列表添加数据。
	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int arg0, View v, ViewGroup arg2) {
			v = LayoutInflater.from(CollectionFragment.this).inflate(
					R.layout.list_collection, null);
			return v;
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public int getCount() {
			return 20000;
		}
	};

}
