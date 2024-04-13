package com.xisuo.sellmanager.controller;

import com.alibaba.fastjson.JSON;
import com.xisuo.sellmanager.entity.ToDoList;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.ToDoListService;
import com.xisuo.sellmanager.service.impl.ParamService;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 用户的代办事项
 */
@Controller
@RequestMapping("todo")
public class ToDoListController {


    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private ParamService paramService;


    /**
     * 去创建代办事项
     */
    @GetMapping("to_create")
    public String toCreateUser(ModelMap map) {
        map.put("user", UserContext.getUser());
        return "todo/to_create";
    }


    /**
     * 保存代办事项
     */
    @PostMapping("create")
    public String createUser(ToDoList toDoList) {
        toDoListService.createWork(toDoList);
        return "redirect:/todo/list";
    }

    /**
     * 编辑代办事项
     */
    @GetMapping("detail")
    public String modify(@RequestParam("id") Long id, ModelMap map) {
        ToDoList toDoList = toDoListService.queryOneToDo(id);
        map.put("user", UserContext.getUser());
        map.put("toDoList", toDoList);
        return "todo/detail";
    }


    /**
     * 保存用户修改后的代办事项
     */
    @PostMapping("save")
    public String saveUserDetail(ToDoList toDoList) {
        toDoListService.saveModifyWork(toDoList);
        return "redirect:/todo/list";
    }


    /**
     * 完成一个事项
     *
     * @param id 事项id
     */
    @PostMapping("done")
    @ResponseBody
    public String doneWord(@RequestParam("id") String id) {
        toDoListService.doneWord(id);
        return JSON.toJSONString("success");
    }


    /**
     * 用户的代办事项列表
     *
     * @Param keyword  根据名字进行关键字搜索
     */
    @GetMapping("list")
    public String getUserList(@RequestParam(required = false, value = "pageNo") Integer pageNo,
                              @RequestParam(required = false, value = "keyword") String keyword,
                              ModelMap map) {
        Map<String, Object> params = paramService.handlePageData(pageNo);
        params = paramService.handleKeyLike(params, Pair.of("keyword", keyword));
        Page<ToDoList> page = toDoListService.getAllWork(params);
        map.put("pageInfo", page);
        map.put("keyword", keyword);
        map.put("user", UserContext.getUser());
        return "todo/list";
    }


    /**
     * 删除代办事项
     */
    @PostMapping("delete")
    public String delete(@RequestParam("id") Long id) {
        toDoListService.deleteWork(id);
        return "redirect:/todo/list";
    }

}
