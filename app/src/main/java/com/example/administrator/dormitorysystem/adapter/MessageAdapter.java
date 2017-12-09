package com.example.administrator.dormitorysystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.activity.CommentActivity;
import com.example.administrator.dormitorysystem.bean.Message;
import com.example.administrator.dormitorysystem.bean.Student;
import com.example.administrator.dormitorysystem.util.UserUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/12/9.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessgaeViewHolder> {
    List<Message> list;
    Context context;
    public MessageAdapter(List<Message> list ,Context context) {
        this.list = list;
        this.context = context;
    }
    public List<Message> getData() {
        return list;
    }
    //添加data数据
    public void addData(int position, List<Message> data) {
        this.list.addAll(position, data);
        this.notifyItemRangeInserted(position, data.size());
    }
    public void setList(List<Message> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MessgaeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessgaeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false));
    }

    @Override
    public void onBindViewHolder(MessgaeViewHolder holder, int position) {
        holder.load(list.get(position) ,context);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MessgaeViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTime;
        TextView tvContent;
        CircleImageView cmMessagePerson;
        public MessgaeViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_message_content);
            tvName = itemView.findViewById(R.id.tv_message_name);
            cmMessagePerson = itemView.findViewById(R.id.cm_message_person);
            tvTime = itemView.findViewById(R.id.tv_message_time);
        }
        public void load(final Message message , final Context context){
            tvContent.setText(message.getContent().toString());
            tvTime.setText(message.getTime().toString());
            UserUtil.findUser(message.getStudent().toString(), new UserUtil.UserListener() {
                @Override
                public void complete(List<Student> students) {
                    if (students.get(0).getNickUrl().isEmpty()) {
                        Glide.with(context).load("http://bmob-cdn-13164.b0.upaiyun.com/2017/09/04/b1b8899cc0934c899bc86f88bafdf302.jpg").into(cmMessagePerson);
                    } else {
                        Glide.with(context).load(students.get(0).getNickUrl()).into(cmMessagePerson);
                    }
                    tvName.setText(students.get(0).getName().toString());
                }

                @Override
                public void fail(String error) {
                    Glide.with(context).load("http://bmob-cdn-13164.b0.upaiyun.com/2017/09/04/b1b8899cc0934c899bc86f88bafdf302.jpg").into(cmMessagePerson);
                    tvName.setText("null");
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CommentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("message",message);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
