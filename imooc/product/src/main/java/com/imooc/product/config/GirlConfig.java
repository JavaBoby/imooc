package com.imooc.product.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ConfigurationProperties("girl")
@Component
@Data
@RefreshScope
public class GirlConfig {

    private String name;

    private Integer age;
}
