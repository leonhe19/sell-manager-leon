package com.xisuo.sellmanager.entity;

import java.util.Date;

/**
 * 待办事项
 */
public class ToDoList {

    private long id;
    private long userId;
    private String content;//待办事项内容
    private Date createTime;//创建时间
    private String doTime;//设定的应该完成时间
    //任务状态,0 未完成, 1已完成
    private int workState;


    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDoTime() {
        return doTime;
    }

    public void setDoTime(String doTime) {
        this.doTime = doTime;
    }

    public int getWorkState() {
        return workState;
    }

    public void setWorkState(int workState) {
        this.workState = workState;
    }
}
