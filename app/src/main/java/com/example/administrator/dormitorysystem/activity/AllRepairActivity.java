package com.example.administrator.dormitorysystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.adapter.RepairAdapter;
import com.example.administrator.dormitorysystem.bean.RepairInfo;
import com.example.administrator.dormitorysystem.util.RepairUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllRepairActivity extends BaseActivity {

    @Bind(R.id.tb_repair)
    Toolbar tbRepair;
    @Bind(R.id.rv_repair)
    RecyclerView rvRepair;
    private RepairAdapter repairAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_repair2);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_repair);
        initHome();
        List<RepairInfo> list = new ArrayList<>();
        repairAdapter = new RepairAdapter(list);
        rvRepair.setLayoutManager(new LinearLayoutManager(this));
        rvRepair.setItemAnimator(new DefaultItemAnimator());
        rvRepair.setAdapter(repairAdapter);
        initView();
    }
    private void initView() {
        RepairUtil.findAllRepair(0, new RepairUtil.QueryListener() {
            @Override
            public void complete(List<RepairInfo> repairInfos) {
                repairAdapter.setList(repairInfos);
                Log.i("test","111111111111"+repairInfos.size());
            }

            @Override
            public void fail(String error) {
                Log.i("test","111111111111"+error);
                Toast.makeText(AllRepairActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
