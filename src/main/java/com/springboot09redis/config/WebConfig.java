package com.springboot09redis.config;

import com.springboot09redis.interceptor.RedisUrlCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /* Filter 、Interceptor 几乎拥有相同的功能
          1. Filter是Servlet定义的原生组件,好处:脱离了Spring应用也能使用
          2.Interceptor是Spring定义的接口,可以使用Spring的自动装配等功能*/
    @Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;

    /*将拦截器注册到容器中,再指定拦截规则*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(redisUrlCountInterceptor)
                .addPathPatterns("/**");  //所有请求都被拦截包括静态资源
    }
}
