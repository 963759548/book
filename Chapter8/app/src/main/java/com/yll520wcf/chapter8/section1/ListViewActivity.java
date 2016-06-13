package com.yll520wcf.chapter8.section1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yll520wcf.chapter8.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    //数据
//    String[] datas=new String[]{"阿尔巴尼亚","安道尔","奥地利",
//            "白俄罗斯","保加利亚","法国", "德国","意大利","葡萄牙","罗马尼亚",
//            "俄罗斯","塞尔维亚","西班牙", "英国"};
    private List<Country> countryList=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView= (ListView) findViewById(R.id.list_view);
        //初始化数据
        initDatas();
        //适配器
        // 参数1 上下文
        // 参数2 每个条目的布局,这里使用Android SDK提供的布局。
        // 参数3 数据
//        ArrayAdapter<String> adapter=
//                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,datas);
        //给ListView设置适配器,从而显示到界面上。
        final CountryAdapter adapter=
                new CountryAdapter(this,R.layout.item_country,countryList);
        listView.setAdapter(adapter);
        // 设置ListView条目的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // parent  指的是ListView
            // view    指的是被点击条目的View
            // position 条目的位置
            // id 如果Adapter继承自ArrayAdapter, id和postion一致。
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        countryList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                countryList.remove(position);//删除长按位置的数据
                adapter.notifyDataSetChanged();//刷新ListView界面
                return true;
            }
        });
    }

    private void initDatas() {
        countryList.add(new Country("阿尔巴尼亚",R.drawable.albania));
        countryList.add(new Country("安道尔",R.drawable.andorra));
        countryList.add(new Country("奥地利",R.drawable.austria));
        countryList.add(new Country("白俄罗斯",R.drawable.belarus));
        countryList.add(new Country("保加利亚",R.drawable.belgium));
        countryList.add(new Country("法国",R.drawable.france));
        countryList.add(new Country("德国",R.drawable.germany));
        countryList.add(new Country("意大利",R.drawable.italy));
        countryList.add(new Country("葡萄牙",R.drawable.portugal));
        countryList.add(new Country("罗马尼亚",R.drawable.romania));
        countryList.add(new Country("俄罗斯",R.drawable.russia));
        countryList.add(new Country("塞尔维亚",R.drawable.serbia));
        countryList.add(new Country("西班牙",R.drawable.spain));
        countryList.add(new Country("英国",R.drawable.united_kingdom));
    }
}
