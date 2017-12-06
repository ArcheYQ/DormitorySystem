package com.example.administrator.dormitorysystem.util;

import android.widget.Toast;

import com.example.administrator.dormitorysystem.bean.RepairInfo;
import com.example.administrator.dormitorysystem.bean.Student;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/12/6.
 */

public class UserUtil {

    public interface UserListener{
        void complete(List<Student> students);
        void fail(String error);
    }
    public static void findUser(String id , final UserListener listener){
        BmobQuery<Student> query = new BmobQuery<Student>();
        query.addWhereEqualTo("objectId", id);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> object,BmobException e) {
                if(e==null){
                    listener.complete(object);
                }else{
                    listener.fail("更新用户信息失败");
                }
            }
        });
    }
}
