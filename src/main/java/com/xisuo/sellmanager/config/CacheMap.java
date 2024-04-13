package com.xisuo.sellmanager.config;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zk
 * @Description: 缓存
 */
@Service("cacheMap")
public class CacheMap {

    /**
     * 缓存的map
     */
    private static ConcurrentHashMap<String, CacheValue> map = new ConcurrentHashMap<>(16);


    /**
     * 设置缓存
     *
     * @param key        键
     * @param cacheValue 值
     */
    public void set(String key, CacheValue cacheValue) {
        map.put(key, cacheValue);
    }


    /**
     * 设置缓存
     *
     * @param key 键
     */
    public void set(String key, Object object) {
        CacheValue<Object> value = new CacheValue<>(object);
        map.put(key, value);
    }

    /**
     * 删除缓存
     *
     * @param key 键
     */
    public void remove(String key) {
        map.remove(key);
    }


    /**
     * 获取缓存数据
     * 如果过期了就返回null
     */
    public Object get(String key) {
        CacheValue value = map.get(key);
        if (key == null || value == null) {
            return null;
        }
        LocalDateTime dateTime = value.getCreateTime().plusSeconds(value.getExpireTime());
        boolean after = LocalDateTime.now().isAfter(dateTime);
        if (after) {
            map.remove(key);
            return null;
        } else {
            value.setTimes(value.getTimes() + 1);
            map.put(key, value);
            return value.getData();
        }
    }


    /**
     * 就只有当前包可以获取
     */
    ConcurrentHashMap<String, CacheValue> getMap() {
        return map;
    }


}
