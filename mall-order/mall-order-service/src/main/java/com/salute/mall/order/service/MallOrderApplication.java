package com.salute.mall.order.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.salute.mall.order.service.mapper")
@EnableFeignClients(basePackages = {"com.salute.mall.product.api.client","com.salute.mall.marketing.api.client"})
public class MallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class,args);
    }
}
