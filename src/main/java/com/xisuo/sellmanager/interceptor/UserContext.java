package com.xisuo.sellmanager.interceptor;

import com.xisuo.sellmanager.entity.User;

/**
 * 用户的线程持有
 */
public class UserContext {

    private static ThreadLocal<User> userHolder=new ThreadLocal<>();

    public static void setUser(User user){
        userHolder.set(user);
    }

    public static User getUser(){
        return userHolder.get();
    }

    public static void remove(){
        userHolder.remove();
    }

}
