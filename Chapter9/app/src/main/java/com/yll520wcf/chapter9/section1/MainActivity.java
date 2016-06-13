package com.yll520wcf.chapter9.section1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.yll520wcf.chapter9.R;

public class MainActivity extends AppCompatActivity {
    LeftFragment leftFragment;
    RightFragment rightFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm=getWindowManager();
        DisplayMetrics displayMetrics=new DisplayMetrics();
        //测量屏幕的尺寸
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        //获取屏幕宽高
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;

        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(widthPixels>heightPixels){
            // 当宽度大于高度,则表示手机处于横屏
            ft.replace(R.id.fl_container,getLeftFragment());
            ft.addToBackStack(null);
        }else{
            // 当宽度小于高度,则表示手机处于竖屏
            ft.replace(R.id.fl_container,getRightFragment());
            ft.addToBackStack(null);
        }
        ft.commit();
    }


    // 获取LeftFragment
    public LeftFragment getLeftFragment(){
        if(leftFragment==null)
            leftFragment=new LeftFragment();

        return leftFragment;
    }
    // 获取RightFragment
    public RightFragment getRightFragment(){
        if(rightFragment==null)
            rightFragment=new RightFragment();
        return rightFragment;
    }

}
