package com.yll520wcf.chapter8.section3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.yll520wcf.chapter8.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    List<String> datas=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        initDatas();
        //创建默认的线性LayoutManager
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//      layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //参数为每列显示的数量
        //参数1 位
//        StaggeredGridLayoutManager layoutManager=
//                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
        assert recyclerView != null;
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        final MyAdapter adapter = new MyAdapter(datas);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String data, int position) {
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initDatas() {
        datas.add("阿尔巴尼亚");
        datas.add("安道尔");
        datas.add("奥地利");
        datas.add("白俄罗斯");
        datas.add("保加利亚");
        datas.add("法国");
        datas.add("德国");
        datas.add("意大利");
        datas.add("葡萄牙");
        datas.add("罗马尼亚");
        datas.add("俄罗斯");
        datas.add("塞尔维亚");
        datas.add("西班牙");
        datas.add("英国");
    }
}
