package com.xisuo.sellmanager.config;

import com.xisuo.sellmanager.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 这里不要直接new LoginInterceptor 不然  拦截器中的service会为null
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/login","/static/**", "/error");
        //为了开发的方便....
        //registry.addInterceptor(loginInterceptor).excludePathPatterns("/**");
    }

}
