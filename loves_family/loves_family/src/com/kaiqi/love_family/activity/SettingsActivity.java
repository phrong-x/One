package com.kaiqi.love_family.activity;

import com.kaiqi.love_family.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class SettingsActivity extends Activity implements
		OnCheckedChangeListener {

	private LinearLayout mLinOne;
	private LinearLayout mLinTwo;
	ImageView imgSys, imgPower, mImgDiscuss, mImgTipSwitch, mImgTime;

	private ToggleButton mTbSysSwitch, mTbPowerSwitch, mTbPushMessage,
			mTbDiscuss, mTbTipSwitch, mTbTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.settings);

		findControls();

		mTbSysSwitch.setOnCheckedChangeListener(this);// 系统消息的开关点击事件
		mTbPowerSwitch.setOnCheckedChangeListener(this);// 省流量模式开关点击事件
		mTbPushMessage.setOnCheckedChangeListener(this);// 点击推送信息显示下来linear控件
		mTbDiscuss.setOnCheckedChangeListener(this);// 讨论消息开关点击事件
		mTbTipSwitch.setOnCheckedChangeListener(this);// 提示方式震动、铃声点击事件。
		mTbTime.setOnCheckedChangeListener(this);

	}

	/**
	 * 找到控件
	 */
	public void findControls() {
		mLinOne = (LinearLayout) findViewById(R.id.linOne);
		mLinTwo = (LinearLayout) findViewById(R.id.linTwo);
		mTbPushMessage = (ToggleButton) findViewById(R.id.tbPushMessage);
		mTbSysSwitch = (ToggleButton) findViewById(R.id.tbSysSwitch);
		mTbPowerSwitch = (ToggleButton) findViewById(R.id.tbPowerSwitch);
		mTbDiscuss = (ToggleButton) findViewById(R.id.tbDiscuss);
		mTbTipSwitch = (ToggleButton) findViewById(R.id.tbTipSwitch);
		mTbTime = (ToggleButton) findViewById(R.id.tbTime);
		imgSys = (ImageView) findViewById(R.id.imgSys);
		imgPower = (ImageView) findViewById(R.id.imgPower);
		mImgDiscuss = (ImageView) findViewById(R.id.imgDiscuss);
		mImgTipSwitch = (ImageView) findViewById(R.id.imgTipSwitch);
		mImgTime = (ImageView) findViewById(R.id.imgTime);
	}

	@Override
	public void onCheckedChanged(CompoundButton v, boolean isChecked) {
		switch (v.getId()) {
		case R.id.tbPushMessage:
			mTbPushMessage.setChecked(isChecked);
			// 当点击推送消息时显示里面的item，再次点击时隐藏。
			mLinOne.setVisibility(isChecked ? View.VISIBLE : View.GONE);
			break;

		case R.id.tbSysSwitch:
			mTbSysSwitch.setChecked(isChecked);
			imgSys.setImageResource(isChecked ? R.drawable.off_sys_ms
					: R.drawable.on_sys_ms);
			break;

		case R.id.tbPowerSwitch:
			mTbPowerSwitch.setChecked(isChecked);
			imgPower.setImageResource(isChecked ? R.drawable.off_sys_ms
					: R.drawable.on_sys_ms);
			break;
		case R.id.tbDiscuss:
			mTbDiscuss.setChecked(isChecked);
			mImgDiscuss.setImageResource(isChecked ? R.drawable.off_sys_ms
					: R.drawable.on_sys_ms);
			break;
		case R.id.tbTipSwitch:
			mTbTipSwitch.setChecked(isChecked);
			mImgTipSwitch.setImageResource(isChecked ? R.drawable.ringing_on
					: R.drawable.ringing_off);
			break;
		case R.id.tbTime:
			mTbTime.setChecked(isChecked);
			mImgTime.setImageResource(isChecked ? R.drawable.day_on
					: R.drawable.day_off);
			break;
		}
	}
}
