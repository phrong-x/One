package com.kaiqi.love_family.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiqi.love_family.R;

/**
 * 公司详情。包括公司名字、介绍、服务、类别、营业区域、联系方式（人）、公司详细地址。 可以直接拨打公司对外提供的电话，也可以直接定位公司所在的位置。
 * 
 * @author 李鑫 18290545819
 * 
 */
@SuppressLint("NewApi")
public class CompanyActivity extends BaseActivity implements OnClickListener {
	Button btn_more_1, btn_more_2, btn_more_3;
	ImageButton ib_back, tel, map;
	TextView company_name, tv_us, tv_service, tv_category, tv_tel, tv_address,
			tv_area;
	boolean checked = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_company);
		initViewListener();
		btn_more_1.setBackgroundResource(R.drawable.down_more);
		btn_more_2.setBackgroundResource(R.drawable.down_more);
		btn_more_3.setBackgroundResource(R.drawable.down_more);
		String sb = "1、如果直接在XML文件中写入反斜杠可以换行。"
				+ "\n2、在Java文件中用textViewObj.setText(内容+反斜杠+内容)也可以换行。"
				+ "\n3、将数据封装到模型类后，在Java文件中使用textViewObj.setText(obj.getXXX())。"
				+ "\n4、第3条无论是模拟器中，还是真机中，均可换行。";
		tv_service.setText(sb);
	}

	/**
	 * 初始化控件，并对控件绑定监听。
	 */
	public void initViewListener() {
		ib_back = (ImageButton) findViewById(R.id.btn_back);
		tel = (ImageButton) findViewById(R.id.telephing);
		map = (ImageButton) findViewById(R.id.map);
		btn_more_1 = (Button) findViewById(R.id.more_1);
		btn_more_2 = (Button) findViewById(R.id.more_2);
		btn_more_3 = (Button) findViewById(R.id.more_3);
		tv_area = (TextView) findViewById(R.id.contentOfarea);
		company_name = (TextView) findViewById(R.id.companyname);
		tv_service = (TextView) findViewById(R.id.contentOfservice);
		tv_us = (TextView) findViewById(R.id.contentOfus);
		tv_category = (TextView) findViewById(R.id.service_category);
		tv_tel = (TextView) findViewById(R.id.leader_number);
		tv_address = (TextView) findViewById(R.id.leader_address);
		ib_back.setOnClickListener(this);
		tel.setOnClickListener(this);
		map.setOnClickListener(this);
		btn_more_1.setOnClickListener(this);
		btn_more_2.setOnClickListener(this);
		btn_more_3.setOnClickListener(this);
	}

	/**
	 * 处理监听事件.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	@Override
	public void onClick(View arg0) {
		int resid = arg0.getId();

		switch (resid) {
		case R.id.btn_back:
			//返回，结束当前界面。
			CompanyActivity.this.finish();
			break;
		case R.id.telephing:
			//跳转到拨打电话界面，拨打公司对外提供的电话。
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_CALL);
			intent.setData(Uri.parse("tel:" + tv_tel.getText().toString())); // 获取公司的电话号码。
			this.startActivity(intent);
			break;
		case R.id.map:
			//定位公司所在的位置。
			String companyname = company_name.getText().toString();
			String address = tv_address.getText().toString();
			Log.i("result", companyname + address);
			Intent intent2 = new Intent();
			intent2.setClass(CompanyActivity.this, GeoCoderActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("address", address);
			bundle.putString("companyname", companyname);
			intent2.putExtras(bundle);
			CompanyActivity.this.startActivity(intent2);
			break;
		case R.id.more_1:
			//处理公司内容显示问题。
			if (checked) {
				btn_more_1.setBackgroundResource(R.drawable.up_more);
				if (tv_us.getLineCount() <= 5) {
					setShow2(tv_us);
					Toast.makeText(CompanyActivity.this, "已经完全显示",
							Toast.LENGTH_SHORT).show();
				} else {
					setShow(tv_us);
				}

			} else {
				setShow2(tv_us);
				btn_more_1.setBackgroundResource(R.drawable.down_more);
			}
			break;
		case R.id.more_2:
			//处理服务内容显示问题。
			if (checked) {
				btn_more_2.setBackgroundResource(R.drawable.up_more);
				if (tv_service.getLineCount() <= 5) {
					setShow2(tv_service);
					Toast.makeText(CompanyActivity.this, "已经完全显示",
							Toast.LENGTH_SHORT).show();
				} else {
					setShow(tv_service);
				}

			} else {
				btn_more_2.setBackgroundResource(R.drawable.down_more);
				setShow2(tv_service);
			}
			break;
		case R.id.more_3:
			//处理服务类别显示问题。
			if (checked) {
				btn_more_3.setBackgroundResource(R.drawable.up_more);
				if (tv_us.getLineCount() <= 5) {
					setShow2(tv_category);
					Toast.makeText(CompanyActivity.this, "已经完全显示",
							Toast.LENGTH_SHORT).show();
				} else {
					setShow(tv_category);
				}

			} else {
				btn_more_3.setBackgroundResource(R.drawable.down_more);
				setShow2(tv_category);
			}
			break;
		default:
			break;
		}
		checked = !checked;
	}

	public void setShow(View v) {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		v.setLayoutParams(lp);
	}

	public void setShow2(View v) {
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 110);
		v.setLayoutParams(lp);
	}
}
