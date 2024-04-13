package com.xisuo.sellmanager.dao;

import com.xisuo.sellmanager.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 产品dao
 */
@Repository
public interface ProductDao {

    /**
     * 保存产品
     *
     * @param product
     */
    void save(Product product);


    /**
     * 查询所有的产品
     */
    List<Product> queryAllProduct(Map<String, Object> map);

    /**
     * 查询产品的数量
     */
    int queryAllProductNum(Map<String, Object> map);

    /**
     * 根据id 查询产品
     *
     * @param id 产品id
     */
    Product queryProductById(@Param("id") Long id);


    /**
     * 修改一个产品
     *
     * @param product
     */
    void modifyProduct(Product product);


    /**
     * 删除一个产品
     *
     * @param id 产品id
     */
    void deleteProduct(@Param("id") Long id);


    /**
     * 查询所有产品,返回id name
     */
    List<Map<String, Object>> queryProducts();

    /**
     * 根据产品名字查询产品id
     *
     * @param productNames 产品名字
     */
    List<Long> queryProductIds(@Param("productNames") List<String> productNames);

    /**
     * 根据名字查询完整的产品名字进行填充
     *
     * @param name 搜索的简短名字
     */
    List<String> searchName(@Param("name") String name);

    /**
     * 创建产品时候,搜索当前名字的产品是否存在
     *
     * @param name 产品名
     */
    int findName(@Param("name") String name);
}
