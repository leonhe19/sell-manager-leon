package com.xisuo.sellmanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zk
 * @Description: 定时清理缓存
 */
@Component
public class CacheFlushScheduled {

    private static Logger log = LoggerFactory.getLogger(CacheFlushScheduled.class);

    @Autowired
    private CacheMap cacheMap;

    //10分钟
    private final static long TEN_MINUTE = 60 * 1000 * 10;


    /**
     * 每10分钟清理一次缓存的map,把过期的删除了
     */
    @Scheduled(fixedDelay = TEN_MINUTE)
    public void flushCache() {
        log.info("每10分钟清理一次缓存,开始清理缓存数据了");
        HashSet<String> set = new HashSet<>();
        ConcurrentHashMap<String, CacheValue> map = cacheMap.getMap();
        for (Map.Entry<String, CacheValue> entry : map.entrySet()) {
            String key = entry.getKey();
            CacheValue value = entry.getValue();
            LocalDateTime dateTime = value.getCreateTime().plusSeconds(value.getExpireTime());
            boolean after = LocalDateTime.now().isAfter(dateTime);
            if (after) {
                set.add(key);
            }
        }
        moveKeys(set, map);
    }


    /**
     * 每两个小时就删除使用次数小于5的cache
     */
    @Scheduled(cron = "0 0 0/2 * * ? ")
    public void flushMinCache() {
        log.info("每两个小时就删除缓存,开始清理缓存数据了");
        HashSet<String> set = new HashSet<>();
        ConcurrentHashMap<String, CacheValue> map = cacheMap.getMap();
        for (Map.Entry<String, CacheValue> entry : map.entrySet()) {
            String key = entry.getKey();
            CacheValue value = entry.getValue();
            if (value.getTimes() < 5) {
                set.add(key);
            }
        }
        moveKeys(set, map);
    }


    private void moveKeys(HashSet<String> set, ConcurrentHashMap<String, CacheValue> map) {
        for (String key : set) {
            map.remove(key);
        }
    }


}
