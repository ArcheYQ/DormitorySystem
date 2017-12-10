package com.example.administrator.dormitorysystem.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.adapter.NoticeListAdapter;
import com.example.administrator.dormitorysystem.bean.Administrato;
import com.example.administrator.dormitorysystem.bean.Notice;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.UpdateListener;
import rx.Subscription;

/**
 * Created by reoger on 2017/12/5.
 *
 */


public class NoticeActivity extends BaseActivity {


    private List<Notice> mData;

    private ListView mListView;
    private final static String TAG = "NoticeActivity";

    private NoticeListAdapter  mNoticeAdapter;

    private FloatingActionButton mFab;
    private int CurrenIndex =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        setToolBar(R.id.tb_repair);
        initHome();
        initView();
        initData();


        mNoticeAdapter = new NoticeListAdapter(NoticeActivity.this);
        mListView.setAdapter(mNoticeAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Notice notice = mData.get(i);
                Intent intent = new Intent(NoticeActivity.this,NoticeDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("notice",notice);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });


        Administrato administrato = BmobUser.getCurrentUser(Administrato.class);

        if (administrato != null&& administrato.getPer()){
            adminManger();
        }else{
            mFab.hide();
        }
    }

    private void adminManger() {
        mFab.show();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoticeActivity.this,PublishNoticActivity.class));
            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                new AlertDialog.Builder(NoticeActivity.this)
//                        .setTitle("操作")
//                        .setItems(0, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//
//                            }
//                        });
                CurrenIndex = i;

                return false;
            }
        });

        mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,0,0,"添加");
                contextMenu.add(0,1,0,"删除");
                contextMenu.add(0,2,0,"删除ALL");
            }
        });


    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 0:
                startActivity(new Intent(NoticeActivity.this,PublishNoticActivity.class));
                break;
            case 1:
                showNormalDialog("确认删除当前通知？",false);
                break;
            case 2:
                showNormalDialog("确认删除所有的通知？",true);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void deleteNotice() {
        Notice notice = new Notice();
        notice.setObjectId(mData.get(CurrenIndex).getObjectId());
        notice.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                toastShow("删除数据成功");
                mData.remove(CurrenIndex);
                mNoticeAdapter.setContent(mData);
            }
        });
    }

    private void initData() {
        BmobQuery<Notice> query = new BmobQuery<>();
        query.setLimit(8).order("-createdAt").findObjects(new FindListener<Notice>() {
            @Override
            public void done(List<Notice> list, BmobException e) {
                if(e == null){
                    mData = list;
                   mNoticeAdapter.setContent(mData);
                }else{

                }
            }
        });


    }



    private void initView() {
        mListView = (ListView) findViewById(R.id.list_notice);
        mFab = (FloatingActionButton) findViewById(R.id.fb_add_notice);
    }



    private void showNormalDialog(String str, final boolean isAll){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setIcon(R.drawable.ic_access_time_black_24dp);
        normalDialog.setTitle("确认操作！");
        normalDialog.setMessage(str);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isAll)
                            deleteAllNotice();
                        else
                            deleteNotice();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void deleteAllNotice() {
        List<BmobObject> lists = new ArrayList<>();
        for (Notice mDatum : mData) {
            lists.add(mDatum);
        }
       new BmobBatch().deleteBatch(lists).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                if (e == null) {
                    mData.clear();
                    mNoticeAdapter.setContent(mData);
                    toastShow("通知删除成功，共删除" + list.size() + "个通知");
                }
            }
        });
    }
}


