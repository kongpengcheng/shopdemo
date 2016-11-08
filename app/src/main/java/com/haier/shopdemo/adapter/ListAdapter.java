package com.haier.shopdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.haier.shopdemo.R;
import com.haier.shopdemo.bean.WineBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kong on 2016/9/27.
 */
public class ListAdapter extends BaseAdapter {
    private List<WineBean> productList;
    private List<WineBean> selectList;
    private Context mContext;
    private ListView listView;
    private myWatcher mWatcher;
    private int index = -1;//记录选中的位置
    private List<String> text;
    // private String text[];//记录输入的值
    private Handler handler;
    private int num;// 商品数量

    public ListAdapter(List<WineBean> list, Context context, ListView listView, Handler handler) {
        productList = list;
        mContext = context;
        this.listView = listView;
        this.handler = handler;
        text = new ArrayList<>();
        //   text = new String[list.size()];
        //默认把数据设置成1
        for (int i = 0; i < list.size(); i++) {
            //  text[i] = String.valueOf(1);
            text.add(i, 1 + "");
        }
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final int poisition = i;
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.shop_item, null);
            holder.tv_delete = (TextView) view.findViewById(R.id.tv_delete);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_money = (TextView) view.findViewById(R.id.tv_money);
            holder.checkBox = (CheckBox) view.findViewById(R.id.cb_check);
            holder.editText = (EditText) view.findViewById(R.id.et_number);
            //设置光标不显示,但不能设置光标颜色
            //holder.editText.setCursorVisible(false);
            holder.btnmins = (Button) view.findViewById(R.id.btn_min);
            holder.btnadd = (Button) view.findViewById(R.id.btn_add);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList.remove(productList.get(poisition));
                text.remove(poisition);
                Log.d("kong--->", text.toString());
                float monetotal = getTotalPrice();
                Message message = new Message();
                message.what = 1;
                message.obj = monetotal;
                handler.sendMessage(message);
                int totalNumber = getTotalNumber();
                Message messageNumber = new Message();
                messageNumber.what = 2;//2代表总件数
                messageNumber.obj = totalNumber;
                handler.sendMessage(messageNumber);
                notifyDataSetChanged();
            }
        });
        //s输入框的监听
        holder.editText.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    index = i;
                    holder.editText.clearFocus();
                }
                return false;
            }
        });
        //设置焦点监听，当获取到焦点的时候才给它设置内容变化监听解决卡的问题
        holder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et = (EditText) v;
                if (mWatcher == null) {
                    mWatcher = new myWatcher();
                }
                if (hasFocus) {
                    et.addTextChangedListener(mWatcher);//设置edittext内容监听
                } else {
                    et.removeTextChangedListener(mWatcher);
                }

            }
        });

        holder.editText.clearFocus();//防止点击以后弹出键盘，重新getview导致的焦点丢失
        if (index != -1 && index == i) {
            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
            holder.editText.requestFocus();
        }
        holder.editText.setText(text.get(i));//这一定要放在clearFocus()之后，否则最后输入的内容在拉回来时会消失
        holder.editText.setSelection(holder.editText.getText().length());
        holder.tv_name.setText(productList.get(i).getWineName());
        holder.tv_money.setText(productList.get(i).getWineMoney());
        //checkbok的监听
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    productList.get(i).setCheck(true);
                    float monetotal = getTotalPrice();
                    Message message = new Message();
                    message.what = 1;
                    message.obj = monetotal;
                    handler.sendMessage(message);
                    int totalNumber = getTotalNumber();
                    Message messageNumber = new Message();
                    messageNumber.what = 2;//2代表总件数
                    messageNumber.obj = totalNumber;
                    handler.sendMessage(messageNumber);
                    Message messageNumber1 = new Message();
                    messageNumber1.what = 3;//3是否全选
                    messageNumber1.obj = productList;
                    handler.sendMessage(messageNumber1);

                } else {
//                    Message messageNumber1 = new Message();
//                    messageNumber1.what = 3;//3是否全选
//                    handler.sendMessage(messageNumber1);
                    productList.get(i).setCheck(false);
                    float monetotal = getTotalPrice();
                    Message message = new Message();
                    message.what = 1;
                    message.obj = monetotal;
                    handler.sendMessage(message);
                    int totalNumber = getTotalNumber();
                    Message messageNumber = new Message();
                    messageNumber.what = 2;//2代表总件数
                    messageNumber.obj = totalNumber;
                    handler.sendMessage(messageNumber);
                    Message messageNumber1 = new Message();
                    messageNumber1.what = 3;//3是否全选
                    messageNumber1.obj = productList;
                    handler.sendMessage(messageNumber1);
                }
            }
        });
        if (productList.get(i).isCheck()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        final String numString = holder.editText.getText().toString();
        //商品的减号事件
        holder.btnmins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numString == null || numString.equals("")) {

                    holder.editText.setText("1");
                    float monetotal = getTotalPrice();
                    Message message = new Message();
                    message.what = 1;
                    message.obj = monetotal;
                    handler.sendMessage(message);
                    int totalNumber = getTotalNumber();
                    Message messageNumber = new Message();
                    messageNumber.what = 2;//2代表总件数
                    messageNumber.obj = totalNumber;
                    handler.sendMessage(messageNumber);
                } else {
                    num = Integer.valueOf(text.get(i));
                    Log.d("kong-->", num + "个数" + index + "第几位");
                    if (--num < 1) {
                        num++;
                    } else {
                        text.set(i, num + "");
                        Log.d("kong--->", i + "第几位");
                        Log.d("kong--->", text.toString());
                        holder.editText.setText(num + "");
                        productList.get(poisition).setWineNumText(num + "");
                        float monetotal = getTotalPrice();
                        Message message = new Message();
                        message.what = 1;
                        message.obj = monetotal;
                        handler.sendMessage(message);
                        int totalNumber = getTotalNumber();
                        Message messageNumber = new Message();
                        messageNumber.what = 2;//2代表总件数
                        messageNumber.obj = totalNumber;
                        handler.sendMessage(messageNumber);

                    }
                }
            }
        });
        //商品的增加事件
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numString == null || numString.equals("")) {
                    holder.editText.setText("1");
                } else {
                    num = Integer.valueOf(text.get(i));
                    Log.d("kong-->", num + "个数" + index + "第几位");
                    if (++num < 1) {
                        num--;
                    } else {
                        holder.editText.setText(num + "");
                        productList.get(poisition).setWineNumText(num + "");
                        Log.d("kong--->", text.toString());
                        //  text.add(i, num + "");
                        text.set(i, num + "");
                        Log.d("kong--->", i + "第几位");
                        Log.d("kong--->", text.toString());
                        //  text[i] = num + "";
                        float moneytotal = getTotalPrice();
                        int totalNumber = getTotalNumber();
                        Message messageMoney = new Message();
                        messageMoney.what = 1;//1代表总钱数
                        messageMoney.obj = moneytotal;
                        handler.sendMessage(messageMoney);
                        Message messageNumber = new Message();
                        messageNumber.what = 2;//2代表总件数
                        messageNumber.obj = totalNumber;
                        handler.sendMessage(messageNumber);
                    }
                }

            }
        });
        return view;
    }

    /**
     * 复用
     */
    class ViewHolder {
        public TextView tv_name;
        public TextView tv_money;
        public CheckBox checkBox;
        public EditText editText;
        public Button btnmins;
        public Button btnadd;
        public TextView tv_delete;

    }

    /**
     * edit的监听
     */
    class myWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub


        }

        @Override
        public void afterTextChanged(Editable s) {
            //text[index] = s.toString();
            text.set(index, s.toString() + "");
            productList.get(index).setWineNumText(s.toString());
            Log.d("kong--->Changed", text.toString());
            //为输入的位置内容设置数组管理器，防止item重用机制导致的上下内容一样的问题
        }

    }


    /**
     * 计算选中商品的money
     *
     * @return 返回需要付费的总计算选中商品的money
     */
    private float getTotalPrice() {
        float totalPrice = 0;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).isCheck()) {
                if (productList.get(i).getWineMoney() != null) {
                    float money = Float.valueOf(productList.get(i).getWineMoney()).floatValue();
                    int number = Integer.valueOf(text.get(i)).intValue();
                    totalPrice = totalPrice + number * money;
                }

            }
        }
        return totalPrice;
    }

    /**
     * 计算选中的总个数
     *
     * @return
     */
    private int getTotalNumber() {
        int totalNumber = 0;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).isCheck()) {
                int number = Integer.valueOf(text.get(i)).intValue();
                totalNumber = totalNumber + number;
            }
        }
        return totalNumber;
    }
}
