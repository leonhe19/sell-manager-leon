package com.xisuo.sellmanager.service.impl;

import com.alibaba.fastjson.JSON;
import com.xisuo.sellmanager.dao.ToDoListDao;
import com.xisuo.sellmanager.entity.ToDoList;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.ToDoListService;
import com.xisuo.sellmanager.utils.NumUtil;
import com.xisuo.sellmanager.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("toDoListService")
public class ToDoListServiceImpl implements ToDoListService {

    private static Logger logger = LoggerFactory.getLogger(ToDoListServiceImpl.class);

    @Autowired
    private ToDoListDao toDoListDao;

    @Override
    public List<ToDoList> queryUserTodo() {
        long id = UserContext.getUser().getId();
        List<ToDoList> lists = toDoListDao.queryUserTodos(id);
        return lists;
    }


    @Override
    public void createWork(ToDoList toDoList) {
        logger.info("用户:{},创建了一个代办事项:{}",UserContext.getUser().getId(), JSON.toJSONString(toDoList));
        toDoList.setUserId(UserContext.getUser().getId());
        toDoList.setCreateTime(new Date());
        toDoList.setWorkState(0);
        toDoListDao.createWork(toDoList);
    }


    @Override
    public void saveModifyWork(ToDoList toDoList) {
        toDoList.setCreateTime(new Date());
        toDoList.setUserId(UserContext.getUser().getId());
        toDoListDao.modifyWork(toDoList);
    }


    @Override
    public Page<ToDoList> getAllWork(Map<String, Object> params) {
        params.put("userId", UserContext.getUser().getId());
        List<ToDoList> list = toDoListDao.getAllWork(params);
        int num = toDoListDao.getAllWorkNum(params);
        Page<ToDoList> page = new Page<ToDoList>(NumUtil.num2Int(params.get("pageNo")), num, list);
        return page;
    }


    @Override
    public void deleteWork(Long id) {
        logger.info("用户:{},删除了一个代办事项:{}",UserContext.getUser().getId(), id);
        toDoListDao.deleteWork(id, UserContext.getUser().getId());
    }


    @Override
    public void doneWord(String id) {
        toDoListDao.doneWord(id);
    }


    @Override
    public ToDoList queryOneToDo(Long id) {
        return toDoListDao.queryOneToDo(id);
    }
}
