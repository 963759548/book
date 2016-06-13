package com.baidu.chapter4.section6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baidu.chapter4.R;


public class AppBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle(" ");



//        ActionBar supportActionBar = getSupportActionBar();
//        supportActionBar.setTitle("我是ActionBar");
//        supportActionBar.setSubtitle("good");
//        supportActionBar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);  // 用ToolBar代替ActionBar
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击事件
            }
        });
        toolbar.setLogo(R.mipmap.ic_launcher);
    }
//
    // 创建了Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }
    //  当菜单条目被点击了
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this,"我是设置按钮",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id==R.id.action_1){
            Toast.makeText(this,"按钮1",Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id==R.id.action_2){
            Toast.makeText(this,"按钮2",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
