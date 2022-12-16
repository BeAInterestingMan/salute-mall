package com.salute.mall.common.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "mall.security")
public class MallSecurityProperties implements Serializable {

    private String whiteUrlList;
}
