package com.baidu.chapter5.section4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.chapter5.R;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        Button createDB = (Button) findViewById(R.id.button);
        assert createDB != null;
        createDB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 升级数据库时 最后一个参数改成2
        PersonOpenHelper openHelper = new PersonOpenHelper(this, "person.db", null, 2);
        // 创建数据库
        openHelper.getWritableDatabase();
        Toast.makeText(this, "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    public SQLiteDatabase getDataBase() {
        PersonOpenHelper openHelper = new PersonOpenHelper(this, "person.db", null, 2);
        return openHelper.getWritableDatabase();
    }

    public void insert(View v) {
        //获取数据库对象
//        SQLiteDatabase dataBase = getDataBase();

//        // ? 为占位符
//        String sql = "insert into person(name,age,phone) values(?,?,?)";
//        //执行sql语句 通过 Object[] 依次用实际数据替换sql语句中的 ?占位符
//        dataBase.execSQL(sql, new
//                Object[]{"zhangsan", "10", "13333333333"});

//        ContentValues values = new ContentValues();
//        values.put("person_name", "zhangsan");
//        values.put("age", 10);
//        values.put("phone", "1333333333");
//        //第一个参数 table，代表要将数据插入哪家表
//        // 第二个参数nullColumnHack，字符串类型，指明如果某一字段没有值，
//        // 那么会将该字段的值设为 NULL一般给该参数传递 null 就行
//        //第三个参数ContentValues 类似一个Map<key,value>的数据结构，
//        // key是表中的字段，value 是值
//        dataBase.insert("person_info", null, values);
//
//        //关闭数据库,回收资源
//        dataBase.close();
        testTransactionEfficient();
    }
    public void testTransactionEfficient(){
        SQLiteDatabase dataBase = getDataBase();
        // ------测试不使用事务时插入 1w 条数据耗时--------------------
        long beginTime = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            ContentValues values = new ContentValues();
            values.put("person_name", "zhangsan");
            values.put("age", 10);
            values.put("phone", "1333333333");
            dataBase.insert("person_info", null, values);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("不使用事务插入 1w 条数据耗时"+(endTime-beginTime)+"毫秒");
        // ---------测试使用事务时耗时-----------------------
        beginTime = System.currentTimeMillis();
        dataBase.beginTransaction();
        for(int i=0;i<10000;i++){
            ContentValues values = new ContentValues();
            values.put("person_name", "zhangsan");
            values.put("age", 10);
            values.put("phone", "1333333333");
            dataBase.insert("person_info", null, values);
        }
        dataBase.setTransactionSuccessful();
        dataBase.endTransaction();
        endTime = System.currentTimeMillis();
        System.out.println("使用事务插入 1w 条数据耗时"+(endTime-beginTime)+"毫秒");
    }


    public void update(View v) {
        SQLiteDatabase dataBase = getDataBase();
//        String sql = "update person_info set age=? where person_name=?";
//        //将 zhangsan 的年龄修改为 20
//        dataBase.execSQL(sql,new String[]{"20","zhangsan"});
        ContentValues values = new ContentValues();
        values.put("age", "20");
/*
* 第一个参数 table，要更新的表名
* 第二个参数 ContentValues 设置要修改的字段的新值，没有涉及到的字
段则默认不修改
第三个参数 whereArgs ,如果有条件选项，对应的条件选项的具体参
数，没有写 null
第四个参数 是给第三个参数中的占位符提供相应的值
*/
        dataBase.update("person_info", values, "person_name=?", new
                String[]{"zhangsan"});
        dataBase.close();
    }

    public void query(View v) {
//        SQLiteDatabase dataBase = getDataBase();
//        String sql = "select person_name,age,phone from person_info where person_name=?";
//        //执行 rawQuery 查询，返回 Cursor 对象
//        Cursor cursor = dataBase.rawQuery(sql , new
//                String[]{"zhangsan"});
//        ////如果游标还有下一个元素，跟我们集合中 Iterator 中 hasNext()方法类似
//        if(cursor.moveToNext()){
//            //获取当前游标的第 0 个元素，元素是从 0 开始的，而不是 1
//            String person_name = cursor.getString(0);
//            //也可以通过列名来查询该字段在游标中的位置
//            int age = cursor.getInt(cursor.getColumnIndex("age"));
//            String phone = cursor.getString(2);
//            // 输出结果
//            Log.i("result",person_name + "..." + age + "..." + phone);
//        }
//        //关闭游标
//        cursor.close();
        SQLiteDatabase dataBase = getDataBase();
        Cursor cursor = dataBase.query("person_info",
                new String[]{"person_name,age,phone"},
                "person_name=?",
                new String[]{"zhangsan"},
                null, null, null);
        //如果游标还有下一个元素，跟我们集合中 Iterator 中 hasNext()方法类似
        if (cursor.moveToNext()) {
            String person_name = cursor.getString(0);
            //也可以通过列名来查询该字段在游标中的位置
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String phone = cursor.getString(2);
            // 输出结果
            Log.i("result", person_name + "..." + age + "..." + phone);
        }
        //关闭游标
        cursor.close();
    }

    public void delete(View v) {
        SQLiteDatabase dataBase = getDataBase();
        // dataBase.execSQL("delete from person_info where person_name=?",new Object[]{"zhangsan"});

        dataBase.delete("person_info", "person_name=?", new String[]{"zhangsan"});

        dataBase.close();
    }

    public void queryAll(View v) {
        SQLiteDatabase dataBase = getDataBase();
        Cursor cursor = dataBase.query("person_info", null, null, null,
                null, null, null);
        //如果游标还有下一个元素，跟我们集合中 Iterator 中 hasNext()方法类似
        while (cursor.moveToNext()) {
            String person_name = cursor.getString(cursor.getColumnIndex("person_name"));
            //也可以通过列名来查询该字段在游标中的位置
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            // 输出结果
            Log.i("result", person_name + "..." + age + "..." + phone);
        }
        //关闭游标
        cursor.close();
    }

    public void testTransaction() {
        // 假设数据表person_money中有name和balance两列,有张三和lisi
        PersonOpenHelper helper = new PersonOpenHelper(this, "person.db", null, 1);
        SQLiteDatabase database = helper.getWritableDatabase();
        try {
            //开启事务
            database.beginTransaction();
            database.execSQL("update person_money set balance = balance-100 where name = ? "
                    ,new String[]{" lisi "});
            //当把 int a=1/0;放开的时候，发现抛出异常，那么事务就会回滚，
            // 上面的扣除 lsii 的 100 元钱不会被真正执行
            //如果把 int a = 1/0;注释掉，才发现事务成功了，
            // lisi 的钱被扣除了 100 元，同时 zhangsan 的钱也多了 100 元。
            int a = 1 / 0;
            database.execSQL("update person_money set balance = balance+100 where name = ? "
                    ,new String[]{" zhangsan "});
            //设置事务成功，也就是只有当代码执行到此行，才代表事务已经成功
            database.setTransactionSuccessful();
        } finally {
            //提交事务，如果 setTransactionSuccessful（）方法已经执行，则beginTransaction（）后的语句执行成功
            //否则，事务回滚到开启事务前的状态
            database.endTransaction();
        }
    }
}
