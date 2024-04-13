package com.xisuo.sellmanager.service;

import com.xisuo.sellmanager.entity.OrderTarget;
import com.xisuo.sellmanager.entity.VoOrderTarget;
import com.xisuo.sellmanager.utils.Page;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

/**
 * 业绩目标
 */
public interface OrderTargetService {


    /**
     * 获取首页需要的公司和个人的以及目标
     * 公司业绩, 个人业绩
     */
    Pair<String, String> queryTargets();

    /**
     * 添加一个目标
     */
    void addTarget(OrderTarget orderTarget);


    /**
     * 获取当前时间后5个月的目标
     */
    Page<VoOrderTarget> getTargets(Map<String, Object> data);


    /**
     * 完成了某个目标
     */
    void doneTarget(Map<String, Object> map);

    /**
     * 修改某个目标
     */
    void modifyTarget(OrderTarget orderTarget);

    /**
     * 删除一个目标
     *
     * @param id 目标id
     */
    void deleteTarget(Long id);

    /**
     * 查询一个目标
     *
     * @param id 目标id
     */
    OrderTarget getOneTarget(Long id);
}
