package com.example.administrator.dormitorysystem.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/5.
 */

public class RepairInfo extends BmobObject{

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    /**
     * 报修人员
     */
    private String student;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 时间
     */
    private String time;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
