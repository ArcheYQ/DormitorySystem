package com.example.administrator.dormitorysystem.bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/11/30.
 */

public class Student extends BmobUser{
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 年级
     */
    private String grade;
    /**
     * 班级
     */
    private String className;
    /**
     * 学院
     */
    private String collega;
    /**
     * 头像地址
     */
    private String nickUrl;
    /**
     * 宿舍栋数
     */
    private String dorNum;
    /**
     * 宿舍门牌号
     */
    private String dorDetaliNum;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCollega() {
        return collega;
    }

    public void setCollega(String collega) {
        this.collega = collega;
    }

    public String getNickUrl() {
        return nickUrl;
    }

    public void setNickUrl(String nickUrl) {
        this.nickUrl = nickUrl;
    }

    public String getDorNum() {
        return dorNum;
    }

    public void setDorNum(String dorNum) {
        this.dorNum = dorNum;
    }

    public String getDorDetaliNum() {
        return dorDetaliNum;
    }

    public void setDorDetaliNum(String dorDetaliNum) {
        this.dorDetaliNum = dorDetaliNum;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 学号
     */
    private String number;
}
