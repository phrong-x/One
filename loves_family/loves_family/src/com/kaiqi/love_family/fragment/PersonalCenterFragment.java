package com.kaiqi.love_family.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.activity.BrowsingHistory;
import com.kaiqi.love_family.activity.Call_recording;
import com.kaiqi.love_family.activity.Evaluation;
import com.kaiqi.love_family.activity.Help;
import com.kaiqi.love_family.activity.NoticeActivity;
import com.kaiqi.love_family.activity.SettingsActivity;

public class PersonalCenterFragment extends Fragment implements
		OnClickListener, OnItemClickListener {
	private View mView;
	private ListView mListPersonalCenter;
	private ImageButton mImgBtnSettings;
	private Intent mIntent;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.activity_personal_center, container,
				false);

		findControls();
		onClick();

		String[] strPersonalItem = getResources().getStringArray(
				R.array.personal_center_listing);
		mListPersonalCenter.setAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.list_personal_content, R.id.txt_personal_item,
				strPersonalItem));
		return mView;

	}

	/**
	 * 找到控件findViewById
	 */
	public void findControls() {
		mListPersonalCenter = (ListView) mView
				.findViewById(R.id.listPersonalCenter);
		mImgBtnSettings = (ImageButton) mView.findViewById(R.id.imgBtnSettings);
	}

	/**
	 * 设置button点击事件
	 */
	public void onClick() {
		mImgBtnSettings.setOnClickListener(this);
		mListPersonalCenter.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgBtnSettings:
			mIntent = new Intent(getActivity(), SettingsActivity.class);
			startActivity(mIntent);
			break;

		default:
			break;
		}

	}

	/**
	 * 设置item点击事件
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		// 点击我的收藏
		case 0:
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction
					.replace(R.id.fragment_content, new CollectionFragment());
			transaction.commit();
			break;
		// 点击我的发布
		case 1:
			break;
		// 点击通知
		case 2:
			mIntent = new Intent(getActivity(), NoticeActivity.class);
			startActivity(mIntent);
			break;
		// 点击我的帮帮
		case 3:
			mIntent = new Intent(getActivity(), Help.class);
			startActivity(mIntent);
			break;
		// 点击拨打记录
		case 4:
			mIntent = new Intent(getActivity(), Call_recording.class);
			startActivity(mIntent);
			break;
		// 我的评价
		case 5:
			mIntent = new Intent(getActivity(), Evaluation.class);
			startActivity(mIntent);
			break;
		// 点击浏览历史
		case 6:
			mIntent = new Intent(getActivity(), BrowsingHistory.class);
			startActivity(mIntent);
			break;
		}

	}
}
