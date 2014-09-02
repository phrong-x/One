package com.kaiqi.love_family.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.kaiqi.love_family.R;

public class CollectionFragment extends Fragment {

	private ListView listCollectItem;
	LayoutInflater inflater;
	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			android.os.Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.collection, container, false);
		listCollectItem = (ListView) view.findViewById(R.id.listCollectItem);
		// 为列表添加数据。
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View v, ViewGroup arg2) {
				v = LayoutInflater.from(context).inflate(
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
				return 20;
			}
		};
		listCollectItem.setAdapter(adapter);
		return view;
	};

}
