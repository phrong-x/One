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

public class NoticeActivity extends Activity {
	ListView mListNotice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.notice);

		mListNotice = (ListView) findViewById(R.id.listNotice);

		mListNotice.setAdapter(adapter);
	}

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(NoticeActivity.this);
			// view = LayoutInflater.from(context).inflate(R.layout.notice,
			// null);

			view = inflater.inflate(R.layout.list_notice, null);
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
