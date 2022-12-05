package com.salute.mall.mq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"com.salute.mall.search.api.client","com.salute.mall.product.api.client"})
public class MallMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallMqApplication.class,args);
    }
}
