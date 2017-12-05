package com.example.administrator.dormitorysystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.RepairInfo;
import com.example.administrator.dormitorysystem.bean.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PublishRepairActivity extends BaseActivity {

    @Bind(R.id.tb_new_repair)
    Toolbar tbNewRepair;
    @Bind(R.id.et_add_repair_title)
    EditText etAddRepairTitle;
    @Bind(R.id.et_add_repair_content)
    EditText etAddRepairContent;
    @Bind(R.id.btn_add_repair)
    Button btnAddRepair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_repair);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_new_repair);
        initHome();
    }

    @OnClick(R.id.btn_add_repair)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etAddRepairContent.getText().toString())||TextUtils.isEmpty(etAddRepairTitle.getText().toString())){
            Toast.makeText(mActivity, "请先完善信息。", Toast.LENGTH_SHORT).show();
        }else {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            RepairInfo repairInfo = new RepairInfo();
            repairInfo.setContent(etAddRepairContent.getText().toString());
            repairInfo.setTitle(etAddRepairTitle.getText().toString());
            repairInfo.setStudent(BmobUser.getCurrentUser(Student.class));
            repairInfo.setTime(format.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis())))));
            repairInfo.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if(e==null){
                        Toast.makeText(mActivity, "保修成功，请耐心等待。", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        if(e.getErrorCode() == 9016){
                            Toast.makeText(mActivity, "请检查网络连接( ▼-▼ )", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mActivity, "报修失败。", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }

    }
}
