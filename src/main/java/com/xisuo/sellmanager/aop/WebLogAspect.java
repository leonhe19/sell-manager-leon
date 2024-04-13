package com.xisuo.sellmanager.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面日志的处理
 * zk
 */

@Aspect
@Component
@Order(1)
public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * 定义切入点，切入点为com.example.aop下的所有函数
     */
    @Pointcut("execution(public * com.xisuo.sellmanager.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 前置通知：在连接点之前执行的通知
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求内容
        logger.info("请求内容开始 请求地址:{} 请求方式:{}", request.getRequestURL().toString(), request.getMethod());
        logger.info("请求类方法: {} 请求类方法参数: {} ", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }


    @After("webLog()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
    }


    @AfterReturning(returning = "obj", pointcut = "webLog()")
    public void doAfterReturning(Object obj) throws Throwable {
    }

}