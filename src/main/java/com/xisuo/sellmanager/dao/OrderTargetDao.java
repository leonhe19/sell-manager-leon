package com.xisuo.sellmanager.dao;

import com.xisuo.sellmanager.entity.OrderTarget;
import com.xisuo.sellmanager.entity.VoOrderTarget;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderTargetDao {

    /**
     * 添加一个目标
     */
    void addTarget(OrderTarget orderTarget);


    /**
     * 获取当前时间后5个月的目标
     */
    List<VoOrderTarget> queryTargets(Map<String, Object> map);

    int queryTargetsNum(Map<String, Object> map);

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
     * @param id
     */
    void deleteTarget(@Param("id") Long id);

    /**
     * 查询一个目标
     */
    OrderTarget getOneTarget(@Param("id") Long id);

    /**
     * 一个人的一段时期的目标
     *
     * @param id   用户id
     * @param now 时间
     */
    String getNowOrder(@Param("id") long id, @Param("time") String now);
}
