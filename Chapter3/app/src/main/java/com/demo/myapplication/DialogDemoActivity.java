package com.demo.myapplication;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
    }

    // 一般对话框
    public void normalDialog(View v) {
        //先得到构造器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("提示");
        builder.setMessage("是否确认退出");   //设置内容
        builder.setIcon(R.mipmap.ic_launcher); //自定义图标
        builder.setCancelable(false);           //设置是否能点击，对话框的其他区域取消
        //设置其确认按钮和监听事件
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //设置其取消按钮和监听事件
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //设置其忽略按钮和监听事件
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        // 下面两行代码 可以合成一行 builder.show()
        Dialog dialog=builder.create();       //创建对话框
        dialog.show();         //显示对话框
    }

    //列表对话框
    public void listDialog(View v) {
        final String items[] = {"AAA", "BBB", "CCC"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        //设置列表显示，注意设置了列表显示就不要设置builder.setMessage()了，否则列表不起作用。
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    // 单选按钮对话框
    public void singleDialog(View v) {
        final String items[] = {"男", "未知", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("提示");
        //设置图标，图片id即可
        builder.setIcon(R.mipmap.ic_launcher);

        //设置单选按钮
        //  items   为列表项
        //  0       为默认选中第一个
        //  第三个参数是监听器
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), items[which], Toast.LENGTH_SHORT).show();
            }
        });
        //  设置监听器
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "确定", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    // 多选对话框
    public void mulDialog(View v) {
        final String items[] = {"篮球", "足球", "排球"};
        final boolean selected[] = {true, false, true};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("爱好"); //设置标题
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可

        //  参数一：列表项
        //  参数二：默认打勾的
        //  参数三：监听器
        builder.setMultiChoiceItems(items, selected,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        // dialog.dismiss();
                        Toast.makeText(getApplicationContext(),
                                items[which] + isChecked, Toast.LENGTH_SHORT).show();
                    }
                });
        //  确认按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "确定", Toast.LENGTH_SHORT).show();
                //android会自动根据你选择的改变selected数组的值。
                for (int i = 0; i < selected.length; i++) {
                    Log.i("mulDialog", "" + selected[i]);
                }
            }
        });
        builder.create().show();
    }

    // 自定义对话框
    public void customDialog(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //设置标题
        builder.setTitle("自定义dialog");
        //设置图标，图片id即可
        builder.setIcon(R.mipmap.ic_launcher);
        //  载入布局
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_custom, null);
        builder.setView(layout);
        //  初始化自定义布局中的控件, 因为控件在自定义layout中, 必须用layout.findViewByID
        EditText editText_name = (EditText) layout.findViewById(R.id.editText_name);
        EditText editText_password = (EditText) layout.findViewById(R.id.editText_password);
        //  确认按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //  显示
        builder.create().show();
    }
    // 圆形进度条
    public void showProgressDialog(View v){
        // 参数必须也是Activity对象
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载，请稍后...");
        progressDialog.setCancelable(true);//设置是否能取消加载
        progressDialog.show(); //显示进度条
    }
    // 水平进度条
    public void showHorizontalProgressDialog(View v) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在加载，请稍后...");
        //设置进度条的风格（水平）
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);//设置是否能取消加载
        progressDialog.setMax(100);//设置最大进度,默认就是100
        progressDialog.show(); //显示进度条
        progressDialog.setProgress(50);//设置默认进度
    }
}
