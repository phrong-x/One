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
			switch (arg0) {
			case 0:
				holder.image_stars.setImageResource(R.drawable.star50);
				holder.tv_name.setText("重庆北流科技责任有限公司");
				holder.tv_content
						.setText("我们主要经营“null”等产品。公司尊崇“踏实、拼搏、责任的企业精神，并以诚信、共赢、开创经营理念，"
								+ "创造良好的企业环境，以全新的管理模式，完善的技术，周到的服务，卓越的品质为生存根本，" +
								"我们始终坚持用户至上 用心服务于客户，坚持用自己的服务去打动客户。");
				holder.tv_property.setText("null");
				break;
			case 1:
				holder.image_stars.setImageResource(R.drawable.star45);
				holder.tv_name.setText("重庆北流科技责任有限公司");
				holder.tv_content
						.setText("本公司致力打造专业的程序猿，为各位屌丝成就终极梦想，我们的宗旨是没有最屌只有更屌！");
				holder.tv_property.setText("屌丝天堂");
				break;
			default:
				break;
			}

			holder.image_identification
					.setImageResource(R.drawable.identification);
			holder.image_telephone.setImageResource(R.drawable.telephone_image);
			holder.image_telephone.setOnClickListener(new OnClickListener() {

				@SuppressLint("ResourceAsColor")
				@Override
				public void onClick(View arg0) {

					tView = new TextView(context);
					tView.setText("是否拨打该电话？");
					tView.setTextColor(R.color.divider_color);
					tView.setGravity(Gravity.CENTER);
					dialogShow();
					// ShowDialogOfTelephone.dialogShow("18590544821");
				}

				public void dialogShow() {
					dialogBuilder = NiftyDialogBuilder.getInstance(context);
					effect = Effectstype.RotateBottom;
					dialogBuilder
							.withTitle("拨打电话")
							.withTitleColor("#ffffff")
							.withDividerColor("#000000")
							.withMessage("准备拨打电话:10000")
							.withMessageColor("#636363")
							.withIcon(
									context.getResources().getDrawable(
											R.drawable.icon))
							.isCancelableOnTouchOutside(false)
							.withDuration(700).withEffect(effect)
							.withButton1Text("取消").withButton2Text("确定")
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
									in2.setData(Uri.parse("tel:10000"));// 指定电话号码
									context.startActivity(in2);
									dialogBuilder.dismiss();
								}
							}).show();
				}

			});
		}
		return view;
	}

}
