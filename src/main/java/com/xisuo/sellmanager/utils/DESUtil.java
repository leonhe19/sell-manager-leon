package com.xisuo.sellmanager.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES加密算法
 * 这个加密算法是对称的加密算法
 * 这个加密算法，在本系统中的应用，主要是因为该加密算法加密后
 * 密文都是字符串和数字的，没有其他字符。这样的密文可以应用在url地址上，
 * 不用担心被URL 地址上传递的数字被转义
 */

public class DESUtil {

    private static Logger log = LoggerFactory.getLogger(DESUtil.class);

    /**
     * 加密算法,可用 DES,DESede,Blowfish.
     */
    private final static String ALGORITHM = "DES";

    private final static String SALT = "shanghaixisuodianqi";

    /**
     * DES解密算法
     *
     * @param data 加密后的字符串
     *             SALT 密钥 要是偶数
     */
    public static String decrypt(String data) {
        if(StringUtils.isBlank(data)){
            return null;
        }
        try {
            return new String(decrypt(hex2byte(data.getBytes()),
                    SALT.getBytes()));
        } catch (Exception e) {
            log.error("DES解密算法出现错误,解密的字符串是:{}", data);
        }
        return null;
    }

    /**
     * DES加密算法
     *
     * @param data 数据
     *             SALT  秘钥
     */
    public final static String encrypt(Object data) {
        String src = String.valueOf(data);
        try {
            return byte2hex(encrypt(src.getBytes(), SALT.getBytes()));
        } catch (Exception e) {
            log.error("DES加密算法出现错误,加密的字符串是:{}", data);
        }
        return null;
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(data);
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) {
        String data = "1";
        try {
            String t1 = DESUtil.encrypt(1);
            String t2 = DESUtil.decrypt(t1);
            System.out.println(t1);
            System.out.println(t2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}