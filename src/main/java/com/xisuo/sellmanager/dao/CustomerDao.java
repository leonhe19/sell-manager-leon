package com.xisuo.sellmanager.dao;

import com.xisuo.sellmanager.entity.Customer;
import com.xisuo.sellmanager.entity.CustomerFollow;
import com.xisuo.sellmanager.entity.VoCustomer;
import com.xisuo.sellmanager.entity.VoCustomerFollow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 客户dao
 */
@Repository
public interface CustomerDao {

    /**
     * 创建客户
     *
     * @param customer 客户
     */
    void createCustomer(Customer customer);


    /**
     * 根据id查询客户
     *
     * @param id 客户id
     */
    Customer getCustomerById(@Param("id") Long id);


    /**
     * 修改客户
     *
     * @param customer
     */
    void modifyCustomer(Customer customer);


    /**
     * 创建一个客户的跟进
     *
     * @param customerFollow
     */
    void createFellow(CustomerFollow customerFollow);

    /**
     * 查询一个客户的所有跟进记录
     */
    List<VoCustomerFollow> getFellowList(Map<String, Object> param);

    int getFellowListNum(Map<String, Object> param);

    /**
     * 查询客户列表.
     */
    List<VoCustomer> queryCustomerList(Map<String, Object> param);


    /**
     * 查询客户列表的数量
     */
    int queryCustomerListNum(Map<String, Object> param);

    /**
     * 根据名字模糊查询客户信息
     */
    List<Customer> queryCustomer(@Param("name") String name);


    /**
     * 更改客户的状态
     *
     * @param customerId
     * @param toState
     */
    void updateState(@Param("customerId") long customerId, @Param("toState") int toState);

    /**
     * 获取一个跟进的详情
     *
     * @param id 跟进id
     */
    CustomerFollow queryFellowDetail(@Param("id") Long id);

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
     * 删除客户
     *
     * @param id 客户id
     */
    void deleteCustomer(Long id);

    /**
     * 删除客户跟进
     *
     * @param id 跟进id
     */
    void deleteCustomerFellow(Long id);

    /**
     * 创建客户时候,搜索当前名字的产品是否存在
     *
     * @param name 客户名
     */
    int findName(@Param("name") String name);
}
