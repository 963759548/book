package com.yll520wcf.chapter8.section3;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yll520wcf.chapter8.R;

import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
        implements View.OnClickListener {
    public List<String> datas = null;
    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        //获取布局填充器
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        //把布局转换成View对象, 参数1 布局  参数2 父容器,  参数3 是否主动挂载到父容器上
        View view = layoutInflater.inflate(R.layout.item,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTextView.setText(datas.get(position));
        //将position保存在itemView的Tag中，以便点击时进行获取
        viewHolder.itemView.setTag(position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            int position= (int) v.getTag();
            mOnItemClickListener.onItemClick(v,datas.get(position),position);
        }

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }



    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View view){
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data,int position);
    }
    //添加数据
    public void addItem(String content, int position) {
        datas.add(position, content);
        notifyItemInserted(position);
    }
    //删除数据
    public void removeItem(String content,int position) {
        datas.remove(content);
        notifyItemRemoved(position);
    }

}

