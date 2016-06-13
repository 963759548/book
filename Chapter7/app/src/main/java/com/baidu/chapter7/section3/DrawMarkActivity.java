package com.baidu.chapter7.section3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.baidu.chapter7.R;

public class DrawMarkActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_mark);
        imageView = (ImageView) findViewById(R.id.imageView);
        drawMark();
    }

    private void drawMark() {
        //获取原始图片
        Bitmap example =
                BitmapFactory.decodeResource(getResources(), R.mipmap.pp);
        //获取水印图片
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //创建一个新的空白 Bitmap 对象，用于合成后的额图片
        Bitmap bm_new = Bitmap.createBitmap(example.getWidth(),
                example.getHeight(), Bitmap.Config.RGB_565);
        //在上一步创建的 Bitmap 的基础上新建一个画布对象
        Canvas canvas = new Canvas(bm_new);
        //将原始图片绘制到画布上，第二个参数是左边，
        // 第三个参数是上边距，第四个参数是 Pain 对象，这里设置为 null
        canvas.drawBitmap(example, 0, 0, null);
            //新创建一个 Pain 对象
        Paint paint = new Paint();
        //设置两张图片的相交模式：Darken，
        // 注意：Porter、Duff 是两个发明人的合成单词，本身并没有任何意义
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        //在画布上将水印绘制上去, 0,0 代表图片左上角
       // canvas.drawBitmap(logo, 0, 0, paint);
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        canvas.drawText("爱上Android",100,100,paint);
        //在控件中显示合成后的图片
        imageView.setImageBitmap(bm_new);
    }

}
