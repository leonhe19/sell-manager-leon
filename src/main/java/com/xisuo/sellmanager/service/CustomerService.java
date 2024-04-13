package com.xisuo.sellmanager.service;

import com.xisuo.sellmanager.entity.Customer;
import com.xisuo.sellmanager.entity.CustomerFollow;
import com.xisuo.sellmanager.entity.VoCustomer;
import com.xisuo.sellmanager.entity.VoCustomerFollow;
import com.xisuo.sellmanager.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 客户服务类
 * @date 2021-07-05 11:49
 */
public interface CustomerService {

    /**
     * 创建客户
     *
     * @param customer 客户
     */
    void createCustomer(Customer customer);

    /**
     * 根据客户id查找客户
     *
     * @param id 客户id
     */
    Customer getCustomerById(Long id);

    /**
     * 保存修改后的客户
     *
     * @param customer 客户
     */
    void saveModifyCustomer(Customer customer);

    /**
     * 获取客户跟进信息
     */
    Page<VoCustomerFollow> getCustomerFellow(Map<String, Object> param);

    /**
     * 创建一条客户的跟进消息
     *
     * @param customerFollow 客户的跟进
     */
    void createFellow(CustomerFollow customerFollow);

    /**
     * 获取客户列表的分页信息
     */
    Page<VoCustomer> getCustomerList(Map<String, Object> param);


    /**
     * 根据公司名字或者联系人的名字模糊查询客户信息
     *
     * @param name 客户名
     */
    Page<Customer> queryCustomer(String name);


    /**
     * 更改客户的状态
     *
     * @param customerId 客户id
     * @param state      状态
     */
    void updateCustomerState(long customerId, int state);


    /**
     * 获取一个跟进的详情
     *
     * @param id 跟进id
     */
    CustomerFollow queryFellowDetail(Long id);

    /**
     * 查询所有客户
     */
    List<Map<String, Object>> queryAllCustomer();

    /**
     * 删除一个跟进
     *
     * @param id 跟进id
     */
    void deleteFellow(Long id);

    /**
     * 删除一个客户
     *
     * @param id 客户id
     */
    void deleteCustomer(Long id);

    /**
     * 创建客户时候,搜索当前名字的产品是否存在
     *
     * @param name 客户名
     */
    int findName(String name);
}
