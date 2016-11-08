package com.haier.shopdemo.bean;

import com.haier.shopdemo.bean.base.BaseBean;

/**
 * Created by centling on 2016/9/27.
 */
public class WineBean extends BaseBean {
    private Long id;  //当前Bean的ID
    private String wineName;
    private String wineMoney;
    private String wineNumText;
    //是否选中商品
    private boolean isCheck;
    //是否选中订单个人
    private boolean isSelect;
    //是否选中订单商业
    private boolean isSelectUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelectUnit() {
        return isSelectUnit;
    }

    public void setSelectUnit(boolean selectUnit) {
        isSelectUnit = selectUnit;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public String getWineMoney() {
        return wineMoney;
    }

    public void setWineMoney(String wineMoney) {
        this.wineMoney = wineMoney;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getWineNumText() {
        return wineNumText;
    }

    public void setWineNumText(String wineNumText) {
        this.wineNumText = wineNumText;
    }
}
