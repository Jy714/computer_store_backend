package com.Jy714.computerStore.config;

import com.Jy714.computerStore.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 处理器拦截器的注册 */
@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /** 配置拦截器 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 完成拦截器的注册
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // 表示拦截所有url
                .excludePathPatterns("/*/login","/*/reg");
    }
}
