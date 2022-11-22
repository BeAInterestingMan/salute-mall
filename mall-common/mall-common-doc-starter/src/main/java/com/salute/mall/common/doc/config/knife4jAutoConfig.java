package com.salute.mall.common.doc.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.salute.mall.common.doc.properties.MallKnife4jProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 *  @Description EnableKnife4j 配置
 *  @author liuhu
 *  @Date 2022/11/22 20:56
 */
@ConditionalOnProperty(name = "mall.swagger.enable",havingValue = "true",matchIfMissing = true)
@Configuration
@EnableSwagger2WebMvc
@EnableConfigurationProperties(MallKnife4jProperties.class)
public class knife4jAutoConfig {

    @Autowired
    private  MallKnife4jProperties mallKnife4jProperties;

    @Bean
    public Docket createRestApi() {
        return  new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(mallKnife4jProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(mallKnife4jProperties.getTitle())
                .description(mallKnife4jProperties.getDescription())
                .contact(mallKnife4jProperties.getContact())
                .termsOfServiceUrl(mallKnife4jProperties.getTermsOfServiceUrl())
                .version(mallKnife4jProperties.getVersion())
                .build();
    }
}
