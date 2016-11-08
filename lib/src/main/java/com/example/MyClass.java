package com.example;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyClass {
    private final static String SCHEMA_NAME = "green_";
    private final static int DB_VERSION = 1;
    private final static String PACKAGE = "com.kong";
    private final static String CODE_PATH = "../shopdemo/app/src/main/java-gen";
    private static Schema manageSchema;  //数据库模式对象

    public static void main(String[] args) throws Exception {
        //创建模式对象
        createSchema();
        //添加Wine数据库表
        addWineTable();

        //生成GreenDao代码到相应目录
        generateGreenDaoCode();
    }

    /**
     * 创建模式对象
     */
    private static void createSchema() {
        manageSchema = new Schema(SCHEMA_NAME, DB_VERSION, PACKAGE);
    }

    /**
     * 生成GreenDao代码到相应目录
     *
     * @throws Exception
     */
    private static void generateGreenDaoCode() throws Exception {
        new DaoGenerator().generateAll(manageSchema, CODE_PATH);
    }

    /**
     * 添加shop数据库表
     */
    private static void addWineTable() {
        Entity wine = manageSchema.addEntity("Green_Wine_Shop");
        wine.setTableName("Shop");
        wine.addIdProperty().primaryKey().autoincrement();  //主键自动增长
        wine.addStringProperty("wine_name");  //酒品的所属分类
        wine.addStringProperty("wine_money");  //商品中文名称
        wine.addStringProperty("wine_num");  //商品英文名称
        wine.addBooleanProperty("isCheck");//商品选择
    }
}
