package com.xisuo.sellmanager.config.result;

/**
 * 只要get不要set,进行更好的封装
 */
public class CodeMsg {


    private int code;
    private String msg;

    /**
     * 通用的异常
     */
    public static CodeMsg SUCCESS=new CodeMsg(0,"success");


    /**
     * 登录用户名或者密码错误
     */
    public static CodeMsg ERROR_PASSWORD=new CodeMsg(1000,"用户名或者密码错误");
    public static CodeMsg USER_LIMIT=new CodeMsg(1001,"当前用户不允许登录");


    /**
     * 服务器内部异常
     */
    public static CodeMsg SERVER_ERROR=new CodeMsg(5000,"服务端异常");


    /**
     * 参数异常....6000
     */
    public static CodeMsg LACK_PARAMS=new CodeMsg(6000,"缺少参数");
    public static CodeMsg PARAMS_ERROR=new CodeMsg(6001,"参数错误,类型只能是在1-13之间");


    public CodeMsg fillArgs(Object...args){
        int code=this.code;
        String message=String.format(this.msg,args);
        return new CodeMsg(code,message);
    }

    private CodeMsg(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }



}
