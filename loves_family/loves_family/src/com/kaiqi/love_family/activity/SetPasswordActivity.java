package com.kaiqi.love_family.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kaiqi.love_family.R;

public class SetPasswordActivity extends Activity implements OnClickListener {

	private EditText mEdtNewPassword; // 新密码输入框
	private EditText mEdtAffirmPassword; // 确认新密码输入框
	private ImageButton mImgBtnReset; // 重置密码按钮
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.activity_set_password);

		findControls();

		mImgBtnReset.setOnClickListener(this);
	}

	/**
	 * 找到控件findViewById
	 */
	public void findControls() {
		mEdtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
		mEdtAffirmPassword = (EditText) findViewById(R.id.edtAffirmPassword);
		mImgBtnReset = (ImageButton) findViewById(R.id.btnReset);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 各个按钮的具体实现
	 */
	@Override
	public void onClick(View v) {
		String strNewPassword = mEdtNewPassword.getText().toString().trim();
		String strAffirmPassword = mEdtAffirmPassword.getText().toString()
				.trim();
		switch (v.getId()) {
		case R.id.btnReset:
			// 只要用户名和密码有一个为空
			if (("".equals(strNewPassword) || null == strNewPassword)
					|| ("".equals(strAffirmPassword) || null == strAffirmPassword)) {
				Toast.makeText(SetPasswordActivity.this, "密码不能为空...",
						Toast.LENGTH_SHORT).show();
			} else if (strAffirmPassword.length() < 6
					|| strNewPassword.length() < 6) {
				Toast.makeText(SetPasswordActivity.this, "密码长度不能低于6位...",
						Toast.LENGTH_SHORT).show();
			} else if (!strAffirmPassword.equals(strNewPassword)) {
				Toast.makeText(SetPasswordActivity.this, "两次密码输入不一致...",
						Toast.LENGTH_SHORT).show();
			} else if (strAffirmPassword.equals(strNewPassword)
					&& strAffirmPassword.length() >= 6) {
				Toast.makeText(SetPasswordActivity.this, "密码设置成功...",
						Toast.LENGTH_SHORT).show();
				mIntent = new Intent(SetPasswordActivity.this,
						LoginActivity.class);
				finish();
				startActivity(mIntent);
			}
			break;
		}
	}
}
