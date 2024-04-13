package com.xisuo.sellmanager.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CacheUtils {

    private static Cache cache;

    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class.getClass());

    static {
        cache = CacheBuilder.newBuilder().build();
    }

    public static Object get(String key, Callable callable) {
        try {
            return cache.get(key, callable);
        } catch (ExecutionException e) {
            logger.error("execution exception", e);
        }
        return null;
    }

    public static void remove(String key) {
        cache.invalidate(key);
    }

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static Object get(String key) {
        return cache.getIfPresent(key);
    }


}