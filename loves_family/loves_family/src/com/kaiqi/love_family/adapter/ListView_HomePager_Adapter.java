package com.kaiqi.love_family.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.adapter.HomePage_ListView_Adapter.ViewHolder;
import com.kaiqi.love_family.view.dialog.Effectstype;
import com.kaiqi.love_family.view.dialog.NiftyDialogBuilder;

public class ListView_HomePager_Adapter extends BaseAdapter {
	Context context;
	View view;
	LayoutInflater inflater;
	ViewHolder holder = null;
	NiftyDialogBuilder dialogBuilder;
	private Effectstype effect;
	TextView tView;

	public ListView_HomePager_Adapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return 5;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		ViewHolder holder = new ViewHolder();
		if (view == null) {
			view = inflater.inflate(R.layout.homepage_listview_header, null);
			holder.image_stars = (ImageView) view.findViewById(R.id.stars);
			holder.tv_name = (TextView) view.findViewById(R.id.company_name);
			holder.tv_content = (TextView) view
					.findViewById(R.id.company_content);
			holder.tv_property = (TextView) view.findViewById(R.id.working);
			holder.image_identification = (ImageButton) view
					.findViewById(R.id.logo_identification);
			holder.image_telephone = (ImageButton) view
					.findViewById(R.id.tel_phone);
			holder.image_identification
					.setImageResource(R.drawable.identification);
			holder.image_telephone.setImageResource(R.drawable.telephone_image);
			switch (arg0) {
			case 0:
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
			case 1:
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

	@SuppressLint("ResourceAsColor")
	public void setListenerOfTel(String telephoneNumber) {
		tView = new TextView(context);
		tView.setText("是否拨打该电话？");
		tView.setTextColor(R.color.divider_color);
		tView.setGravity(Gravity.CENTER);
		dialogShow(telephoneNumber);
	}

	public void dialogShow(final String telephoneNumber) {
		dialogBuilder = NiftyDialogBuilder.getInstance(context);
		effect = Effectstype.RotateBottom;
		dialogBuilder.withTitle("拨打电话").withTitleColor("#ffffff")
				.withDividerColor("#000000")
				.withMessage("准备拨打电话:" + telephoneNumber)
				.withMessageColor("#636363")
				.withIcon(context.getResources().getDrawable(R.drawable.icon))
				.isCancelableOnTouchOutside(false).withDuration(700)
				.withEffect(effect).withButton1Text("取消").withButton2Text("确定")
				.setCustomView(tView, context)
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
						context.startActivity(in2);
						dialogBuilder.dismiss();
					}
				}).show();
	}
}
