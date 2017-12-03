package com.example.administrator.dormitorysystem.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Administrato;
import com.example.administrator.dormitorysystem.bean.Student;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.iv_login_background)
    ImageView ivLoginBackground;
    @Bind(R.id.et_login_account)
    EditText etLoginAccount;
    @Bind(R.id.et_login_password)
    EditText etLoginPassword;
    @Bind(R.id.ll_login)
    LinearLayout llLogin;
    @Bind(R.id.rb_administrato)
    RadioButton rbAdministrato;
    @Bind(R.id.rb_student)
    RadioButton rbStudent;
    @Bind(R.id.rg_choose)
    RadioGroup rgChoose;
    @Bind(R.id.bu_login)
    Button buLogin;
    @Bind(R.id.bu_registered)
    Button buRegistered;
    @Bind(R.id.tv_loginRule)
    TextView tvLoginRule;
//1回来请吃东西
    //2份吃的
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Bmob.initialize(this,"b499fe8d6326dbc2623632d893910526");
        rgChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

            }
        });
    }

    @OnClick({R.id.bu_login, R.id.bu_registered, R.id.tv_loginRule})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bu_login:
                if (isChoose()){
                    if(isStudent()){
                        loginStudent();
                    }else{
                        loginAdministrato();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "请选择你的身份再进行注册", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bu_registered:
                if (isChoose()){
                    if(isStudent()){
                        resgisterStudent();

                    }else{
                        resgisterAdministrato();

                    }
                }else {
                    Toast.makeText(LoginActivity.this, "请选择你的身份再进行注册", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_loginRule:
                showNormalDialog();
                break;
        }
    }
    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.drawable.rule);
        normalDialog.setTitle("登录说明n(*≧▽≦*)n");
        normalDialog.setMessage("账号说明：请用学号/工号作为账号名进行登录。");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        // 显示
        normalDialog.show();
    }
    public boolean isChoose(){
        return rbAdministrato.isChecked()||rbStudent.isChecked();
    }
    public boolean isStudent(){
        return rbStudent.isChecked();
    }
    public void resgisterStudent(){
        Student student = new Student();
        student.setUsername(etLoginAccount.getText().toString());
        student.setPassword(etLoginPassword.getText().toString());
        student.signUp(new SaveListener<Student>() {
            @Override
            public void done(Student student, BmobException e) {
                if (e==null){
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,DetailStudentActivity.class));
                }else if(e.getErrorCode() == 9016){
                    Toast.makeText(LoginActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "该用户名已经被注册", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void loginStudent(){
        Student student = new Student();
        student.setUsername(etLoginAccount.getText().toString());
        student.setPassword(etLoginPassword.getText().toString());
        student.login(new SaveListener<Student>() {
            @Override
            public void done(Student student, BmobException e) {
                if (e == null){
                    Student student1 = BmobUser.getCurrentUser(Student.class);
                    if(student1.getPer()==false){
                        //tiao
                    }else{
                        Toast.makeText(LoginActivity.this, "请选择管理员方式登录", Toast.LENGTH_SHORT).show();
                    }
                }else if (e.getErrorCode() == 9016){
                    Toast.makeText(LoginActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "请检查你的账号密码是否正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void loginAdministrato(){
        Administrato administrato = new Administrato();
        administrato.setUsername(etLoginAccount.getText().toString());
        administrato.setPassword(etLoginPassword.getText().toString());
        administrato.login(new SaveListener<Administrato>() {
            @Override
            public void done(Administrato administrato, BmobException e) {
                if (e == null){
                    Administrato administrato1 = BmobUser.getCurrentUser(Administrato.class);
                    if(administrato1.getPer()==true){
                        //tiao
                    }else{
                        Toast.makeText(LoginActivity.this, "请选择学生方式登录", Toast.LENGTH_SHORT).show();
                    }
                }else if (e.getErrorCode() == 9016){
                    Toast.makeText(LoginActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "请检查你的账号密码是否正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void resgisterAdministrato(){
        Administrato ad = new Administrato();
        ad.setUsername(etLoginAccount.getText().toString());
        ad.setPassword(etLoginPassword.getText().toString());
        ad.signUp(new SaveListener<Student>() {
            @Override
            public void done(Student student, BmobException e) {
                if (e==null){
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,DetailAdministratoActivity.class));
                }else if(e.getErrorCode() == 9016){
                    Toast.makeText(LoginActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "该用户名已经被注册", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
