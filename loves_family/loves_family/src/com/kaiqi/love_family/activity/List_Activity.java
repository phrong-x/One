package com.kaiqi.love_family.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.adapter.HomePage_ListView_Adapter.ViewHolder;

public class List_Activity extends Activity implements OnItemSelectedListener,
		OnItemClickListener {
	EditText et_search;
	ImageView iv_sure;
	Button btn_1, btn_2, btn_3;
	int screenWidth, screenHeigh;
	PopupWindow pw;
	View view;
	ListView listView, lv;
	/*
	 * ListView_Header header; List<ListView_Header> list=new
	 * ArrayList<ListView_Header>(); List<String> tag; ListViewAdapter adapter;
	 */
	String[] arae = { "南岸区", "渝中区", "巴南区", "江北区", "渝北区", "北碚区", "九龙坡区", "大渡口区",
			"沙坪坝" };
	String[] classify = { "搬家", "宠物", "家政" };
	String[] sort = { "默认", "按星级排序", "认证优先" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		view = this.getLayoutInflater().inflate(R.layout.item_listview, null);
		getScreen();
		pw = new PopupWindow(view, screenWidth / 3, screenHeigh);// 创建popupwindow，并设置属性。
		initview();
		setBackGroud();
		// 为列表添加数据。
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View view, ViewGroup arg2) {
				ViewHolder holder = new ViewHolder();
				if (view == null) {
					view = LayoutInflater.from(List_Activity.this).inflate(
							R.layout.homepage_listview_header, null);
					holder.image_stars = (ImageView) view
							.findViewById(R.id.stars);
					holder.tv_name = (TextView) view
							.findViewById(R.id.company_name);
					holder.tv_content = (TextView) view
							.findViewById(R.id.company_content);
					holder.tv_property = (TextView) view
							.findViewById(R.id.working);
					holder.image_identification = (ImageButton) view
							.findViewById(R.id.logo_identification);
					holder.image_telephone = (ImageButton) view
							.findViewById(R.id.tel_phone);
					holder.image_stars.setImageResource(R.drawable.star50);
					holder.tv_name.setText("重庆汉昌文化传播有限责任公司");
					holder.tv_content
							.setText("本公司致力打造专业的程序猿，为各位屌丝成就终极梦想，我们的宗旨是没有最屌只有更屌！");
					holder.tv_property.setText("屌丝天堂");
					holder.image_identification
							.setImageResource(R.drawable.identification);
					holder.image_telephone
							.setImageResource(R.drawable.telephone_image);
					holder.image_telephone
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									Intent in2 = new Intent();
									in2.setAction(Intent.ACTION_CALL);// 指定意图动作
									in2.setData(Uri.parse("tel:10000"));// 指定电话号码
									startActivity(in2);
								}
							});
				}
				return view;
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
		// setListContent();
		/*
		 * adapter = new ListViewAdapter(List_Activity.this, list);
		 * adapter.notifyDataSetChanged();//更新。
		 */
		listView.setAdapter(adapter);
		setListener();
	}

	/*
	 * public List<ListView_Header> setListContent() { List<ListView_Header>
	 * list1 = new ArrayList<ListView_Header>(); header=new ListView_Header();
	 * list1.add(header); return list; }
	 */

	/**
	 * 为各个控件绑定监听
	 */
	public void setListener() {
		et_search.setOnClickListener(listener);
		iv_sure.setOnClickListener(listener);
		btn_1.setOnClickListener(listener);
		btn_2.setOnClickListener(listener);
		btn_3.setOnClickListener(listener);
		listView.setOnItemSelectedListener(this);
		listView.setOnItemClickListener(this);
	}

	/**
	 * 获取设备的屏幕大小。
	 */
	public void getScreen() {
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;
	}

	/**
	 * 初始化控件。
	 */
	public void initview() {
		et_search = (EditText) findViewById(R.id.list_search);
		iv_sure = (ImageView) findViewById(R.id.list_sure);
		btn_1 = (Button) findViewById(R.id.area);
		btn_2 = (Button) findViewById(R.id.classify);
		btn_3 = (Button) findViewById(R.id.sort);
		listView = (ListView) findViewById(R.id.lists);
		lv = (ListView) view.findViewById(R.id.item_pop);
	}

	public void setBackGroud() {
		listView.setFocusable(false);
		Drawable background = getResources().getDrawable(
				R.drawable.pop_bg_normal);
		pw.setBackgroundDrawable(background);
	}

	// 其他点击事件的处理。
	OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			pw.setFocusable(true);
			switch (arg0.getId()) {
			case R.id.list_search:
				et_search.setHint("");
				break;
			case R.id.list_sure:
				et_search.setText("");
				break;
			case R.id.area:
				pw.showAsDropDown(arg0);
				// setBackGroud();
				lv.setAdapter(new ArrayAdapter<String>(List_Activity.this,
						android.R.layout.simple_list_item_1, arae));
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						btn_1.setText(arae[position]);
						pw.dismiss();
					}
				});
				pw.showAtLocation(arg0, Gravity.BOTTOM, 10, 0);
				break;
			case R.id.classify:
				pw.showAsDropDown(arg0);
				// setBackGroud();
				lv.setAdapter(new ArrayAdapter<String>(List_Activity.this,
						android.R.layout.simple_list_item_1, classify));
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						btn_2.setText(classify[position]);
						pw.dismiss();
					}
				});
				pw.showAtLocation(arg0, Gravity.BOTTOM, 10 + screenWidth / 3, 0);

				break;
			case R.id.sort:
				pw.showAsDropDown(arg0);
				// setBackGroud();
				lv.setAdapter(new ArrayAdapter<String>(List_Activity.this,
						android.R.layout.simple_list_item_1, sort));
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						btn_3.setText(sort[position]);
						pw.dismiss();
					}
				});
				pw.showAtLocation(arg0, Gravity.BOTTOM,
						10 + 2 * screenWidth / 3, 0);
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onItemSelected(AdapterView<?> parent, View arg1, int arg2,
			long arg3) {
		Toast.makeText(parent.getContext(),
				parent.getItemAtPosition(arg2).toString(), Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Intent intent = new Intent(this, CompanyActivity.class);
		startActivity(intent);
	}
}
