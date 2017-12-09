//package com.example.administrator.dormitorysystem.util;
//
//import android.util.Log;
//
//import com.example.administrator.dormitorysystem.MyApplication;
//import com.example.administrator.dormitorysystem.R;
//import com.example.administrator.dormitorysystem.bean.Notice;
//
//import java.util.List;
//
//import cn.bmob.v3.BmobObject;
//import cn.bmob.v3.BmobQuery;
//import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.FindListener;
//
///**
// * Created by reoger on 2017/12/6.
// */
//
//public class QueryUtil<T extends BmobObject> {
//
//    public interface queryListener{
//        void fail(String error);
//
//        <T extends BmobObject> void complete(List<T> list);
//    }
//
//    public static   void  query(int page, final queryListener listener){
//
//        BmobQuery<T> query = new BmobQuery<T>();
//        query.setLimit(10);//每次查询十个数据
//        query.setSkip(10*page);//跳过前面多少数据
//        query.order("-createdAt");//排序，按时间降序
//        Log.d("TAG", "query: "+query.toString());
//        try {
//            query.findObjects(new FindListener<T>() {
//                @Override
//                public void done(List<T> list, BmobException e) {
//                     if(e == null){
//                         //listener.complete(list);
//                         try {
//                             List<Notice> list1 = (List<Notice>) list;
//                             for (Notice notice : list1) {
//                                 Log.d("TYY", "done: "+notice.getContenr());
//                             }
//                         }catch (Exception err){
//                             Log.d("TYY", "done: 强转失败");
//                         }
//
//
//                     }else{
//                         if (e.getErrorCode() == 9016){
//                             listener.fail(MyApplication
//                                     .getContext()
//                                     .getString(R.string.err_no_net));
//                         }else {
//                             listener.fail(e.getMessage());
//                         }
//                     }
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
