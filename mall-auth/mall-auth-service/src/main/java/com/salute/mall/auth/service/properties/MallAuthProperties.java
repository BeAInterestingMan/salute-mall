package com.salute.mall.auth.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@ConfigurationProperties(prefix = "mall.auth")
@Configuration
@Data
public class MallAuthProperties implements Serializable {

    /**用户信息缓存时间*/
    private Long userInfoCacheTime=1000*60*60*24L;

    /**用户权限缓存时间*/
    private Long userPermissionInfoCacheTime=1000*60*60*24L;
}
