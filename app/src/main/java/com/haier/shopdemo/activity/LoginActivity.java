package com.haier.shopdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.haier.shopdemo.R;
import com.haier.shopdemo.util.Checkutil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_account;
    private Button btn_login;
    private TextView tv_phone_name;
    private EditText et_phone;
    private TextView tv_password_name;
    private EditText et_password;
    private TextView tv_forget;
    private TextView tv_regison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        initView();
    }

    private void initView() {
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_phone_name = (TextView) findViewById(R.id.tv_phone_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_password_name = (TextView) findViewById(R.id.tv_password_name);
        et_password = (EditText) findViewById(R.id.et_password);
        // 忘记密码
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);
        //注册
        tv_regison = (TextView) findViewById(R.id.tv_regison);
        tv_regison.setOnClickListener(this);
        //登陆
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 登陆
            case R.id.btn_login:
                submit();
                break;
            // 忘记密码
            case R.id.tv_forget:
                Intent intentRegis = new Intent(LoginActivity.this, RestAcitivty.class);
                startActivity(intentRegis);
                break;
            // 注册
            case R.id.tv_regison:
                Intent intent = new Intent(LoginActivity.this, RegistorActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void submit() {
        // validate
        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "phone不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "password不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断是不是符合手机格式
        Boolean isTrue = Checkutil.isPhoneNumberValid(phone);
        if (!isTrue) {
            Toast.makeText(this, "手机格式不对", Toast.LENGTH_SHORT).show();
            return;
        }
        // TODO validate success, do something
    }
}
