package com.xisuo.sellmanager.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author zk
 * @Description: 中文拼音工具类
 * @date 2018-05-21 14:52
 */
public class ChinesePinyinUtil {
    /**
     * 获取汉字的全拼,例如"专科"得到zhuanke返回
     * @param src 中文字符串
     * @return  汉字的全拼
     */
    public static String getPingYin(Object src) {
        if(src==null){
            return "";
        }
        String value = String.valueOf(src);
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder sb = new StringBuilder();
        char[] srcArray = value.toCharArray();
        try {
            for (int i = 0; i < srcArray.length; i++) {
                // 判断是否为汉字字符
                if (Character.toString(srcArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] targetArray = PinyinHelper.toHanyuPinyinStringArray(srcArray[i], format);
                    sb.append(targetArray[0]);
                } else {
                    sb.append(Character.toString(srcArray[i]));
                }
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 得到中文首字母,例如"专科"得到zk返回
     * @param str 中文字符串
     * @return 返回中文汉字的首字母
     */
    public static String getPinYinHeadChar(Object str) {
        if(str==null){
            return "";
        }
        String value = String.valueOf(str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char word = value.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                sb.append(pinyinArray[0].charAt(0));
            } else {
                sb.append(word);
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串转移为ASCII码
     * @param cnStr  中文字符串
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuilder sb = new StringBuilder();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            sb.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return sb.toString();
    }


    /**
     * 测试方法
     */
    public static void main(String[] args) {
        String cnStr = "重庆,重视昭君发展(专科)环-境喵邈";
        System.out.println(getPingYin(cnStr));
        System.out.println(getPinYinHeadChar(cnStr));
        System.out.println(getCnASCII("专科"));
    }


}
