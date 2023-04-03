package com.hayashisama.managerback.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @program: managerback
 * @ClassName CorsConfig
 * @Description: 后端跨域配置类
 * @Author: HayashiSama
 * @Create: 2023-03-16 14:51
 * @Version 1.0
 **/
@Configuration
public class CorsConfig {

    // 当前跨域请求最大有效时长。这里默认是一天
    private static final long MAX_AGE = 24 * 60 * 60;
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 设置访问源请求方法
        corsConfiguration.setMaxAge(MAX_AGE);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
