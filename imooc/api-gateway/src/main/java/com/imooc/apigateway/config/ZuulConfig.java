package com.imooc.apigateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;

import java.beans.ConstructorProperties;

/**
 * @author 刘海洋
 * @version V1.0
 * @Title: ZuulConfig
 * @Description: zuul配置类
 * @date 2018/7/27 18:31
 */
@Component
public class ZuulConfig {

    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }
}
