package com.haier.shopdemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.haier.shopdemo.R;
import com.haier.shopdemo.adapter.ListAdapter;
import com.haier.shopdemo.bean.WineBean;
import com.haier.shopdemo.db.dao.WineShopDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kong on 2016/9/29.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) { //更改选中商品的总价格
                float price = (Float) msg.obj;
                if (price > 0) {
                    tvTotalMoney.setText(price + "");
                } else {
                    tvTotalMoney.setText("0");
                }
            } else if (msg.what == 2) {
                //更改选中商品的总件数
                int num = (int) msg.obj;
                if (num > 0) {
                    tvNumber.setText("去结算（" + num + "）");
                } else {
                    tvNumber.setText("去结算（" + 0 + "）");
                }
            } else if (msg.what == 3) {
//                imTotal.setImageResource(R.drawable.uncheck);
//                totalCheck = false;
                Boolean ischeck1 = true;
                List<WineBean> productList = (List<WineBean>) msg.obj;
                for (int i = 0; i < productList.size(); i++) {
                    if (!productList.get(i).isCheck()) {
                        ischeck1 = false;
                        Log.d("kong", productList.get(i).isCheck() + "是否选择" + ischeck1 + "判断");
                        //return 是跳出整个方法，break 跳出当前循环
                        break;
                    }

                }
                if (ischeck1) {
                    imTotal.setImageResource(R.drawable.check);
                    Log.d("kong", ischeck1 + "判断");
                } else {
                    imTotal.setImageResource(R.drawable.uncheck);
                    Log.d("kong", ischeck1 + "判断");
                }

            }
        }
    };
    private List<WineBean> productList = new ArrayList<>();
    private List<WineBean> selectProdect = new ArrayList<>();
    private ListAdapter myAdapter;
    private Boolean totalCheck = false;
    private ListView lvWine;
    private ImageView imTotal;
    private TextView tvTotalMoney;
    private TextView tvNumber;
    private TextView tv_shop_title;
    private WineShopDao wineShopDao;
    private List<WineBean> wineBeanDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        iniDate();
        wineBeanDB = querDB();
        delet();
        //插入数据库
        saveDB();
        wineBeanDB = querDB();
        myAdapter = new ListAdapter(wineBeanDB, MainActivity.this, lvWine, handler);
        lvWine.setAdapter(myAdapter);
    }

    public void iniDate() {
        WineBean wineBean;
        for (int i = 0; i < 30; i++) {
            wineBean = new WineBean();
            wineBean.setWineName("商品名称商品名称商品名称商品名称商品名称商品名称" + i);
            wineBean.setWineMoney("" + i * 100.00);
            wineBean.setWineNumText(1 + "");
            productList.add(wineBean);
        }
    }

    //防止输入框进来
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //隐藏软键盘
            if ((null != getCurrentFocus()) && (null != getCurrentFocus().getWindowToken())) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.dispatchTouchEvent(ev);

    }

    private void initView() {
        lvWine = (ListView) findViewById(R.id.lv_wine);
        imTotal = (ImageView) findViewById(R.id.im_total);
        imTotal.setOnClickListener(this);
        tvTotalMoney = (TextView) findViewById(R.id.tv_total_money);
        tvTotalMoney.setOnClickListener(this);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvNumber.setOnClickListener(this);
        tv_shop_title = (TextView) findViewById(R.id.tv_shop_title);
        tv_shop_title.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lv_wine:
                break;
            //全选按钮
            case R.id.im_total:
                if (totalCheck) {
                    totalCheck = false;
                    imTotal.setImageResource(R.drawable.uncheck);
                    tvTotalMoney.setText("0");
                    tvNumber.setText("去结算（" + 0 + "）");
                    for (int i = 0; i < wineBeanDB.size(); i++) {
                        wineBeanDB.get(i).setCheck(false);
                    }
                    myAdapter.notifyDataSetChanged();
                } else {
                    totalCheck = true;
                    Boolean seleAll = true;

                    imTotal.setImageResource(R.drawable.check);
                    for (int i = 0; i < wineBeanDB.size(); i++) {
                        wineBeanDB.get(i).setCheck(true);
                    }
                    myAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.tv_total_money:
                break;
            //购物车标题
            case R.id.tv_shop_title:
              //  Intent intentRegis = new Intent(MainActivity.this, TestActivity.class);
              //  startActivity(intentRegis);
                break;
            //结算的按钮
            case R.id.tv_number:
                selectProdect.clear();
                if (productList != null) {
                    for (int i = 0; i < wineBeanDB.size(); i++) {
                        if (wineBeanDB.get(i).isCheck()) {
                            selectProdect.add(wineBeanDB.get(i));
                        }
                    }
                }

                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                intent.putExtra("shuju", (Serializable) selectProdect);
                startActivity(intent);
                break;


        }


    }

    //存入数据库
    public void saveDB() {
        WineShopDao wineShopDao = WineShopDao.getInstance(MainActivity.this);
        for (int i = 0; i < productList.size(); i++) {
            wineShopDao.insertOneWine(productList.get(i));
        }

    }

    //查询数据库的
    public List<WineBean> querDB() {
        WineShopDao wineShopDao = WineShopDao.getInstance(MainActivity.this);
        List<WineBean> wineBeanList = wineShopDao.queryAllWineList();
        return wineBeanList;
    }

    //删除数据库所以数据
    public void delet() {
        WineShopDao wineShopDao = WineShopDao.getInstance(MainActivity.this);
        wineShopDao.deleteWineList(wineBeanDB);
    }


}
