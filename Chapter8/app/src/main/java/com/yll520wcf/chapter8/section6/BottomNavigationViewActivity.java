package com.yll520wcf.chapter8.section6;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.yll520wcf.chapter8.R;

public class BottomNavigationViewActivity extends AppCompatActivity {
    private TextView textView;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_view);

        textView = (TextView) findViewById(R.id.text);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);

        //选中条目的监听事件
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        textView.setText(item.getTitle().toString());
                        return true;
                    }
                });
    }
}
