package com.kaiqi.love_family.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
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
import android.widget.AbsListView;
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
import com.kaiqi.love_family.view.dialog.Effectstype;
import com.kaiqi.love_family.view.dialog.NiftyDialogBuilder;

public class List_Activity extends BaseActivity implements
		OnItemSelectedListener, OnItemClickListener {
	EditText et_search;
	ImageView iv_sure;
	Button btn_1, btn_2, btn_3;
	int screenWidth, screenHeigh;
	PopupWindow pw;
	View view;
	ListView listView, lv;
	TextView tView;
	String showResult;
	NiftyDialogBuilder dialogBuilder;
	private Effectstype effect;
	ViewHolder holder = new ViewHolder();
	/*
	 * ListView_Header header; List<ListView_Header> list=new
	 * ArrayList<ListView_Header>(); List<String> tag; ListViewAdapter adapter;
	 */
	String[] arae = { "南岸区", "渝中区", "巴南区", "江北区", "渝北区", "北碚区", "九龙坡区", "大渡口区",
			"沙坪坝" };
	String[] classify = { "搬家", "宠物", "棒棒", "帮一把", "家政服务", "派出所", "房产", "棋牌娱乐",
			"快递物流", "送餐", "烟酒", "医疗卫生", "超市", "电脑宽带", "生活配送", "回收", "银行",
			"装修装潢", "维修服务", "解锁开锁", "驾校", "鲜花", "洗衣护理", "家教服务", "早教服务", "足浴休闲",
			"管道输通" };
	String[] sort = { "默认", "按星级排序", "认证优先", "按访问量排序" };

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		view = this.getLayoutInflater().inflate(R.layout.item_listview, null);
		getScreen();
		pw = new PopupWindow(view, screenWidth / 3, LayoutParams.WRAP_CONTENT);// 创建popupwindow，并设置属性。
		initview();
		setBackGroud();

		// 为列表添加数据。
		BaseAdapter adapter = new BaseAdapter() {

			@Override
			public View getView(int arg0, View view, ViewGroup arg2) {
				// ViewHolder holder = new ViewHolder();
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
					/*
					 * holder.image_stars.setImageResource(R.drawable.star50);
					 * holder.tv_name.setText("重庆汉昌文化传播有限责任公司");
					 * holder.tv_content
					 * .setText("本公司致力打造专业的程序猿，为各位屌丝成就终极梦想，我们的宗旨是没有最屌只有更屌！");
					 * holder.tv_property.setText("屌丝天堂");
					 */
					holder.image_identification
							.setImageResource(R.drawable.identification);
					holder.image_telephone
							.setImageResource(R.drawable.telephone_image);
					switch (arg0) {
					case 1:
					case 5:
					case 10:
					case 15:
						holder.image_stars.setImageResource(R.drawable.star50);
						holder.tv_name.setText("重庆北流科技责任有限公司");
						holder.tv_content
								.setText("我们主要经营“null”等产品。公司尊崇“踏实、拼搏、责任的企业精神，并以诚信、共赢、开创经营理念，"
										+ "创造良好的企业环境，以全新的管理模式，完善的技术，周到的服务，卓越的品质为生存根本，"
										+ "我们始终坚持用户至上 用心服务于客户，坚持用自己的服务去打动客户。");
						holder.tv_property.setText("不详");
						holder.image_telephone
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View view) {
										setListenerOfTel("18523333365");
									}

								});
						break;
					case 0:
					case 6:
					case 11:
					case 16:
						holder.image_stars.setImageResource(R.drawable.star45);
						holder.tv_name.setText("重庆顺喜搬家服务有限公司");
						holder.tv_content
								.setText("重庆顺喜搬家服务有限公司成立于2001年6月，是经重庆市工商局批准注册的以从事重庆搬家、设备搬迁、"
										+ "搬家搬厂为主要经营范围的服务.；重庆顺喜搬家现有员工70余人，其中管理人员7人、空调及其他拆装技术人员48名；"
										+ "重庆顺喜搬家拥有福田轻卡等各型车辆20来辆、液压叉车3台、手动液压拖车3台、打包机3台及其他搬家、清洁及空调专用工具用品若干。");
						holder.tv_property.setText("搬家");
						holder.image_telephone
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View view) {
										setListenerOfTel("023-86809222");
									}

								});

						break;
					case 2:
					case 7:
					case 12:
					case 17:
						holder.image_stars.setImageResource(R.drawable.star40);
						holder.tv_name.setText("易到用车");
						holder.tv_content
								.setText("1、提前预约，无需担心堵车迟到   ●随叫随到，不受昼夜/天气困扰   ●短信告知行程，出门即上车   ●先用车后付费，无需现金交易。"
										+ "2、车型任选，符合出行风格   ●可查行车轨迹，避免路边等待   ●专业司机，保障安全又有范儿   ●行程自由，免去停车烦恼。"
										+ "3、告知航班号和到达日期，为您合理安排时间   ●航班晚点免费等，无需额外收费   ●订乘分离，免去繁琐手续   ●专车24小时待命，随叫随到。"
										+ "4、随叫随到，说走就走   ●专业司机高档车型，出行有面儿   ●行程任意安排，无需担心拒载   ●线上支付，避免现金风险。"
										+ "5、24小时专车服务，聚会再晚也不担心   ●聚会畅饮，免去酒驾隐患   ●服务负责，送人到楼下保障安全   ●车费预估，提前知晓所需费用。");
						holder.tv_property.setText("租车");
						holder.image_telephone
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View view) {
										setListenerOfTel("400-1111-777");
									}

								});

						break;
					case 3:
					case 8:
					case 13:
					case 18:
						holder.image_stars.setImageResource(R.drawable.star45);
						holder.tv_name.setText("西南医院");
						holder.tv_content
								.setText("西南医院坐落在重庆歌乐山下的高滩岩，两山环抱、江水回绕，环境优美。"
										+ "她的前身是国民政府“中央医院”，1929年始建于南京，1941年迁至重庆，"
										+ "1951年定名为“西南医院”，现为第三军医大学第一附属医院和第一临床医学院。"
										+ "在科学发展、和谐新风沐浴下的西南医院人，锐意进取、开拓创新，医院全面建设取得了骄人的成就。"
										+ "经过近70多年的励精图治与开创发展，西南医院现已成为一所集医疗、教学、科研为一体的大型综合性“三级甲等医院”，在国内享有盛名。");
						holder.tv_property.setText("医疗卫生");
						holder.image_telephone
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View view) {
										setListenerOfTel("023-68754000");
									}

								});
						break;
					case 4:
					case 9:
					case 14:
					case 19:
						holder.image_stars.setImageResource(R.drawable.star35);
						holder.tv_name.setText("重庆市鹏源汽车驾驶培训学校");
						holder.tv_content
								.setText("鹏源驾校有着十多年的办学经验，师资力量雄厚，教练素质高车辆分配合理，不管大车小车平均4人一车 让您不用再排队练车。"
										+ "我们练车的场地位于主城边上，方便主城已经郊区的学员，考证快，不耽误您的正常上班和生活。");
						holder.tv_property.setText("驾校");
						holder.image_telephone
								.setOnClickListener(new OnClickListener() {

									@Override
									public void onClick(View view) {
										setListenerOfTel("15826105958");
									}

								});
						break;
					default:
						break;
					}
				}
				return view;
			}

			@Override
			public long getItemId(int arg0) {
				return arg0;
			}

			@Override
			public Object getItem(int arg0) {
				return arg0;
			}

			@Override
			public int getCount() {
				return 20;
			}
		};
		setListener();
		adapter.notifyDataSetChanged();
		listView.setAdapter(adapter);

	}

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

		@SuppressLint("NewApi")
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
						showResult = arae[position];
						btn_1.setText(showResult);
						pw.dismiss();
					}
				});
				pw.showAtLocation(arg0, Gravity.BOTTOM, 10, 10);
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
						showResult = classify[position];
						for (int i = 0; i < 19; i++) {
							if (holder.tv_property.getText() == showResult) {
								AbsListView.LayoutParams frParam = new AbsListView.LayoutParams(
										ViewGroup.LayoutParams.MATCH_PARENT,
										ViewGroup.LayoutParams.WRAP_CONTENT);
								arg1.setLayoutParams(frParam);
								 listView.getChildAt(i).setVisibility(View.VISIBLE);
							} else {
								AbsListView.LayoutParams frParam = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
							    arg1.setLayoutParams(frParam);
								listView.getChildAt(i).setVisibility(View.GONE);
							}
						}
						btn_2.setText(showResult);
						pw.dismiss();
					}
				});
				pw.showAtLocation(arg0, Gravity.BOTTOM, 10 + screenWidth / 3,
						10);

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
						showResult = sort[position];
						switch (position) {
						case 0:

							break;

						default:
							break;
						}
						btn_3.setText(showResult);
						pw.dismiss();
					}
				});
				pw.showAtLocation(arg0, Gravity.BOTTOM,
						10 + 2 * screenWidth / 3, 10);

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

	@SuppressLint("ResourceAsColor")
	public void setListenerOfTel(String telephoneNumber) {
		tView = new TextView(this);
		tView.setText("是否拨打该电话？");
		tView.setTextColor(R.color.divider_color);
		tView.setGravity(Gravity.CENTER);
		dialogShow(telephoneNumber);
	}

	public void dialogShow(final String telephoneNumber) {
		dialogBuilder = NiftyDialogBuilder.getInstance(this);
		effect = Effectstype.RotateBottom;
		dialogBuilder
				.withTitle("拨打电话")
				.withTitleColor("#ffffff")
				.withDividerColor("#000000")
				.withMessage("准备拨打电话:" + telephoneNumber)
				.withMessageColor("#636363")
				.withIcon(
						List_Activity.this.getResources().getDrawable(
								R.drawable.icon))
				.isCancelableOnTouchOutside(false).withDuration(700)
				.withEffect(effect).withButton1Text("取消").withButton2Text("确定")
				.setCustomView(tView, List_Activity.this)
				.setButton1Click(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
					}
				}).setButton2Click(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent in2 = new Intent();
						in2.setAction(Intent.ACTION_CALL);// 指定意图动作
						in2.setData(Uri.parse("tel:" + telephoneNumber));// 指定电话号码
						List_Activity.this.startActivity(in2);
						dialogBuilder.dismiss();
					}
				}).show();
	}
}
