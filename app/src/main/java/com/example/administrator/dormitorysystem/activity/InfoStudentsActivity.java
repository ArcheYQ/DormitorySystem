package com.example.administrator.dormitorysystem.activity;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.adapter.StudnetsInfoAdapter;
import com.example.administrator.dormitorysystem.bean.Student;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by reoger on 2017/12/9.
 *
 */

public class InfoStudentsActivity extends BaseActivity {

    private ListView mListView;
    private StudnetsInfoAdapter studnetsInfoAdapter;
    private List<Student> mData;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studen_info);

        initView();
        initData();
        studnetsInfoAdapter = new StudnetsInfoAdapter(this);
        mListView.setAdapter(studnetsInfoAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student s = mData.get(i);
                Bundle bundle = new Bundle();
                bundle.putSerializable("student_info",s);
                Intent intent = new Intent(InfoStudentsActivity.this,StudentDetailInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        BmobQuery<Student> query = new BmobQuery<>();
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if(e == null){
                    mData = list;
                    studnetsInfoAdapter.setContent(mData);
                }else{
                    toastShow("数据加载失败"+e);
                }
            }
        });
    }

    private void initView() {
        initHome();
        setToolBar(R.id.tb_student_info);
        mListView = (ListView) findViewById(R.id.list_students_info);
        mFab = (FloatingActionButton) findViewById(R.id.fb_search_students_info);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dailog_search_parameter, null);
        // dialog.setView(view);// 将自定义的布局文件设置给dialog
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        final EditText etUserName = view
                .findViewById(R.id.ed_username);
        final EditText etDorNum = view.findViewById(R.id.ed_dorDetaliNum);


     view.findViewById(R.id.bu_search_username).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etUserName.getText())){
                    toastShow("请输入用户名后再进行查询");
                }else{
                    searchByCondition("username",etUserName, dialog);
                }
            }
        });


     view.findViewById(R.id.bu_dorDetaliNum).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            if(TextUtils.isEmpty(etDorNum.getText())){
                toastShow("请输入宿舍门牌号后再进行查询");
            }else{
                searchByCondition("dorDetaliNum",etDorNum, dialog);
            }
         }
     });
        dialog.show();
    }


    private void searchByCondition(String str,EditText etUserName, final AlertDialog dialog) {
        BmobQuery<Student> studentBmobQuery = new BmobQuery<>();
        studentBmobQuery.addWhereEqualTo(str,etUserName.getText().toString());
        studentBmobQuery.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
              if(e == null){
                  if(list.size() == 0){
                      toastShow("没有符合条件的结果！");
                  }else{
                      mData = list;
                      studnetsInfoAdapter.setContent(mData);
                      dialog.dismiss();
                  }
              }
            }
        });
    }


}
