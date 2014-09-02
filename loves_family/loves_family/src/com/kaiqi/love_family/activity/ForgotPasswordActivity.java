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

public class ForgotPasswordActivity extends Activity implements OnClickListener {

	private EditText mEdtPhoneNumber; // 电话号码输入框
	private EditText mEdtAuthCode; // 验证码输入框
	private ImageButton mImgBtnGetAuthCode; // 获取验证码按钮
	private ImageButton mImgBtnNext; // 下一步按钮
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.activity_forgot_password);

		findControls();
		mImgBtnGetAuthCode.setOnClickListener(this);
		mImgBtnNext.setOnClickListener(this);
	}

	/**
	 * 找到控件findViewById
	 */
	public void findControls() {
		mEdtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
		mEdtAuthCode = (EditText) findViewById(R.id.edtAuthCode);
		mImgBtnGetAuthCode = (ImageButton) findViewById(R.id.imgBtnGetAuthCode);
		mImgBtnNext = (ImageButton) findViewById(R.id.imgBtnNext);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		String strPhoneNumber = mEdtPhoneNumber.getText().toString().trim();
		String strAuthCode = mEdtAuthCode.getText().toString().trim();
		switch (v.getId()) {
		case R.id.imgBtnGetAuthCode:

			// 只要输入的电话号码为空或者号码不满11位
			if (("".equals(strPhoneNumber) || null == strPhoneNumber)
					|| strPhoneNumber.length() != 11) {
				Toast.makeText(ForgotPasswordActivity.this, "请输入正确的电话号码...",
						Toast.LENGTH_SHORT).show();
			} else {
				int a = (int) (Math.random() * 10);
				int b = (int) (Math.random() * 10);
				int c = (int) (Math.random() * 10);
				int d = (int) (Math.random() * 10);
				String aa = a + "" + b + c + d;
				Toast.makeText(ForgotPasswordActivity.this,
						"您的验证码是: " + aa + " ...", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.imgBtnNext:
			if ("".equals(strPhoneNumber) || null == strPhoneNumber) {
				Toast.makeText(ForgotPasswordActivity.this, "电话号码不能为空...",
						Toast.LENGTH_SHORT).show();
			} else if (strPhoneNumber.length() != 11) {
				Toast.makeText(ForgotPasswordActivity.this, "请输入11位正确的电话号码...",
						Toast.LENGTH_SHORT).show();
			} else if ("".equals(strAuthCode) || null == strAuthCode) {
				Toast.makeText(ForgotPasswordActivity.this, "验证码不能为空...",
						Toast.LENGTH_SHORT).show();
			} else {
				mIntent = new Intent(ForgotPasswordActivity.this,
						SetPasswordActivity.class);
				finish();
				startActivity(mIntent);
			}

			break;

		}
	}
}
