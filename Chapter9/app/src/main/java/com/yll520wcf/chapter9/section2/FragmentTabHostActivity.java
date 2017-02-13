package com.yll520wcf.chapter9.section2;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.yll520wcf.chapter9.R;


public class FragmentTabHostActivity extends AppCompatActivity {
    FragmentTabHost fragmentTabHost;
    //标签的图片
    private int imageIds[] = {R.drawable.tab1, R.drawable.tab2,
           };
    //加载的Fragment
    private Class<?> mFragmentClasses[] = {Fragment1.class, Fragment2.class};
    //标签文字
    private String[] text=new String[]{"标签1","标签2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);
        fragmentTabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        //初始化
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        fragmentTabHost.getTabWidget().setDividerDrawable(null); // 去掉分割线
        //添加标签
        for (int i = 0; i < imageIds.length; i++) {
            // Tab按钮添加文字和图片
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(i + "").setIndicator(getIndicatorView(i));
            // 添加Fragment
            fragmentTabHost.addTab(tabSpec, mFragmentClasses[i], null);
        }
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //切换标签时调用
            }
        });
    }
    //获取标签的View
    private View getIndicatorView(int i) {
        View view = View.inflate(this,
                R.layout.layout_indicator_view, null);
        ImageView ivTab = (ImageView) view.findViewById(R.id.iv_tab);
        TextView tvTag= (TextView) view.findViewById(R.id.tv_tab);
        ivTab.setImageResource(imageIds[i]);
        tvTag.setText(text[i]);
        return view;
    }
}
