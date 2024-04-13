package com.xisuo.sellmanager.controller;

import com.xisuo.sellmanager.entity.User;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.UserService;
import com.xisuo.sellmanager.service.impl.ParamService;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zk
 * @Description: 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //HTML 文件所在的文件夹的前缀
    private final String PREFIX = "user/";

    @Autowired
    private ParamService paramService;
    @Autowired
    private UserService userService;


    /**
     * 修改密码
     *
     * @param id          用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @PostMapping("modify_password")
    @ResponseBody
    public String modifyPassword(@RequestParam("id") Long id, ModelMap map,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword) {
        String password = userService.modifyPassword(id, oldPassword, newPassword);
        map.put("user", UserContext.getUser());
        return password;
    }


    /**
     * 创建用户
     */
    @PostMapping("create")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/user/list";
    }


    /**
     * 创建用户的时候,进行用户名的唯一性校验
     *
     * @param name 名字
     */
    @PostMapping("name_search")
    @ResponseBody
    public String nameSearch(@RequestParam("name") String name) {
        int num = userService.findName(name);
        if (num == 0) {
            return "0";
        } else {
            return String.valueOf(num);
        }
    }


    /**
     * 获取用户详情
     */
    @GetMapping("detail")
    public String getUserDetail(@RequestParam("id") Long id, ModelMap map) {
        User user = userService.getUserDetail(id);
        map.put("user", user);
        return PREFIX + "detail";
    }


    /**
     * 保存用户修改后的详情
     */
    @PostMapping("save")
    public String saveUserDetail(User user) {
        userService.saveModifyUser(user);
        return "redirect:/index";
    }


    /**
     * 用户列表
     *
     * @Param keyword  根据名字进行关键字搜索
     */
    @GetMapping("list")
    public String getUserList(@RequestParam(required = false, value = "pageNo") Integer pageNo,
                              @RequestParam(required = false, value = "keyword") String keyword,
                              @RequestParam(required = false, value = "phone") String phone,
                              @RequestParam(required = false, value = "role") Integer role,
                              @RequestParam(required = false, value = "userState") Integer userState,
                              ModelMap map) {
        Map<String, Object> params = paramService.handlePageData(pageNo);
        params = paramService.handleKeyLike(params, Pair.of("keyword", keyword), Pair.of("phone", phone));
        params = paramService.handleInt(params, Triple.of("role", role, 0), Triple.of("userState", userState, 0));
        Page<User> page = userService.getAllUser(params);
        map.put("pageInfo", page);
        //参数回填
        map = paramService.handleObj(map, Pair.of("keyword", keyword), Pair.of("phone", phone),
                Pair.of("role", role), Pair.of("userState", userState));
        map.put("user", UserContext.getUser());
        return PREFIX + "list";
    }


    /**
     * 删除用户
     */
    @PostMapping("delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }


    /**
     * 重置一个用户的密码
     *
     * @param id 用户id
     */
    @PostMapping("reset")
    @ResponseBody
    public String restPassword(@RequestParam("id") Long id) {
        userService.restPassword(id);
        return "重置密码成功";
    }

    /**
     * 去上传个人图像
     */
    @Deprecated
    private String toUploadPhoto(ModelMap map) {
        map.put("user", UserContext.getUser());
        return "user/photo";
    }


    /**
     * to_create   rest_password
     * 去创建用户/去修改密码
     */
    @GetMapping("{path}")
    public String toCreateUser(@PathVariable("path") String path, ModelMap map) {
        map.put("user", UserContext.getUser());
        return PREFIX + path;
    }


}
