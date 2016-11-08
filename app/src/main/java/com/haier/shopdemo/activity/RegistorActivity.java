package com.haier.shopdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.haier.shopdemo.R;
import com.haier.shopdemo.util.Checkutil;

public class RegistorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_account;
    private TextView tv_phone_name;
    private EditText et_phone;
    private TextView tv_password_name;
    private EditText et_password;
    private Button button;
    private TextView tv_forget;
    private EditText tv_regison;
    private RadioButton rb_check;
    private TextView tv_test;
    private Button btn_sure_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgect_password);
        initView();
    }

    private void initView() {
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_phone_name = (TextView) findViewById(R.id.tv_phone_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_password_name = (TextView) findViewById(R.id.tv_password_name);
        et_password = (EditText) findViewById(R.id.et_password);
        button = (Button) findViewById(R.id.button);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_regison = (EditText) findViewById(R.id.tv_regison);
        rb_check = (RadioButton) findViewById(R.id.rb_check);
        tv_test = (TextView) findViewById(R.id.tv_test);
        btn_sure_login = (Button) findViewById(R.id.btn_sure_login);

        button.setOnClickListener(this);
        btn_sure_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //发送验证码
            case R.id.button:

                break;
            //确认注册
            case R.id.btn_sure_login:
                submit();
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

        String regison = tv_regison.getText().toString().trim();
        if (TextUtils.isEmpty(regison)) {
            Toast.makeText(this, "regison不能为空", Toast.LENGTH_SHORT).show();
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
