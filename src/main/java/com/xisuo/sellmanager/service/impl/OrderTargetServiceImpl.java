package com.xisuo.sellmanager.service.impl;

import com.xisuo.sellmanager.constant.Constant;
import com.xisuo.sellmanager.dao.OrderTargetDao;
import com.xisuo.sellmanager.entity.OrderTarget;
import com.xisuo.sellmanager.entity.VoOrderTarget;
import com.xisuo.sellmanager.interceptor.UserContext;
import com.xisuo.sellmanager.service.OrderTargetService;
import com.xisuo.sellmanager.utils.NumUtil;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service("orderTargetService")
public class OrderTargetServiceImpl implements OrderTargetService {

    private static Logger logger = LoggerFactory.getLogger(OrderTargetServiceImpl.class);

    @Autowired
    private OrderTargetDao orderTargetDao;

    @Override
    public Pair<String, String> queryTargets() {
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
        String order0 = orderTargetDao.getNowOrder(0L, now);
        String order1 = orderTargetDao.getNowOrder(UserContext.getUser().getId(), now);
        if (StringUtils.isBlank(order0)) {
            order0 = Constant.DEFAULT_MONEY;
        }
        if (StringUtils.isBlank(order1)) {
            order1 = Constant.DEFAULT_MONEY;
        }
        return Pair.of(order0, order1);
    }


    @Override
    public void addTarget(OrderTarget orderTarget) {
        orderTarget.setUserId(UserContext.getUser().getId());
        orderTarget.setTargetState(0);
        orderTargetDao.addTarget(orderTarget);
    }


    @Override
    public Page<VoOrderTarget> getTargets(Map<String, Object> data) {
        List<VoOrderTarget> list = orderTargetDao.queryTargets(data);
        int targetsNum = orderTargetDao.queryTargetsNum(data);
        Page<VoOrderTarget> page = new Page<>(NumUtil.num2Int(data.get("pageNo")), targetsNum, list);
        return page;
    }


    @Override
    public void doneTarget(Map<String, Object> map) {
        orderTargetDao.doneTarget(map);

    }


    @Override
    public void modifyTarget(OrderTarget orderTarget) {
        orderTarget.setUserId(UserContext.getUser().getId());
        orderTargetDao.modifyTarget(orderTarget);

    }


    @Override
    public void deleteTarget(Long id) {
        orderTargetDao.deleteTarget(id);
    }


    @Override
    public OrderTarget getOneTarget(Long id) {
        return orderTargetDao.getOneTarget(id);
    }


}
