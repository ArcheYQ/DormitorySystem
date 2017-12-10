package com.example.administrator.dormitorysystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Student;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by reoger on 2017/12/9.
 */

public class StudnetsInfoAdapter extends BaseAdapter {
    private List<Student> mDatas;
    private Context mContext;

    public StudnetsInfoAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setContent(List<Student> mDatas){
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        }
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View studentInfoView;
        ViewHold viewHold;
        if (view == null) {
            viewHold = new ViewHold();
            studentInfoView = LayoutInflater.from(mContext).inflate(R.layout.item_student_info,null);
            viewHold.mPhone = studentInfoView.findViewById(R.id.cm_person);
            viewHold.mName = studentInfoView.findViewById(R.id.tx_name);
            viewHold.mBedRoomNum = studentInfoView.findViewById(R.id.tx_bedroom_num);
            studentInfoView.setTag(viewHold);

        }else{
            studentInfoView = view ;
            viewHold = (ViewHold) studentInfoView.getTag();
        }

        Student student = mDatas.get(i);
        Glide.with(mContext).load(student.getNickUrl()).into(viewHold.mPhone);
        viewHold.mName.setText("姓名： "+student.getName());
        viewHold.mBedRoomNum.setText("宿舍："+student.getDorDetaliNum());

        return studentInfoView;

    }

    class ViewHold{
        CircleImageView mPhone;
        TextView mName;
        TextView mBedRoomNum;
    }
}
