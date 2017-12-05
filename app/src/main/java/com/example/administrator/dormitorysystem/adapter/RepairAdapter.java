package com.example.administrator.dormitorysystem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.RepairInfo;

import java.util.List;


/**
 * Created by Administrator on 2017/12/5.
 */

public class RepairAdapter extends RecyclerView.Adapter<RepairAdapter.RepairViewHolder> {
    List<RepairInfo> list;
    public RepairAdapter(List<RepairInfo> list) {
        this.list = list;
    }

    public void setList(List<RepairInfo> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RepairViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepairViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repair, parent, false));
    }

    @Override
    public void onBindViewHolder(RepairViewHolder holder, int position) {
    holder.load(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class RepairViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvTime;
        TextView tvDorNum;
        TextView tvTel;
        TextView tvTitle;
        TextView tvContent;

        public RepairViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_repair_name);
            tvTime = itemView.findViewById(R.id.tv_repair_time);
            tvDorNum = itemView.findViewById(R.id.tv_repair_dorNum);
            tvContent = itemView.findViewById(R.id.tv_repair_content);
            tvTel = itemView.findViewById(R.id.tv_repair_Tel);
            tvTitle = itemView.findViewById(R.id.tv_repair_title);
        }
        public void load(RepairInfo repairInfo){
            tvContent.setText(repairInfo.getContent().toString());
            tvTitle.setText(repairInfo.getTitle().toString());
            tvDorNum.setText("宿舍号："+repairInfo.getStudent().getDorDetaliNum());
            tvTel.setText("电话号码："+repairInfo.getStudent().getMobilePhoneNumber());
            tvName.setText("保修人姓名："+repairInfo.getStudent().getName());
            tvTime.setText(repairInfo.getTime().toString());
        }
    }
}
