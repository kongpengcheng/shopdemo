package com.haier.shopdemo.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.haier.shopdemo.bean.WineBean;
import com.haier.shopdemo.db.DbOpenHelper;
import com.haier.shopdemo.db.conver.WineShopConver;
import com.kong.Green_DaoMaster;
import com.kong.Green_DaoSession;
import com.kong.Green_Wine_Shop;
import com.kong.Green_Wine_ShopDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 孔 on 2016/10/9.
 */
public class WineShopDao {
    private Context context;
    private DbOpenHelper dbOpenHelper;  //数据库打开帮助实例
    private SQLiteDatabase database;  //数据库实例

    public WineShopDao(Context context) {
        this.context = context;
        //实例化Helper类
        dbOpenHelper = new DbOpenHelper(context);
    }

    private static WineShopDao INSTANCE;

    public synchronized static WineShopDao getInstance(Context context) {

        if (null == INSTANCE) {
            synchronized (WineShopDao.class) {
                if (null == INSTANCE) {
                    INSTANCE = new WineShopDao(context);

                }
            }
        }
        return INSTANCE;
    }

    /**
     * 查询得到所有的酒品列表
     *
     * @return
     */
    public List<WineBean> queryAllWineList() {
        List<WineBean> wineBeanList = new ArrayList<>();

        //打开数据库
        Green_Wine_ShopDao greenWineDao = openDatabase();

        //查询所有购买酒品
        QueryBuilder<Green_Wine_Shop> opendQueryBuilder = greenWineDao.queryBuilder();
        List<Green_Wine_Shop> greenWineList = new ArrayList<>();
        greenWineList = opendQueryBuilder.list();
        //将数据库酒品Bean列表转换为本地操作的酒品Bean列表
        wineBeanList = WineShopConver.greenWineListToWineBeanList(greenWineList);


        //关闭数据库
        closeDatabase();

        return wineBeanList;
    }

    /**
     * 插入一条酒品记录
     *
     * @param wineBean
     */
    public void insertOneWine(WineBean wineBean) {
        //打开数据库
        Green_Wine_ShopDao greenWineDao = openDatabase();

        //将本地操作Bean转换为数据库对应Bean
        Green_Wine_Shop wine = WineShopConver.wineBeanToGreenWine(wineBean);
        //插入一条记录
        greenWineDao.insert(wine);

        //关闭数据库
        closeDatabase();
    }

    /**
     * 根据Id查询相应的酒品实例
     *
     * @param id
     * @return
     */
    public WineBean queryOneWineById(int id) {
        WineBean wineBean = new WineBean();

        //打开数据库
        Green_Wine_ShopDao greenWineDao = openDatabase();

        //查询指定位置的一条记录
        QueryBuilder<Green_Wine_Shop> queryBuilder = greenWineDao.queryBuilder().where(Green_Wine_ShopDao.Properties.Id.eq(id));
        if (queryBuilder != null) {
            Green_Wine_Shop greenWine = queryBuilder.unique();
            //将数据库记录Bean转换为本地操作的Bean
            wineBean = WineShopConver.greenWineToWineBean(greenWine);
        }

        //关闭数据库
        closeDatabase();

        return wineBean;
    }

    /**
     * 更新一条数据库记录
     *
     * @param wineBean
     */
    public void updateOneWine(WineBean wineBean) {
        //打开数据库
        Green_Wine_ShopDao greenWineDao = openDatabase();

        //将本地Bean转换为数据库Bean
        Green_Wine_Shop greenWine = WineShopConver.wineBeanToGreenWine(wineBean);
        //更新本条记录,根据ID自动判断所对应的记录
        greenWineDao.update(greenWine);

        //关闭数据库
        closeDatabase();
    }

    /**
     * 删除一条记录
     *
     * @param wineBean
     */
    public void deleteOneWine(WineBean wineBean) {
        //打开数据库
        Green_Wine_ShopDao greenWineDao = openDatabase();

        //将本地Bean转换为数据库Bean
        Green_Wine_Shop greenWine = WineShopConver.wineBeanToGreenWine(wineBean);
        //删除本条记录，根据ID自动判断所对应的记录
        greenWineDao.delete(greenWine);

        //关闭数据库
        closeDatabase();
    }

    /**
     * 删除酒品列表
     *
     * @param wineBeanList
     */
    public void deleteWineList(List<WineBean> wineBeanList) {
        //打开数据库
        Green_Wine_ShopDao greenWineDao = openDatabase();

        //将本地Bean列表转换为数据库Bean列表
        List<Green_Wine_Shop> greenWineList = WineShopConver.wineBeanListToGreenWineList(wineBeanList);
        //删除酒品列表
        greenWineDao.deleteInTx(greenWineList);

        //关闭数据库
        closeDatabase();
    }

    /**
     * 打开数据库
     *
     * @return
     */
    private Green_Wine_ShopDao openDatabase() {
        //打开数据库
        database = dbOpenHelper.getWritableDatabase();
        Green_DaoMaster daoMaster = new Green_DaoMaster(database);
        Green_DaoSession daoSession = daoMaster.newSession();
        Green_Wine_ShopDao greenWineDao = daoSession.getGreen_Wine_ShopDao();

        return greenWineDao;
    }

    /**
     * 关闭数据库
     */
    private void closeDatabase() {
        if ((database != null) && (database.isOpen())) {
            database.close();
        }
    }
}
