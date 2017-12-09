package com.example.administrator.dormitorysystem.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/12/9.
 */

public class Comment extends BmobObject {
    /**
     * 留言内容
     */
    private String context;
    /**
     * 评论留言的id
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 回复时间
      */
    private String time;
    /**
     * 回复人姓名
     */
    private String name;
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
