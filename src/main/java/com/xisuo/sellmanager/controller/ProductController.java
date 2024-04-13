package com.xisuo.sellmanager.controller;


import com.alibaba.fastjson.JSON;
import com.xisuo.sellmanager.entity.Product;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.ProductService;
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

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 产品管理
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ParamService paramService;


    /**
     * 去创建一个产品
     */
    @GetMapping("to_create")
    public String toCreate(ModelMap map) {
        map.put("user", UserContext.getUser());
        return "product/to_create";
    }


    /**
     * 创建用户的时候,进行产品名的唯一性校验
     *
     * @param name 名字
     */
    @PostMapping("name_search")
    @ResponseBody
    public String nameSearch(@RequestParam("name") String name) {
        int num = productService.findName(name);
        if (num == 0) {
            return "0";
        } else {
            return String.valueOf(num);
        }
    }


    /**
     * 创建一个产品
     */
    @PostMapping("create")
    public String createProduct(Product product) {
        productService.createProduct(product);
        return "redirect:/product/list";
    }


    /**
     * 获取产品详情
     */
    @GetMapping("detail")
    public String getProductDetail(@RequestParam("id") Long id, ModelMap map) {
        Product product = productService.getProductById(id);
        map.put("product", product);
        map.put("user", UserContext.getUser());
        return "product/detail";
    }


    /**
     * 保存用户修改后的产品详情
     */
    @PostMapping("save")
    public String saveProduct(Product product) {
        productService.modifyProduct(product);
        return "redirect:/product/list";
    }


    /**
     * 删除产品
     */
    @PostMapping("delete")
    public String delete(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/list";
    }


    /**
     * 产品列表
     */
    @GetMapping("list")
    public String productList(@RequestParam(required = false, value = "pageNo") Integer pageNo,
                              @RequestParam(required = false, value = "keyword") String keyword,
                              @RequestParam(required = false, value = "supplier") String supplier,
                              ModelMap map) {
        Map<String, Object> params = paramService.handlePageData(pageNo);
        params = paramService.handleKeyLike(params, Pair.of("keyword", keyword), Pair.of("supplier", supplier));
        Page<Product> page = productService.queryAllProduct(params);
        map.put("pageInfo", page);
        map.put("user", UserContext.getUser());
        paramService.handleObj(map, Pair.of("keyword", keyword), Pair.of("supplier", supplier));
        return "product/list";
    }


    /**
     * 根据名字查询完整的产品名字进行填充
     *
     * @param name 搜索的简短名字
     */
    @GetMapping("name_search")
    @ResponseBody
    public List<String> searchName(@RequestParam("name") String name) {
        List<String> names = productService.searchName(name);
        return names;
    }


    /**
     * 创建订单之间 校验输入的产品的名字是否正确,不正确进行提示
     */
    @GetMapping("verify_name")
    @ResponseBody
    public String verifyProductName(@RequestParam("productName") List<String> productNames) {
        String result = productService.productService(productNames);
        return JSON.toJSONString(result);
    }


}
