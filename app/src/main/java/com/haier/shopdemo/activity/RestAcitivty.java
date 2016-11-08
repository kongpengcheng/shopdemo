package com.haier.shopdemo.activity;

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

public class RestAcitivty extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_account;
    private TextView tv_phone_name;
    private EditText et_phone;
    private TextView tv_code_name;
    private EditText et_code;
    private TextView tv_password_name;
    private EditText et_password;
    private Button btn_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_pasword);
        initView();
    }

    private void initView() {
        tv_account = (TextView) findViewById(R.id.tv_account);
        tv_phone_name = (TextView) findViewById(R.id.tv_phone_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_code_name = (TextView) findViewById(R.id.tv_code_name);
        et_code = (EditText) findViewById(R.id.et_code);
        tv_password_name = (TextView) findViewById(R.id.tv_password_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_sure = (Button) findViewById(R.id.btn_sure);

        btn_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sure:
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

        String code = et_code.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            Toast.makeText(this, "code不能为空", Toast.LENGTH_SHORT).show();
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
