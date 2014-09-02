package com.kaiqi.love_family.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.adapter.GuidanceAdapter;

/**
 * 导航界面：用户首次进入，为用户一个视觉上的导航，初步了解APP的大概功能等。
 * 
 * @author 李鑫 Tel：18290545819
 * 
 */
public class GuidanceActivity extends BaseActivity implements
		OnCheckedChangeListener, OnPageChangeListener {
	GuidanceAdapter adapter;
	ViewPager pager;
	List<Integer> list;
	RadioGroup rg;
	RadioButton rb_1, rb_2, rb_3, rb_4;
	int imageId[] = { R.drawable.guide1, R.drawable.guide2, R.drawable.guide3,
			R.drawable.guide4 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guidance);
		initView();
		adapter.change(getList());
		adapter.notifyDataSetChanged();
		pager.setAdapter(adapter);
		setListener();
	}

	public void setListener() {
		rg.setOnCheckedChangeListener(this);
		pager.setOnPageChangeListener(this);
	}

	public void initView() {
		pager = (ViewPager) findViewById(R.id.pager);
		rg = (RadioGroup) findViewById(R.id.guide_rg);
		rb_1 = (RadioButton) findViewById(R.id.guide_1);
		rb_2 = (RadioButton) findViewById(R.id.guide_2);
		rb_3 = (RadioButton) findViewById(R.id.guide_3);
		rb_4 = (RadioButton) findViewById(R.id.guide_4);
		adapter = new GuidanceAdapter(this);// 构造器。
	}

	private List<Integer> getList() {
		list = new ArrayList<Integer>();
		list.add(0, imageId[0]);
		list.add(1, imageId[1]);
		list.add(2, imageId[2]);
		list.add(3, imageId[3]);
		return list;
	}

	@Override
	public void onCheckedChanged(RadioGroup rg, int checkedId) {
		switch (checkedId) {
		case R.id.guide_1:
			pager.setCurrentItem(0);
			break;
		case R.id.guide_2:
			pager.setCurrentItem(1);
			break;
		case R.id.guide_3:
			pager.setCurrentItem(2);
			break;
		case R.id.guide_4:
			pager.setCurrentItem(3);
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case 0:
			rb_1.setChecked(true);
			break;
		case 1:
			rb_2.setChecked(true);
			break;
		case 2:
			rb_3.setChecked(true);
			break;
		case 3:
			rb_4.setChecked(true);
			newThread();
			Intent intent = new Intent(GuidanceActivity.this,
					IndexActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	public void newThread() {
		new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			};
		}.start();
	}
}
