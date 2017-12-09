package com.example.administrator.dormitorysystem.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Notice;

import java.text.SimpleDateFormat;

/**
 * Created by reoger on 2017/12/6.
 */

public class NoticeDetailActivity extends BaseActivity {

    private TextView mTextTitle;
    private TextView mTextContent;
    private TextView mTextTime;
    private TextView mTextAuthor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);

        setToolBar(R.id.tb_detail_notice);
        initHome();
        initView();
        Notice notice = (Notice) getIntent().getSerializableExtra("notice");
        Log.d("TAG", "onCreate: "+notice.getmContent());

        mTextTitle.setText(notice.getmTitle());
        mTextContent.setText(notice.getmContent());
        mTextAuthor.setText(notice.getmAuthor());

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            mTextTime.setText(format.format(notice.getmDate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        mTextTitle = (TextView) findViewById(R.id.et_detail_notice_title);
        mTextContent = (TextView) findViewById(R.id.et_detail_notice_tcontent);
        mTextAuthor = (TextView) findViewById(R.id.et_detail_notice_author);
        mTextTime= (TextView) findViewById(R.id.et_detail_notice_time);
    }
}
