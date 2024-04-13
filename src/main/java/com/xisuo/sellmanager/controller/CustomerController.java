package com.xisuo.sellmanager.controller;


import com.xisuo.sellmanager.constant.CustomerState;
import com.xisuo.sellmanager.entity.Customer;
import com.xisuo.sellmanager.entity.CustomerFollow;
import com.xisuo.sellmanager.entity.VoCustomer;
import com.xisuo.sellmanager.entity.VoCustomerFollow;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.CustomerService;
import com.xisuo.sellmanager.service.impl.ParamService;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author zk
 * @Description: 客户
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ParamService paramService;


    /**
     * 去创建一个客户
     */
    @GetMapping("to_create")
    public String toCreate(ModelMap map) {
        map.put("user", UserContext.getUser());
        return "customer/to_create";
    }

    /**
     * 创建用户的时候,进行客户名的唯一性校验
     *
     * @param name 名字
     */
    @PostMapping("name_search")
    @ResponseBody
    public String nameSearch(@RequestParam("name") String name) {
        int num = customerService.findName(name);
        if (num == 0) {
            return "0";
        } else {
            return String.valueOf(num);
        }
    }


    /**
     * 创建一个客户
     */
    @PostMapping("create")
    public String createCustomer(Customer customer) {
        customerService.createCustomer(customer);
        return "redirect:/customer/list";
    }


    /**
     * 获取客户详情
     */
    @GetMapping("detail")
    public String getCustomerDetail(@RequestParam("id") Long id, ModelMap map) {
        Customer customer = customerService.getCustomerById(id);
        map.put("customer", customer);
        map.put("user", UserContext.getUser());
        return "customer/detail";
    }


    /**
     * 保存用户修改后的客户
     */
    @PostMapping("save")
    public String saveCustomer(Customer customer) {
        customerService.saveModifyCustomer(customer);
        return "redirect:/customer/list";
    }


    /**
     * 删除客户
     */
    @PostMapping("delete")
    public String deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer/list";
    }


    /**
     * 获取客户列表
     */
    @GetMapping("list")
    public String getCustomerList(
            @RequestParam(required = false, value = "pageNo") Integer pageNo,
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "person") String person,
            @RequestParam(required = false, value = "profession") String profession,
            @RequestParam(required = false, value = "province") String province,
            @RequestParam(required = false, value = "city") String city,
            @RequestParam(required = false, value = "userId") Long userId,
            @RequestParam(required = false, value = "customerState") Integer customerState,
            ModelMap map) {
        Map<String, Object> params = paramService.handlePageData(pageNo);
        params = paramService.handleKeyLike(params, Pair.of("name", name),
                Pair.of("person", person), Pair.of("profession", profession));
        paramService.handleString(params, Pair.of("province", province), Pair.of("city", city));
        paramService.handleInt(params, Triple.of("customer_state", customerState, CustomerState.NEW));
        paramService.handleLong(params, Pair.of("user_id", userId));
        Page<VoCustomer> page = customerService.getCustomerList(params);
        map.put("pageInfo", page);
        map.put("user", UserContext.getUser());
        paramService.handleObj(map, Pair.of("name", name), Pair.of("person", person), Pair.of("profession", profession),
                Pair.of("province", province), Pair.of("city", city), Pair.of("customerState", customerState),
                Pair.of("userId", userId));
        return "customer/list";
    }


    /**
     * 根据公司名字或者联系人名字搜索公司
     * @param name 输入的客户名
     */
    @GetMapping("search")
    public String searchCustomer(@RequestParam("keyword") String name, ModelMap map) {
        Page<Customer> customers = customerService.queryCustomer(name);
        map.put("pageInfo", customers);
        map.put("user", UserContext.getUser());
        return "customer/list";
    }


    /**
     * 去创建一个客户的跟进
     */
    @GetMapping("to_create_fellow")
    public String toCreateFellow(@RequestParam("customerId") long customerId, ModelMap map) {
        map.put("user", UserContext.getUser());
        map.put("customer", customerService.getCustomerById(customerId));
        return "customer/to_create_fellow";
    }


    /**
     * 创建一个客户的跟踪信息
     */
    @PostMapping("create_fellow")
    public String createCustomerFellow(CustomerFollow customerFollow, RedirectAttributes attributes) {
        customerService.createFellow(customerFollow);
        //重定向带着参数,也可以在下面的url上面拼接参数
        attributes.addAttribute("customerId", customerFollow.getCustomerId());
        return "redirect:/customer/fellow_list";
    }


    /**
     * TODO  list 页面的 搜素框....
     * 获取客户跟进详情列表
     * id   客户id
     */
    @GetMapping("fellow_list")
    public String getCustomerFellowDetail(
            @RequestParam(required = false, value = "pageNo") Integer pageNo,
            @RequestParam(required = false, value = "name") String name,//客户名
            @RequestParam(required = false, value = "subject") String subject,//跟进主题
            @RequestParam(required = false, value = "startTime") String startTime,
            @RequestParam(required = false, value = "endTime") String endTime,
            @RequestParam("customerId") long customerId,
            ModelMap map) {
        Map<String, Object> data = paramService.handlePageData(pageNo);
        paramService.handleLong(data, Pair.of("customerId", customerId));
        paramService.handleDateTime(data, Pair.of("startTime", startTime), Pair.of("endTime", endTime));
        paramService.handleString(data, Pair.of("subject", subject));
        paramService.handleKeyLike(data, Pair.of("name", name));
        Page<VoCustomerFollow> followPage = customerService.getCustomerFellow(data);
        map.put("pageInfo", followPage);
        map.put("user", UserContext.getUser());
        paramService.handleObj(map, Pair.of("startTime", startTime), Pair.of("endTime", endTime),
                Pair.of("subject", subject), Pair.of("name", name), Pair.of("customerId", customerId));
        return "customer/fellow_list";
    }


    /**
     * 获取一个跟进的详情
     *
     * @param id 跟进id
     */
    @GetMapping("fellow_detail")
    public String getFellowDetail(@RequestParam("id") Long id, ModelMap map) {
        CustomerFollow customerFollow = customerService.queryFellowDetail(id);
        map.put("customer", customerService.getCustomerById(customerFollow.getCustomerId()));
        map.put("customerFollow", customerFollow);
        map.put("user", UserContext.getUser());
        return "customer/fellow_detail";
    }

    /**
     * 删除一个跟进
     */
    @PostMapping("fellow_delete")
    public String deleteFellow(@RequestParam("id") Long id, @RequestParam("customerId") long customerId,
                               RedirectAttributes attributes) {
        customerService.deleteFellow(id);
        attributes.addAttribute("customerId", customerId);
        return "redirect:/customer/fellow_list";
    }


}
