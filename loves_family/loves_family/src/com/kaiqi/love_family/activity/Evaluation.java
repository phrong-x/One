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

public class Evaluation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.evaluation);

		ListView list_evaluation = (ListView) findViewById(R.id.list_evaluation);
		list_evaluation.setAdapter(adapter);

	}

	BaseAdapter adapter = new BaseAdapter() {

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			view = LayoutInflater.from(Evaluation.this).inflate(
					R.layout.item_evaluation, null);
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
