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

public class Help extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.help);

		ListView list_help = (ListView) findViewById(R.id.list_help);
		list_help.setAdapter(adapter);
	}

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			view = LayoutInflater.from(Help.this).inflate(R.layout.list_help,
					null);
			return view;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 200;
		}
	};
}
