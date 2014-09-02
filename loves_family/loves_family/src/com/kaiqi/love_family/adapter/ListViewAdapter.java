package com.kaiqi.love_family.adapter;

import java.util.List;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.adapter.HomePage_ListView_Adapter.ViewHolder;
import com.kaiqi.love_family.comment.ListView_Header;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 从服务器获取数据，并填充到listview中去。
 * @author 李鑫    18290545819
 *
 */
public class ListViewAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	List<ListView_Header> list;
	ViewHolder holder = null;

	public ListViewAdapter(Context context, List<ListView_Header> list) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		holder = new ViewHolder();
		ListView_Header header = list.get(position);
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
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		header.setStars(R.drawable.star50);
		holder.image_stars.setImageResource(header.getStars());
		header.setCompany_name("重庆汉昌文化传播有限责任公司");
		holder.tv_name.setText(header.getCompany_name());
		header.setCompany_content("本公司致力打造专业的程序猿，为各位屌丝成就终极梦想，我们的宗旨是没有最屌只有更屌！");
		holder.tv_content.setText(header.getCompany_content());
		header.setProperty("屌丝的天堂");
		holder.tv_property.setText(header.getProperty());
		header.setIdentification(R.drawable.identification);
		holder.image_identification.setImageResource(header.getIdentification());
		header.setTelephone(R.drawable.telephone_image);
		holder.image_telephone.setImageResource(header.getTelephone());
		return view;
	}

}
