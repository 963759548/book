package com.baidu.news.ui.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.news.R;
import com.baidu.news.utils.PreUtils;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private Button btnStart;// 开始体验
    private int[] imageIds = new int[]{R.drawable.guide_0,
            R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnStart = (Button) findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 表示新手引导已经展示过了,下次不再展示
                PreUtils.putBoolean(GuideActivity.this, "isFirst", false);
                // 跳到主页面
                Intent intent = new Intent(GuideActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //初始化展示的数据
        initData();
    }

    private void initData() {
        // 初始化4张引导图片数据
        imageViews = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(imageIds[i]);
            imageViews.add(image);
        }

        GuideAdapter adapter = new GuideAdapter();
        viewPager.setAdapter(adapter);// viewpager设置数据
        // 设置滑动监听
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //当显示最后一个条目的时候 跳转按钮可见
                if (position == imageIds.length - 1) {
                    btnStart.setVisibility(View.VISIBLE);
                } else {
                    btnStart.setVisibility(View.GONE);
                }
            }
        });
    }


    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        // 初始化界面数据,类似getView
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }
}
