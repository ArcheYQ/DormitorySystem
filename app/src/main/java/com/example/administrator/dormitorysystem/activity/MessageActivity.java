package com.example.administrator.dormitorysystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.adapter.MessageAdapter;
import com.example.administrator.dormitorysystem.bean.Message;
import com.example.administrator.dormitorysystem.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    @Bind(R.id.tb_message)
    Toolbar tbMessage;
    @Bind(R.id.rv_message)
    RecyclerView rvMessage;
    @Bind(R.id.fb_message)
    FloatingActionButton fbMessage;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private MessageAdapter messageAdapter;
    private boolean isLoading = false;
    private int page = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_message);
        initHome();
        List<Message> list = new ArrayList<>();
        messageAdapter = new MessageAdapter(list, this);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        rvMessage.setItemAnimator(new DefaultItemAnimator());
        rvMessage.setAdapter(messageAdapter);
        initView();
    }

    private void initView() {
                MessageUtil.findMessage(page, new MessageUtil.QueryListener() {
                    @Override
                    public void complete(List<Message> messageList) {
                        messageAdapter.setList(messageList);
                    }

                    @Override
                    public void fail(String error) {
                        Toast.makeText(MessageActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }

    @OnClick(R.id.fb_message)
    public void onViewClicked() {
        startActivity(new Intent(MessageActivity.this, PublicMessageActivity.class));
    }
}
