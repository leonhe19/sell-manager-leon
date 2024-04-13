package com.xisuo.sellmanager.service;

import com.xisuo.sellmanager.entity.User;
import com.xisuo.sellmanager.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description:用户服务类
 * @date 2021-07-05 11:51
 */
public interface UserService {

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 保存修改之后的user
     *
     * @param user 用户对象
     */
    void saveModifyUser(User user);

    /**
     * 根据id 获取用户详情
     *
     * @param id 用户id
     */
    User getUserDetail(Long id);

    /**
     * 创建用户
     *
     * @param user 用户对象
     */
    void createUser(User user);

    /**
     * 根据名字获取用户
     *
     * @param username 用户名
     * @param password 密码
     */
    User login(String username, String password);

    /**
     * 修改用户密码
     *
     * @param id          用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    String modifyPassword(Long id, String oldPassword, String newPassword);

    /**
     * 获取所有用户名
     */
    List<String> getAllUsername();

    /**
     * 获取所有用户
     */
    Page<User> getAllUser(Map<String, Object> params);

    /**
     * 获取所有用户的名字,以及id
     */
    List<Map<String, Object>> getUserIdName();

    /**
     * 重置一个用户的密码
     *
     * @param id 用户id
     */
    void restPassword(Long id);

    /**
     * 创建用户的时候,进行用户名的唯一性校验
     *
     * @param name 名字
     */
    int findName(String name);

}
