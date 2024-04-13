package com.xisuo.sellmanager.service;

import com.xisuo.sellmanager.entity.Product;
import com.xisuo.sellmanager.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 产品服务类
 * @date 2021-07-05 11:51
 */
public interface ProductService {

    /**
     * 根据id 查找一个产品
     *
     * @param id 产品id
     */
    Product getProductById(Long id);

    /**
     * 创建产品
     *
     * @param product 产品
     */
    void createProduct(Product product);

    /**
     * 保存修改后的产品
     *
     * @param product 产品
     */
    void modifyProduct(Product product);

    /**
     * 删除产品
     *
     * @param id 产品id
     */
    void deleteProduct(Long id);

    /**
     * 查询所有产品
     * pageNo  页数
     * keyword  名字关键字
     */
    Page<Product> queryAllProduct(Map<String, Object> params);

    /**
     * 查询所有产品,创建订单用
     */
    List<Map<String, Object>> queryProducts();

    /**
     * 根据产品名字查询产品id
     *
     * @param productNames 产品名字
     */
    List<Long> queryProductIds(List<String> productNames);

    /**
     * 根据名字查询完整的产品名字进行填充
     *
     * @param name 搜索的简短名字
     */
    List<String> searchName(String name);

    /**
     * 校验输入的产品的名字是否正确
     */
    String productService(List<String> productNames);

    /**
     * 创建产品时候,搜索当前名字的产品是否存在
     *
     * @param name 产品名
     */
    int findName(String name);
}
