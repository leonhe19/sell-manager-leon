package com.xisuo.sellmanager.dao;

import com.xisuo.sellmanager.entity.ToDoList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ToDoListDao {
    /**
     * 创建个代办事项
     */
    void createWork(ToDoList toDoList);

    /**
     * 保存修改后的代办事项
     */
    void modifyWork(ToDoList toDoList);

    /**
     * 获取分页的代办事项
     */
    List<ToDoList> getAllWork(Map<String, Object> params);

    int getAllWorkNum(Map<String, Object> params);

    /**
     * 删除代办事项
     */
    void deleteWork(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * 完成一个事项
     *
     * @param id 事项id
     */
    void doneWord(@Param("id") String id);

    /**
     * 查询一个用户的代办事项
     *
     * @param id 用户id
     */
    List<ToDoList> queryUserTodos(long id);

    /**
     * 根据主键查询一个代办事项
     */
    ToDoList queryOneToDo(@Param("id") Long id);
}
