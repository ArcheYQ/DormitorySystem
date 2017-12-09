package com.example.administrator.dormitorysystem.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Notice;
import com.example.administrator.dormitorysystem.util.DateTimePickDialogUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by reoger on 2017/12/6.
 */



public class PublishNoticActivity extends BaseActivity {


    @Bind(R.id.tb_public_notice)
    Toolbar tbNewNotice;
    @Bind(R.id.et_add_notice_title)
    EditText etAddNoticeTitle;
    @Bind(R.id.et_add_notice_content)
    EditText etAddNoticeContent;
    @Bind(R.id.ed_add_notice_date)
    TextView edAddNoticeTime;

    @Bind(R.id.ed_add_notice_author)
    EditText edAddNoticeAuthor;
    @Bind(R.id.btn_add_notice)
    Button btnAddNotice;
    private Calendar mCalendar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_public);
        ButterKnife.bind(this);
        initHome();
        setToolBar(R.id.tb_public_notice);

        mCalendar = Calendar.getInstance();
        updateTimeShow();

        edAddNoticeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDatePicker();
            }
        });

        btnAddNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etAddNoticeTitle.getText().toString();
                String content = etAddNoticeContent.getText().toString();
                String author = edAddNoticeAuthor.getText().toString();
                if ("".equals(title)||"".equals(content)||"".equals(author)){
                    toastShow("请将数据填写完整");
                    return;
                }
                enables(false);

                Notice notice = new Notice();
                notice.setmTitle(title);
                notice.setmContent(content);
                notice.setmDate(mCalendar.getTime());
                notice.setmAuthor(author);
                notice.setmImageUrl("https://avatars3.githubusercontent.com/u/28390117?s=400&v=4");
                notice.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                    toastShow("上传成功！");
                    finish();
                    }
                });

            }
        });
    }

    private  void enables(boolean flag) {

        btnAddNotice.setEnabled(flag);
        etAddNoticeTitle.setEnabled(flag);
        etAddNoticeContent.setEnabled(flag);
        edAddNoticeAuthor.setEnabled(flag);
    }

    private void initDatePicker() {
        //生成一个DatePickerDialog对象，并显示。显示的DatePickerDialog控件可以选择年月日，并设置
        DatePickerDialog datePickerDialog = new DatePickerDialog(PublishNoticActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //修改日历控件的年，月，日
                //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
                mCalendar.set(Calendar.YEAR,year);
                mCalendar.set(Calendar.MONTH,month);
                mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateTimeShow();
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
        updateTimeShow();
    }

    private void updateTimeShow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        //将页面TextView的显示更新为最新时间
        edAddNoticeTime.setText(format.format(mCalendar.getTime()));
    }




}
