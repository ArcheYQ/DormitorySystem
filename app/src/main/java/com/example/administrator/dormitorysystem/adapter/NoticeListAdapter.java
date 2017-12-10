package com.example.administrator.dormitorysystem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Notice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by reoger on 2017/12/5.
 *
 */

public class NoticeListAdapter extends BaseAdapter{

    private List<Notice> mData;
    private Context mContext;
    private LayoutInflater mInflater;

    public NoticeListAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void  setContent(List<Notice> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mData==null)
            return 0;
        return mData.size();
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
    public View getView(int i, View convertView , ViewGroup viewGroup) {
        View noticeView ;
        ViewHolder viewHolder;
        if(convertView  == null){
            viewHolder = new ViewHolder();
            if(mInflater==null){
                mInflater = LayoutInflater.from(mContext);
            }
            noticeView = mInflater.inflate(R.layout.item_notice,null);

            viewHolder.noticeImage = noticeView.findViewById(R.id.notice_image);
            viewHolder.noticeTitle = noticeView.findViewById(R.id.notice_title);
            viewHolder.noticeAuthor = noticeView.findViewById(R.id.notice_author);
            viewHolder.noticeTime = noticeView.findViewById(R.id.notice_time);

            noticeView.setTag(viewHolder);
        }else{
            noticeView = convertView ;
            viewHolder = (ViewHolder) noticeView.getTag();
        }
        Notice notice = mData.get(i);
        Glide.with(mContext).load(notice.getmImageUrl()).into(viewHolder.noticeImage);
        viewHolder.noticeAuthor.setText(mData.get(i).getmAuthor());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        String time = format.format(notice.getmDate());
        viewHolder.noticeTime.setText(time);
        viewHolder.noticeTitle.setText(notice.getmTitle());


        return noticeView;
    }

    private class ViewHolder{
        ImageView noticeImage;
        TextView noticeTitle;
        TextView noticeAuthor;
        TextView noticeTime;
    }
}
