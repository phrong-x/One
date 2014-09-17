package com.kaiqi.love_family.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.fragment.BianMinFragment;
import com.kaiqi.love_family.fragment.HomePageFragment;
import com.kaiqi.love_family.fragment.PersonalCenterFragment;
import com.kaiqi.love_family.fragment.PublishFragment;

/**
 * 主要界面，导航。
 * 
 * @author 李鑫 18290545819
 * 
 */
public class IndexActivity extends FragmentActivity {
	LinearLayout linear;
	RadioGroup rg;
	RadioButton rb_1;
	HomePageFragment homepage;
	PublishFragment publish;
	BianMinFragment bianmin;
	PersonalCenterFragment personal;
	FragmentTransaction transaction;
	int screenWidth, screenHeigh;
	LayoutParams lp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_index);
		getScreen();
		initview();
		setRadioGroupSize();
		accessInterface();

	}

	public void setRadioGroupSize() {
		lp = rg.getLayoutParams();
		lp.height = screenHeigh / 10;
		lp.width = screenWidth;
		rg.setLayoutParams(lp);
	}

	/**
	 * 初始界面；
	 */

	public void accessInterface() {
		transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.fragment_content, homepage);
		rb_1.setChecked(true);
	}

	/**
	 * 初始化控件,并为部分控件绑定监听；
	 */
	public void initview() {
		linear = (LinearLayout) findViewById(R.id.fragment_content);
		rg = (RadioGroup) findViewById(R.id.index_rg);
		rg.setOnCheckedChangeListener(listener);
		
		rb_1 = (RadioButton) findViewById(R.id.index_rb_1);
		homepage = new HomePageFragment();
		publish=new PublishFragment();
		bianmin=new BianMinFragment();
		personal=new PersonalCenterFragment();
	}


	OnCheckedChangeListener listener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup rg, int checkedId) {
			transaction = getSupportFragmentManager().beginTransaction();
			switch (checkedId) {
			case R.id.index_rb_1:
				transaction.replace(R.id.fragment_content, homepage).commit();
				break;
			case R.id.index_rb_2:
				transaction.replace(R.id.fragment_content, bianmin).commit();
				break;
			case R.id.index_rb_3:
				transaction.replace(R.id.fragment_content, publish).commit();
				break;
			case R.id.index_rb_4:
				transaction.replace(R.id.fragment_content, personal).commit();
				break;
			}
		}
	};
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
