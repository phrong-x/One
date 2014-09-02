package com.kaiqi.love_family.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.kaiqi.love_family.R;

public class BrowsingHistory extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.browsing_history);

		ListView list_history = (ListView) findViewById(R.id.list_history);
		list_history.setAdapter(adapter);
	}

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			view = LayoutInflater.from(BrowsingHistory.this).inflate(
					R.layout.history_item, null);
			return view;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 100;
		}
	};
}
