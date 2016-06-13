package com.baidu.chapter6.section5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.chapter6.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParserJsonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parser_json);
        //为了方便观察结果,用华丽的分割线分开
        Log.i("ParserJsonActivity","----parseJson1-----");
        parseJson1();
        Log.i("ParserJsonActivity","----parseJson2-----");
        parseJson2();
        Log.i("ParserJsonActivity","----parseJson3-----");
        parseJson3();
    }
    public void parseJson1(){
        String json1="{\"name\" : \"jack\", \"age\" : 10}";
        try {
            //把要解析的json通过构造方法告诉JSONObject
            JSONObject jsonObject=new JSONObject(json1);
            // 获取name
            String name=jsonObject.getString("name");
            int age=jsonObject.getInt("age");
            Log.i("ParserJsonActivity","name:"+name);
            Log.i("ParserJsonActivity","age:"+age);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void parseJson2(){
        String json2="{\"names\" : [\"jack\", \"rose\", \"jim\"]}";
        try {
            //把要解析的json通过构造方法告诉JSONObject
            JSONObject jsonObject=new JSONObject(json2);
            //names对应的 JsonArray
            JSONArray jsonArray=jsonObject.getJSONArray("names");
            //遍历JSONArray
            for(int i=0;i<jsonArray.length();i++){
                Log.i("ParserJsonActivity","name"+i+":"+jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseJson3(){
        String json3="[{\"name\" : \"jack\", \"age\" : 10},{\"name\" : \"rose\", \"age\" : 11}]";
        try {
            //把要解析的json通过构造方法告诉JSONArray
            JSONArray jsonArray=new JSONArray(json3);
            //遍历JSONArray
            for(int i=0;i<jsonArray.length();i++){
                //根据i的位置获取JSONObject
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                int age=jsonObject.getInt("age");
                Log.i("ParserJsonActivity","name"+i+":"+name);
                Log.i("ParserJsonActivity","age"+i+":"+age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
