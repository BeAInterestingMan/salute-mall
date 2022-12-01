package com.salute.mall.ploy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.salute.mall.product.api.client")
@MapperScan("com.salute.mall.ploy.mapper")
public class MallPloyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPloyApplication.class,args);
    }
}
