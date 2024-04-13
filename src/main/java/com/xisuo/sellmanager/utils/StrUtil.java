package com.xisuo.sellmanager.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Title: StrUtil
 * Description:  字符串工具类
 *
 * @author zk
 * @date 2018年3月26日
 */
public class StrUtil {
    private StrUtil() {
    }

    /**
     * Title: getPropertys
     * Description: 进行字符串的切割  1$2$3 ,然后返回一个list,1 2 3
     *
     * @date 2018年3月23日
     * @author zk
     */
    public static List<String> getPropertys(String tmp) {
        String[] split = tmp.trim().split("\\$");
        List<String> list = new ArrayList<String>();
        for (String string : split) {
            list.add(string);
        }
        return list;
    }


    /**
     * @param fileName 文件名
     * @return java.lang.String
     * @Description: 返回当前日期格式的文件名
     * @author zk
     * @date 2018-03-30 11:01
     */
    public static String renameFileName(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("重命名的文件名不能为空");
        }
        // 当前时间字符串
        String formatDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        int random = new Random().nextInt(10000);
        // 文件后缀
        String extension = fileName.substring(fileName.lastIndexOf("."));
        StringBuilder sb = new StringBuilder(formatDate);
        sb.append(random);
        sb.append(extension);
        return sb.toString();
    }



    /**
     * @param s 字符串
     * @return java.util.List
     * @Description: 把使用中文逗号, 作为分隔符的字符串进行分割
     * @author zk
     * @date 2018-04-09 12:25
     */
    public static List string2List(String s) {
        return string2List(s, "，");
    }


    /**
     * @param s 字符串, spiltSignal 分隔符
     * @return java.util.List
     * @Description: 把字符串进行切割, 返回list
     * @author zk
     * @date 2018-04-09 12:30
     */
    public static List<String> string2List(String s, String spiltSignal) {
        List<String> strings = new ArrayList<>();
        String[] split = s.split(spiltSignal);
        for (String s1 : split) {
            strings.add(s1);
        }
        return strings;
    }


    /**
     * 监测一个对象是否是空的字符串.主要是为了判断字符串"null"
     * 空返回false.   不为空返回true
     */
    public static boolean checkStr(Object s) {
        if (s == null) {
            return false;
        }
        String value = String.valueOf(s).trim();
        if ("null".equals(value)) {
            return false;
        }
        return StringUtils.isNotBlank(value);
    }


    /**
     * List  set  变成一个字符串
     *
     * @param collection
     * @return
     */
    public static String collection2String(Collection collection) {
        StringBuilder sb = new StringBuilder("");
        if (collection == null || collection.size() < 1) {
            return sb.toString();
        }
        for (Object o : collection) {
            sb.append(String.valueOf(o));
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }


    /**
     * 判断一个字符串是否含有中文
     */
    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (isChinese(c)) {
                // 有一个中文字符就返回
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个字符是否是中文
     */
    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }



    /**
     * 把 user_id  这样的换成  userId
     * user_id_pro 变成 userIdPro
     */
    public static String str2UpCase(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        int index = str.indexOf("_");
        if (index == -1) {
            return str;
        }
        String[] strings = str.split("_");
        StringBuilder sb = new StringBuilder(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            String value = strings[i];
            if (StringUtils.isNotBlank(value)) {
                sb.append(Character.toString(value.charAt(0)).toUpperCase());
                sb.append(value.substring(1, value.length()));
            }
        }
        return sb.toString();
    }


    /**
     * 把从数据库查出来的key进行转换
     */
    public static Map<String, Object> mapKey2UpCase(Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return map;
        }
        Map<String, Object> hashMap = new HashMap<>(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = str2UpCase(entry.getKey());
            hashMap.put(key, entry.getValue());
        }
        return hashMap;
    }


}
