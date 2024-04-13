package com.xisuo.sellmanager.entity;


import java.util.Date;

public class CustomerFollow {

    private long id;
    private long customerId;//跟进客户id
    private long userId;//创建用户id
    private Date createTime;//跟进时间
    private String subject;//主题
    private String content;//跟进内容
    private String nextTime;//下次跟进时间
    private String nextContent;//下次跟进内容


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getNextContent() {
        return nextContent;
    }

    public void setNextContent(String nextContent) {
        this.nextContent = nextContent;
    }

}
