package com.example.administrator.dormitorysystem.bean;

import java.util.Date;

import cn.bmob.v3.BmobObject;

/**
 * Created by reoger on 2017/12/5.
 * 通知类的实体对象
 */

public class Notice  extends BmobObject  {
    //图片URL
    private String mImageUrl;

    private String mTitle;

    private String mContent;

    private Date mDate;

    private String mAuthor;

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }
}
