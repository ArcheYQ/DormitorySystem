package com.example.administrator.dormitorysystem.util;

import com.example.administrator.dormitorysystem.MyApplication;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.RepairInfo;
import com.example.administrator.dormitorysystem.bean.Student;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2017/12/5.
 */

public class RepairUtil  {
    public interface QueryListener{
        void complete(List<RepairInfo> repairInfos);
        void fail(String error);
    }
    public static void findMyRepair(int page , final QueryListener listener){
        BmobQuery<RepairInfo> query = new BmobQuery<>();
        query.setLimit(10);//每次查询十个数据
        query.setSkip(10*page);//跳过前面多少数据
        query.order("-createdAt");//排序，按时间降序
        query.addWhereEqualTo("student", BmobUser.getCurrentUser(Student.class).getObjectId());
        query.findObjects(new FindListener<RepairInfo>() {
            @Override
            public void done(List<RepairInfo> data, BmobException e) {
                if (e == null){
                    listener.complete(data);
                }else {
                    if (e.getErrorCode() == 9016){
                        listener.fail(MyApplication
                                .getContext()
                                .getString(R.string.err_no_net));
                    }else {
                        listener.fail(e.getMessage());
                    }
                }
            }
        });
    }
    public static void findAllRepair(int page , final QueryListener listener){
        BmobQuery<RepairInfo> query = new BmobQuery<>();
        query.setLimit(10);//每次查询十个数据
        query.setSkip(10*page);//跳过前面多少数据
        query.order("-createdAt");//排序，按时间降序
        query.findObjects(new FindListener<RepairInfo>() {
            @Override
            public void done(List<RepairInfo> data, BmobException e) {
                if (e == null){
                    listener.complete(data);
                }else {
                    if (e.getErrorCode() == 9016){
                        listener.fail(MyApplication
                                .getContext()
                                .getString(R.string.err_no_net));
                    }else {
                        listener.fail(e.getMessage());
                    }
                }
            }
        });
    }
}
