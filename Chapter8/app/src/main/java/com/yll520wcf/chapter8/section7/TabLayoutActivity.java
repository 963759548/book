package com.yll520wcf.chapter8.section7;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yll520wcf.chapter8.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {
    List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab_layout);

        LayoutInflater lf = LayoutInflater.from(this);
        View view1 = lf.inflate(R.layout.item_vp_0, null);
        View view2 = lf.inflate(R.layout.item_vp_1, null);
        View view3=lf.inflate(R.layout.item_vp_2,null);
        viewList=new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        ViewPager viewPager= (ViewPager) findViewById(R.id.view_pager);
        TabLayoutActivity.MyPageAdapter adapter=new TabLayoutActivity.MyPageAdapter();
        viewPager.setAdapter(adapter);
        //关联viewPager
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        for (int i = 0; i < adapter.getCount(); i++) {
            View view = lf.inflate(R.layout.custom_tab,null);
            TextView tv = (TextView) view.findViewById(R.id.tv_tab);
            tv.setText(adapter.getPageTitle(i));
            //设置自定义条目
            tabLayout.getTabAt(i).setCustomView(view);
        }
    }
    private class MyPageAdapter extends PagerAdapter {
        // 返回条目的数量
        @Override
        public int getCount() {
            return viewList.size();
        }
        // 判断一下 添加view和返回object的关系
        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }
        // 在该方法中 添加view 返回一个对象 一般情况返回view
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=viewList.get(position);
            container.addView(view); // container 添加了view
            return view;
        }
        // 销毁一个条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("MyPageAdapter","回收了哪一个位置" + position);
            container.removeView(viewList.get(position));
        }
        //指定每个页面的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return "条目"+position;
        }
    }
}
