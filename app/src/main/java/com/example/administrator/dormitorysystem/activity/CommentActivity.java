package com.example.administrator.dormitorysystem.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.example.administrator.dormitorysystem.R;
import com.example.administrator.dormitorysystem.adapter.CommentAdapter;
import com.example.administrator.dormitorysystem.bean.Administrato;
import com.example.administrator.dormitorysystem.bean.Message;
import com.example.administrator.dormitorysystem.bean.Student;
import com.example.administrator.dormitorysystem.bean.Comment;
import com.example.administrator.dormitorysystem.util.CommentUtil;
import com.example.administrator.dormitorysystem.util.UserUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends BaseActivity implements OnItemClickListener {


    @Bind(R.id.cm_comment_person)
    CircleImageView cmCommentPerson;
    @Bind(R.id.tv_comment_name)
    TextView tvCommentName;
    @Bind(R.id.tv_comment_time)
    TextView tvCommentTime;
    @Bind(R.id.tv_message_content)
    TextView tvMessageContent;
    @Bind(R.id.rv_comment)
    RecyclerView rvComment;
    @Bind(R.id.fb_comment)
    FloatingActionButton fbComment;
    @Bind(R.id.tb_comment)
    Toolbar tbComment;
    private Message message;
    private List<Comment> comments;
    private CommentAdapter commentAdapter;
    private AlertView mAlertViewExit;
    private EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        setToolBar(R.id.tb_comment);
        message = (Message) getIntent().getSerializableExtra("message");
        initData();
        comments = new ArrayList<>();
        commentAdapter = new CommentAdapter(comments);
        rvComment.setLayoutManager(new LinearLayoutManager(this));
        rvComment.setItemAnimator(new DefaultItemAnimator());
        rvComment.setAdapter(commentAdapter);
        initView();

    }

    private void initView() {
        CommentUtil.findMessage(0, message.getObjectId().toString(),new CommentUtil.QueryListener() {
            @Override
            public void complete(List<Comment> commentList) {
                commentAdapter.setList(commentList);
            }

            @Override
            public void fail(String error) {
                Toast.makeText(CommentActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        tvMessageContent.setText(message.getContent().toString());
        tvCommentTime.setText(message.getTime().toString());
        UserUtil.findUser(message.getStudent().toString(), new UserUtil.UserListener() {
            @Override
            public void complete(List<Student> students) {
                if (students.get(0).getNickUrl().isEmpty()) {
                    Glide.with(CommentActivity.this).load("http://bmob-cdn-13164.b0.upaiyun.com/2017/09/04/b1b8899cc0934c899bc86f88bafdf302.jpg").into(cmCommentPerson);
                } else {
                    Glide.with(CommentActivity.this).load(students.get(0).getNickUrl()).into(cmCommentPerson);
                }
                tvCommentName.setText(students.get(0).getName().toString());
            }

            @Override
            public void fail(String error) {
                Glide.with(CommentActivity.this).load("http://bmob-cdn-13164.b0.upaiyun.com/2017/09/04/b1b8899cc0934c899bc86f88bafdf302.jpg").into(cmCommentPerson);
                tvCommentName.setText("null");
            }
        });
        mAlertViewExit = new AlertView("评论", "请输入你要评论的内容！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form, null);
        etName =  extView.findViewById(R.id.etName);
        mAlertViewExit.addExtView(extView);
    }

    @OnClick(R.id.fb_comment)
    public void onViewClicked() {
    mAlertViewExit.show();
    }

    @Override
    public void onItemClick(Object o, int position) {
        if (o == mAlertViewExit && position != AlertView.CANCELPOSITION) {
            String name = etName.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
            } else {
                Comment comment = new Comment();
                if (BmobUser.getCurrentUser() instanceof Administrato){
                    comment.setName(BmobUser.getCurrentUser(Administrato.class).getName().toString());

                }else{
                    comment.setName(BmobUser.getCurrentUser(Student.class).getName().toString());
                }
                comment.setMessage(message.getObjectId().toString());
                SimpleDateFormat format = new SimpleDateFormat("MM-dd");
                comment.setTime(format.format(new Date(Long.parseLong(String.valueOf(System.currentTimeMillis())))));
                comment.setContext(name);
                comment.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId, BmobException e) {
                        if(e==null){
                            Toast.makeText(mActivity, "评论成功", Toast.LENGTH_SHORT).show();
                            initView();
                        }else{
                            if(e.getErrorCode() == 9016){
                                Toast.makeText(mActivity, "请检查网络连接( ▼-▼ )", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mActivity, "评论失败。", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        }
    }
}
