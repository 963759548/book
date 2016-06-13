package com.baidu.chapter7.section4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.baidu.chapter7.R;

public class MatrixActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        imageView = (ImageView) findViewById(R.id.imageView);
        //scale();
        //mirror();
        //rotate();
        translate();
    }

    // 缩放
    public void scale() {
        //获取原图
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        // 高度跟原始图片保持一致,宽度要*2, 否则显示不开了
        Bitmap newBm = Bitmap.createBitmap(bm.getWidth() * 2,
                bm.getHeight(), Bitmap.Config.ARGB_8888);
        //以新 Bitmap 构造一个画布
        Canvas canvas = new Canvas(newBm);
        //创建一个 Matrix 对象
        Matrix matrix = new Matrix();
//        //矩阵值
//        float[] values = new float[]{2, 0, 0, //x=2*x+0*y+0*z
//                0, 1, 0, //y=0*x+1*y+0*z
//                0, 0, 1};//z=0*x+0*y+1*z
//        matrix.setValues(values);
        //参数1 宽度缩放比例  参数2 高度缩放比例
        // 参数3 和参数4 基于哪个点进行缩放 0.5f 0.5f分别代表x的中心和y的中心
        matrix.setScale(2, 1, 0.5f, 0.5f);
        //将原始图片乘以矩阵后画到画布上
        canvas.drawBitmap(bm, matrix, null);
        imageView.setImageBitmap(newBm);
    }

    // 镜面
    public void mirror() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        Bitmap newBm = Bitmap.createBitmap(bm.getWidth(),
                bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBm);
        Matrix matrix = new Matrix();

        float[] values = new float[]{1, 0, 0, //x=1*x+0*y+0*z
                0, -1, 0, //y=0*x-1*y+0*z
                0, 0, 1};//z=0*x+0*y+1*z
        matrix.setValues(values);
        //因为镜面成像以后，图片 y 轴全为负数，跑出了屏幕范围，
        // 因此为了看到效果把图像往 y 轴正方向移动一个图片的高度
        matrix.postTranslate(0, bm.getHeight());
        canvas.drawBitmap(bm, matrix, null);
        imageView.setImageBitmap(newBm);
    }

    //旋转
    public void rotate() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        Bitmap newBm = Bitmap.createBitmap(bm.getWidth(),
                bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBm);
        Matrix matrix = new Matrix();
        //以图片中心点为基准点,顺时针旋转 30 度
        matrix.setRotate(30, bm.getWidth() / 2, bm.getHeight() / 2);
        canvas.drawBitmap(bm, matrix, null);
        imageView.setImageBitmap(newBm);
    }

    // 位移
    public void translate() {
        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        Bitmap newBm = Bitmap.createBitmap(bm.getWidth(),
                bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBm);
        Matrix matrix = new Matrix();
        //像 x 轴方向移动 10，y 轴方向移动 40
        matrix.setTranslate(10, 40);
        canvas.drawBitmap(bm, matrix, null);
        imageView.setImageBitmap(newBm);
    }
}
