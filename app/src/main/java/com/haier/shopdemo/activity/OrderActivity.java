package com.haier.shopdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;

import com.haier.shopdemo.R;
import com.haier.shopdemo.adapter.RecycleAdapter;
import com.haier.shopdemo.bean.WineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kong on 2016/9/29.
 */
public class OrderActivity extends Activity {
    private List<WineBean> productList;
    private TextView tv_order;
    private TextView tv_name_top;
    private TextView tv_name;
    private TextView tv_phone_top;
    private TextView tv_phone;
    private TextView tv_address_top;
    private TextView tv_address;
    private RecyclerView id_recyclerview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.recycle_main);
        initView();
        List<WineBean> ll = (List<WineBean>) getIntent().getSerializableExtra("shuju");

        productList = new ArrayList<>();
        iniDate();
        id_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        id_recyclerview.setAdapter(new RecycleAdapter(OrderActivity.this, ll));
    }

    public void iniDate() {
        WineBean wineBean;
        for (int i = 0; i < 10; i++) {
            wineBean = new WineBean();
            wineBean.setWineName("商品名称商品名称商品名称商品名称商品名称商品名称" + i);
            wineBean.setWineMoney("" + i * 100.00);
            wineBean.setWineNumText(i + "");
            productList.add(wineBean);
        }
    }

    private void initView() {
        tv_order = (TextView) findViewById(R.id.tv_order);
        tv_name_top = (TextView) findViewById(R.id.tv_name_top);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone_top = (TextView) findViewById(R.id.tv_phone_top);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_address_top = (TextView) findViewById(R.id.tv_address_top);
        tv_address = (TextView) findViewById(R.id.tv_address);
        id_recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
    }
}
