package com.xisuo.sellmanager.controller;

import com.xisuo.sellmanager.constant.Constant;
import com.xisuo.sellmanager.entity.ToDoList;
import com.xisuo.sellmanager.entity.User;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.OrderService;
import com.xisuo.sellmanager.service.OrderTargetService;
import com.xisuo.sellmanager.service.ToDoListService;
import com.xisuo.sellmanager.service.UserService;
import com.xisuo.sellmanager.utils.CacheUtils;
import com.xisuo.sellmanager.utils.DESUtil;
import com.xisuo.sellmanager.utils.FileUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 登录
 */
@Controller
public class LoginController {


    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ToDoListService toDoListService;
    @Autowired
    private OrderTargetService orderTargetService;
    @Autowired
    private OrderService orderService;

    /**
     * 去登录
     */
    @GetMapping("/")
    public String toLogin() {
        return "login";
    }


    /**
     * 登录
     * map 设置值去前端显示
     */
    @PostMapping("login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpServletResponse response, ModelMap map) {
        List<String> lists = userService.getAllUsername();
        if (!lists.contains(username)) {
            map.put("msg", "没有用户名,就不要瞎乱的登录别人的系统!");
            return "login";
        }
        User user = userService.login(username, password);
        if (user == null) {
            map.put("msg", "用户名或者密码错误");
            return "login";
        }
        if (user.getUserState() == 1) {
            map.put("msg", "用户名不能登录,请联系管理员进行处理");
            return "login";
        }
        response.addCookie(getCookie(user, Constant.COOKIE_TIME));
        CacheUtils.put(Constant.USER_CACHE_PREFIX + (user.getId()), user);
        UserContext.setUser(user);
        return "forward:/index";
    }


    /**
     * 登录后进入首页
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "index")
    public String index(ModelMap map) {
        map.put("user", UserContext.getUser());
        //当前用户待办事项
        List<ToDoList> index = toDoListService.queryUserTodo();
        map.put("toDoLists", index);
        //公司和当前用户目标
        Pair<String, String> pair = orderTargetService.queryTargets();
        map.put("companyTarget", pair.getLeft());
        map.put("userTarget", pair.getRight());
        //当月订单总金额和当前已支付
        Pair<String, String> indexMoney = orderService.getMouthMoney();
        map.put("totalMoney", indexMoney.getLeft());
        map.put("alreadyPay", indexMoney.getRight());
        //今日订单总金额
        String dayMoney = orderService.getDayMoney(null,null);
        map.put("dayMoney", dayMoney);
        //今天订单个数
        int num = orderService.queryOrderCount(null,null);
        map.put("todayCount", num);
        //今日待发货
        List<String> sipping = orderService.queryDaySipping(LocalDate.now());
        //今日待收款
        List<Map<String, Object>> receipt = orderService.queryDayReceipt(LocalDate.now());
        map.put("sipping", sipping);
        map.put("receipt", receipt);
        return "index";
    }


    /**
     * 获取cookie
     */
    private Cookie getCookie(User user, int time) {
        Cookie cookie = new Cookie(Constant.COOKIE_NAME, DESUtil.encrypt(user.getId()));
        cookie.setMaxAge(time);
        cookie.setPath("/");
        return cookie;
    }


    /**
     * 退出
     */
    @GetMapping("login_out")
    public String loginOut(HttpServletResponse response) {
        User user = UserContext.getUser();
        Cookie cookie = getCookie(user, 0);
        response.addCookie(cookie);
        CacheUtils.remove(Constant.USER_CACHE_PREFIX + user.getId());
        return "/";
    }


    /**
     * 整个系统的文件上传的接口
     * flag 1是用户上传图片,去更新用户图片地址.
     */
    @Deprecated
    private String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("flag") Integer flag) {
        try {
            String uploadFile;
            if (flag == 1) {
                uploadFile = FileUtils.handleUploadFile(file, "user");
                return "修改图像成功";
            } else {
                uploadFile = FileUtils.handleUploadFile(file, "product");
                return uploadFile;
            }
        } catch (Exception e) {
            //错误标志
            return "0";
        }
    }

}
