package com.imooc.product.controller;

import com.imooc.product.config.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "dev")
public class EnvController {

    @Value("${env}")
    private String env;

    @Autowired
    private GirlConfig girlConfig;

    @RequestMapping(value = "getEnv")
    public String getEnv(){
        return env;
    }

    @GetMapping(value = "getGril")
    public String getGril(){
        return "name:" + girlConfig.getName() + " age:" + girlConfig.getAge();
    }
}
