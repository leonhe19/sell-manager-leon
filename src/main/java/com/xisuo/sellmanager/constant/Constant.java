package com.xisuo.sellmanager.constant;

/**
 * @author zk
 * @Description: 常量
 */
public class Constant {

    /**
     * 订单的状态
     * 未支付
     */
    public final static int ORDER_NOT_PAY = 0;
    /**
     * 支付部分
     */
    public final static int ORDER_PAY_SOME = 1;
    /**
     * 支付完成
     */
    public final static int ORDER_PAY_ALL = 2;


    /**
     * 发货的状态
     * 未发货
     */
    public final static int NOT_SHIP = 0;
    /**
     * 发货部分
     */
    public final static int SHIP_SOME = 1;
    /**
     * 发货完成
     */
    public final static int SHIP_ALL = 2;


    /**
     * 用户标志
     * 普通用户
     */
    public final static int USER_NORMAL = 0;
    /**
     * 管理员
     */
    public final static int USER_SUPER = 1;


    /**
     * cookie的名字
     */
    public final static String COOKIE_NAME = "xisuo";
    /**
     * cookie的时间  15天
     */
    public final static int COOKIE_TIME = 60 * 60 * 24 * 15;

    /**
     * user 对象的缓存key前缀
     */
    public final static String USER_CACHE_PREFIX = "user:";


    /**
     * 默认密码
     */
    public final static String DEFAULT_PASSWORD = "123456";

    /**
     * 默认显示第一页
     */
    public final static int PAGE = 1;
    /**
     * 默认一页显示10个
     */
    public final static int PAGE_SIZE = 15;

    /**
     * 用户上传的所有图片的存放总文件夹,用户图像文件夹
     */
    public final static String USER_IMG_DIR = "/static/user/";
    /**
     * 产品的图片文件夹
     */
    public final static String PRODUCT_IMG_DIR = "/static/product/";


    /**
     * 默认显示的钱数
     */
    public final static String DEFAULT_MONEY = "0.0";


}
