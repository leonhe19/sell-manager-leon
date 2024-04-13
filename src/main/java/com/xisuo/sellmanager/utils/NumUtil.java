package com.xisuo.sellmanager.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


/**
 * Description:  数字工具类
 *
 * @author zk
 * @date 2018年3月23日
 */
public class NumUtil {

    private NumUtil() {
    }

    /**
     * Title: trimNumber
     * Description: 数字的格式转换,把 3.0 变成 3返回
     *
     * @date 2018年3月23日
     * @author zk
     */
    public static String num2String(Object num) {
        String s = String.valueOf(num);
        int indexOf = s.indexOf(".");
        if (indexOf != -1) {
            return s.substring(0, indexOf);
        } else {
            return s;
        }
    }

    /**
     * Object 类型的数字变成 int
     */
    public static int num2Int(Object num) {
        if(num==null){
            throw new IllegalArgumentException("null不能转化为数字");
        }
        String value = String.valueOf(num);
        boolean numeric = StringUtils.isNumeric(value);
        if(numeric){
            return NumberUtils.toInt(value);
        }else {
            throw new IllegalArgumentException("null不能转化为数字");
        }
    }







}
