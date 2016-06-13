package com.baidu.chapter6.section5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.chapter6.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UseGsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_gson);
        //为了方便观察结果,用华丽的分割线分开
        Log.i("UseGsonActivity","----parseJson1-----");
        useGsonParser1();
        Log.i("UseGsonActivity","----parseJson2-----");
        useGsonParser2();
        Log.i("UseGsonActivity","----parseJson3-----");
        useGsonParser3();
    }

    public  void  useGsonParser1(){
        String json1="{\"name\" : \"jack\", \"age\" : 10}";
        Gson gson=new Gson();
        //把json数据解析成Person1对象
        Person1 person1 = gson.fromJson(json1, Person1.class);
        Log.i("UseGsonActivity","name:"+person1.getName());
        Log.i("UseGsonActivity","age:"+person1.getAge());
    }
    public  void  useGsonParser2(){
        String json2="{\"names\" : [\"jack\", \"rose\", \"jim\"]}";
        Gson gson=new Gson();
        Person2 person2=gson.fromJson(json2,Person2.class);
        List<String> names = person2.getNames();
        for(int i=0;i<names.size();i++){
            Log.i("UseGsonActivity","name"+i+":"+names.get(i));
        }
    }
    public  void useGsonParser3(){
        String json3="[{\"name\" : \"jack\", \"age\" : 10},{\"name\" : \"rose\", \"age\" : 11}]";
        Gson gson=new Gson();
        List<Person1> person2=gson.fromJson(json3,new TypeToken<ArrayList<Person1>>(){}.getType());
        for(int i=0;i<person2.size();i++){
            //根据i的位置获取JSONObject
            Person1 person1 = person2.get(i);
            String name = person1.getName();
            int age=person1.getAge();
            Log.i("UseGsonActivity","name"+i+":"+name);
            Log.i("UseGsonActivity","age"+i+":"+age);
        }
    }
}
