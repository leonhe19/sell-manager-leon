package com.xisuo.sellmanager.dao;

import com.xisuo.sellmanager.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 用户dao
 */
@Repository
public interface UserDao {

    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     */
    User getUserByName(@Param("username") String username);


    /**
     * 创建一个新的user对象
     *
     * @param user user对象
     */
    void createUser(User user);

    /**
     * 删除一个用户,用户id
     *
     * @param id 用户id
     */
    void deleteUser(@Param("id") Long id);

    /**
     * 获取用户
     *
     * @param id 用户id
     */
    User getUserById(@Param("id") Long id);

    /**
     * 修改密码
     */
    void modifyPassword(@Param("id") Long id, @Param("password") String password);

    /**
     * 保存修改后的用户资料
     *
     * @param user 用户信息
     */
    void saveModifyUser(User user);

    /**
     * 获取所有的用户名
     */
    List<String> getAllUsername();

    /**
     * 获取所有用户信息
     */
    List<User> getAllUser(Map<String, Object> params);

    /**
     * 获取所有用户数量
     */
    int getAllUserNum(Map<String, Object> params);


    /**
     * 获取所有用户的名字,以及id
     */
    List<Map<String, Object>> queryUserIdName();

    /**
     * 创建用户的时候,进行用户名的唯一性校验
     *
     * @param name 名字
     */
    int findName(@Param("name") String name);

}
