package com.xisuo.sellmanager.config;

import com.xisuo.sellmanager.interceptor.ResourceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态文件的过滤器的配置
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResourceInterceptor()).excludePathPatterns("/static/**");
    }

    @Override
    /**
     * 需要告知系统，这是要被当成静态文件的！
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置文件上传的文件不拦截TaleUtils.getUplodFilePath()
        //registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ "/" +"upload/");
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}