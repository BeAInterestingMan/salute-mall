package com.salute.mall.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "mall.security")
@Configuration
public class MallSecurityProperties implements Serializable {

    private String whiteUrlList;

}
