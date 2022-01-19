/*
 * @(#) WebMvcConfig.java 2018/10/11
 *
 * Copyright 2018 CEC(Fujian) Healthcare Big Data Operation Service Co., Ltd. All rights reserved.
 */
package com.fu.config;

import com.fu.handler.MdcInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    MdcInterceptor mdcInterceptor;

    /**
     * 日志添加traceid
     *
     * @param interceptorRegistry
     * @return
     * @throws
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(mdcInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-ui.html", "/swagger**", "/swagger-resources/**");

    }

    /**
     * 跨域支持
     *
     * @param corsRegistry
     * @return
     * @throws
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .maxAge(3600L * 24);
    }
}
