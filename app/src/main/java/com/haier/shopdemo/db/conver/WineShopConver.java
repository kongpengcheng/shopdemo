package com.haier.shopdemo.db.conver;

import com.haier.shopdemo.bean.WineBean;
import com.kong.Green_Wine_Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by centling on 2016/10/9.
 */
public class WineShopConver {
    /**
     * 数据库Bean转换为本地操作Bean
     *
     * @param greenWine
     * @return
     */
    public static WineBean greenWineToWineBean(Green_Wine_Shop greenWine) {
        WineBean wineBean = new WineBean();
        wineBean.setId(greenWine.getId());
        wineBean.setWineName(greenWine.getWine_name());
        wineBean.setWineMoney(greenWine.getWine_money());
        wineBean.setWineNumText(greenWine.getWine_num());
        wineBean.setCheck(greenWine.getIsCheck());
        return wineBean;
    }

    /**
     * 本地操作Bean转换为数据库Bean
     *
     * @param wineBean
     * @return
     */
    public static Green_Wine_Shop wineBeanToGreenWine(WineBean wineBean) {
        Green_Wine_Shop greenWine = new Green_Wine_Shop();
        greenWine.setId(wineBean.getId());
        greenWine.setWine_name(wineBean.getWineName());
        greenWine.setWine_money(wineBean.getWineMoney());
        greenWine.setWine_num(wineBean.getWineNumText());
        greenWine.setIsCheck(wineBean.isCheck());
        return greenWine;
    }

    /**
     * 数据库Bean列表转换为本地操作Bean列表
     *
     * @param greenWineList
     * @return
     */
    public static List<WineBean> greenWineListToWineBeanList(List<Green_Wine_Shop> greenWineList) {
        List<WineBean> wineBeanList = new ArrayList<>();
        for (Green_Wine_Shop greenWine : greenWineList) {
            wineBeanList.add(greenWineToWineBean(greenWine));
        }

        return wineBeanList;
    }

    /**
     * 本地操作Bean列表转换为数据库Bean列表
     *
     * @param wineBeanList
     * @return
     */
    public static List<Green_Wine_Shop> wineBeanListToGreenWineList(List<WineBean> wineBeanList) {
        List<Green_Wine_Shop> greenWineList = new ArrayList<>();
        for (WineBean wineBean : wineBeanList) {
            greenWineList.add(wineBeanToGreenWine(wineBean));
        }
        return greenWineList;
    }
}
