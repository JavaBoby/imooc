package com.imooc.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: CorsFilterConfig
 * @Description: 跨域配置
 * @date 2018/8/15 11:12
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilterConfig(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);//设置是否支持cookie跨越
        config.setAllowedMethods(Arrays.asList("*"));//设置请求方式,有POST和GET,*代表所有
        config.setAllowedOrigins(Arrays.asList("*"));//设置原始域,*代表所有
        config.setMaxAge(600L);//设置过期时间,在这个时间内相同的跨域请求不会再进行检查
        config.setAllowedHeaders(Arrays.asList("*"));//设置允许的头,*代表所有
        source.registerCorsConfiguration("/**",config);//将配置注册到source上,/**代表所有路径
        return new CorsFilter(source);
    }
}
