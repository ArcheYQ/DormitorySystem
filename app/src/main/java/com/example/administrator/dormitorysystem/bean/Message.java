package com.example.administrator.dormitorysystem.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/9.
 */

public class Message extends BmobObject {
    /**
     *内容
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     *评论者
     */
    private String student;
    /**
     *时间
     */
    private String time;
}
