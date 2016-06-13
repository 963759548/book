package com.yll520wcf.chapter8.section1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yll520wcf.chapter8.R;

import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    int resourceId;

    public CountryAdapter(Context context, int resource, List<Country> objects) {
        super(context, resource, objects);
        resourceId = resource;//记录布局资源id
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Country country = getItem(position);//获取当前位置条目的数据实例
        View view;
        ViewHolder viewHolder = null;
        //当convertView不为空 复用converView
        if (convertView == null) {
            viewHolder = new ViewHolder();//创建ViewHolder
            //把xml转换成view对象
            view = View.inflate(getContext(), resourceId, null);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.iv_flag);
            viewHolder.textView = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);// 将viewHolder保存到view对象中
        } else {
            view = convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        //设置数据
        viewHolder.imageView.setImageResource(country.getImageId());
        viewHolder.textView.setText(country.getName());
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
