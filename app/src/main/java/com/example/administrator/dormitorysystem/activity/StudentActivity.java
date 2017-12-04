package com.example.administrator.dormitorysystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Student;
import com.youth.banner.Banner;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class StudentActivity extends BaseActivity {

    @Bind(R.id.tb_stduent)
    Toolbar tbStduent;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.bu_find)
    Button buFind;
    @Bind(R.id.bu_notice)
    Button buNotice;
    @Bind(R.id.bu_repair)
    Button buRepair;
    @Bind(R.id.bu_comment)
    Button buComment;
    @Bind(R.id.cm_person)
    CircleImageView cmPerson;
    @Bind(R.id.tv_true_name)
    TextView tvTrueName;
    @Bind(R.id.tv_id)
    TextView tvId;
    @Bind(R.id.tv_tel)
    TextView tvTel;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_garde)
    TextView tvGarde;
    @Bind(R.id.tv_collega)
    TextView tvCollega;
    @Bind(R.id.tv_class)
    TextView tvClass;
    @Bind(R.id.tv_dorNum)
    TextView tvDorNum;
    @Bind(R.id.tv_dorDetaliNum)
    TextView tvDorDetaliNum;
    private Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        ButterKnife.bind(this);
        student = (Student) getIntent().getSerializableExtra("sInfo");
        initData();
    }

    private void initData() {
        tvTrueName.setText(student.getName().toString());

        tvId.setText(student.getUsername().toString());

        tvTel.setText(student.getMobilePhoneNumber().toString());

        tvSex.setText(student.getSex().toString());

        tvGarde.setText(student.getGrade().toString());

        tvCollega.setText(student.getCollega().toString());

        tvClass.setText(student.getClassName().toString());

        tvDorNum.setText(student.getDorNum().toString());

        tvDorDetaliNum.setText(student.getDorDetaliNum().toString());

        if (student.getNickUrl().isEmpty()){
            Glide.with(this).load("http://bmob-cdn-13164.b0.upaiyun.com/2017/09/04/b1b8899cc0934c899bc86f88bafdf302.jpg").into(cmPerson);
        }else{
            Glide.with(this).load(student.getNickUrl().toString()).into(cmPerson);
        }

    }

    @OnClick({R.id.bu_find, R.id.bu_notice, R.id.bu_repair, R.id.bu_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bu_find:
                break;
            case R.id.bu_notice:
                break;
            case R.id.bu_repair:
                break;
            case R.id.bu_comment:
                break;
        }
    }
}
