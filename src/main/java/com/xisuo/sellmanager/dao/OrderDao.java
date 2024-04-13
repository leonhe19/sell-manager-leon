package com.xisuo.sellmanager.dao;

import com.xisuo.sellmanager.entity.Order;
import com.xisuo.sellmanager.entity.OrderDetail;
import com.xisuo.sellmanager.entity.VoOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 订单dao
 */
@Repository
public interface OrderDao {


    /**
     * 获取首页用的订单金额的数据
     */
    Map<String, Object> getMonMoney(@Param("startTime") Date startDayOfMonth, @Param("endTime") Date endDayOfMonth);

    /**
     * 获取一天的总的订单金额
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    String getDayMoney(@Param("startTime")String startTime, @Param("endTime")String endTime);

    /**
     * 根据订单id查询订单
     *
     * @param orderId 订单id
     * @return
     */
    VoOrder getOrderById(@Param("orderId") Long orderId);

    /**
     * 查询订单产品详情
     *
     * @param orderId 订单id
     */
    List<OrderDetail> queryOrderDetail(@Param("orderId") Long orderId);

    /**
     * 修改订单
     */
    void modifyOrder(Order order);

    /**
     * 修改一个订单明细
     */
    void modifyOrderDetail(OrderDetail orderDetail);

    /**
     * 创建订单
     */
    int createOrder(Order order);

    /**
     * 创建订单明细
     */
    void createOrderDetail(@Param("lists") List<OrderDetail> lists);


    /**
     * 获取订单的分页数据
     */
    List<VoOrder> getOrderList(Map<String, Object> params);

    int getOrderListNum(Map<String, Object> params);

    /**
     * 某天待发货的公司名字
     */
    List<String> queryDaySipping(@Param("days") LocalDate time);

    /**
     * 某天待收款的公司名字和金额
     */
    List<Map<String, Object>> queryDayReceipt(@Param("days") LocalDate time);

    /**
     * 一段时间的订单总数量
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    int queryOrderCount(@Param("startTime")String startTime, @Param("endTime")String endTime);
}
