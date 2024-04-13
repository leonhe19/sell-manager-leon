package com.xisuo.sellmanager.service.impl;

import com.xisuo.sellmanager.config.CacheMap;
import com.xisuo.sellmanager.config.CustomerStateTask;
import com.xisuo.sellmanager.constant.Constant;
import com.xisuo.sellmanager.constant.CustomerState;
import com.xisuo.sellmanager.dao.OrderDao;
import com.xisuo.sellmanager.entity.Order;
import com.xisuo.sellmanager.entity.OrderDetail;
import com.xisuo.sellmanager.entity.Product;
import com.xisuo.sellmanager.entity.VoOrder;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.CustomerService;
import com.xisuo.sellmanager.service.OrderService;
import com.xisuo.sellmanager.service.ProductService;
import com.xisuo.sellmanager.utils.ArithUtil;
import com.xisuo.sellmanager.utils.DateAndLocalDateUtil;
import com.xisuo.sellmanager.utils.NumUtil;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @Description: 订单服务类
 * @date 2021-07-05 14:22
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CacheMap cacheMap;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;


    @Override
    public Pair<String, String> getMouthMoney() {
        Date startDayOfMonth = DateAndLocalDateUtil.getStartDayOfMonth();
        Date endDayOfMonth = DateAndLocalDateUtil.getEndDayOfMonth();
        Map<String, Object> map = orderDao.getMonMoney(startDayOfMonth, endDayOfMonth);
        if (map == null) {
            return Pair.of(Constant.DEFAULT_MONEY, Constant.DEFAULT_MONEY);
        }
        return Pair.of(map.get("total_money") + "", map.get("already_pay") + "");
    }


    @Override
    public String getDayMoney(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            //设置零点
            startTime = DateAndLocalDateUtil.getStartOfDay();
        }
        if (endTime == null) {
            //设置当天的结束时间
            endTime = DateAndLocalDateUtil.getEndOfDay();
        }
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:ss");
        String money = orderDao.getDayMoney(pattern.format(startTime), pattern.format(endTime));
        if (StringUtils.isBlank(money)) {
            money = Constant.DEFAULT_MONEY;
        }
        return money;
    }


    @Override
    public void modifyOrder(Order order, List<Long> ids, List<Long> amounts) {
        logger.info("用户{} 修改了一个订单", UserContext.getUser().getId());
        orderDao.modifyOrder(order);
    }


    @Transactional
    @Override
    public void createOrder(Order order, List<String> productIds, List<String> prices, List<Long> amounts) {
        //更新客户的状态
        CustomerStateTask.submitTask(() -> {
            if (order != null) {
                customerService.updateCustomerState(order.getCustomerId(), CustomerState.DONE);
            }
        });

        //计算总价格
        double totalPrice = 0;
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>(productIds.size());
        //订单明细,保存
        for (int i = 0; i < productIds.size(); i++) {
            Long id = Long.parseLong(productIds.get(i));
            Long amount = amounts.get(i);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setAmount(amount.intValue());
            orderDetail.setProductId(id);
            Product product = productService.getProductById(id);
            orderDetail.setProductCode(product.getCode());
            orderDetail.setProductName(product.getName());
            orderDetail.setUnivalent(Double.valueOf(prices.get(i)));
            double mul = ArithUtil.mul(amount, Double.valueOf(prices.get(i)));
            orderDetail.setTotalPrice(mul);
            totalPrice = ArithUtil.add(mul, totalPrice);
            orderDetails.add(orderDetail);
        }

        //设置订单,保存
        order.setUserId(UserContext.getUser().getId());
        order.setCreateDate(new Date());
        order.setTotalMoney(totalPrice);
        orderDao.createOrder(order);
        logger.info("用户{} 创建了一个订单:{}", UserContext.getUser().getId(), order.getId());

        //设置orderId
        for (OrderDetail detail : orderDetails) {
            detail.setOrderId(order.getId());
        }
        orderDao.createOrderDetail(orderDetails);

    }


    @Override
    public VoOrder getOrderById(Long orderId) {
        return orderDao.getOrderById(orderId);
    }


    @Override
    public Page<VoOrder> getOrderList(Map<String, Object> params) {
        List<VoOrder> list = orderDao.getOrderList(params);
        int num = orderDao.getOrderListNum(params);
        Page<VoOrder> page = new Page<>(NumUtil.num2Int(params.get("pageNo")), num, list);
        return page;
    }


    @Override
    public List<OrderDetail> queryOrderDetail(Long orderId) {
        return orderDao.queryOrderDetail(orderId);
    }

    @Override
    public List<String> queryDaySipping(LocalDate time) {
        if (time == null) {
            time = LocalDate.now();
        }
        return orderDao.queryDaySipping(time);
    }

    @Override
    public List<Map<String, Object>> queryDayReceipt(LocalDate time) {
        if (time == null) {
            time = LocalDate.now();
        }
        return orderDao.queryDayReceipt(time);
    }

    @Override
    public int queryOrderCount(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null) {
            //设置零点
            startTime = DateAndLocalDateUtil.getStartOfDay();
        }
        if (endTime == null) {
            //设置当天的结束时间
            endTime = DateAndLocalDateUtil.getEndOfDay();
        }
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:ss");
        return orderDao.queryOrderCount(pattern.format(startTime), pattern.format(endTime));
    }


}
