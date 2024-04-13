package com.xisuo.sellmanager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zk
 * @Description: 异常处理
 * @date 2021-07-14 13:58
 */
@ControllerAdvice
public class ExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 异常的处理
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView handleException(Exception e){
        ModelAndView m = new ModelAndView();
        logger.error("服务器发生了异常,原因是:{}",e.toString());
        m.addObject("error", e.getCause());
        e.printStackTrace();
        m.setViewName("error/500");
        return m;
    }

    /**
     * 不支持的方法
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView methodSupport(Exception e){
        ModelAndView m = new ModelAndView();
        logger.error("不正确的访问方法,原因是:{}",e.getCause());
        m.addObject("error", "不正确的访问方法");
        e.printStackTrace();
        m.setViewName("error/404");
        return m;
    }

}
