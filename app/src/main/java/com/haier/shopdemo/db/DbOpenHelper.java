package com.haier.shopdemo.db;

import android.content.Context;

import com.kong.Green_DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by centling on 2016/10/9.
 */
public class DbOpenHelper extends Green_DaoMaster.DevOpenHelper {

    private final static String DB_NAME = "wine_manage_db";

    public DbOpenHelper(Context context) {
        super(context, DB_NAME);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}

