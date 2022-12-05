package com.salute.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.salute.mall.product.service.mapper")
@EnableFeignClients(basePackages = {"com.salute.mall.search.api.client"})
public class MallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class,args);
    }
}
