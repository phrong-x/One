package com.kaiqi.love_family.activity;

import com.kaiqi.love_family.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ClassifyActivity extends BaseActivity implements OnClickListener {
	ImageButton ib_back;
	ImageView iv_01, iv_02, iv_03, iv_04, iv_11, iv_12, iv_13, iv_14, iv_21,
			iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34, iv_41, iv_42,
			iv_43, iv_44, iv_51, iv_52, iv_53, iv_54, iv_61, iv_62, iv_63,
			iv_64;
	int screenWidth, screenHeigh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classify);
		getScreen();
		setInitViewListener();
		setPicture();
	}

	public void setInitViewListener() {
		ib_back = (ImageButton) findViewById(R.id.classify_back);
		iv_01 = (ImageView) findViewById(R.id.iv_01);
		iv_02 = (ImageView) findViewById(R.id.iv_02);
		iv_03 = (ImageView) findViewById(R.id.iv_03);
		iv_04 = (ImageView) findViewById(R.id.iv_04);
		iv_11 = (ImageView) findViewById(R.id.iv_11);
		iv_12 = (ImageView) findViewById(R.id.iv_12);
		iv_13 = (ImageView) findViewById(R.id.iv_13);
		iv_14 = (ImageView) findViewById(R.id.iv_14);
		iv_21 = (ImageView) findViewById(R.id.iv_21);
		iv_22 = (ImageView) findViewById(R.id.iv_22);
		iv_23 = (ImageView) findViewById(R.id.iv_23);
		iv_24 = (ImageView) findViewById(R.id.iv_24);
		iv_31 = (ImageView) findViewById(R.id.iv_31);
		iv_32 = (ImageView) findViewById(R.id.iv_32);
		iv_33 = (ImageView) findViewById(R.id.iv_33);
		iv_34 = (ImageView) findViewById(R.id.iv_34);
		iv_41 = (ImageView) findViewById(R.id.iv_41);
		iv_42 = (ImageView) findViewById(R.id.iv_42);
		iv_43 = (ImageView) findViewById(R.id.iv_43);
		iv_44 = (ImageView) findViewById(R.id.iv_44);
		iv_51 = (ImageView) findViewById(R.id.iv_51);
		iv_52 = (ImageView) findViewById(R.id.iv_52);
		iv_53 = (ImageView) findViewById(R.id.iv_53);
		iv_54 = (ImageView) findViewById(R.id.iv_54);
		iv_61 = (ImageView) findViewById(R.id.iv_61);
		iv_62 = (ImageView) findViewById(R.id.iv_62);
		iv_63 = (ImageView) findViewById(R.id.iv_63);
		iv_64 = (ImageView) findViewById(R.id.iv_64);
		ib_back.setOnClickListener(this);
		iv_01.setOnClickListener(this);
	}

	/**
	 * 获取设备的屏幕大小。
	 */
	public void getScreen() {
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;
	}

	/**
	 * 设置控件在手机中的大小。
	 * 
	 * @param iv
	 */
	private void setImagePicture(View iv) {
		LayoutParams lp;
		lp = iv.getLayoutParams();
		lp.height = screenWidth / 4 - 25;
		lp.width = screenWidth / 4 - 10;
		iv.setLayoutParams(lp);
	}

	/**
	 * 设置图标的大小。
	 */
	private void setPicture() {
		setImagePicture(iv_01);
		setImagePicture(iv_02);
		setImagePicture(iv_03);
		setImagePicture(iv_04);
		setImagePicture(iv_11);
		setImagePicture(iv_12);
		setImagePicture(iv_13);
		setImagePicture(iv_14);
		setImagePicture(iv_21);
		setImagePicture(iv_22);
		setImagePicture(iv_23);
		setImagePicture(iv_24);
		setImagePicture(iv_31);
		setImagePicture(iv_32);
		setImagePicture(iv_33);
		setImagePicture(iv_34);
		setImagePicture(iv_31);
		setImagePicture(iv_32);
		setImagePicture(iv_33);
		setImagePicture(iv_34);
		setImagePicture(iv_41);
		setImagePicture(iv_42);
		setImagePicture(iv_43);
		setImagePicture(iv_44);
		setImagePicture(iv_51);
		setImagePicture(iv_52);
		setImagePicture(iv_53);
		setImagePicture(iv_54);
		setImagePicture(iv_61);
		setImagePicture(iv_62);
		setImagePicture(iv_63);
		setImagePicture(iv_64);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.classify_back:
			ClassifyActivity.this.finish();
			break;
		case R.id.iv_01:
			Intent intent = new Intent();
			intent.setClass(this, List_Activity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
