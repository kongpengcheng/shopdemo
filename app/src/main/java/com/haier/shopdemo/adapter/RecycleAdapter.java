
package com.haier.shopdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.haier.shopdemo.R;
import com.haier.shopdemo.bean.WineBean;

import java.util.List;

/**
 * 订单的适配器
 * Created by 孔 on 2016/9/23.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.WineViewHolder> {

    private Context context;
    private List<WineBean> wineBeanList;
    private OnItemLongClickListener onItemLongClickListener;    //长按点击事件监听器

    public RecycleAdapter(Context context, List<WineBean> wineBeanList) {
        this.context = context;
        this.wineBeanList = wineBeanList;
    }

    /**
     * 设置酒品的集合并通知Adapter更新数据
     *
     * @param wineBeanList
     */
    public void setWinList(List<WineBean> wineBeanList) {
        this.wineBeanList = wineBeanList;
        notifyDataSetChanged();
    }

    @Override
    public WineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WineViewHolder holder = new WineViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(final WineViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.tv_wine_name.setText(wineBeanList.get(position).getWineName());
        holder.tv_wine_monet.setText(wineBeanList.get(position).getWineMoney());
        holder.tv_wine_number.setText(wineBeanList.get(position).getWineNumText());
        //个人的单选按钮
        holder.imageView_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = ((RadioButton) view).isChecked();
                Log.d("kong", "点击了个人" + isCheck + position);
                wineBeanList.get(position).setSelect(true);
                wineBeanList.get(position).setSelectUnit(false);
                holder.tv_name_person.setVisibility(View.VISIBLE);
                holder.tv_name_unit.setVisibility(View.GONE);
            }
        });


        //单位的单选按钮
        holder.imageView_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = ((RadioButton) view).isChecked();
                Log.d("kong", "点击了单位" + isCheck + position);
                wineBeanList.get(position).setSelectUnit(true);
                wineBeanList.get(position).setSelect(false);
                holder.tv_name_unit.setVisibility(View.VISIBLE);
                holder.tv_name_person.setVisibility(View.GONE);


            }
        });
        if (wineBeanList.get(position).isSelect()) {
            Log.d("kong", "复用个人" + wineBeanList.get(position).isSelect() + position);
            holder.imageView_person.setChecked(true);
            holder.imageView_unit.setChecked(false);
            holder.tv_name_person.setVisibility(View.VISIBLE);
            holder.tv_name_unit.setVisibility(View.INVISIBLE);
        }

        if (wineBeanList.get(position).isSelectUnit()) {
            Log.d("kong", "复用单位" + wineBeanList.get(position).isSelectUnit() + position);
            holder.imageView_unit.setChecked(true);
            holder.imageView_person.setChecked(false);
            holder.tv_name_person.setVisibility(View.INVISIBLE);
            holder.tv_name_unit.setVisibility(View.VISIBLE);
        }

        //设置当前item的长按监听
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onClick(holder.itemView, position);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return wineBeanList.size();
    }

    public class WineViewHolder extends RecyclerView.ViewHolder {
        TextView tv_wine_name;
        TextView tv_name_person;
        TextView tv_name_unit;
        TextView tv_wine_monet;
        TextView tv_wine_number;
        RadioButton imageView_person;
        RadioButton imageView_unit;

        public WineViewHolder(View itemView) {
            super(itemView);
            tv_wine_name = (TextView) itemView.findViewById(R.id.tv_wine_name);
            tv_wine_monet = (TextView) itemView.findViewById(R.id.tv_money);
            tv_wine_number = (TextView) itemView.findViewById(R.id.tv_number);
            imageView_person = (RadioButton) itemView.findViewById(R.id.rb_person);
            imageView_unit = (RadioButton) itemView.findViewById(R.id.rb_unint);
            tv_name_person = (TextView) itemView.findViewById(R.id.tv_name_person);
            tv_name_unit = (TextView) itemView.findViewById(R.id.tv_name_unit);
        }
    }

    /**
     * 自定义Item长按点击事件的监听器
     */
    public interface OnItemLongClickListener {
        public void onClick(View view, int position);
    }
}
