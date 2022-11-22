package com.salute.mall.common.doc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  @Description mall swagger配置
 *  @author liuhu
 *  @Date 2022/5/13 22:45
 */
@ConfigurationProperties("mall.swagger")
@Data
public class MallKnife4jProperties {

    private String enable;

    private String basePackage;

    private String title;

    private String description;

    private String contact;

    private String termsOfServiceUrl;

    private String version= "1.0";
}
