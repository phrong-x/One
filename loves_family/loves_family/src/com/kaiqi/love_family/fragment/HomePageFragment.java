package com.kaiqi.love_family.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.ksoap2.serialization.SoapObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.kaiqi.love_family.R;
import com.kaiqi.love_family.activity.ClassifyActivity;
import com.kaiqi.love_family.activity.CompanyActivity;
import com.kaiqi.love_family.activity.List_Activity;
import com.kaiqi.love_family.adapter.AdvAdapter;
import com.kaiqi.love_family.adapter.ListView_HomePager_Adapter;
import com.kaiqi.love_family.comment.Config;
import com.kaiqi.love_family.comment.ListView_Header;
import com.kaiqi.love_family.comment.WebServiceUtils;
import com.kaiqi.love_family.maps.MyApplication;
import com.kaiqi.love_family.view.dialog.Effectstype;
import com.kaiqi.love_family.view.dialog.NiftyDialogBuilder;

@SuppressWarnings("unused")
public class HomePageFragment extends Fragment implements OnClickListener,
		OnItemClickListener {
	Context context;
	View view;
	int screenWidth, screenHeigh;// 手机屏的尺寸。
	/**
	 * 广告位声明。
	 */
	private List<View> advPics;// 这里存放的是四张广告背景
	private ImageView[] imageViews = null;// 按钮集合；
	private ImageView imageView = null;
	private ViewPager advPager = null;
	private ViewGroup group = null;
	private AtomicInteger what = new AtomicInteger(0);// 线程安全递增。
	private boolean isContinue = true;
	/**
	 * 
	 */
	private String WEB_SERVICE_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
	// 命名空间
	private String NAMESPACE = "http://WebXml.com.cn/";

	/**
	 * 其他。
	 */
	Button more;
	EditText search;
	ImageView image_del, image_01, image_02, image_03, image_04, image_11,
			image_12, image_13, image_14, image_stars_1, image_stars_2,
			image_stars_3;
	ImageButton location;
	TextView tv_location, tv_weather;
	LinearLayout ll;
	ScrollView scroll;
	ListView lv_hot;
	ListView_HomePager_Adapter adapter;
	List<ListView_Header> list;
	List<String> property;
	private Effectstype effect;
	NiftyDialogBuilder dialogBuilder;
	private LocationClient mLocationClient;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_homepage, container, false);
		getScreen();
		initview();
		setPositionParameters();
		mLocationClient.start();// 启动定位
		String location = mLocationClient.toString();
		tv_location.setText(location);
		showWeather();
		scroll.smoothScrollTo(0, 0);
		setADViewPager();
		setPicture();
		adapter = new ListView_HomePager_Adapter(context);
		lv_hot.setAdapter(adapter);
		setListener();
		return view;
	}

	/**
	 * 初始化控件。
	 */
	public void initview() {
		more = (Button) view.findViewById(R.id.list_more);
		// search = (EditText) view.findViewById(R.id.hp_search);
		ll = (LinearLayout) view.findViewById(R.id.AD_ALL);
		image_del = (ImageView) view.findViewById(R.id.AD_del);
		tv_location = (TextView) view.findViewById(R.id.tv_location);
		tv_weather = (TextView) view.findViewById(R.id.weather);
		location = (ImageButton) view.findViewById(R.id.location);
		// 首页热门公司列表
		scroll = (ScrollView) view.findViewById(R.id.scrollview);
		lv_hot = (ListView) view.findViewById(R.id.homepager_listview);
		// 项目扩展。
		image_01 = (ImageView) view.findViewById(R.id.b01);
		image_02 = (ImageView) view.findViewById(R.id.b02);
		image_03 = (ImageView) view.findViewById(R.id.b03);
		image_04 = (ImageView) view.findViewById(R.id.b04);
		image_11 = (ImageView) view.findViewById(R.id.b11);
		image_12 = (ImageView) view.findViewById(R.id.b12);
		image_13 = (ImageView) view.findViewById(R.id.b13);
		image_14 = (ImageView) view.findViewById(R.id.b14);
		// 其他。
		list = new ArrayList<ListView_Header>();// 数据是从服务器上解析得来。
		advPager = (ViewPager) view.findViewById(R.id.adv_pager);
		group = (ViewGroup) view.findViewById(R.id.viewGroup);
		((MyApplication) context.getApplicationContext()).locationaddr = tv_location;
		mLocationClient = ((MyApplication) context.getApplicationContext()).mLocationClient;
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

	/**
	 * 初始化ViewPager的相关信息。
	 */
	private void setADViewPager() {
		addAD();
		smallPoints(group);
		setADSize();
		advPager.setAdapter(new AdvAdapter(advPics));
		advPager.setOnPageChangeListener(new GuidePageChangeListener());
		advPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					isContinue = false;
					break;
				case MotionEvent.ACTION_MOVE:
					isContinue = false;
					break;
				case MotionEvent.ACTION_UP:
					isContinue = true;
					break;
				default:
					isContinue = true;
					break;
				}
				return false;
			}
		});
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(what.get());
						whatOption();
					}
				}
			}

		}).start();
	}

	/**
	 * 设置广告的大小。
	 */
	public void setADSize() {
		LayoutParams lp;
		lp = advPager.getLayoutParams();
		lp.height = screenHeigh / 4;
		lp.width = screenWidth;
		advPager.setLayoutParams(lp);
	}

	/**
	 * 对imageviews进行填充,小点的生成。
	 * 
	 * @param group
	 */
	public void smallPoints(ViewGroup group) {
		imageViews = new ImageView[advPics.size()];
		for (int i = 0; i < advPics.size(); i++) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new LayoutParams(10, 10));// 10dp×10dp
			imageView.setPadding(5, 5, 5, 5);// 距边。
			imageViews[i] = imageView;
			// 初始显示设置。
			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.rb_1_click);

			} else {
				imageViews[i].setBackgroundResource(R.drawable.rb_1_unclick);
			}
			group.addView(imageViews[i]);
		}
	}

	/**
	 * 向广告位上添加广告集合信息。 这里存放的是四张广告背景.
	 * 
	 * @param advPics
	 */
	public void addAD() {
		advPics = new ArrayList<View>();
		ImageView img1 = new ImageView(context);
		img1.setBackgroundResource(R.drawable.test);
		advPics.add(img1);
		ImageView img2 = new ImageView(context);
		img2.setBackgroundResource(R.drawable.test1);
		advPics.add(img2);
		ImageView img3 = new ImageView(context);
		img3.setBackgroundResource(R.drawable.test2);
		advPics.add(img3);
		ImageView img4 = new ImageView(context);
		img4.setBackgroundResource(R.drawable.test3);
		advPics.add(img4);
	}

	private void whatOption() {
		what.incrementAndGet();
		if (what.get() > imageViews.length - 1) {
			what.getAndAdd(-4);
		}
		try {
			Thread.sleep(3000);// 设置图片自动切换的间隔时间
		} catch (InterruptedException e) {

		}
	}

	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			advPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	/**
	 * 内部类：实现滑动监听。
	 * 
	 * @author 李鑫 18290545819
	 * 
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			what.getAndSet(arg0);
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0].setBackgroundResource(R.drawable.rb_1_click);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.rb_1_unclick);
				}
			}
		}
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
		setImagePicture(image_01);
		setImagePicture(image_02);
		setImagePicture(image_03);
		setImagePicture(image_04);
		setImagePicture(image_11);
		setImagePicture(image_12);
		setImagePicture(image_13);
		setImagePicture(image_14);
	}

	/**
	 * 为控件添加监听事件
	 */
	private void setListener() {
		more.setOnClickListener(this);
		image_del.setOnClickListener(this);
		location.setOnClickListener(this);
		image_01.setOnClickListener(this);
		image_02.setOnClickListener(this);
		image_03.setOnClickListener(this);
		image_04.setOnClickListener(this);
		image_11.setOnClickListener(this);
		image_12.setOnClickListener(this);
		image_13.setOnClickListener(this);
		image_14.setOnClickListener(this);
		lv_hot.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent();

		int id = arg0.getId();
		switch (id) {
		case R.id.AD_del:
			showDialog();
			break;
		case R.id.location:
				
				//String city;
				/*
				 * if (location.contains("黑龙江")||location.contains("内蒙古")) {
				 * city=location.substring(0,2); } else {
				 * city=location.substring(0, 1); }
				 * tv_weather.setText(showWeather(city));
				 */
			break;
		case R.id.list_more:
			// 跳转；
			intent.setClass(context, List_Activity.class);
			startActivity(intent);
			break;
		case R.id.b01:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			intent.putExtra("result", "搬家");
			startActivityForResult(intent, Config.REQUESTCODE_01);

			break;
		case R.id.b02:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			startActivityForResult(intent, Config.REQUESTCODE_02);
			break;
		case R.id.b03:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			startActivityForResult(intent, Config.REQUESTCODE_03);
			break;
		case R.id.b04:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			startActivityForResult(intent, Config.REQUESTCODE_04);
			break;
		case R.id.b11:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			startActivityForResult(intent, Config.REQUESTCODE_11);
			break;
		case R.id.b12:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			startActivityForResult(intent, Config.REQUESTCODE_12);
			break;
		case R.id.b13:
			// 跳转到对应的界面；
			intent.setClass(context, List_Activity.class);
			startActivityForResult(intent, Config.REQUESTCODE_13);
			break;
		case R.id.b14:
			// 跳转到对应的界面；
			intent.setClass(context, ClassifyActivity.class);
			startActivityForResult(intent, Config.REQUESTCODE_14);
			break;
		default:
			break;
		}
	}

	public void setPositionParameters() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(3000);// 设置发起定位请求的间隔时间为3000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		mLocationClient.setLocOption(option);
	}

	/**
	 * 对话框show。
	 */

	public void showDialog() {
		dialogBuilder = NiftyDialogBuilder.getInstance(context);
		effect = Effectstype.RotateBottom;
		dialogBuilder.withTitle("提示：广告隐藏").withTitleColor("#ffffff")
		.withDividerColor("#636363").withMessage("亲，通过切换可以了解更多的广告哦！")
				.withMessageColor("#636363")
				.withIcon(getResources().getDrawable(R.drawable.icon))
				.isCancelableOnTouchOutside(false).withDuration(700)
				.withEffect(effect).withButton1Text("取消").withButton2Text("确定")
				.setCustomView(R.layout.custom_view, view.getContext())
				.setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						ll.setVisibility(View.GONE);
						dialogBuilder.dismiss();
					}
				}).show();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		switch (position) {
		case 0:

			break;
		case 1:

			break;
		case 2:

			break;
		case 3:

			break;
		case 4:

			break;
		default:
			break;
		}
		Intent intent = new Intent(context, CompanyActivity.class);
		startActivity(intent);
	}

	/*
	 * public void onActivityResult(int requestCode, int resultCode, Intent
	 * intent) { if (resultCode == Activity.RESULT_OK) { switch (requestCode) {
	 * case Config.REQUESTCODE_01: // property=intent.getIntExtra("property", //
	 * Config.REQUESTCODE_01); property =
	 * intent.getStringArrayListExtra("property"); break;
	 * 
	 * default: break; }
	 * 
	 * } };
	 */
	// 展现天气预报的具体数据

	private void showWeather() {
		WebServiceUtils.WEB_SERVICE_URL = this.WEB_SERVICE_URL;
		WebServiceUtils.NAMESPACE = this.NAMESPACE;
		final String methodName = "getWeatherbyCityName";
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("theCityName", "重庆");

		// 获取远程Web Service返回的对象
		WebServiceUtils.CallWebService(methodName, properties,
				new WebServiceUtils.WebServiceCallBack() {

					public void CallBack(SoapObject result) {
						if (result != null) {
							SoapObject detail = (SoapObject) result
									.getProperty(methodName + "Result");
							String sb;
							sb = detail.getProperty(6).toString().split(" ")[1];
							sb += "\u3000";
							sb += detail.getProperty(5).toString();
							tv_weather.setText(sb);
						} else {
							Toast.makeText(context, "获取WebService数据错误,请确保网路通畅！",
									Toast.LENGTH_SHORT).show();
							tv_weather.setText("加载定位数据失败，请检测网路情况！");
						}
					}
				});// 根据城市获取城市具体天气情况
					// 解析今天的天气情况
	}
}
