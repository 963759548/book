package com.baidu.chapter6.section4;

import android.accounts.NetworkErrorException;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.chapter6.R;
import com.baidu.chapter6.section5.Bean;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MobileActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        textView= (TextView) findViewById(R.id.textView);
        button= (Button) findViewById(R.id.button);
        editText= (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);

        //监听文本变化
        editText.addTextChangedListener(new TextWatcher() {
            // 文本变化前
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            // 文本变化
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String num=s.toString();
                if(num.length()>7){
                    //TODO  .... 调用网络请求代码
                }
            }
            // 文本变化后
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    Handler handler=new Handler();
    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    String url="http://apis.juhe.cn/mobile/get";
                    String num = editText.getText().toString().trim();
                    url=url+"?phone="+num+"&key=e6d7409002af0588ba8679910833659f";
                    // 利用string url构建URL对象
                    URL mURL = new URL(url);
                    conn = (HttpURLConnection) mURL.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(10000);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        InputStream is = conn.getInputStream();
                         String response = getStringFromInputStream(is);
                        Log.i("MobileActivity",response);
                        //解析JSON
                        final String address=parJson(response);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(address);
                            }
                        });
                    } else {
                        throw new NetworkErrorException("response status is "+responseCode);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
    // 参考6.5章节
    private String parJson(String response) {
        Gson gson=new Gson();
        Bean bean=gson.fromJson(response, Bean.class);
        // 如果结果码为200返回号码归属地
        if("200".equals(bean.getResultcode())) {
            Bean.ResultBean body = bean.getResult();
            StringBuilder sb=new StringBuilder();
            //如果省份不为空 地址拼装省份
            if(!TextUtils.isEmpty(body.getProvince())){
                sb.append(body.getProvince());
            }
            // 城市
            if(!TextUtils.isEmpty(body.getCity())){
                sb.append(body.getCity());
            }

            return sb.toString();
        }else{
          // 状态码不为200 返回错误信息
            return bean.getReason();
        }
    }

    // 把InputStream转换成String
    private static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        // 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        String state = os.toString();
        os.close();
        return state;
    }
}
