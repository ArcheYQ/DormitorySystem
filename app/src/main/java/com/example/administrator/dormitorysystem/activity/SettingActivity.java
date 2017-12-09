package com.example.administrator.dormitorysystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.bean.Feedback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SettingActivity extends BaseActivity implements OnItemClickListener {

    @Bind(R.id.tb_personal)
    Toolbar tbPersonal;
    @Bind(R.id.cb_suggestion)
    CheckBox cbSuggestion;
    @Bind(R.id.tv_false)
    TextView tvFalse;
    @Bind(R.id.cb_false)
    CheckBox cbFalse;
    @Bind(R.id.et_content)
    EditText etContent;
    @Bind(R.id.et_way)
    EditText etWay;
    @Bind(R.id.btn_publish)
    Button btnPublish;
    @Bind(R.id.btn_exit)
    Button btnExit;
    private AlertView mAlertView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_personal);
        initHome();
        mAlertView = new AlertView("提示", "是否退出登录", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).
                setCancelable(true).setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(Object o) {

            }
        });
    }

    @OnClick({R.id.btn_publish, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_publish:
                if (!cbSuggestion.isChecked()&&!cbFalse.isChecked()){
                    Toast.makeText(this, "请选择一个反馈类型", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etContent.getText().toString())) {
                    Toast.makeText(this, "请填写您的反馈内容", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(etWay.getText().toString())){
                    Toast.makeText(this, "请填写您的联系方式", Toast.LENGTH_SHORT).show();
                }else{
                    Feedback feedback = new Feedback();
                    feedback.setContent(etContent.getText().toString());
                    feedback.setSuggestion(cbSuggestion.isChecked());
                    feedback.setWay(etWay.getText().toString());
                    feedback.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null){
                                Toast.makeText(SettingActivity.this, "感谢您的反馈", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                if (e.getErrorCode()==9016){
                                    Toast.makeText(SettingActivity.this, "网络不给力╮(╯_╰)╭", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(SettingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
                break;
            case R.id.btn_exit:
                mAlertView.show();
                break;
            case R.id.cb_suggestion:
                if (cbSuggestion.isChecked()){
                    cbFalse.setChecked(false);
                }
                break;
            case R.id.cb_false:
                if (cbFalse.isChecked()){
                    cbSuggestion.setChecked(false);
                }
                break;
        }
    }

    @Override
    public void onItemClick(Object o, int position) {
        switch (position){
            case 0:
                BmobUser.logOut();   //清除缓存用户对象
                startActivity(new Intent(this,LoginActivity.class));
                finish();
                break;

        }
    }
}
