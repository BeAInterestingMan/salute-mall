package com.salute.mall.common.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mall.redisson")
public class RedissonProperties {

    private String address;
}
