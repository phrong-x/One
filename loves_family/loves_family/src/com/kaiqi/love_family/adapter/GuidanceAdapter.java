package com.kaiqi.love_family.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaiqi.love_family.R;

/**
 * adapter:为导航界面填充数据。 本人觉得导航的图片资源来自四大主界面的截图比较切合主题。
 * 
 * @author 李鑫 Tel：18290545819
 * 
 */
public class GuidanceAdapter extends PagerAdapter {
	Context context;
	List<Integer> list;
	int imageId[] = { R.drawable.guide1, R.drawable.guide2, R.drawable.guide3,
			R.drawable.guide4 };

	// 将接口暴露出来，以便实现回调功能。
	public GuidanceAdapter(Context context) {
		this.context = context.getApplicationContext();
	}

	public void change(List<Integer> list) {
		this.list = list;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (View) arg1;
	}

	public Object instantiateItem(ViewGroup container, int position) {
		ImageView iv = new ImageView(context);
		iv.setImageResource(imageId[position]);
		((ViewPager) container).addView(iv, 0);
		return iv;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	};

}
