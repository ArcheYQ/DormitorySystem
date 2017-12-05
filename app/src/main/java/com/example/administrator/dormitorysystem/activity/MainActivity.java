package com.example.administrator.dormitorysystem.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.activity.BaseActivity;
import com.example.administrator.dormitorysystem.bean.Administrato;
import com.example.administrator.dormitorysystem.bean.Student;

import cn.bmob.v3.BmobUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Student student = BmobUser.getCurrentUser(Student.class);
                Administrato administrato = BmobUser.getCurrentUser(Administrato.class);
                if(student != null){
                    Intent intent = new Intent(MainActivity.this,StudentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("sInfo",student);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else if (administrato !=null ){
                    Intent intent = new Intent(MainActivity.this,AdministratoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("aInfo",administrato);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();
                }else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 1000);

    }

    }

