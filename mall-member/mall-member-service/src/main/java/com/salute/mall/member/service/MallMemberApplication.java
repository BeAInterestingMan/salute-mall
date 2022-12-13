package com.salute.mall.member.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.salute.mall.member.service.mapper")
public class MallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallMemberApplication.class,args);
    }
}
