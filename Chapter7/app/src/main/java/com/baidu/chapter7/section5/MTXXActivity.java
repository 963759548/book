package com.baidu.chapter7.section5;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.baidu.chapter7.R;

public class MTXXActivity extends AppCompatActivity
        implements SeekBar.OnSeekBarChangeListener {
    //声明页面控件对象
    private ImageView iv;
    private SeekBar sb_red;
    private SeekBar sb_green;
    private SeekBar sb_blue;
    private SeekBar sb_rgb;
    //初始化一个矩阵数组
    private float[] arrays = new float[]{
            1,0,0,0,0,
            0,1,0,0,0,
            0,0,1,0,0,
            0,0,0,1,0
    };
    //声明一个颜色过滤器
    private ColorFilter colorFilter = new ColorMatrixColorFilter(arrays);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtxx);
        //实例化界面控件
        sb_red = (SeekBar) findViewById(R.id.sb_red);
        sb_green = (SeekBar) findViewById(R.id.sb_green);
        sb_blue = (SeekBar) findViewById(R.id.sb_blue);
        sb_rgb = (SeekBar) findViewById(R.id.sb_rgb);
        iv = (ImageView) findViewById(R.id.iv);
        //给 SeekBar 对象设置监听事件，
        //当前Activity实现了OnSeekBarChangeListener 接口
        sb_blue.setOnSeekBarChangeListener(this);
        sb_green.setOnSeekBarChangeListener(this);
        sb_red.setOnSeekBarChangeListener(this);
        sb_rgb.setOnSeekBarChangeListener(this);
    }
    // 当Seekbar进度改变的时候调用该方法
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        switch (id) {
            case R.id.sb_red:
                arrays[4] = progress;
                break;
            case R.id.sb_blue:
                arrays[14] = progress;
                break;
            case R.id.sb_green:
                arrays[9] = progress;
                break;
            case R.id.sb_rgb:
                arrays[4] = arrays[9] = arrays[14] = progress;
                break;
            default:
                break;
        }
        //修改对应的 RGB 值，穿创建新的 ColorFilter 对象
        colorFilter = new ColorMatrixColorFilter(arrays);
        //给 ImageView 控件设置颜色过滤器
        iv.setColorFilter(colorFilter);
    }
    //开始拖拽SeekBar 调用该方法
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }
    //停止拖拽SeekBar 调用该方法
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
