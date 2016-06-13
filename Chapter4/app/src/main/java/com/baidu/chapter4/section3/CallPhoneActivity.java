package com.baidu.chapter4.section3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baidu.chapter4.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

public class CallPhoneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone);
    }

    public void callPhone(View v) {
        // 检查程序具备拨打电话的授权
        int i = ContextCompat.checkSelfPermission(this, Manifest.permission
                .CALL_PHONE);
        if (i != PackageManager.PERMISSION_GRANTED) {
            //  如果发现没有授权 ,申请授权
            ActivityCompat.requestPermissions(this, new String[]{Manifest
                            .permission.CALL_PHONE},
                    1);
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:5333548"));
        startActivity(intent);
    }

    public void callPhoneUseRxPemissions(){
        RxPermissions.getInstance(this)
                // 申请权限
                .request(Manifest.permission.CALL_PHONE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean granted) {
                        if(granted){
                            //请求成功
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:5333548"));
                            startActivity(intent);
                        }else{
                            // 请求失败
                        }
                    }
                });
    }

    //  当用户选择是否允许
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
        if (requestCode == 1) {
            // 证明申请到了权限
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:5333548"));
                startActivity(intent);
            }
        }
    }
}
