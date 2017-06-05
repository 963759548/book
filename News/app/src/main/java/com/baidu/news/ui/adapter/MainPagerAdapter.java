package com.baidu.news.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baidu.news.ui.fragment.CaijingFragment;
import com.baidu.news.ui.fragment.JunshiFragment;
import com.baidu.news.ui.fragment.KejiFragment;
import com.baidu.news.ui.fragment.ShehuiFragment;
import com.baidu.news.ui.fragment.TiyuFragment;
import com.baidu.news.ui.fragment.ToutiaoFragment;
import com.baidu.news.ui.fragment.YuleFragment;

/**
 * Created by yll on 2017/2/8.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"头条","社会","娱乐","体育","军事","科技","财经"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new ToutiaoFragment();
        }else if(position==1){
            return new ShehuiFragment();
        }else if(position==2){
            return new YuleFragment();
        }else if(position==3){
            return new TiyuFragment();
        }else if(position==4){
            return new JunshiFragment();
        }else if(position==5){
            return new KejiFragment();
        }else {
            return new CaijingFragment();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
