package com.example.administrator.dormitorysystem.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/11/30.
 */

public class Administrato extends BmobUser {
    public Boolean getPer() {
        return isPer;
    }

    public void setPer(Boolean per) {
        isPer = per;
    }

    /**
     * quanxian
     */
    private Boolean isPer;
    /**
     * 工号
     */
    private String id;
    /**
     * 姓名
     */
    private String name;
    /**性别
     *
     */
    private String sex;
    /**
     * 主要管理范围
     */
    private String mainRage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMainRage() {
        return mainRage;
    }

    public void setMainRage(String mainRage) {
        this.mainRage = mainRage;
    }

    public String getNickUrl() {
        return nickUrl;
    }

    public void setNickUrl(String nickUrl) {
        this.nickUrl = nickUrl;
    }

    /**
     * 头像地址
     */
    private String nickUrl;
public Administrato(){
    this.isPer = true;
}
}
