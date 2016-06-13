package com.yll520wcf.chapter8.section2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.yll520wcf.chapter8.R;
import com.yll520wcf.chapter8.section1.Country;
import com.yll520wcf.chapter8.section1.CountryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private List<Country> countryList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        GridView gridView= (GridView) findViewById(R.id.grid_view);
        initDatas();//初始化数据
        //创建Adapter
        final CountryAdapter adapter=
                new CountryAdapter(this,R.layout.item_grid_view,countryList);
        assert gridView != null;
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
