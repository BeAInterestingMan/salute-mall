package com.salute.mall.search.config;

import com.salute.mall.search.properties.EsProperties;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 *  @Description es配置
 *  @author liuhu
 *  @Date 2022/11/24 9:34
 */
@Configuration
public class EsConfig {

    @Autowired
    private EsProperties esProperties;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        HttpHost[] hosts = Arrays.stream(esProperties.getHosts().split(",")).map(HttpHost::create).toArray(HttpHost[]::new);
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(esProperties.getUsername(), esProperties.getPassword()));
        RestClientBuilder builder = RestClient.builder(hosts).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);
    }
}
