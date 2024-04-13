package com.xisuo.sellmanager.service.impl;

import com.xisuo.sellmanager.constant.CustomerState;
import com.xisuo.sellmanager.dao.CustomerDao;
import com.xisuo.sellmanager.entity.Customer;
import com.xisuo.sellmanager.entity.CustomerFollow;
import com.xisuo.sellmanager.entity.VoCustomer;
import com.xisuo.sellmanager.entity.VoCustomerFollow;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.CustomerService;
import com.xisuo.sellmanager.utils.NumUtil;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @date 2021-07-05 14:21
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customerDao;


    /**
     * 创建客户
     */
    @Override
    public void createCustomer(Customer customer) {
        logger.info("用户:{} 创建了客户:{}", UserContext.getUser().getId(), customer.getName());
        customer.setCreateTime(new Date());
        customer.setUserId(UserContext.getUser().getId());
        customer.setCustomerState(CustomerState.NEW);
        customerDao.createCustomer(customer);
    }


    /**
     * 获取客户详情 by  客户id
     */
    @Override
    public Customer getCustomerById(Long id) {
        return customerDao.getCustomerById(id);
    }

    /**
     * 修改客户
     */
    @Override
    public void saveModifyCustomer(Customer customer) {
        logger.info("用户:{} 修改了客户:{}", UserContext.getUser().getId(), customer.getName());
        customerDao.modifyCustomer(customer);
    }


    /**
     * 查询一个客户的跟进列表
     */
    @Override
    public Page<VoCustomerFollow> getCustomerFellow(Map<String, Object> param) {
        List<VoCustomerFollow> list = customerDao.getFellowList(param);
        int num = customerDao.getFellowListNum(param);
        Page<VoCustomerFollow> page = new Page<>(NumUtil.num2Int(param.get("pageNo")), num, list);
        return page;
    }

    /**
     *
     * 创建一条客户的跟进
     */
    @Override
    public void createFellow(CustomerFollow customerFollow) {
        customerFollow.setCreateTime(new Date());
        customerFollow.setUserId(UserContext.getUser().getId());
        customerDao.createFellow(customerFollow);
    }

    /**
     * 获取客户列表
     */
    @Override
    public Page<VoCustomer> getCustomerList(Map<String, Object> param) {
        List<VoCustomer> customerPage = customerDao.queryCustomerList(param);
        int num = customerDao.queryCustomerListNum(param);
        Page<VoCustomer> page = new Page<>(NumUtil.num2Int(param.get("pageNo")), num, customerPage);
        return page;
    }

    /**
     *
     * 根据客户名查询客户
     */
    @Override
    public Page<Customer> queryCustomer(String name) {
        if (StringUtils.isNotBlank(name)) {
            name = "%" + name + "%";
        } else {
            name = null;
        }
        List<Customer> list = customerDao.queryCustomer(name);
        Page<Customer> page = new Page<>(list.size(), list);
        return page;
    }

    /**
     * 根据客户id更新客户的状态
     */
    @Override
    public void updateCustomerState(long customerId, int state) {
        logger.info("用户{} 改变了 客户{} 状态为{}", UserContext.getUser().getId(), customerId, state);
        customerDao.updateState(customerId, state);
    }

    /**
     * 查询客户跟进的详情
     * @param id 跟进id
     * @return
     */
    @Override
    public CustomerFollow queryFellowDetail(Long id) {
        return customerDao.queryFellowDetail(id);
    }

    /**
     * 查询所有的客户
     */
    @Override
    public List<Map<String, Object>> queryAllCustomer() {
        return customerDao.queryAllCustomer();
    }

    /**
     * 删除一个跟进
     * @param id 跟进id
     */
    @Override
    public void deleteFellow(Long id) {
        customerDao.deleteFellow(id);
    }


    /**
     * 删除客户
     * @param id 客户id
     */
    @Transactional
    @Override
    public void deleteCustomer(Long id) {
        customerDao.deleteCustomer(id);
        customerDao.deleteCustomerFellow(id);
    }

    /**
     * 查询客户
     * @param name 客户名
     * @return
     */
    @Override
    public int findName(String name) {
        return customerDao.findName(name);
    }


}
