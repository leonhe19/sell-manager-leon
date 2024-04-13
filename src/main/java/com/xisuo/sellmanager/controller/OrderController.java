package com.xisuo.sellmanager.controller;


import com.xisuo.sellmanager.entity.Customer;
import com.xisuo.sellmanager.entity.Order;
import com.xisuo.sellmanager.entity.OrderDetail;
import com.xisuo.sellmanager.entity.VoOrder;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.CustomerService;
import com.xisuo.sellmanager.service.OrderService;
import com.xisuo.sellmanager.service.ProductService;
import com.xisuo.sellmanager.service.UserService;
import com.xisuo.sellmanager.service.impl.ParamService;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 订单
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ParamService paramService;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;


    /**
     * 去创建个订单
     */
    @GetMapping("to_create")
    public String toCreate(ModelMap map) {
        List<Map<String, Object>> list = customerService.queryAllCustomer();
        List<Map<String, Object>> maps = productService.queryProducts();
        map.put("products", maps);
        map.put("customers", list);
        map.put("user", UserContext.getUser());
        return "order/to_create";
    }


    /**
     * 创建一个订单
     */
    @PostMapping("create")
    public String createOrder(Order order,
                              @RequestParam("productId") List<String> productIds,
                              @RequestParam("prices") List<String> prices,
                              @RequestParam("amount") List<Long> amounts) {
        if (productIds.size() < 1) {
            return "redirect:/order/to_create";
        }
        orderService.createOrder(order, productIds, prices, amounts);
        return "redirect:/order/list";
    }


    /**
     * 订单列表
     */
    @GetMapping("list")
    public String toList(@RequestParam(required = false, value = "pageNo") Integer pageNo,
                         @RequestParam(required = false, value = "customerName") String customerName, //客户名
                         @RequestParam(required = false, value = "userId") Long userId,  //用户id
                         @RequestParam(required = false, value = "startTime") String startTime, //开始时间
                         @RequestParam(required = false, value = "endTime") String endTime,   //结束时间
                         @RequestParam(required = false, value = "sendTime") String sendTime,   //发货时间
                         ModelMap map) {
        Map<String, Object> params = paramService.handlePageData(pageNo);
        paramService.handleKeyLike(params, Pair.of("customerName", customerName));
        paramService.handleLong(params, Pair.of("userId", userId));
        paramService.handleDate(params, Pair.of("startTime", startTime), Pair.of("endTime", endTime), Pair.of("sendTime", sendTime));
        Page<VoOrder> page = orderService.getOrderList(params);
        //现在用户的id,名字
        List<Map<String, Object>> list = userService.getUserIdName();
        map.put("users", list);
        map.put("pageInfo", page);
        map.put("user", UserContext.getUser());
        paramService.handleObj(map, Pair.of("customerName", customerName),
                Pair.of("userId", userId), Pair.of("startTime", startTime), Pair.of("endTime", endTime));
        return "order/list";
    }


    /**
     * detail  to_edit
     * 获取订单详情,查看详情,去编辑
     */
    @GetMapping("{path}")
    public String getOrderDetail(@PathVariable("path") String path, @RequestParam("id") Long orderId, ModelMap map) {
        VoOrder order = orderService.getOrderById(orderId);
        List<OrderDetail> list = orderService.queryOrderDetail(orderId);
        map.put("orderProducts", list);
        map.put("order", order);
        map.put("user", UserContext.getUser());
        return "order/" + path;
    }


    /**
     * 保存用户修改后的订单
     */
    @PostMapping("save")
    public String saveOrder(Order order, @RequestParam("productId") List<Long> ids, @RequestParam("amount") List<Long> amounts) {
        orderService.modifyOrder(order, ids, amounts);
        return "redirect:/order/list";
    }


    /**
     * 选择一个订单查看开票信息
     */
    @GetMapping("to_invoice")
    public String toInvoice(@RequestParam("id") Long orderId, ModelMap map) {
        Customer customer = customerService.getCustomerById(orderId);
        VoOrder order = orderService.getOrderById(orderId);
        List<OrderDetail> list = orderService.queryOrderDetail(orderId);
        map.put("orderProducts", list);
        map.put("order", order);
        map.put("customer", customer);
        map.put("user", UserContext.getUser());
        return "order/to_invoice";
    }


}
