package com.kaiqi.love_family.activity;

import com.kaiqi.love_family.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	private Button mBtnForgotPassWord; // 忘记密码按钮
	private Button mBtnRegister; // 注册按钮
	private Button mBtnLogin; // 登陆按钮
	private EditText mEdtInputUser; // 账户输入框
	private EditText mEdtInputPassword; // 密码输入框
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
		setContentView(R.layout.activity_login);

		findControls();
		mBtnForgotPassWord.setOnClickListener(this);
		mBtnRegister.setOnClickListener(this);
		mBtnLogin.setOnClickListener(this);

	}

	/**
	 * 找到控件findViewById
	 */
	public void findControls() {
		mBtnForgotPassWord = (Button) findViewById(R.id.btnForgotPassWord);
		mBtnRegister = (Button) findViewById(R.id.btnRegister);
		mBtnLogin = (Button) findViewById(R.id.btnLogin);
		mEdtInputUser = (EditText) findViewById(R.id.edtInputUser);
		mEdtInputPassword = (EditText) findViewById(R.id.edtInputPwd);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		String strUser = mEdtInputUser.getText().toString().trim();
		String strPassword = mEdtInputPassword.getText().toString().trim();
		switch (v.getId()) {
		case R.id.btnForgotPassWord:
			mBtnForgotPassWord.setTextColor(Color.RED); // 文本变成红色
			// 点击忘记密码跳转到忘记密码界面
			mIntent = new Intent(LoginActivity.this,
					ForgotPasswordActivity.class);
			finish();
			startActivity(mIntent);

			break;
		case R.id.btnRegister:

			break;
		case R.id.btnLogin:

			// 只要用户名和密码有一个为空
			if (("".equals(strUser) || null == strUser)
					|| ("".equals(strPassword) || null == strPassword)) {
				Toast.makeText(LoginActivity.this, "账户或密码不能为空...",
						Toast.LENGTH_SHORT).show();
			}
			break;

		}

	}
}
