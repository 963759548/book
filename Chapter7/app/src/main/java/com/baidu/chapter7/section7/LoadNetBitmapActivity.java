package com.baidu.chapter7.section7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.baidu.chapter7.R;
import com.squareup.picasso.Picasso;

public class LoadNetBitmapActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_net_bitmap);
        imageView= (ImageView) findViewById(R.id.image_view);
        String url="http://www.baidu.com/img/bdlogo.gif";
        Picasso.with(this).load(url).into(imageView);

    }
// 伪代码
//    public Bitmap getBitmap(String url){
//        if(内存中有图片){
//            return 内存中的图片;
//        }
//        if(硬盘中有图片){
//            if(内存足够) {
//                从硬盘加载到内存;
//            }else{
//                移除内存中之前不常用的图片;
//                从硬盘加载到内存;
//            }
//            return 内存中的图片;
//        }
//        异步联网加载url;
//        //加载完成
//        if(硬盘空间足够大){
//            保存到文件中;
//        }else{
//            删除不常用的图片;
//            保存到文件中;
//        }
//        if(内存不足了){
//            移除之前不常用的图片;
//            把图片加载到内存中;
//        }
//        return 内存中的图片;
//    }
}
