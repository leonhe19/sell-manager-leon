package com.xisuo.sellmanager.service.impl;

import com.xisuo.sellmanager.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数服务类
 */
@Service("paramService")
public class ParamService {

    private static Logger logger = LoggerFactory.getLogger(ParamService.class);


    /**
     * 进行页码数据的判断,已经数据的放入
     *
     * @param pageNo 页码
     */
    public Map<String, Object> handlePageData(Integer pageNo) {
        Map<String, Object> map = new HashMap<>(4);
        if (pageNo == null || pageNo < 0) {
            map.put("pageNum", (Constant.PAGE - 1) * Constant.PAGE_SIZE);
            map.put("pageNo", Constant.PAGE);
        } else {
            map.put("pageNum", (pageNo - 1) * Constant.PAGE_SIZE);
            map.put("pageNo", pageNo);
        }
        map.put("pageSize", Constant.PAGE_SIZE);
        return map;
    }


    /**
     * 非空判断,不为空放进去,拼接前后的%
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public Map<String, Object> handleKeyLike(Map<String, Object> map, Pair<String, String>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, String> immutablePair : pair) {
            String right = immutablePair.getRight();
            if (StringUtils.isNotBlank(right)) {
                map.put(immutablePair.getLeft(), "%" + right + "%");
            }
        }
        return map;
    }

    public Map<String, Object> handleKeyLike(Pair<String, String>... pair) {
        HashMap<String, Object> map = new HashMap<>(pair.length);
        for (Pair<String, String> immutablePair : pair) {
            String right = immutablePair.getRight();
            if (StringUtils.isNotBlank(right)) {
                map.put(immutablePair.getLeft(), "%" + right + "%");
            }
        }
        return map;
    }


    /**
     * Integer 不为空的判断,为空就保存默认值
     * 非空判断,不为空放进去
     *
     * @param map
     * @param triples 前面是名字,中间是值, 第三个是默认值
     * @return
     */
    public Map<String, Object> handleInt(Map<String, Object> map, Triple<String, Integer, Integer>... triples) {
        if (map == null) {
            return map;
        }
        for (Triple<String, Integer, Integer> triple : triples) {
            Integer middle = triple.getMiddle();
            if (middle != null) {
                map.put(triple.getLeft(), middle);
            } else {
                map.put(triple.getLeft(), triple.getRight());
            }

        }
        return map;
    }


    /**
     * Integer 不为空的判断
     * 非空判断,不为空放进去
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public Map<String, Object> handleInt(Map<String, Object> map, Pair<String, Integer>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, Integer> immutablePair : pair) {
            Integer right = immutablePair.getRight();
            if (right != null) {
                map.put(immutablePair.getLeft(), right);
            }
        }
        return map;
    }


    /**
     * 这个是用于参数的回填用的...
     * Object 不为空的判断
     * 非空判断,不为空放进去
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public ModelMap handleObj(ModelMap map, Pair<String, Object>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, Object> immutablePair : pair) {
            Object right = immutablePair.getRight();
            if (right != null) {
                map.put(immutablePair.getLeft(), right);
            }
        }
        return map;
    }


    /**
     * Long 不为空的判断
     * 非空判断,不为空放进去
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public Map<String, Object> handleLong(Map<String, Object> map, Pair<String, Long>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, Long> immutablePair : pair) {
            Long right = immutablePair.getRight();
            if (right != null) {
                map.put(immutablePair.getLeft(), right);
            }
        }
        return map;
    }


    /**
     * String 不为空的判断
     * 非空判断,不为空放进去
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public Map<String, Object> handleString(Map<String, Object> map, Pair<String, String>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, String> immutablePair : pair) {
            String right = immutablePair.getRight();
            if (StringUtils.isNotBlank(right)) {
                map.put(immutablePair.getLeft(), right);
            }
        }
        return map;
    }


    /**
     * String 不为空的判断 ,同时把String 变成格式为yyyy-MM-dd的格式
     * 非空判断,不为空放进去
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public Map<String, Object> handleDate(Map<String, Object> map, Pair<String, String>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, String> stringPair : pair) {
            String right = stringPair.getRight();
            if (StringUtils.isNotBlank(right)) {
                map.put(stringPair.getLeft(), LocalDate.parse(right));
            }
        }
        return map;
    }


    /**
     * String 不为空的判断 ,同时把String 变成格式为yyyy-MM-dd HH:mm:ss的格式
     * 非空判断,不为空放进去
     *
     * @param map
     * @param pair 前面是名字,后面是值
     * @return
     */
    public Map<String, Object> handleDateTime(Map<String, Object> map, Pair<String, String>... pair) {
        if (map == null) {
            return map;
        }
        for (Pair<String, String> stringPair : pair) {
            String right = stringPair.getRight();
            if (StringUtils.isNotBlank(right)) {
                map.put(stringPair.getLeft(), LocalDateTime.parse(right));
            }
        }
        return map;
    }


}
