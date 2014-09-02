package com.kaiqi.love_family.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiqi.love_family.R;
import com.kaiqi.love_family.comment.ListView_Header;

/**
 * 向首页显示的listview中添加内容。
 * 
 * @author 李鑫 18580544821
 * 
 */
public class HomePage_ListView_Adapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	List<ListView_Header> list;
	ViewHolder holder = null;

	public HomePage_ListView_Adapter(Context context, List<ListView_Header> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public Object getItem(int postion) {
		return list == null ? 0 : list.get(postion);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		ListView_Header header = list.get(position);
		if (view == null) {
			view = inflater.inflate(R.layout.homepage_listview_header, null);
			initHolder(view);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// holder.image_stars.setxxx(header.getStars());
		holder.tv_name.setText(header.getCompany_name());
		holder.tv_content.setText(header.getCompany_content());
		holder.tv_property.setText(header.getProperty());
		return view;
	}

	/**
	 * @param view
	 */
	private void initHolder(View view) {
		holder = new ViewHolder();
		holder.image_stars = (ImageView) view.findViewById(R.id.stars);
		holder.tv_name = (TextView) view.findViewById(R.id.company_name);
		holder.tv_content = (TextView) view.findViewById(R.id.company_content);
		holder.tv_property=(TextView) view.findViewById(R.id.working);
		holder.image_identification = (ImageButton) view.findViewById(R.id.logo_identification);
		holder.image_telephone = (ImageButton) view.findViewById(R.id.tel_phone);
		view.setTag(holder);
	}

	/**
	 * 当处理一些耗时的资源加载的时候需要做到以下几点，以使你的加载更快更平滑： 1.适配器在界面主线程中进行修改
	 * 2.可以在任何地方获取数据但应该在另外一个地方请求数据 3.在主界面的线程中提交适配器的变化并调用notifyDataSetChanged()方法
	 * 缓存机制。
	 * 
	 * @author 李鑫 18580544821
	 * 
	 */
	public static class ViewHolder {
		public ImageView image_stars;
		public ImageButton image_identification, image_telephone;
		public TextView tv_name, tv_content,tv_property;
	}

}
