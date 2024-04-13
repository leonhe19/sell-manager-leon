package com.xisuo.sellmanager.utils;

import java.math.BigDecimal;

/**
 * add  by 张科,用于高精度的计算.
 */
public class ArithUtil {


    /**
     * 功能：BigDecimal类型的加法运算。
     *
     * @param s1 被加数
     * @param s2 加数
     * @return 两个参数的和
     */
    public static double add(double s1, double s2) {
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.add(b2).doubleValue();
    }

    public static double add(double s1, double s2,double s3) {
        double add = add(s1, s2);
        return add(add,s3);
    }

    /**
     * 功能：BigDecimal类型的减法运算。
     *
     * @param s1 被减数
     * @param s2 减数
     * @return 两个参数的差
     */
    public static double sub(double s1, double s2) {
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 功能：BigDecimal类型的乘法运算。
     *
     * @param s1 被乘数
     * @param s2 乘数
     * @return 两个参数的积
     */
    public static double mul(double s1, double s2) {
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.multiply(b2).doubleValue();
    }



    /**
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param s1    被除数
     * @param s2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double s1, double s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字向上取。
     *
     * @param s1    被除数
     * @param s2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divUp(double s1, double s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.divide(b2, scale, BigDecimal.ROUND_UP).doubleValue();
    }


    /**
     * 向下取值
     * 功能：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param s1    被除数
     * @param s2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divDown(double s1, double s2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(s1));
        BigDecimal b2 = new BigDecimal(Double.toString(s2));
        return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
    }

    /**
     * 功能：提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("精确度不能小于0！");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


}
