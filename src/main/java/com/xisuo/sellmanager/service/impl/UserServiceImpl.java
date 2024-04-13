package com.xisuo.sellmanager.service.impl;

import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.config.CacheMap;
import com.xisuo.sellmanager.constant.Constant;
import com.xisuo.sellmanager.dao.UserDao;
import com.xisuo.sellmanager.entity.User;
import com.xisuo.sellmanager.service.UserService;
import com.xisuo.sellmanager.utils.MD5Util;

import com.xisuo.sellmanager.utils.NumUtil;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 用户服务类
 * @date 2021-07-05 14:22
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private CacheMap cacheMap;
    @Autowired
    private UserDao userDao;


    @Override
    public void deleteUser(Long id) {
        cacheMap.remove("user:" + id);
        cacheMap.remove("allName");
        logger.info("用户id为{} 的 删除了id为 {} 的用户", UserContext.getUser().getId(), id);
        userDao.deleteUser(id);
    }


    @Override
    public void saveModifyUser(User user) {
        cacheMap.remove("user:" + user.getId());
        logger.info("用户id为{} 的 修改了id为 {} 的用户", UserContext.getUser().getId(), user.getId());
        userDao.saveModifyUser(user);
    }


    @Override
    public User getUserDetail(Long id) {
        Object o = cacheMap.get("user:" + id);
        if (o != null) {
            return (User) o;
        }
        User user = userDao.getUserById(id);
        user.setPassword(null);
        cacheMap.set("user:" + id, user);
        return user;
    }


    @Override
    public void createUser(User user) {
        //默认密码都是123456
        user.setPassword(MD5Util.input2Db(Constant.DEFAULT_PASSWORD));
        logger.info("用户id为{} 的 创建了用户,用户名是:{}", UserContext.getUser().getId(), user.getName());
        cacheMap.remove("allName");
        userDao.createUser(user);
    }


    @Override
    public User login(String username, String password) {
        User user = userDao.getUserByName(username);
        if (user != null) {
            String userPassword = MD5Util.input2Db(password);
            if (StringUtils.equals(user.getPassword(), userPassword)) {
                user.setPassword(null);
                logger.info("用户名是:{} 的用户登录了", user.getName());
                cacheMap.set("user:" + user.getId(), user);
                UserContext.setUser(user);
                return user;
            }
        }
        return null;
    }


    @Override
    public String modifyPassword(Long id, String oldPassword, String newPassword) {
        User user = userDao.getUserById(id);
        if (StringUtils.equals(MD5Util.input2Db(oldPassword), user.getPassword())) {
            userDao.modifyPassword(id, MD5Util.input2Db(newPassword));
            logger.info("用户名是:{} 的用户修改了密码", user.getName());
            return "修改密码成功";
        } else {
            //密码不对
            return "密码不对,不能修改密码";
        }

    }

    @Override
    public List<String> getAllUsername() {
        Object allName = cacheMap.get("allName");
        if (allName != null) {
            return (List<String>) allName;
        } else {
            List<String> list = userDao.getAllUsername();
            cacheMap.set("allName", list);
            return list;
        }
    }

    @Override
    public Page<User> getAllUser(Map<String, Object> params) {
        List<User> user = userDao.getAllUser(params);
        int count = userDao.getAllUserNum(params);
        Page<User> page = new Page<User>(NumUtil.num2Int(params.get("pageNo")), count, user);
        return page;
    }


    @Override
    public List<Map<String, Object>> getUserIdName() {
        Object name = cacheMap.get("allUserIdName");
        if (name != null) {
            return (List<Map<String, Object>>) name;
        }
        List<Map<String, Object>> lists = userDao.queryUserIdName();
        List<Map<String, Object>> maps = new ArrayList<>(lists.size() + 1);
        HashMap<String, Object> map = new HashMap<>(lists.size() + 1);
        map.put("id", 0);
        map.put("name", "公司");
        maps.add(map);
        maps.addAll(lists);
        cacheMap.set("allUserIdName", maps);
        return maps;
    }


    @Override
    public void restPassword(Long id) {
        logger.info("用户id为{} 的 重置了id为 {} 的密码", UserContext.getUser().getId(), id);
        userDao.modifyPassword(id, MD5Util.input2Db(Constant.DEFAULT_PASSWORD));
    }


    @Override
    public int findName(String name) {
        return userDao.findName(name);
    }


}
