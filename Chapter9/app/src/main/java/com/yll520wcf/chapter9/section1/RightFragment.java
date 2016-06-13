package com.yll520wcf.chapter9.section1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yll520wcf.chapter9.R;

public class RightFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        //使用打气筒生成一个 View 对象
        //参数1 布局,  参数2 挂载的父容器,  参数3 是否主动挂载,此处为false
        View view = inflater.inflate(R.layout.fragment_right, container,false);
        return view;
    }
}
