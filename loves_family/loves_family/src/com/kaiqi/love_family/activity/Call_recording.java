package com.kaiqi.love_family.activity;

import com.kaiqi.love_family.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class Call_recording extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.call_recording);

		ListView list_calls = (ListView) findViewById(R.id.list_calls);
		list_calls.setAdapter(adapter);
	}

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			view = LayoutInflater.from(Call_recording.this).inflate(
					R.layout.list_call_recording, null);
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
			return 300;
		}
	};

}
