package com.kaiqi.love_family.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.kaiqi.love_family.R;

/**
 * 欢迎界面：打开APP时的欢迎界面。
 * @author 李鑫     Tel：18290545819
 *
 */
public class WelcomeActivity extends BaseActivity {
	ImageView img;
	SharedPreferences sp;//SharedPreferences一个临时存储参数的数据库。
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_activity);
		img = (ImageView) findViewById(R.id.welcome_bg);
		//渐变动画效果：从不可见到完全可见，持续时间2秒
		AlphaAnimation anima=new AlphaAnimation(0, 1);
		anima.setDuration(2000);
		img.startAnimation(anima);//为目标对象添加效果。		
		//getSharedPreferences（String name，mode）；
		sp=getSharedPreferences("info", MODE_PRIVATE);
		//新开线程：让程序暂停一段时间后实现跳转。
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				close();
			}
			
		}.start();
	}
	/**
	 * 第一次进入跳转到导航，非第一次进入跳转到主界面。
	 */
	private void close(){
		boolean isFirst=sp.getBoolean("isFirst", true);//创建状态变量并赋初值
		Intent intent=new Intent();
		if(isFirst){//第一次访问
			sp.edit().putBoolean("isFirst", false).commit();//改变状态值，并提交给sp。
			intent.setClass(WelcomeActivity.this,GuidanceActivity.class);
		}else{//以后访问
			intent.setClass(WelcomeActivity.this, IndexActivity.class);
		}
		startActivity(intent);
		finish();
	}
}

