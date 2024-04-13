package com.xisuo.sellmanager.service;

import com.xisuo.sellmanager.entity.Order;
import com.xisuo.sellmanager.entity.OrderDetail;
import com.xisuo.sellmanager.entity.VoOrder;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 订单服务
 * @date 2021-07-05 11:50
 */
public interface OrderService {


    /**
     * 获取首页的数据,订单总金和已经支付的金额
     */
    Pair<String, String> getMouthMoney();

    /**
     * 获取一天的总的订单金额
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    String getDayMoney(LocalDateTime startTime, LocalDateTime endTime);


    /**
     * TODO 这个功能没完成...
     * 保存修改后的订单
     *
     * @param order   订单
     * @param ids
     * @param amounts
     */
    void modifyOrder(Order order, List<Long> ids, List<Long> amounts);

    /**
     * 创建订单
     *
     * @param order        订单
     * @param productIds  产品id
     * @param prices       价格
     * @param amounts      数量
     */
    void createOrder(Order order, List<String> productIds, List<String> prices, List<Long> amounts);

    /**
     * 获取订单的详情
     *
     * @param orderId 订单id
     */
    VoOrder getOrderById(Long orderId);

    /**
     * 获取订单的分页列表
     */
    Page<VoOrder> getOrderList(Map<String, Object> params);


    /**
     * 查询订单产品详情
     *
     * @param orderId 订单id
     */
    List<OrderDetail> queryOrderDetail(Long orderId);


    /**
     * 某天待发货的公司名字
     */
    List<String> queryDaySipping(LocalDate time);


    /**
     * 某天待收款的公司名字和金额
     */
    List<Map<String, Object>> queryDayReceipt(LocalDate time);

    /**
     * 一段时间的订单总数量
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    int queryOrderCount(LocalDateTime startTime, LocalDateTime endTime);
}
