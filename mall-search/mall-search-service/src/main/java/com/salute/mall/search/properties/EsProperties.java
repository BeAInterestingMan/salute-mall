package com.salute.mall.search.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "es.config")
public class EsProperties {
    private String hosts;
    private String username;
    private String password;
}
