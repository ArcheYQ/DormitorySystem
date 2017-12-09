package com.example.administrator.dormitorysystem.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Message;
import com.example.administrator.dormitorysystem.bean.RepairInfo;
import com.example.administrator.dormitorysystem.bean.Student;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PublicMessageActivity extends BaseActivity {


    @Bind(R.id.et_add_message_content)
    EditText etAddMessageContent;
    @Bind(R.id.btn_add_message)
    Button btnAddMessage;
    @Bind(R.id.tb_new_message)
    Toolbar tbNewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_message);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_new_message);
        initHome();
    }

    @OnClick(R.id.btn_add_message)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etAddMessageContent.getText().toString())){
            Toast.makeText(mActivity, "请填写您的留言内容。", Toast.LENGTH_SHORT).show();
        }else {
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            Message message = new Message();
            message.setContent(etAddMessageContent.getText().toString());
            message.setStudent(BmobUser.getCurrentUser(Student.class).getObjectId());
            message.setTime(format.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis())))));
            message.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if(e==null){
                        Toast.makeText(mActivity, "留言成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        if(e.getErrorCode() == 9016){
                            Toast.makeText(mActivity, "请检查网络连接( ▼-▼ )", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mActivity, "留言失败。", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}
