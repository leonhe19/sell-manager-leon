package com.xisuo.sellmanager.service;

import com.xisuo.sellmanager.entity.ToDoList;
import com.xisuo.sellmanager.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * 代办事项的服务类
 */
public interface ToDoListService {

    /**
     * 首页的个人的代办事项
     */
    List<ToDoList> queryUserTodo();


    /**
     * 创建个代办事项
     */
    void createWork(ToDoList toDoList);

    /**
     * 保存修改后的代办事项
     */
    void saveModifyWork(ToDoList toDoList);

    /**
     * 获取分页的代办事项
     */
    Page<ToDoList> getAllWork(Map<String, Object> params);

    /**
     * 删除代办事项
     */
    void deleteWork(Long id);

    /**
     * 完成一个事项
     *
     * @param id 事项id
     */
    void doneWord(String id);

    /**
     * 获取一个代办事项
     */
    ToDoList queryOneToDo(Long id);
}
