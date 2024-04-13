package com.xisuo.sellmanager.controller;

import com.xisuo.sellmanager.entity.OrderTarget;
import com.xisuo.sellmanager.entity.VoOrderTarget;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.OrderTargetService;
import com.xisuo.sellmanager.service.UserService;
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

import java.util.List;
import java.util.Map;

/**
 * 业绩目标,业绩统计
 */
@Controller
@RequestMapping("/targets")
public class TargetController {


    @Autowired
    private OrderTargetService orderTargetService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private UserService userService;

    /**
     * 去创建一个业绩
     */
    @GetMapping("to_create")
    public String toCreateTarget(ModelMap map) {
        List<Map<String, Object>> lists = userService.getUserIdName();
        map.put("users", lists);
        map.put("user", UserContext.getUser());
        return "targets/to_create";
    }

    /**
     * 保存新建的业绩
     */
    @PostMapping("create")
    public String saveTarget(OrderTarget orderTarget) {
        orderTargetService.addTarget(orderTarget);
        return "redirect:/targets/list";
    }


    /**
     * 获取详情
     *
     * @param id 目标id
     */
    @GetMapping("detail")
    public String getDetail(@RequestParam(value = "id") Long id, ModelMap map) {
        OrderTarget orderTarget = orderTargetService.getOneTarget(id);
        map.put("user", UserContext.getUser());
        map.put("target", orderTarget);
        map.put("users", userService.getUserIdName());
        return "targets/detail";
    }


    /**
     * 保存修改后的业绩
     */
    @PostMapping("save")
    public String modifyTarget(OrderTarget orderTarget) {
        orderTargetService.modifyTarget(orderTarget);
        return "redirect:/targets/list";
    }


    /**
     * 获取5个月的业绩
     */
    @GetMapping("list")
    public String allTarget(@RequestParam(required = false, value = "pageNo") Integer pageNo,
                            @RequestParam(required = false, value = "startTime") String startTime,
                            @RequestParam(required = false, value = "endTime") String endTime,
                            @RequestParam(required = false, value = "target") String target,
                            @RequestParam(required = false, value = "targetState") Integer targetState,
                            @RequestParam(required = false, value = "userId") Long userId,
                            ModelMap map) {
        Map<String, Object> data = paramService.handlePageData(pageNo);
        paramService.handleDate(data, Pair.of("startTime", startTime), Pair.of("endTime", endTime));
        paramService.handleInt(data, Pair.of("targetState", targetState));
        paramService.handleLong(data, Pair.of("userId", userId));
        paramService.handleKeyLike(data, Pair.of("target", target));
        Page<VoOrderTarget> targets = orderTargetService.getTargets(data);
        paramService.handleObj(map, Pair.of("startTime", startTime), Pair.of("endTime", endTime),
                Pair.of("targetState", targetState), Pair.of("userId", userId), Pair.of("target", target));
        map.put("pageInfo", targets);
        map.put("user", UserContext.getUser());
        return "targets/list";
    }


    /**
     * 删除一个目标
     *
     * @param id 目标id
     */
    @PostMapping("delete")
    public String delete(@RequestParam(value = "id") Long id) {
        orderTargetService.deleteTarget(id);
        return "redirect:/targets/list";
    }


}
