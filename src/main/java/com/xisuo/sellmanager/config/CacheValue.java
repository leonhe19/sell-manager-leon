package com.xisuo.sellmanager.config;

import java.time.LocalDateTime;

/**
 * @author zk
 * @Description: 自己定义的缓存的值信息
 */
public class CacheValue<T> {

    /**
     * 实际数据
     */
    private T data;
    /**
     * 过期时间,单位是  秒
     */
    private long expireTime;
    /**
     * 创建时间,就是当前时间
     */
    private LocalDateTime createTime;
    /**
     * 命中次数
     */
    private int times;


    public CacheValue() {
    }

    public CacheValue(T data) {
        this.data = data;
        this.expireTime = 60 * 60 * 2;//120分钟
        this.createTime = LocalDateTime.now();
        times = 0;
    }

    public CacheValue(long expireTime, T data) {
        this.data = data;
        this.expireTime = expireTime;
        this.createTime = LocalDateTime.now();
        times = 0;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
