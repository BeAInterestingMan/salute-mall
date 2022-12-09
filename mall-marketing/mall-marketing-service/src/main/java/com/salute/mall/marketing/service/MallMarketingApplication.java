package com.salute.mall.marketing.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.salute.mall.marketing.xx.mapper")
public class MallMarketingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallMarketingApplication.class,args);
    }
}
