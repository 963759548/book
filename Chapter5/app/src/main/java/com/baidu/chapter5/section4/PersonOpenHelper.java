package com.baidu.chapter5.section4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PersonOpenHelper extends SQLiteOpenHelper{
    /**创建Person表的SQL语句*/
    public static final String CREATE_PERSON="create table person_info(" +
            "  id integer primary key autoincrement," +
            "  person_name varchar(20)," +
            "  age integer(10)," +
            "  phone varchar(20));";
    /**
     * @param context 上下文对象
     * @param name  数据库名称
     * @param factory  游标结果集工厂，如果需要使用则需要自定义结果集工厂，null 值代表使用默认结果集工厂
     * @param version  数据库版本号，必须大于等于 1
     */
    public PersonOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 数据库创建时执行 一般只执行一次
        db.execSQL(CREATE_PERSON); // 执行创建表的SQL语句
    }
    /**
     * 数据库更新的时候调用该方法
     * @param db 当前操作的数据库对象
     * @param oldVersion 老版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库更新时执行
        // 删除之前的person_info表
        //db.execSQL("drop table if exists person_info");
        //db.execSQL(CREATE_PERSON); // 执行创建表的SQL语句
        if (oldVersion == 1 && newVersion == 2) {
            db.execSQL("alter table person_info add column phone varchar(20)");
        }
    }
}
