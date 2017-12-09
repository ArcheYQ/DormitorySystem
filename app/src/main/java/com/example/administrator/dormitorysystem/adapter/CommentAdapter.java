package com.example.administrator.dormitorysystem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Comment;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    List<Comment> list;

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
    }

    public CommentAdapter(List<Comment> list) {
        this.list = list;
    }

    public void setList(List<Comment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.load(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemCommentName;
        TextView tvItemCommentTime;
        TextView tvItemComment;

        public CommentViewHolder(View itemView) {
            super(itemView);
            tvItemComment = itemView.findViewById(R.id.tv_item_comment);
            tvItemCommentTime = itemView.findViewById(R.id.tv_item_comment_time);
            tvItemCommentName = itemView.findViewById(R.id.tv_item_comment_name);
        }
        public void load(Comment comment){
            tvItemComment.setText(comment.getContext().toString());
            tvItemCommentName.setText(comment.getName().toString());
            tvItemCommentTime.setText(comment.getTime().toString());
        }
    }
}

