package com.xisuo.sellmanager.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 */
public class MD5Util {


    /**
     * 获取MD5的加密
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 盐
     */
    public static final String SALT = "1a2b3c4d";

    /**
     * 从from 表单到 加密的中间态
     */
    public static String input2Mid(String inputPass) {
        String str = "" + SALT.charAt(0) + SALT.charAt(2) + inputPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    /**
     * 加密的中间态 到最终的密文
     */
    public static String mid2Db(String formPass) {
        String str = "" + SALT.charAt(0) + SALT.charAt(2) + formPass + SALT.charAt(5) + SALT.charAt(4);
        return md5(str);
    }

    /**
     * 从原始字符串到最终的密文
     */
    public static String input2Db(String inputPass) {
        String formPass = input2Mid(inputPass);
        String dbPass = mid2Db(formPass);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println(input2Mid("123456"));
        System.out.println(mid2Db(input2Mid("123456")));
        System.out.println(input2Db("123456"));
    }

}
