package com.xisuo.sellmanager.entity;


/**
 * 业绩目标
 */
public class OrderTarget {

    private long id;
    private String startTime;
    private String endTime;
    private String target;
    private String hasDone;
    //0 未完成   1已经完成
    private int targetState;
    //用户id,公司的为0
    private long userId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHasDone() {
        return hasDone;
    }

    public void setHasDone(String hasDone) {
        this.hasDone = hasDone;
    }

    public int getTargetState() {
        return targetState;
    }

    public void setTargetState(int targetState) {
        this.targetState = targetState;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
