package com.kaiqi.love_family.fragment;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.activity.List_Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class BianMinFragment extends Fragment implements OnClickListener {
	Context context;
	LayoutInflater inflater;
	View view;
	ImageView iv_01, iv_02, iv_03, iv_04, iv_11, iv_12, iv_13, iv_14, iv_21,
			iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34, iv_41, iv_42,
			iv_43, iv_44, iv_51, iv_52, iv_53, iv_54, iv_61, iv_62, iv_63,
			iv_64;
	int screenWidth, screenHeigh;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_bianmin, container, false);
		getScreen();
		setInitViewListener();
		return view;
	}

	/**
	 * 获取设备的屏幕大小。
	 */
	public void getScreen() {
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;
	}

	public void setInitViewListener() {
		iv_01 = (ImageView) view.findViewById(R.id.iv_01);
		iv_02 = (ImageView) view.findViewById(R.id.iv_02);
		iv_03 = (ImageView) view.findViewById(R.id.iv_03);
		iv_04 = (ImageView) view.findViewById(R.id.iv_04);
		iv_11 = (ImageView) view.findViewById(R.id.iv_11);
		iv_12 = (ImageView) view.findViewById(R.id.iv_12);
		iv_13 = (ImageView) view.findViewById(R.id.iv_13);
		iv_14 = (ImageView) view.findViewById(R.id.iv_14);
		iv_21 = (ImageView) view.findViewById(R.id.iv_21);
		iv_22 = (ImageView) view.findViewById(R.id.iv_22);
		iv_23 = (ImageView) view.findViewById(R.id.iv_23);
		iv_24 = (ImageView) view.findViewById(R.id.iv_24);
		iv_31 = (ImageView) view.findViewById(R.id.iv_31);
		iv_32 = (ImageView) view.findViewById(R.id.iv_32);
		iv_33 = (ImageView) view.findViewById(R.id.iv_33);
		iv_34 = (ImageView) view.findViewById(R.id.iv_34);
		iv_41 = (ImageView) view.findViewById(R.id.iv_41);
		iv_42 = (ImageView) view.findViewById(R.id.iv_42);
		iv_43 = (ImageView) view.findViewById(R.id.iv_43);
		iv_44 = (ImageView) view.findViewById(R.id.iv_44);
		iv_51 = (ImageView) view.findViewById(R.id.iv_51);
		iv_52 = (ImageView) view.findViewById(R.id.iv_52);
		iv_53 = (ImageView) view.findViewById(R.id.iv_53);
		iv_54 = (ImageView) view.findViewById(R.id.iv_54);
		iv_61 = (ImageView) view.findViewById(R.id.iv_61);
		iv_62 = (ImageView) view.findViewById(R.id.iv_62);
		iv_63 = (ImageView) view.findViewById(R.id.iv_63);
		iv_64 = (ImageView) view.findViewById(R.id.iv_64);
		iv_01.setOnClickListener(this);
		iv_02.setOnClickListener(this);
		iv_03.setOnClickListener(this);
		iv_04.setOnClickListener(this);
		iv_11.setOnClickListener(this);
		iv_12.setOnClickListener(this);
		iv_13.setOnClickListener(this);
		iv_14.setOnClickListener(this);
		iv_21.setOnClickListener(this);
		iv_22.setOnClickListener(this);
		iv_23.setOnClickListener(this);
		iv_24.setOnClickListener(this);
		iv_31.setOnClickListener(this);
		iv_32.setOnClickListener(this);
		iv_33.setOnClickListener(this);
		iv_34.setOnClickListener(this);
		iv_41.setOnClickListener(this);
		iv_42.setOnClickListener(this);
		iv_43.setOnClickListener(this);
		iv_44.setOnClickListener(this);
		iv_51.setOnClickListener(this);
		iv_52.setOnClickListener(this);
		iv_53.setOnClickListener(this);
		iv_54.setOnClickListener(this);
		iv_61.setOnClickListener(this);
		iv_62.setOnClickListener(this);
		iv_63.setOnClickListener(this);
		iv_64.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		
		case R.id.iv_01:
			Intent intent = new Intent();
			intent.setClass(context, List_Activity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
